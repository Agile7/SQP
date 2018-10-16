/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereviewserver.DAO;

import com.agileseven.codereviewserver.DTO.UserDTO;
import com.agileseven.codereviewserver.DTO.UserstoryDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the methods of AccountDAO interface
 * 
 * @author vilosh_na
 * @version 1.0
 * @date created : 13.10.2018
 */
public class AccountDAOImpl implements AccountDAO {

    Connection connection = ConnectionFactory.getConnection();

    @Override
    public UserDTO getUserById(int userId) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE user_id = ?;");
        statement.setInt(1, userId);

        ResultSet resultSet = statement.executeQuery();
        resultSet.next();

        UserDTO user = new UserDTO();
        user.setUserId(resultSet.getInt(1));
        user.setFirstName(resultSet.getString(2));
        user.setLastName(resultSet.getString(3));
        user.setEmail(resultSet.getString(4));
        user.setPhoto(resultSet.getString(5));
        user.setPositionId(resultSet.getInt(6));
        user.setProjectId(resultSet.getInt(7));

        return user;
    }

    @Override
    public List<UserDTO> getMembersOfProject(int projectId) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE project_id = ?;");
        statement.setInt(1, projectId);
        ResultSet resultSet = statement.executeQuery();

        List<UserDTO> users = new ArrayList<>();
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
