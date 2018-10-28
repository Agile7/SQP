/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereviewserver.DAO;
import com.agileseven.codereviewserver.DTO.CodeDTO;
import com.agileseven.codereviewserver.DTO.ReviewDTO;
import java.util.ArrayList;

/**
 * This interface contains the methods
 * - to insert reviews
 * - to get the list of reviews
 * 
 * @author vilosh_na
 * @version 1.0
 * @date created : 13.10.2018
 */
public interface ReviewDAO {
    
    public int approveCode(int review_id, int approved);
    public int addReview(ReviewDTO review);
    public ArrayList<ReviewDTO> getReviewedCodesByUser(int userId,int projectId);
    
}
