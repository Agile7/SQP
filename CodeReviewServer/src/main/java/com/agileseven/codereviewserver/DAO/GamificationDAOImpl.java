/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereviewserver.DAO;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import com.agileseven.codereviewserver.DTO.CodeDTO;
import com.agileseven.codereviewserver.DTO.ReviewDTO;
import com.agileseven.codereviewserver.DTO.UserDTO;
import com.agileseven.codereviewserver.Utilities.XpCalculator;

/**
 *
 * @author vilosh_na
 */
public class GamificationDAOImpl implements GamificationDAO{

    @Override
    public LinkedHashMap<String, Integer> getNumberOfCodesPushedByTeam(String startDate, String endDate, int period, int projectId) {
        Connection con = ConnectionFactory.getConnection();
        
        String query = "from code c, user u "+
                        "where c.user_id = u.user_id "+
                        "and u.project_id = ? "+
                        "AND c.push_date BETWEEN ? AND ?";
        
        switch (period) {
            case 2:
                //monthly
                query = "select count(*) AS count_codes, DATE_FORMAT(c.push_date, '%m-%Y') AS date_period " +query;
                query = query + "GROUP BY DATE_FORMAT(c.push_date, '%m-%Y') ";
                break;
            case 1:
                //weekly
                query = "select count(*) AS count_codes, concat(WEEK(c.push_date),'-',YEAR(c.push_date)) AS date_period " +query;
                query = query + "GROUP BY concat(WEEK(c.push_date),'-',YEAR(c.push_date)) ";
                break;
            default:
                //daily
                query = "select count(*) AS count_codes, DATE_FORMAT(c.push_date,'%d-%m-%Y') AS date_period " +query;
                query = query + "GROUP BY DATE_FORMAT(c.push_date,'%d-%m-%Y') ";
                break;
        }
        query = query + " ORDER BY DATE(c.push_date) ASC";
        
        
         LinkedHashMap<String, Integer> map = new LinkedHashMap();
        
           try {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, projectId);
                ps.setDate(2, Date.valueOf(startDate));
                ps.setDate(3, Date.valueOf(endDate));
                ResultSet rs = ps.executeQuery();
                
              System.out.println(ps);
                
                while (rs.next()) {
                    System.out.println(rs.getString("date_period"));
                    map.put(rs.getString("date_period"),rs.getInt("count_codes")) ;
                }
                ps.close();
                rs.close();
                con.close();
            }catch (SQLException ex) {
                
            }
        
        return map;
    }
    
    @Override
    public LinkedHashMap<String, Integer> getSumOfLinesPushedByTeam(String startDate, String endDate, int period, int projectId) {
        Connection con = ConnectionFactory.getConnection();
        
        String query = "from code c, user u "+
                        "where c.user_id = u.user_id "+
                        "and u.project_id = ? "+
                        "AND c.push_date BETWEEN ? AND ?";
        
        switch (period) {
            case 2:
                //monthly
                query = "select SUM(number_of_lines) AS sum_lines, DATE_FORMAT(c.push_date, '%m-%Y') AS date_period " +query;
                query = query + "GROUP BY DATE_FORMAT(c.push_date, '%m-%Y') ";
                break;
            case 1:
                //weekly
                query = "select SUM(number_of_lines) AS sum_lines, concat(WEEK(c.push_date),'-',YEAR(c.push_date)) AS date_period " +query;
                query = query + "GROUP BY concat(WEEK(c.push_date),'-',YEAR(c.push_date)) ";
                break;
            default:
                //daily
                query = "select SUM(number_of_lines) AS sum_lines, DATE_FORMAT(c.push_date,'%d-%m-%Y') AS date_period " +query;
                query = query + "GROUP BY DATE_FORMAT(c.push_date,'%d-%m-%Y') ";
                break;
        }
        
        query = query + " ORDER BY DATE(c.push_date) ASC";
        
         LinkedHashMap<String, Integer> map = new LinkedHashMap();
        
           try {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, projectId);
                ps.setDate(2, Date.valueOf(startDate));
                ps.setDate(3, Date.valueOf(endDate));
                ResultSet rs = ps.executeQuery();
                System.out.println(ps);
              
                
                while (rs.next()) {
                    map.put(rs.getString("date_period"),rs.getInt("sum_lines")) ;
                }
                ps.close();
                rs.close();
                con.close();
            }catch (SQLException ex) {
                
            }
        
        return map;
    }

    @Override
    public LinkedHashMap<String, Integer> getNumberOfCodesPushedByIndividual(String startDate, String endDate, int period, int userId) {
        Connection con = ConnectionFactory.getConnection();
        
        String query = "from code c "+
                        "where c.user_id = ? "+
                        "AND c.push_date BETWEEN ? AND ?";
        
        switch (period) {
            case 2:
                //monthly
                query = "select count(*) AS count_codes, DATE_FORMAT(c.push_date, '%m-%Y') AS date_period " +query;
                query = query + "GROUP BY DATE_FORMAT(c.push_date, '%m-%Y') ";
                break;
            case 1:
                //weekly
                query = "select count(*) AS count_codes, concat(WEEK(c.push_date),'-',YEAR(c.push_date)) AS date_period " +query;
                query = query + "GROUP BY concat(WEEK(c.push_date),'-',YEAR(c.push_date)) ";
                break;
            default:
                //daily
                query = "select count(*) AS count_codes, DATE_FORMAT(c.push_date,'%d-%m-%Y') AS date_period " +query;
                query = query + "GROUP BY DATE_FORMAT(c.push_date,'%d-%m-%Y') ";
                break;
        }
        query = query + " ORDER BY DATE(c.push_date) ASC";
        
        
         LinkedHashMap<String, Integer> map = new LinkedHashMap();
        
           try {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, userId);
                ps.setDate(2, Date.valueOf(startDate));
                ps.setDate(3, Date.valueOf(endDate));
                ResultSet rs = ps.executeQuery();
                
              System.out.println(ps);
                
                while (rs.next()) {
                    System.out.println(rs.getString("date_period"));
                    map.put(rs.getString("date_period"),rs.getInt("count_codes")) ;
                }
                ps.close();
                rs.close();
                con.close();
            }catch (SQLException ex) {
                
            }
        
        return map;
    }
    
    @Override
    public LinkedHashMap<String, Integer> getSumOfLinesPushedByIndividual(String startDate, String endDate, int period, int userId) {
        Connection con = ConnectionFactory.getConnection();
        
        String query = "from code c "+
                        "where c.user_id = ? "+
                        "AND c.push_date BETWEEN ? AND ? ";
        
        switch (period) {
            case 2:
                //monthly
                query = "select SUM(number_of_lines) AS sum_lines, DATE_FORMAT(c.push_date, '%m-%Y') AS date_period " +query;
                query = query + "GROUP BY DATE_FORMAT(c.push_date, '%m-%Y') ";
                break;
            case 1:
                //weekly
                query = "select SUM(number_of_lines) AS sum_lines, concat(WEEK(c.push_date),'-',YEAR(c.push_date)) AS date_period " +query;
                query = query + "GROUP BY concat(WEEK(c.push_date),'-',YEAR(c.push_date)) ";
                break;
            default:
                //daily
                query = "select SUM(number_of_lines) AS sum_lines, DATE_FORMAT(c.push_date,'%d-%m-%Y') AS date_period " +query;
                query = query + "GROUP BY DATE_FORMAT(c.push_date,'%d-%m-%Y') ";
                break;
        }
        
        query = query + " ORDER BY DATE(c.push_date) ASC";
        
         LinkedHashMap<String, Integer> map = new LinkedHashMap();
        
           try {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, userId);
                ps.setDate(2, Date.valueOf(startDate));
                ps.setDate(3, Date.valueOf(endDate));
                ResultSet rs = ps.executeQuery();
                System.out.println(ps);
              
                
                while (rs.next()) {
                    map.put(rs.getString("date_period"),rs.getInt("sum_lines")) ;
                }
                ps.close();
                rs.close();
                con.close();
            }catch (SQLException ex) {
                
            }
        
        return map;
    }

    public List<UserDTOWithXPGains> getXpGainOfProjectMembers(String startDate, String endDate, int period, int projectId) {
        /*
        Get project users
        for each:
            add user to list
            Get codes pushed/reviews
            for each time interval:
                Use XpCalculator to calculate XP gain
         */

        try {

            AccountDAO accountDAO = new AccountDAOImpl();
            CodeDAO codeDAO = new CodeDAOImpl();
            ReviewDAO reviewDAO = new ReviewDAOImpl();
            List<UserDTOWithXPGains> listUserDTOWithXPGains = new ArrayList<>();
            System.out.println("project members: " + accountDAO.getMembersOfProject(projectId).size());
            for (UserDTO user : accountDAO.getMembersOfProject(projectId)){

                int userId = user.getUserId();
                System.out.println("userId: " + userId);

                List<CodeDTO> listOfCodePushedByUserBetweenDates = codeDAO.getListOfCodePushedByUserBetweenDates(userId, startDate, endDate);
                ArrayList<ReviewDTO> reviewedCodesByUserBetweenDates = reviewDAO.getReviewedCodesByUserBetweenDates(userId, user.getProjectId(), startDate, endDate);
                System.out.println("listOfCodePushedByUserBetweenDates: " + listOfCodePushedByUserBetweenDates.size());
                System.out.println("reviewedCodesByUserBetweenDates: " + reviewedCodesByUserBetweenDates.size());

                LocalDate start = Date.valueOf(startDate).toLocalDate();
                LocalDate end = Date.valueOf(endDate).toLocalDate();

                System.out.println("period: " + period);
                System.out.println("start: " + start.toString());
                System.out.println("end: " + end.toString());
                List<LocalDate> dates = new ArrayList<>();

                if (period == 2){
                    for (LocalDate date = start; date.isBefore(end) || date.isEqual(end); date = date.plusMonths(1)){
                        dates.add(date);
                    }
                } else if (period == 1){
//                    System.out.println("dates: " + dates.size());
                    for (LocalDate date = start; date.isBefore(end) || date.isEqual(end); date = date.plusWeeks(1)){
                        dates.add(date);
                    }
                } else{
                    for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)){
                        dates.add(date);
                    }
                }

                XpCalculator calculator = new XpCalculator(userId, listOfCodePushedByUserBetweenDates, reviewedCodesByUserBetweenDates);

                UserDTOWithXPGains userWithXp = new UserDTOWithXPGains();
                userWithXp.setUserId(user.getUserId());
                userWithXp.setFirstName(user.getFirstName());
                userWithXp.setLastName(user.getLastName());
                userWithXp.setXpGainIntervalsList(new ArrayList<>());

                for (int i = 0; i < dates.size() - 1; i++){
                    LocalDate intervalStart = dates.get(i);
                    LocalDate intervalEnd = dates.get(i + 1);
                    // TODO:: Change date string format in response
                    userWithXp.getXpGainIntervalsList().add(
                            new UserXpGainIntervalResponse(calculator.calculateXpGainBetweenDates(intervalStart, intervalEnd),
                                    intervalStart.toString(), intervalEnd.toString())
                    );
                }
                listUserDTOWithXPGains.add(userWithXp);
            }
            return listUserDTOWithXPGains;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public class UserDTOWithXPGains extends UserDTO {
        private List<UserXpGainIntervalResponse> xpGainIntervalsList;

        public UserDTOWithXPGains() {
        }


        public UserDTOWithXPGains(List<UserXpGainIntervalResponse> xpGainIntervalsList) {
            this.xpGainIntervalsList = xpGainIntervalsList;
        }

        public UserDTOWithXPGains(int userId, String firstName, String lastName, String email, String photoPath, int positionId, int projectId, List<UserXpGainIntervalResponse> xpGainIntervalsList) {
            super(userId, firstName, lastName, email, photoPath, positionId, projectId);
            this.xpGainIntervalsList = xpGainIntervalsList;
        }

        public List<UserXpGainIntervalResponse> getXpGainIntervalsList() {
            return xpGainIntervalsList;
        }

        public void setXpGainIntervalsList(List<UserXpGainIntervalResponse> xpGainIntervalsList) {
            this.xpGainIntervalsList = xpGainIntervalsList;
        }


    }

    public class UserXpGainIntervalResponse {
        private int xpGained;
        private String dateFrom;
        private String dateTo;

        public UserXpGainIntervalResponse() {
        }

        public UserXpGainIntervalResponse(int xpGained, String dateFrom, String dateTo) {
            this.xpGained = xpGained;
            this.dateFrom = dateFrom;
            this.dateTo = dateTo;
        }

        public int getXpGained() {
            return xpGained;
        }

        public void setXpGained(int xpGained) {
            this.xpGained = xpGained;
        }

        public String getDateFrom() {
            return dateFrom;
        }

        public void setDateFrom(String dateFrom) {
            this.dateFrom = dateFrom;
        }

        public String getDateTo() {
            return dateTo;
        }

        public void setDateTo(String dateTo) {
            this.dateTo = dateTo;
        }
    }

}
