/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereviewserver.DAO;

import com.agileseven.codereviewserver.DTO.ProjectDTO;
import com.agileseven.codereviewserver.DTO.UserDTO;
import java.sql.Connection;
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
    public ProjectDTO getProjectById(String id) {
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
    
}