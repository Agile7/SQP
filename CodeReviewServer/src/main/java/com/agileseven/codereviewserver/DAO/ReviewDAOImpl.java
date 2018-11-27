/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereviewserver.DAO;

import com.agileseven.codereviewserver.DTO.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class implements the methods of ReviewDAO interface
 * 
 * @author vilosh_na
 * @version 1.0
 * @date created : 13.10.2018
 */
public class ReviewDAOImpl implements ReviewDAO {

    Connection connection = ConnectionFactory.getConnection();
   
    public int approveCode(int code_id, int approved) {
        Connection con = ConnectionFactory.getConnection();
        
        String query = "UPDATE review SET "
                    + "approved = " + approved + " "
                    + "WHERE review.code_id = " + code_id;
        
        
        try { 
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
          
            con.close();
        } catch (SQLException ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
            return -1;
        }
        return 1;
        
    }

    @Override
    public int addReview(ReviewDTO review) {
        
        Connection con = ConnectionFactory.getConnection();
        int review_id = -1;
        
        String query = "INSERT INTO review (code_id, reviewer_id, approved, start_time, submit_time) "
                    + " VALUES(?,?,?,STR_TO_DATE(?,'%Y-%m-%d %T'),STR_TO_DATE(?,'%Y-%m-%d %T'))";
        
        try { 
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, review.getCodeId());
            ps.setInt(2, review.getReviewerId());
            // approved by default for first sprint
            ps.setInt(3, review.getApproved());
            
                   
            ps.setString(4, review.getStartTime());
            ps.setString(5, review.getSubmitTime());
            
            System.out.println(ps.toString());
          
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                review_id = rs.getInt(1);
            }
            CodeDAO codeDAO = new CodeDAOImpl();
            codeDAO.changeStatusOfCode(review.getCodeId(),2);

            for (ReviewAnnotationDTO annotation: review.getAnnotationList()) {
                String annQuery = "INSERT INTO review_annotation (annotation_text, rule_id, review_id, line_number) "
                        + " VALUES(?,?,?,?)";
                PreparedStatement annPS = con.prepareStatement(annQuery, Statement.RETURN_GENERATED_KEYS);
                annPS.setString(1, annotation.getAnnotText());
                annPS.setString(2, annotation.getRuleDTO().getRuleid());
                annPS.setInt(3, review_id);
                annPS.setInt(4, annotation.getLineNumber());
                annPS.executeUpdate();
            }

            // Updating the reviewer's score
            int reviewerId = review.getReviewerId();
            int reviewerScore = 5;
            reviewerScore += review.getAnnotationList().size();

            String reviewerScoreQuery = "UPDATE user SET user_xp = user_xp + ? WHERE user_id = ?";
            PreparedStatement statementReviewer = con.prepareStatement(reviewerScoreQuery);
            statementReviewer.setInt(1, reviewerScore);
            statementReviewer.setInt(2, reviewerId);
            statementReviewer.executeUpdate();

            // Updating the reviewer's gold (if needed)
            String reviewerNewScoreQuery = "SELECT user_xp FROM user WHERE user_id = ?";
            PreparedStatement statementReviewerNewScoreQuery = con.prepareStatement(reviewerNewScoreQuery);
            statementReviewerNewScoreQuery.setInt(1, reviewerId);

            ResultSet rsReviewerNewScore = statementReviewerNewScoreQuery.executeQuery();
            rsReviewerNewScore.next();
            int score = rsReviewerNewScore.getInt(1);

            increaseUserGoldByOne(con, reviewerId, score);

            // If the code was approved, increase the pusher's score by 10
            if (review.getApproved() == 1) {

                String sqlGetPusherId = "SELECT user_id, version, number_of_lines from code WHERE code_id = ?";
                PreparedStatement statement = con.prepareStatement(sqlGetPusherId);
                statement.setInt(1, review.getCodeId());

                statement.executeQuery();
                ResultSet resultSet = statement.executeQuery();
                resultSet.next();

                int pusherId = resultSet.getInt(1);
                int version = resultSet.getInt(2);
                int numberOfLines = resultSet.getInt(3);
                
                int xp = 0;
                if (version == 1){
                    xp += Math.ceil(numberOfLines / 10.0);
                } else if (version == 2){
                    xp += Math.ceil(numberOfLines / 20.0);
                } else {
                    xp += (Math.ceil(numberOfLines / 20.0) - (version - 2));
                }
                
                if (xp < 0){
                    xp = 0;
                }
                
                String pusherScoreQuery = "UPDATE user SET user_xp = user_xp + " + xp + " WHERE user_id = ?";
                PreparedStatement statementPusher = con.prepareStatement(pusherScoreQuery);
                statementPusher.setInt(1, pusherId);
                statementPusher.executeUpdate();

                

                // Updating the pusher's gold (if needed)
                String pusherNewScoreQuery = "SELECT user_xp FROM user WHERE user_id = ?";
                PreparedStatement statementPusherNewScoreQuery = con.prepareStatement(pusherNewScoreQuery);
                statementPusherNewScoreQuery.setInt(1, reviewerId);

                ResultSet rsPusherNewScore = statementPusherNewScoreQuery.executeQuery();
                rsPusherNewScore.next();
                int pusherScore = rsPusherNewScore.getInt(1);

                increaseUserGoldByOne(con, pusherId, pusherScore);

            }
            con.close();
        } catch (SQLException ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
            return -1;
        } 

        return review_id;
    }

    private void increaseUserGoldByOne(Connection con, int pusherId, int scorePusher) throws SQLException {
        if (scorePusher % 50 == 0){
            String pusherBonusGoldQuery = "UPDATE user SET user_gold = user_gold + 1 WHERE user_id = ?";
            PreparedStatement statementPusherBonusGoldQuery = con.prepareStatement(pusherBonusGoldQuery);
            statementPusherBonusGoldQuery.setInt(1, pusherId);
            statementPusherBonusGoldQuery.executeUpdate();
        }
    }

    @Override
    public ArrayList<ReviewDTO> getReviewedCodesByUser(int userId, int projectId) {
        
        ArrayList<ReviewDTO> reviewList = new ArrayList<ReviewDTO>();
        
        Connection con = ConnectionFactory.getConnection();
        String query = "SELECT c.code_id,c.code_text,c.user_story_id," +
                        "us.title, r.review_id, r.reviewer_id, u.first_name, u.last_name,"+
                        "r.approved, STR_TO_DATE(r.submit_time,'%Y-%m-%d %T') as submit_time "+
                        "FROM code c, user u, user_story us, review r " +
                        "where r.reviewer_id = u.user_id " +
                        " AND c.user_story_id = us.user_story_id " +
                        " AND r.code_id = c.code_id " +
                        " AND us.project_id = ? " +
                        " AND c.user_id = ? " +
                        "order by r.submit_time desc ";
        
           try {
                PreparedStatement ps = con.prepareStatement(query);
                System.out.println(ps.toString());
                ps.setInt(1, projectId);
                ps.setInt(2, userId);
                System.out.println(ps.toString());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    reviewList.add(buildReviewDTOfromResult(rs));
                }
                ps.close();
                rs.close();
                con.close();
            }catch (SQLException ex) {
                
            }
        
        return reviewList;
      
    }

    @Override
    public ArrayList<RuleDTO> getRulesList() {
        ArrayList<RuleDTO> ruleList = new ArrayList<RuleDTO>();

        Connection con = ConnectionFactory.getConnection();
        String query = "SELECT * FROM rule; ";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ruleList.add(new RuleDTO(rs.getString(1), rs.getString(2)));
            }
            ps.close();
            rs.close();
            con.close();
        }catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ruleList;
    }

    public ReviewDTO buildReviewDTOfromResult(ResultSet rs){
        
        ReviewDTO review = new ReviewDTO();
        
        try{
            
            review.setReviewId(rs.getInt("review_id"));
            review.setCodeId(rs.getInt("code_id"));
            review.setReviewerId(rs.getInt("reviewer_id"));
            review.setApproved(rs.getInt("approved"));
            review.setSubmitTime(rs.getString("submit_time"));
            review.setSubmitDate(rs.getDate("submit_time"));
            
            CodeDTO code = new CodeDTO();
            code.setCodeId(rs.getInt("code_id"));
            code.setCodeText(rs.getString("code_text"));
            code.setUserStoryId(rs.getString("user_story_id"));
            
            UserstoryDTO userStory = new UserstoryDTO();
            userStory.setUserstoryId(rs.getString("user_story_id"));
            userStory.setTitle(rs.getString("title"));
            code.setUserStory(userStory);
            
            review.setCode(code);
            
            UserDTO user = new UserDTO();
            user.setUserId(rs.getInt("reviewer_id"));
            user.setFirstName(rs.getString("first_name"));            
            user.setLastName(rs.getString("last_name"));
            review.setReviewer(user);
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ReviewDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return review;
        
    }

    @Override
    public List<ReviewDTO> getReviewsOfUser(int userID) throws SQLException {
        return null;
    }

    @Override
    public List<ReviewDTO> getReviewsOfProject(int projectId, Date start, Date end) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();

        String sql = "SELECT * FROM `review` WHERE reviewer_id IN (SELECT user_id FROM user WHERE project_id = ?)" +
                "AND start_time >= ? AND submit_time <= ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, projectId);
        statement.setDate(2, start);
        statement.setDate(3, end);
        ResultSet resultSet = statement.executeQuery();
        List<ReviewDTO> reviews = new ArrayList<>();

        while (resultSet.next()){
            ReviewDTO review = new ReviewDTO();
            review.setReviewId(resultSet.getInt(1));
            review.setCodeId(2);
            review.setReviewerId(3);
            review.setApproved(4);
            review.setStartTime(resultSet.getString(5));
            review.setSubmitTime(resultSet.getString(6));
            reviews.add(review);
        }
        return reviews;
    }

    @Override
    public List<ReviewDTO> getReviewsOfUserCodes(int devId) {
        try {
            CodeDAO codeDAO = new CodeDAOImpl();

            List<ReviewDTO> reviews = new ArrayList<>();
            String sql = "SELECT * FROM review WHERE approved = 1 AND code_id IN (SELECT code_id FROM code WHERE user_id = ?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, devId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                ReviewDTO reviewDTO = createReviewDTO(resultSet);
//                reviewDTO.setCode(codeDAO.getCodeById(reviewDTO.getCodeId()));

                reviewDTO.setSubmitTime(reviewDTO.getSubmitDate().toString());

                reviews.add(reviewDTO);
            }
            return reviews;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private ReviewDTO createReviewDTO(ResultSet set) throws SQLException {
        ReviewDTO review = new ReviewDTO();
        review.setReviewId(set.getInt(1));
        review.setCodeId(2);
        review.setReviewerId(3);
        review.setApproved(4);
        review.setStartTime(set.getString(5));
        review.setSubmitTime(set.getString(6));
        review.setSubmitDate(set.getDate(6));
        return review;
    }

    @Override
    public ArrayList<ReviewDTO> getReviewedCodesByUserBetweenDates(int userId, int projectId, String startDate, String endDate) {
        ArrayList<ReviewDTO> reviewList = new ArrayList<ReviewDTO>();

        Connection con = ConnectionFactory.getConnection();
        String query = "SELECT c.code_id,c.code_text,c.user_story_id," +
                "us.title, r.review_id, r.reviewer_id, u.first_name, u.last_name,"+
                "r.approved, r.submit_time "+
                "FROM code c, user u, user_story us, review r " +
                "where r.reviewer_id = u.user_id " +
                " AND c.user_story_id = us.user_story_id " +
                " AND r.code_id = c.code_id " +
                " AND us.project_id = ? " +
                " AND c.user_id = ? " +
                "AND r.submit_time BETWEEN STR_TO_DATE(?,'%d-%M-%Y') AND STR_TO_DATE(?,'%d-%M-%Y')";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            System.out.println(ps.toString());
            ps.setInt(1, projectId);
            ps.setInt(2, userId);
            ps.setString(3, startDate);
            ps.setString(4, endDate);
            System.out.println(ps.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                reviewList.add(buildReviewDTOfromResult(rs));
            }
            ps.close();
            rs.close();
            con.close();
        }catch (SQLException ex) {

        }

        return reviewList;
    }
    
}
