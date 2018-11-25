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
 * @author Jin Xutong
 */
public interface PersonalDashboardDAO {
    
    public LinkedHashMap<String, Integer> getNumberOfPersonalCodeRejected(String startDate, String endDate, int period, int userId);
    public LinkedHashMap<String, Integer> getNumberOfPersonalCodeApproved(String startDate, String endDate, int period, int userId);
    public LinkedHashMap<String, Integer> getNumberOfTeamCodeRejected(String startDate, String endDate, int period, int projectId);
    public LinkedHashMap<String, Integer> getNumberOfTeamCodeApproved(String startDate, String endDate, int period, int projectId);
    
}
