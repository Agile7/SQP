/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereviewserver.DAO;

import com.agileseven.codereviewserver.DTO.CodeDTO;
import com.agileseven.codereviewserver.DTO.ReviewAnnotationDTO;
import com.agileseven.codereviewserver.DTO.RuleDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Jin Xutong
 * 
 */
public class AnnotationDAOImpl implements AnnotationDAO{

    public AnnotationDAOImpl() {
    }
    
    /*
    SELECT r.code_id, r.reviewer_id, r.approved, ra.annotation_text, ra.rule_id,
        c.code_text, us.title, us.description
    FROM code c, user_story us, review r, review_annotation ra
    WHERE c.code_id = r.code_id
        AND r.review_id = ra.review_id
        AND c.user_story_id = us.user_story_id
    */
    
    @Override
    public ArrayList<ReviewAnnotationDTO> getAnnotationsByReviewId(int reviewId){
        
        Connection con = ConnectionFactory.getConnection();
        
        ResultSet resultSet;
        Statement Statement;
        String query = "SELECT ra.annotation_id, ra.annotation_text,  "
                     + "ra.rule_id, ra.review_id, rl.rule_text " 
                     + "FROM review_annotation ra, rule rl "
                     + "WHERE ra.rule_id = rl.rule_id "
                     + "AND ra.review_id = " + reviewId;

        ArrayList<ReviewAnnotationDTO> reviewAnntations = new ArrayList<ReviewAnnotationDTO>();
        
        ReviewAnnotationDTO annotation;
        try {
            
            Statement = con.createStatement();
            resultSet = Statement.executeQuery(query);

            while (resultSet.next() == true) {
                
                annotation = new ReviewAnnotationDTO();
                annotation.setAnnotId(resultSet.getInt("annotation_id"));
                annotation.setAnnotText(resultSet.getString("annotation_text"));
                annotation.setRuleId(resultSet.getString("rule_id"));
                annotation.setReviewId(reviewId);
                
                RuleDTO ruleDTO = new RuleDTO(resultSet.getString("rule_id"), resultSet.getString("rule_text"));
                annotation.setRuleDTO(ruleDTO);
                
                reviewAnntations.add(annotation);
                
            }
            } catch (SQLException ex) {
            System.out.println(ex);
        }
        return reviewAnntations;
    }
}
