/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereviewserver.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import org.json.JSONObject;

/**
 *
 * @author Jin Xutong
 */
public class PersonalDashboardDAOImpl implements PersonalDashboardDAO{
    
    //Number of rejected codes
    @Override
    public LinkedHashMap<String, Integer> getNumberOfPersonalCodeRejected(String startDate, String endDate, int period, int userId) {
        Connection con = ConnectionFactory.getConnection();
        
        String query = "from review r, code c " +
                        "where r.code_id = c.code_id " +
                        "AND c.user_id = ? " +
                        "AND r.start_time BETWEEN STR_TO_DATE(?,'%d-%M-%Y') "+
                        "AND STR_TO_DATE(?,'%d-%M-%Y') "+
                        "AND r.approved = 0 ";
        
        switch (period) {
            case 2:
                //monthly
                query = "select count(*) AS count_rejectedCodes, DATE_FORMAT(r.submit_time, '%m-%Y') AS date_period " +query;
                query = query + "GROUP BY DATE_FORMAT(r.submit_time, '%m-%Y') ";
                break;
            case 1:
                //weekly
                query = "select count(*) AS count_rejectedCodes, concat(WEEK(r.submit_time),'-',YEAR(r.submit_time)) AS date_period " +query;
                query = query + "GROUP BY concat(WEEK(r.submit_time),'-',YEAR(r.submit_time)) ";
                break;
            default:
                //daily
                query = "select count(*) AS count_rejectedCodes, DATE_FORMAT(r.submit_time,'%d-%m-%Y') AS date_period " +query;
                query = query + "GROUP BY DATE_FORMAT(r.submit_time,'%d-%m-%Y') ";
                break;
        }
        query = query + " ORDER BY DATE(r.submit_time) ASC";
        
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
                    map.put(rs.getString("date_period"),rs.getInt("count_rejectedCodes")) ;
                }
                ps.close();
                rs.close();
                con.close();
            }catch (SQLException ex) {
                
            }
        
        return map;
        
    }
    
    @Override
    public LinkedHashMap<String, Integer> getNumberOfTeamCodeRejected(String startDate, String endDate, int period, int projectId) {
        Connection con = ConnectionFactory.getConnection();
        
        String query = "from review r, code c, user u " +
                        "where r.code_id = c.code_id " +
                        "AND c.user_id = u.user_id " +
                        "AND u.project_id = ? " +
                        "AND r.start_time BETWEEN STR_TO_DATE(?,'%d-%M-%Y') "+
                        "AND STR_TO_DATE(?,'%d-%M-%Y') "+
                        "AND r.approved = 0 ";
        
        switch (period) {
            case 2:
                //monthly
                query = "select count(*) AS count_rejectedCodes, DATE_FORMAT(r.submit_time, '%m-%Y') AS date_period " +query;
                query = query + "GROUP BY DATE_FORMAT(r.submit_time, '%m-%Y') ";
                break;
            case 1:
                //weekly
                query = "select count(*) AS count_rejectedCodes, concat(WEEK(r.submit_time),'-',YEAR(r.submit_time)) AS date_period " +query;
                query = query + "GROUP BY concat(WEEK(r.submit_time),'-',YEAR(r.submit_time)) ";
                break;
            default:
                //daily
                query = "select count(*) AS count_rejectedCodes, DATE_FORMAT(r.submit_time,'%d-%m-%Y') AS date_period " +query;
                query = query + "GROUP BY DATE_FORMAT(r.submit_time,'%d-%m-%Y') ";
                break;
        }
        query = query + " ORDER BY DATE(r.submit_time) ASC";
        
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
                    map.put(rs.getString("date_period"),rs.getInt("count_rejectedCodes")) ;
                }
                ps.close();
                rs.close();
                con.close();
            }catch (SQLException ex) {
                
            }
        
        return map;
        
    }
    //Number of approved codes
    @Override
    public LinkedHashMap<String, Integer> getNumberOfPersonalCodeApproved(String startDate, String endDate, int period, int userId) {
        Connection con = ConnectionFactory.getConnection();
        
        String query = "from review r, code c " +
                        "where r.code_id = c.code_id " +
                        "AND c.user_id = ? " +
                        "AND r.submit_time BETWEEN STR_TO_DATE(?,'%d-%M-%Y') "+
                        "AND STR_TO_DATE(?,'%d-%M-%Y') "+
                        "AND r.approved = 1 ";
        
        switch (period) {
            case 2:
                //monthly
                query = "select count(*) AS count_approvedCodes, DATE_FORMAT(r.submit_time, '%m-%Y') AS date_period " +query;
                query = query + "GROUP BY DATE_FORMAT(r.submit_time, '%m-%Y') ";
                break;
            case 1:
                //weekly
                query = "select count(*) AS count_approvedCodes, concat(WEEK(r.submit_time),'-',YEAR(r.submit_time)) AS date_period " +query;
                query = query + "GROUP BY concat(WEEK(r.submit_time),'-',YEAR(r.submit_time)) ";
                break;
            default:
                //daily
                query = "select count(*) AS count_approvedCodes, DATE_FORMAT(r.submit_time,'%d-%m-%Y') AS date_period " +query;
                query = query + "GROUP BY DATE_FORMAT(r.submit_time,'%d-%m-%Y') ";
                break;
        }
        query = query + " ORDER BY DATE(r.submit_time) ASC";
        
        LinkedHashMap<String, Integer> map = new LinkedHashMap();
        
           try {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, userId);
                ps.setString(2, startDate);
                ps.setString(3, endDate);
                System.out.println("csvfvdfvdf : "+ps);
                ResultSet rs = ps.executeQuery();
                
              
                
                while (rs.next()) {
                    System.out.println(rs.getString("date_period"));
                    map.put(rs.getString("date_period"),rs.getInt("count_approvedCodes")) ;
                }
                ps.close();
                rs.close();
                con.close();
            }catch (SQLException ex) {
                
            }
        return map;
        
    }
    
    @Override
    public LinkedHashMap<String, Integer> getNumberOfTeamCodeApproved(String startDate, String endDate, int period, int projectId) {
        Connection con = ConnectionFactory.getConnection();
        
        String query = "from review r, code c, user u " +
                        "where r.code_id = c.code_id " +
                        "AND c.user_id = u.user_id " +
                        "AND u.project_id = ? " +
                        "AND r.submit_time BETWEEN STR_TO_DATE(?,'%d-%M-%Y') "+
                        "AND STR_TO_DATE(?,'%d-%M-%Y') "+
                        "AND r.approved = 1 ";
        
        switch (period) {
            case 2:
                //monthly
                query = "select count(*) AS count_rejectedCodes, DATE_FORMAT(r.submit_time, '%m-%Y') AS date_period " +query;
                query = query + "GROUP BY DATE_FORMAT(r.submit_time, '%m-%Y') ";
                break;
            case 1:
                //weekly
                query = "select count(*) AS count_rejectedCodes, concat(WEEK(r.submit_time),'-',YEAR(r.submit_time)) AS date_period " +query;
                query = query + "GROUP BY concat(WEEK(r.submit_time),'-',YEAR(r.submit_time)) ";
                break;
            default:
                //daily
                query = "select count(*) AS count_rejectedCodes, DATE_FORMAT(r.submit_time,'%d-%m-%Y') AS date_period " +query;
                query = query + "GROUP BY DATE_FORMAT(r.submit_time,'%d-%m-%Y') ";
                break;
        }
        query = query + " ORDER BY DATE(r.submit_time) ASC";
        
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
                    map.put(rs.getString("date_period"),rs.getInt("count_rejectedCodes")) ;
                }
                ps.close();
                rs.close();
                con.close();
            }catch (SQLException ex) {
                
            }
        
        return map;
        
    }
    
}
