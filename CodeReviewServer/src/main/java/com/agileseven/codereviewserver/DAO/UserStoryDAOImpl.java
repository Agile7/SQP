package com.agileseven.codereviewserver.DAO;

import com.agileseven.codereviewserver.DTO.UserstoryDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mahmoud AL NAJAR
 */
public class UserStoryDAOImpl implements UserStoryDAO {

    @Override
    public List<UserstoryDTO> getAllUserStories() throws SQLException {

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM user_story;");
        ResultSet resultSet = statement.executeQuery();

        List<UserstoryDTO> userStories = new ArrayList<>();
        while(resultSet.next()){
            String userStoryId = resultSet.getString(1);
            int projectId = resultSet.getInt(2);
            String description = resultSet.getString(3);
            String title = resultSet.getString(4);

            userStories.add(new UserstoryDTO(description, projectId, title, userStoryId));
        }
        return userStories;
    }

    @Override
    public List<UserstoryDTO> getAllUserStoriesOfProject(int projectId) throws ClassNotFoundException, SQLException {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM user_story WHERE project_id = ?;");
        statement.setInt(1, projectId);
        ResultSet resultSet = statement.executeQuery();

        List<UserstoryDTO> userStories = new ArrayList<>();
        while(resultSet.next()){
            String userStoryId = resultSet.getString(1);
            String description = resultSet.getString(3);
            String title = resultSet.getString(4);

            userStories.add(new UserstoryDTO(description, projectId, title, userStoryId));
        }
        System.out.println(userStories.size());
        return userStories;
    }

}
