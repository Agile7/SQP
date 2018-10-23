/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereviewserver.DAO;

import com.agileseven.codereviewserver.DTO.CodeDTO;
import com.agileseven.codereviewserver.DTO.UserDTO;
import com.agileseven.codereviewserver.DTO.UserstoryDTO;
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
 * This class implements the methods of CodeDAO interface
 * 
 * @author vilosh_na
 * @version 1.0
 * @date created : 13.10.2018
 */
public class CodeDAOImpl implements CodeDAO{

    public CodeDAOImpl() {
    }
    

    @Override
    public ArrayList<CodeDTO> getUnreadCodes(int projectId) {
        
        ArrayList<CodeDTO> codeList = new ArrayList<CodeDTO>();
        
        Connection con = ConnectionFactory.getConnection();
        String query = "SELECT c.code_id,c.comment,c.user_id,c.user_story_id," +
                        "STR_TO_DATE(c.push_date,'%Y-%m-%d %T') as push_date,c.code_text, "+
                        "u.first_name, u.last_name, us.title "+
                        "FROM code c, user u, user_story us " +
                        "where c.user_id = u.user_id " +
                        "AND c.user_story_id = us.user_story_id " +
                        "AND NOT EXISTS " +
                        "(Select 1 from review r " +
                        " where r.code_id = c.code_id " +
                        ") " +
                        "AND us.project_id = ? " +
                        "order by c.push_date asc ";
        
        System.out.println(query);
           try {
                PreparedStatement ps = con.prepareStatement(query);
                 ps.setInt(1, projectId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    codeList.add(buildCodeDTOfromResult(rs));
                }
                ps.close();
                rs.close();
                con.close();
            }catch (SQLException ex) {
                
            }
        
        return codeList;
    }
    
    public CodeDTO buildCodeDTOfromResult(ResultSet rs){
        CodeDTO code = new CodeDTO();
        
         try { 
             code.setCodeId(rs.getInt("code_id"));
             code.setCodeText(rs.getString("code_text"));
             code.setComment(rs.getString("comment"));
             code.setUserStoryId(rs.getString("user_story_id"));
             code.setUserId(rs.getInt("user_id"));
             code.setPushDate(rs.getDate("push_date"));
             code.setPushDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("push_date")));  
             
             UserDTO user = new UserDTO();
             user.setUserId(rs.getInt("code_id"));
             user.setFirstName(rs.getString("first_name"));
             user.setLastName(rs.getString("last_name"));
             code.setUser(user);
             
             UserstoryDTO userStory = new UserstoryDTO();
             userStory.setUserstoryid(rs.getString("user_story_id"));
             userStory.setTitle(rs.getString("title"));
             code.setUserStory(userStory);
  
           
        } catch (SQLException ex) {
        } catch (ParseException ex) {
            Logger.getLogger(CodeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return code;
        
    }
    
    public int pushCodeToDB(CodeDTO code){
        int result = 0;
        Connection con = ConnectionFactory.getConnection();
//        String query = "INSERT INTO code VALUES(NULL," + code.getCodeText() + "," 
//                     + code.getComment() + "," 
//                     + code.getNumLines() + "," 
//                     + code.getPushDate() + "," 
//                     + code.getUserId()+ "," 
//                     + code.getUserStoryId() + ")";
        
        String query = "INSERT INTO code VALUES(?,?,?,?,?,?)";

        try { 
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, code.getCodeText());
            ps.setString(2, code.getComment());
            ps.setInt(3, code.getNumLines());
            ps.setDate(4, (Date) code.getPushDate());
            ps.setInt(5, code.getUserId());
            ps.setString(6, code.getUserStoryId());
            
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                result = rs.getInt(1);
            }
            
            con.close();
            return result; // If success => result > 0
        } catch (SQLException ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
        return result;
    }
    
    @Override
    public CodeDTO getCodeById(int codeId) {
        CodeDTO code = null;
         Connection con = ConnectionFactory.getConnection();
        String query = "SELECT c.code_id, c.comment, c.code_text, c.push_date, c.user_id, "
                        + "c.user_story_id, u.first_name, u.last_name, us.title "
                        + "FROM code c, user u , user_story us " +
                        "where code_id = "+codeId;
        
        System.out.println(query);
           try {
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    code = buildCodeDTOfromResult(rs);
                }
                ps.close();
                rs.close();
                con.close();
            }catch (SQLException ex) {
                
            }
        
        return code;
    }
}
