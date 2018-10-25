/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereviewserver.DAO;

/**
 *
 * @author Jin Xutong
 * 
 */
public class AnnotationDAOImpl implements AnnotationDAO{
    
    /*
    SELECT r.code_id, r.reviewer_id, r.approved, ra.annotation_text, ra.rule_id,
        c.code_text, us.title, us.description
    FROM code c, user_story us, review r, review_annotation ra
    WHERE c.code_id = r.code_id
        AND r.review_id = ra.review_id
        AND c.user_story_id = us.user_story_id
    */
    
}
