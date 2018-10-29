/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereviewserver.Controller;

import com.agileseven.codereviewserver.DAO.AnnotationDAO;
import com.agileseven.codereviewserver.DAO.AnnotationDAOImpl;
import com.agileseven.codereviewserver.DAO.ReviewDAO;
import com.agileseven.codereviewserver.DAO.ReviewDAOImpl;
import com.agileseven.codereviewserver.DTO.ReviewAnnotationDTO;
import com.agileseven.codereviewserver.DTO.ReviewDTO;

import java.util.ArrayList;

import com.agileseven.codereviewserver.DTO.RuleDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class maps the URL to the methods to perform operations
 * for the review process.
 * 
 * @author vilosh_na
 * @version 1.0
 * @date created : 13.10.2018
 */
@RestController
@RequestMapping(path="/CodeReviewer")
public class ReviewController {
    
    ReviewDAO reviewDAO = new ReviewDAOImpl();
    
    @RequestMapping(path = "/review/approve/{codeId}", method=RequestMethod.GET)
    public int setCodeApproved(@PathVariable int codeId){
        //Tested by postman
       return -1;
        
    }
    
    @RequestMapping(path = "/review", method= RequestMethod.POST)
    public int addReview(@RequestBody  ReviewDTO review){
  
      return reviewDAO.addReview(review);
        
        
    }
    
    @RequestMapping(path = "/review/reviews", method=RequestMethod.GET)
    public ArrayList<ReviewDTO> getReviewedCodeByUser(@RequestParam(value="userId", defaultValue="") int userId,
                                                    @RequestParam(value="projectId", defaultValue="") int projectId)
    {
        return reviewDAO.getReviewedCodesByUser(userId, projectId);
    }
    
    @RequestMapping(path = "/review/annotations/{reviewId}", method=RequestMethod.GET)
    public ArrayList<ReviewAnnotationDTO> getAnnotationsByReviewId(@PathVariable int reviewId){
        //Tested by postman
       AnnotationDAO annotationDAO = new AnnotationDAOImpl();
       
       return annotationDAO.getAnnotationsByReviewId(reviewId);
    }

    @RequestMapping(path = "/review/rules", method=RequestMethod.GET)
    public ArrayList<RuleDTO> getRulesList(){
        return reviewDAO.getRulesList();
    }

}
