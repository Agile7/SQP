/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereviewserver.DAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.agileseven.codereviewserver.DTO.*;
import com.agileseven.codereviewserver.Utilities.XpCalculator;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import org.json.JSONObject;

/**
 *
 * @author vilosh_na
 */
public class GamificationDAOImpl implements GamificationDAO{

    @Override
    public LinkedHashMap<String, Integer> getNumberOfCodesPushedByTeam(String startDate, String endDate, int period, int projectId) {
        Connection con = ConnectionFactory.getConnection();
        
        String query = "from code c, user u "+
                        "where c.user_id = u.user_id "+
                        "and u.project_id = ? "+
                        "AND c.push_date BETWEEN STR_TO_DATE(?,'%d-%M-%Y') "+
                        "AND STR_TO_DATE(?,'%d-%M-%Y') ";
        
        switch (period) {
            case 2:
                //monthly
                query = "select count(*) AS count_codes, DATE_FORMAT(c.push_date, '%m-%Y') AS date_period " +query;
                query = query + "GROUP BY DATE_FORMAT(c.push_date, '%m-%Y') ";
                break;
            case 1:
                //weekly
                query = "select count(*) AS count_codes, concat(WEEK(c.push_date),'-',YEAR(c.push_date)) AS date_period " +query;
                query = query + "GROUP BY concat(WEEK(c.push_date),'-',YEAR(c.push_date)) ";
                break;
            default:
                //daily
                query = "select count(*) AS count_codes, DATE_FORMAT(c.push_date,'%d-%m-%Y') AS date_period " +query;
                query = query + "GROUP BY DATE_FORMAT(c.push_date,'%d-%m-%Y') ";
                break;
        }
        query = query + " ORDER BY DATE(c.push_date) ASC";
        
        
         LinkedHashMap<String, Integer> map = new LinkedHashMap();
        
           try {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, projectId);
                ps.setString(2, startDate);
                ps.setString(3, endDate);
                ResultSet rs = ps.executeQuery();
                
              System.out.println(ps);
                
                while (rs.next()) {
                    System.out.println(rs.getString("date_period"));
                    map.put(rs.getString("date_period"),rs.getInt("count_codes")) ;
                }
                ps.close();
                rs.close();
                con.close();
            }catch (SQLException ex) {
                
            }
        
        return map;
    }
    
    @Override
    public LinkedHashMap<String, Integer> getSumOfLinesPushedByTeam(String startDate, String endDate, int period, int projectId) {
        Connection con = ConnectionFactory.getConnection();
        
        String query = "from code c, user u "+
                        "where c.user_id = u.user_id "+
                        "and u.project_id = ? "+
                        "AND c.push_date BETWEEN STR_TO_DATE(?,'%d-%M-%Y') "+
                        "AND STR_TO_DATE(?,'%d-%M-%Y') ";
        
        switch (period) {
            case 2:
                //monthly
                query = "select SUM(number_of_lines) AS sum_lines, DATE_FORMAT(c.push_date, '%m-%Y') AS date_period " +query;
                query = query + "GROUP BY DATE_FORMAT(c.push_date, '%m-%Y') ";
                break;
            case 1:
                //weekly
                query = "select SUM(number_of_lines) AS sum_lines, concat(WEEK(c.push_date),'-',YEAR(c.push_date)) AS date_period " +query;
                query = query + "GROUP BY concat(WEEK(c.push_date),'-',YEAR(c.push_date)) ";
                break;
            default:
                //daily
                query = "select SUM(number_of_lines) AS sum_lines, DATE_FORMAT(c.push_date,'%d-%m-%Y') AS date_period " +query;
                query = query + "GROUP BY DATE_FORMAT(c.push_date,'%d-%m-%Y') ";
                break;
        }
        
        query = query + " ORDER BY DATE(c.push_date) ASC";
        
         LinkedHashMap<String, Integer> map = new LinkedHashMap();
        
           try {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, projectId);
                ps.setString(2, startDate);
                ps.setString(3, endDate);
                ResultSet rs = ps.executeQuery();
                System.out.println(ps);
              
                
                while (rs.next()) {
                    map.put(rs.getString("date_period"),rs.getInt("sum_lines")) ;
                }
                ps.close();
                rs.close();
                con.close();
            }catch (SQLException ex) {
                
            }
        
        return map;
    }

    @Override
    public LinkedHashMap<String, Integer> getNumberOfCodesPushedByIndividual(String startDate, String endDate, int period, int userId) {
        Connection con = ConnectionFactory.getConnection();
        
        String query = "from code c "+
                        "where c.user_id = ? "+
                        "AND c.push_date BETWEEN STR_TO_DATE(?,'%d-%M-%Y') "+
                        "AND STR_TO_DATE(?,'%d-%M-%Y') ";
        
        switch (period) {
            case 2:
                //monthly
                query = "select count(*) AS count_codes, DATE_FORMAT(c.push_date, '%m-%Y') AS date_period " +query;
                query = query + "GROUP BY DATE_FORMAT(c.push_date, '%m-%Y') ";
                break;
            case 1:
                //weekly
                query = "select count(*) AS count_codes, concat(WEEK(c.push_date),'-',YEAR(c.push_date)) AS date_period " +query;
                query = query + "GROUP BY concat(WEEK(c.push_date),'-',YEAR(c.push_date)) ";
                break;
            default:
                //daily
                query = "select count(*) AS count_codes, DATE_FORMAT(c.push_date,'%d-%m-%Y') AS date_period " +query;
                query = query + "GROUP BY DATE_FORMAT(c.push_date,'%d-%m-%Y') ";
                break;
        }
        query = query + " ORDER BY DATE(c.push_date) ASC";
        
        
         LinkedHashMap<String, Integer> map = new LinkedHashMap();
        
           try {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, userId);
                ps.setString(2, startDate);
                ps.setString(3, endDate);
                ResultSet rs = ps.executeQuery();
                
              System.out.println(ps);
                
                while (rs.next()) {
                    System.out.println(rs.getString("date_period"));
                    map.put(rs.getString("date_period"),rs.getInt("count_codes")) ;
                }
                ps.close();
                rs.close();
                con.close();
            }catch (SQLException ex) {
                
            }
        
        return map;
    }
    
    @Override
    public LinkedHashMap<String, Integer> getSumOfLinesPushedByIndividual(String startDate, String endDate, int period, int userId) {
        Connection con = ConnectionFactory.getConnection();
        
        String query = "from code c "+
                        "where c.user_id = ? "+
                        "AND c.push_date BETWEEN STR_TO_DATE(?,'%d-%M-%Y') "+
                        "AND STR_TO_DATE(?,'%d-%M-%Y') ";
        
        switch (period) {
            case 2:
                //monthly
                query = "select SUM(number_of_lines) AS sum_lines, DATE_FORMAT(c.push_date, '%m-%Y') AS date_period " +query;
                query = query + "GROUP BY DATE_FORMAT(c.push_date, '%m-%Y') ";
                break;
            case 1:
                //weekly
                query = "select SUM(number_of_lines) AS sum_lines, concat(WEEK(c.push_date),'-',YEAR(c.push_date)) AS date_period " +query;
                query = query + "GROUP BY concat(WEEK(c.push_date),'-',YEAR(c.push_date)) ";
                break;
            default:
                //daily
                query = "select SUM(number_of_lines) AS sum_lines, DATE_FORMAT(c.push_date,'%d-%m-%Y') AS date_period " +query;
                query = query + "GROUP BY DATE_FORMAT(c.push_date,'%d-%m-%Y') ";
                break;
        }
        
        query = query + " ORDER BY DATE(c.push_date) ASC";
        
         LinkedHashMap<String, Integer> map = new LinkedHashMap();
        
           try {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, userId);
                ps.setString(2, startDate);
                ps.setString(3, endDate);
                ResultSet rs = ps.executeQuery();
                System.out.println(ps);
              
                
                while (rs.next()) {
                    map.put(rs.getString("date_period"),rs.getInt("sum_lines")) ;
                }
                ps.close();
                rs.close();
                con.close();
            }catch (SQLException ex) {
                
            }
        
        return map;
    }
    
    @Override
    public Integer getTotalLineReviewed(String startDate, String endDate, int projectId) {
        Connection con = ConnectionFactory.getConnection();
        int totalLineReviewed = 0;
        String query = "SELECT SUM(DISTINCT number_of_lines) " + 
                        "FROM code c, review r, user_story us "+
                        "WHERE us.project_id = ? "+
                        "AND c.user_story_id = us.user_story_id " +
                        "AND c.code_id IN (SELECT DISTINCT code_id FROM review rv WHERE rv.submit_time BETWEEN STR_TO_DATE(?,'%d-%M-%Y') " +
                        "AND STR_TO_DATE(?,'%d-%M-%Y'))";
        
//        String query = "SELECT SUM(DISTINCT number_of_lines)" + 
//                        "FROM code c, review r, user_story us  "+
//                        "WHERE us.project_id = ? "+
//                        "AND c.user_story_id = us.user_story_id " +
//                        "AND c.code_id IN (SELECT DISTINCT code_id FROM review rv )";
        
           try {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, projectId);
                ps.setString(2, startDate);
                ps.setString(3, endDate);
                ResultSet rs = ps.executeQuery();
                System.out.println(ps);
              
                while (rs.next()) {
                    totalLineReviewed = rs.getInt(1);
                }
                ps.close();
                rs.close();
                con.close();
            }catch (SQLException ex) {
                System.err.println(ex);
            }
        
        return totalLineReviewed;
    }
    
    @Override
    public Integer getTotalAnnotation(String startDate, String endDate, int projectId) {
        Connection con = ConnectionFactory.getConnection();
        int totalAnnotation = 0;
        String query = "SELECT COUNT(DISTINCT annotation_id) " + 
                        "FROM code c, review r, review_annotation ra, user_story us "+
                        "WHERE us.project_id = ? "+
                        "AND c.code_id = r.code_id " +
                        "AND c.user_story_id = us.user_story_id " +
                        "AND r.review_id IN (SELECT DISTINCT review_id FROM review rv WHERE rv.submit_time BETWEEN STR_TO_DATE(?,'%d-%M-%Y') " +
                        "AND STR_TO_DATE(?,'%d-%M-%Y'))";
        
//        String query = "SELECT COUNT(DISTINCT annotation_id)" + 
//                        "FROM code c, review r, review_annotation ra, user_story us "+
//                        "WHERE us.project_id = ? "+
//                        "AND c.user_story_id = us.user_story_id " +
//                        "AND c.code_id = r.code_id " +
//                        "AND ra.review_id IN (SELECT DISTINCT review_id FROM review rv)";
                  
           try {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, projectId);
                ps.setString(2, startDate);
                ps.setString(3, endDate);
                ResultSet rs = ps.executeQuery();
                System.out.println(ps);
              
                while (rs.next()) {
                    totalAnnotation = rs.getInt(1);
                }
                ps.close();
                rs.close();
                con.close();
            }catch (SQLException ex) {
                System.err.println(ex);
            }
        
        return totalAnnotation;
    }
    
    @Override
    public LinkedHashMap<String, Integer> getListRuleCount(String startDate, String endDate, int projectId) {
        Connection con = ConnectionFactory.getConnection();
        LinkedHashMap<String, Integer> listData = new LinkedHashMap<String, Integer>();
        String query = "SELECT DISTINCT(rule_id) as ruleID, COUNT(*) as nbOfRule " + 
                        "FROM code c, review r, review_annotation ra, user_story us "+
                        "WHERE us.project_id = ? "+
                        "AND c.code_id = r.code_id " +
                        "AND c.user_story_id = us.user_story_id " +
                        "AND ra.review_id IN (SELECT DISTINCT review_id FROM review rv WHERE rv.submit_time BETWEEN STR_TO_DATE(?,'%d-%M-%Y') " +
                        "AND STR_TO_DATE(?,'%d-%M-%Y')) " +
                        "GROUP BY rule_id";
        
//        String query = "SELECT DISTINCT(rule_id) as ruleID, COUNT(*) as nbOfRule " + 
//                        "FROM code c, review r, review_annotation ra, user_story us "+
//                        "WHERE us.project_id = ? "+
//                        "AND c.code_id = r.code_id " +
//                        "AND c.user_story_id = us.user_story_id " +
//                        "AND ra.review_id IN (SELECT DISTINCT review_id FROM review rv ) " +
//                        "GROUP BY rule_id";
        
           try {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, projectId);
                ps.setString(2, startDate);
                ps.setString(3, endDate);
                ResultSet rs = ps.executeQuery();
                System.out.println(ps);
              
                while (rs.next()) {
                    System.out.println(rs.getString("ruleID") + " | " + rs.getInt("nbOfRule") + " /// ");
                    listData.put(rs.getString("ruleID"), rs.getInt("nbOfRule"));
                }
                ps.close();
                rs.close();
                con.close();
            }catch (SQLException ex) {
                System.err.println(ex);
            }
        
        return listData;
    }

    public List<UserDTOWithXPGains> getXpGainOfProjectMembers(String startDate, String endDate, int period, int projectId) {
        /*
        Get project users
        for each:
            add user to list
            Get codes pushed/reviews
            for each time interval:
                Use XpCalculator to calculate XP gain
         */

        try {

            AccountDAO accountDAO = new AccountDAOImpl();
            CodeDAO codeDAO = new CodeDAOImpl();
            ReviewDAO reviewDAO = new ReviewDAOImpl();
            List<UserDTOWithXPGains> listUserDTOWithXPGains = new ArrayList<>();

            for (UserDTO user : accountDAO.getMembersOfProject(projectId)){

                int userId = user.getUserId();

                List<CodeDTO> listOfCodePushedByUserBetweenDates = codeDAO.getListOfCodePushedByUserBetweenDates(userId, startDate, endDate);
                ArrayList<ReviewDTO> reviewedCodesByUserBetweenDates = reviewDAO.getReviewedCodesByUserBetweenDates(userId, user.getProjectId(), startDate, endDate);
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
	
                LocalDate start = LocalDate.parse(startDate, formatter);
                LocalDate end =  LocalDate.parse(endDate, formatter);

                List<LocalDate> dates = new ArrayList<>();

                if (period == 2){
                    for (LocalDate date = start; date.isBefore(end) || date.isEqual(end); date = date.plusMonths(1)){
                        dates.add(date);
                    }
                } else if (period == 1){
//                    System.out.println("dates: " + dates.size());
                    for (LocalDate date = start; date.isBefore(end) || date.isEqual(end); date = date.plusWeeks(1)){
                        dates.add(date);
                    }
                } else{
                    for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)){
                        dates.add(date);
                    }
                }

                XpCalculator calculator = new XpCalculator(userId, listOfCodePushedByUserBetweenDates, reviewedCodesByUserBetweenDates);

                UserDTOWithXPGains userWithXp = new UserDTOWithXPGains();
                userWithXp.setUserId(user.getUserId());
                userWithXp.setFirstName(user.getFirstName());
                userWithXp.setLastName(user.getLastName());
                userWithXp.setXpGainIntervalsList(new ArrayList<>());

                for (int i = 0; i < dates.size() - 1; i++){
                    LocalDate intervalStart = dates.get(i);
                    LocalDate intervalEnd = dates.get(i + 1);
                    // TODO:: Change date string format in response
                    userWithXp.getXpGainIntervalsList().add(
                            new UserXpSingleInterval(calculator.calculateXpGainBetweenDates(intervalStart, intervalEnd),
                                    intervalStart.toString(), intervalEnd.toString())
                    );
                }
                listUserDTOWithXPGains.add(userWithXp);
            }
            return listUserDTOWithXPGains;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

}
