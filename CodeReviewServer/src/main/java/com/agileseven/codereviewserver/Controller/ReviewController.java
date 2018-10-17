/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereviewserver.Controller;

import com.agileseven.codereviewserver.DAO.ReviewDAO;
import com.agileseven.codereviewserver.DAO.ReviewDAOImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    
    @RequestMapping(path = "/review/approve", method=RequestMethod.PUT)
    public int setCodeApproved(){
        
        reviewDAO.approveCode(1, 1);
        return -1;
    }
}
