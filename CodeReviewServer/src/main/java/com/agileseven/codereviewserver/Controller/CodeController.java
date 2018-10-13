/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereviewserver.Controller;

import com.agileseven.codereviewserver.DAO.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vilosh_na
 */

@RestController
@RequestMapping(path="/CodeReviewer")
public class CodeController {
    
    @RequestMapping(path = "/pushCode", method=RequestMethod.GET)
    public int pushCode(){
        
       Connection con = ConnectionFactory.getConnection();
       
       String query = "Select count(*) from project ";
                try {
                    PreparedStatement ps = con.prepareStatement(query);
                    
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        return rs.getInt(1);   
                    }
                    ps.close();
                    rs.close();
                    con.close();
    }   catch (SQLException ex) {
            Logger.getLogger(CodeController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
         return -1;
    }
    
}
