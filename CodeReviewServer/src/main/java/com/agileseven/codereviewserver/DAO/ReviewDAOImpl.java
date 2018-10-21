/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereviewserver.DAO;

import com.agileseven.codereviewserver.DTO.ReviewDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    
}
