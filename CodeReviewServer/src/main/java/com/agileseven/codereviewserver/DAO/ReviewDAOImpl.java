/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereviewserver.DAO;

import com.agileseven.codereviewserver.DTO.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        
        System.out.println("dhbcdh");
        
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
            
            con.close();
        } catch (SQLException ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
            return -1;
        } 
        
        
        return review_id;
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

    
}
