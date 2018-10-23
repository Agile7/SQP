/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereviewserver.DAO;

import com.agileseven.codereviewserver.DTO.ProjectDTO;
import com.agileseven.codereviewserver.DTO.UserDTO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ckcen
 */
public interface ProjectDAO {
    public ArrayList<ProjectDTO> getProjectList();
    public ProjectDTO getProjectById(int id);
    public ArrayList<UserDTO> getUsersByProject(int projectId) throws SQLException;
}