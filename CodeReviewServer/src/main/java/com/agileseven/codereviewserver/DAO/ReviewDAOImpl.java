/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereviewserver.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    
}
