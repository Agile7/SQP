package com.agileseven.codereviewserver.Controller;

import com.agileseven.codereviewserver.DAO.UserStoryDAO;
import com.agileseven.codereviewserver.DAO.UserStoryDAOImpl;
import com.agileseven.codereviewserver.DTO.UserstoryDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Mahmoud AL NAJAR
 */
@RestController
@RequestMapping(path="/CodeReviewer")
public class ProjectController {

    private UserStoryDAO userStoryDAO = new UserStoryDAOImpl();

    @RequestMapping(path = "/userStories", method= RequestMethod.GET, produces = "application/json")
    public List<UserstoryDTO> userStories(){

        try {
            return userStoryDAO.getAllUserStories();
        } catch (Exception e){
            return null;
        }

    }

}
