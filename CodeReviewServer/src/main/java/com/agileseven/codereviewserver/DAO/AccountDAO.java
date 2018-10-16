/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereviewserver.DAO;

import com.agileseven.codereviewserver.DTO.UserDTO;

import java.sql.SQLException;
import java.util.List;

/**
 * This interface contains the methods to 
 * - get the list of projects
 * - get the list of user stories
 * - get the list of users
 * 
 * @author vilosh_na
 * @version 1.0
 * @date created : 13.10.2018
 */
public interface AccountDAO {
    public UserDTO getUserById(int userId) throws SQLException;
    public List<UserDTO> getMembersOfProject(int projectId) throws SQLException;
}
