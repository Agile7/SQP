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

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    public int addReview(@RequestBody ReviewDTO review){
  
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

    @RequestMapping(path = "/review/project", method=RequestMethod.GET, produces = "application/json")
    public ProjectReviewsResponse getReviewsOfProject(@RequestParam(value="projectId", defaultValue="") int projectId,
                                                      @RequestParam(value="start", defaultValue="") String start,
                                                    @RequestParam(value="end", defaultValue="") String end) {
        try {
            ReviewDAO reviewDAO = new ReviewDAOImpl();
            List<ReviewDTO> reviews = reviewDAO.getReviewsOfProject(projectId, Date.valueOf(start), Date.valueOf(end));
            long min = Long.MAX_VALUE;
            long max = Long.MIN_VALUE;
            long total = 0;

            for (ReviewDTO r: reviews) {
                long duration = getDateDiff(Timestamp.valueOf(r.getStartTime()), Timestamp.valueOf(r.getSubmitTime()), TimeUnit.SECONDS);
                total += duration;
                if (duration < min) min = duration;
                if (duration > max) max = duration;
            }

            return new ProjectReviewsResponse(min, max, total);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static long getDateDiff(Timestamp date1, Timestamp date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }

    class ProjectReviewsResponse {
        private double min;
        private double max;
        private double total;

        public ProjectReviewsResponse(double min, double max, double total) {
            this.min = min;
            this.max = max;
            this.total = total;
        }

        public ProjectReviewsResponse() {
        }

        public double getMin() {
            return min;
        }

        public void setMin(double min) {
            this.min = min;
        }

        public double getMax() {
            return max;
        }

        public void setMax(double max) {
            this.max = max;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }
    }

}
