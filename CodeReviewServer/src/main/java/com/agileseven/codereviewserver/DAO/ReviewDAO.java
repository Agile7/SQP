/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereviewserver.DAO;
import com.agileseven.codereviewserver.DTO.CodeDTO;
import com.agileseven.codereviewserver.DTO.ReviewDTO;
import com.agileseven.codereviewserver.DTO.RuleDTO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public ArrayList<RuleDTO> getRulesList();
    public List<ReviewDTO> getReviewsOfUser(int userID) throws SQLException;
    public List<ReviewDTO> getReviewsOfProject(int projetcId, Date start, Date end) throws SQLException;
    public List<ReviewDTO> getReviewsOfUserCodes(int devId);
    public ArrayList<ReviewDTO> getReviewedCodesByUserBetweenDates(int userId,int projectId, String startDate, String endDate);

}
