/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereviewserver.DAO;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 * @author vilosh_na
 */
public interface GamificationDAO {
    
    public LinkedHashMap<String, Integer>  getNumberOfCodesPushedByTeam(String startDate, String endDate, int period, int projectId);
    public LinkedHashMap<String, Integer>  getSumOfLinesPushedByTeam(String startDate, String endDate, int period, int projectId);
    public LinkedHashMap<String, Integer>  getNumberOfCodesPushedByIndividual(String startDate, String endDate, int period, int userId);
    public LinkedHashMap<String, Integer>  getSumOfLinesPushedByIndividual(String startDate, String endDate, int period, int userId);


}
