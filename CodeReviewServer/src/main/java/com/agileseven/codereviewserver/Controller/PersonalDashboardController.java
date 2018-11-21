/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereviewserver.Controller;

import com.agileseven.codereviewserver.DAO.PersonalDashboardDAO;
import com.agileseven.codereviewserver.DAO.PersonalDashboardDAOImpl;
import java.util.HashMap;
import java.util.LinkedHashMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jin Xutong
 */

@RestController
@RequestMapping(path="/CodeReviewer")
public class PersonalDashboardController {
    private PersonalDashboardDAO personalDashboardDAO = new PersonalDashboardDAOImpl();
    
    @RequestMapping(path = "/numCodeRejected", method=RequestMethod.GET)
    public LinkedHashMap<String, Integer> numberOfPersonalCodeRejected(@RequestParam(value="startDate", defaultValue="") String startDate,
                        @RequestParam(value="endDate", defaultValue="") String endDate,
                        @RequestParam(value="period", defaultValue="") int period,
                        @RequestParam(value="userId", defaultValue="") int userId) {
        
        LinkedHashMap<String, Integer> map = null;
        try{
           map = personalDashboardDAO.getNumberOfPersonalCodeRejected(startDate, endDate, period, userId);
       
        }
        catch(Exception ex){
//            System.err.println("Got an exception!");
//            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        
        
        return map;
    }
    
    @RequestMapping(path = "/numCodeApproved", method=RequestMethod.GET)
    public LinkedHashMap<String, Integer> numberOfPersonalCodeApproved(@RequestParam(value="startDate", defaultValue="") String startDate,
                        @RequestParam(value="endDate", defaultValue="") String endDate,
                        @RequestParam(value="period", defaultValue="") int period,
                        @RequestParam(value="userId", defaultValue="") int userId) {
        
        LinkedHashMap<String, Integer> map = null;
        try{
           map = personalDashboardDAO.getNumberOfPersonalCodeApproved(startDate, endDate, period, userId);
       
        }
        catch(Exception ex){
//            System.err.println("Got an exception!");
//            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        
        return map;
    }
    
}
