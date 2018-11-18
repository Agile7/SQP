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


}
