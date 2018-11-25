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
                        "AND c.push_date BETWEEN STR_TO_DATE(?,'%d-%M-%Y') "+
                        "AND STR_TO_DATE(?,'%d-%M-%Y') "+
                        "AND r.approved = 0";
        
        switch (period) {
            case 2:
                //monthly
                query = "select count(*) AS count_rejectedCodes, DATE_FORMAT(c.push_date, '%m-%Y') AS date_period " +query;
                query = query + "GROUP BY DATE_FORMAT(c.push_date, '%m-%Y') ";
                break;
            case 1:
                //weekly
                query = "select count(*) AS count_rejectedCodes, concat(WEEK(c.push_date),'-',YEAR(c.push_date)) AS date_period " +query;
                query = query + "GROUP BY concat(WEEK(c.push_date),'-',YEAR(c.push_date)) ";
                break;
            default:
                //daily
                query = "select count(*) AS count_rejectedCodes, DATE_FORMAT(c.push_date,'%d-%m-%Y') AS date_period " +query;
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
                        "AND c.push_date BETWEEN STR_TO_DATE(?,'%d-%M-%Y') "+
                        "AND STR_TO_DATE(?,'%d-%M-%Y') "+
                        "AND r.approved = 1";
        
        switch (period) {
            case 2:
                //monthly
                query = "select count(*) AS count_approvedCodes, DATE_FORMAT(c.push_date, '%m-%Y') AS date_period " +query;
                query = query + "GROUP BY DATE_FORMAT(c.push_date, '%m-%Y') ";
                break;
            case 1:
                //weekly
                query = "select count(*) AS count_approvedCodes, concat(WEEK(c.push_date),'-',YEAR(c.push_date)) AS date_period " +query;
                query = query + "GROUP BY concat(WEEK(c.push_date),'-',YEAR(c.push_date)) ";
                break;
            default:
                //daily
                query = "select count(*) AS count_approvedCodes, DATE_FORMAT(c.push_date,'%d-%m-%Y') AS date_period " +query;
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
                    map.put(rs.getString("date_period"),rs.getInt("count_approvedCodes")) ;
                }
                ps.close();
                rs.close();
                con.close();
            }catch (SQLException ex) {
                
            }
        
        return map;
        
    }
    
}
