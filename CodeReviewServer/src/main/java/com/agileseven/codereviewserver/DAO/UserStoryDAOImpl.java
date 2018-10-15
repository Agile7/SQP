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
    public List<UserstoryDTO> getAllUserStories() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:9000/code_review_db",
                "root","");
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM user_stories;");
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

}
