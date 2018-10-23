/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereviewserver.DAO;

import com.agileseven.codereviewserver.DTO.ProjectDTO;
import com.agileseven.codereviewserver.DTO.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ckcen
 */
public class ProjectDAOImpl implements ProjectDAO{

    @Override
    public ArrayList<ProjectDTO> getProjectList() {
        ArrayList<ProjectDTO> projectList = new ArrayList<ProjectDTO>();
        
        Connection con = ConnectionFactory.getConnection();
        ResultSet rs;
        Statement st;
        String query = "SELECT * FROM project";
        
        try {
            
            st = con.createStatement();
            rs = st.executeQuery(query);

            while (rs.next() == true) {
                int projectid = rs.getInt(1);
                String projectname = rs.getString(2);
                
                ProjectDTO project = new ProjectDTO(projectid, projectname);
                projectList.add(project);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return projectList;
    }
    
    @Override
    public ProjectDTO getProjectById(int id) {
        ProjectDTO project = new ProjectDTO();
        
        Connection con = ConnectionFactory.getConnection();
        ResultSet rs;
        Statement st;
        String query = "SELECT * FROM project"
                        + "WHERE project_id = " + id;
        
        try {
            
            st = con.createStatement();
            rs = st.executeQuery(query);

            while (rs.next() == true) {
                int projectid = rs.getInt(1);
                String projectname = rs.getString(2);
                project.setProjectId(projectid);
                project.setProjectName(projectname);
                
                
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return project;
    }
    
    @Override
    public ArrayList<UserDTO> getUsersByProject(int projectId) throws SQLException {
        
        Connection con = ConnectionFactory.getConnection();

        String query = "SELECT u.* from ";
        PreparedStatement statement = con.prepareStatement("SELECT * FROM user u  WHERE project_id = ?");
        statement.setInt(1, projectId);
        ResultSet resultSet = statement.executeQuery();

        ArrayList<UserDTO> users = new ArrayList<>();
        while(resultSet.next()){
            int userId = resultSet.getInt(1);
            String firstName = resultSet.getString(2);
            String lastName = resultSet.getString(3);
            String email = resultSet.getString(4);
            String photoPath = resultSet.getString(5);
            int positionId = resultSet.getInt(6);
            int pId = resultSet.getInt(7);

            users.add(new UserDTO(userId, firstName, lastName, email, photoPath, positionId, pId));
        }
        return users;
    }
    
}