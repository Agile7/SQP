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
import java.util.List;

import com.agileseven.codereviewserver.DTO.UserDTOWithXPGains;
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
    
    @RequestMapping(path = "/codePushedByIndividual", method=RequestMethod.GET)
    public LinkedHashMap<String, Integer> numberOfCodesPushedByIndividual(@RequestParam(value="startDate", defaultValue="") String startDate,
                        @RequestParam(value="endDate", defaultValue="") String endDate,
                        @RequestParam(value="period", defaultValue="") int period,
                        @RequestParam(value="userId", defaultValue="") int userId) {
        
        LinkedHashMap<String, Integer> map = null;
        try{
           map = gamificationDAO.getNumberOfCodesPushedByIndividual(startDate, endDate, period, userId);
       
        }
        catch(Exception ex){
//            System.err.println("Got an exception!");
//            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        
        return map;
    }
    
    @RequestMapping(path = "/linesPushedByIndividual", method=RequestMethod.GET)
    public LinkedHashMap<String, Integer> numberOfLinesPushedByIndividual(@RequestParam(value="startDate", defaultValue="") String startDate,
                        @RequestParam(value="endDate", defaultValue="") String endDate,
                        @RequestParam(value="period", defaultValue="") int period,
                        @RequestParam(value="userId", defaultValue="") int userId) {
        
        LinkedHashMap<String, Integer> map = null;
        try{
           map = gamificationDAO.getSumOfLinesPushedByIndividual(startDate, endDate, period, userId);
           
        }
        catch(Exception ex){
//            System.err.println("Got an exception!");
//            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        
        return map;
    }
    
    @RequestMapping(path = "/annotationByTeam", method=RequestMethod.GET)
    public Integer numberOfAnnotationByTeam(@RequestParam(value="startDate", defaultValue="") String startDate,
                        @RequestParam(value="endDate", defaultValue="") String endDate,
                        @RequestParam(value="projectId", defaultValue="") int projectId)  {
        
        int nbOfAnnotation = 0;
        try{
           nbOfAnnotation = gamificationDAO.getTotalAnnotation(startDate, endDate, projectId);
           
        }
        catch(Exception ex){
//            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        
        return nbOfAnnotation;
    }
    
    @RequestMapping(path = "/lineReviewedByTeam", method=RequestMethod.GET)
    public Integer numberOfLineReviewedByTeam(@RequestParam(value="startDate", defaultValue="") String startDate,
                        @RequestParam(value="endDate", defaultValue="") String endDate,
                        @RequestParam(value="projectId", defaultValue="") int projectId)  {
        
        int nbOfLineReviewed = 0;
        try{
           nbOfLineReviewed = gamificationDAO.getTotalLineReviewed(startDate, endDate, projectId);
           
        }
        catch(Exception ex){
//            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        
        return nbOfLineReviewed;
    }
    
    @RequestMapping(path = "/ruleByAnnotation", method=RequestMethod.GET)
    public LinkedHashMap<String, Integer> numberOfRuleByAnnotation(@RequestParam(value="startDate", defaultValue="") String startDate,
                        @RequestParam(value="endDate", defaultValue="") String endDate,
                        @RequestParam(value="projectId", defaultValue="") int projectId) {
        
        LinkedHashMap<String, Integer> mapRule = null;
        try{
           mapRule = gamificationDAO.getListRuleCount(startDate, endDate, projectId);
           
        }
        catch(Exception ex){
//            System.err.println("Got an exception!");
//            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        
        return mapRule;
    }

    @RequestMapping(path = "/usersXpList", method=RequestMethod.GET, produces = "application/json")
    public List<UserDTOWithXPGains> userXpList(@RequestParam(value="start", defaultValue="") String startDate,
                                               @RequestParam(value="end", defaultValue="") String endDate,
                                               @RequestParam(value="period", defaultValue="") int period,
                                               @RequestParam(value="projectId", defaultValue="") int projectId) {
        return gamificationDAO.getXpGainOfProjectMembers(startDate, endDate, period, projectId);
    }
}
