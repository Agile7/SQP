package com.agileseven.codereviewserver.DAO;

import com.agileseven.codereviewserver.DTO.UserstoryDTO;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Mahmoud AL NAJAR
 */
public interface UserStoryDAO {
    public List<UserstoryDTO> getAllUserStories() throws ClassNotFoundException, SQLException;
}
