/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereviewserver.Controller;

import com.agileseven.codereviewserver.DAO.GamificationDAO;
import com.agileseven.codereviewserver.DAO.GamificationDAOImpl;
import java.util.HashMap;
import java.util.LinkedHashMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vilosh_na
 */
@RestController
@RequestMapping(path="/CodeReviewer")
public class GamificationController {
     private GamificationDAO gamificationDAO = new GamificationDAOImpl();
     
     
    @RequestMapping(path = "/codePushed", method=RequestMethod.GET)
    public LinkedHashMap<String, Integer> numberOfCodesPushedByTeam(@RequestParam(value="startDate", defaultValue="") String startDate,
                        @RequestParam(value="endDate", defaultValue="") String endDate,
                        @RequestParam(value="period", defaultValue="") int period,
                        @RequestParam(value="projectId", defaultValue="") int projectId) {
        
        LinkedHashMap<String, Integer> map = null;
        try{
           map = gamificationDAO.getNumberOfCodesPushedByTeam(startDate, endDate, period, projectId);
       
        }
        catch(Exception ex){
//            System.err.println("Got an exception!");
//            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        
        return map;
    }
    
        @RequestMapping(path = "/linesPushed", method=RequestMethod.GET)
    public LinkedHashMap<String, Integer> numberOfLinesPushedByTeam(@RequestParam(value="startDate", defaultValue="") String startDate,
                        @RequestParam(value="endDate", defaultValue="") String endDate,
                        @RequestParam(value="period", defaultValue="") int period,
                        @RequestParam(value="projectId", defaultValue="") int projectId) {
        
        LinkedHashMap<String, Integer> map = null;
        try{
           map = gamificationDAO.getSumOfLinesPushedByTeam(startDate, endDate, period, projectId);
           
        }
        catch(Exception ex){
//            System.err.println("Got an exception!");
//            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        
        return map;
    }
}
