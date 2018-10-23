/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereviewserver.Controller;

import com.agileseven.codereviewserver.DAO.AccountDAO;
import com.agileseven.codereviewserver.DAO.AccountDAOImpl;
import com.agileseven.codereviewserver.DTO.UserDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class maps the URL to the methods for login and
 * project details.
 * @author vilosh_na
 * @version 1.0
 * @date created : 13.10.2018
 */

@RestController
@RequestMapping(path="/CodeReviewer")
public class AccountController {
    private AccountDAO accountDAO = new AccountDAOImpl();
   
    
    @RequestMapping(path = "/user/{userId}", method=RequestMethod.GET)
    public UserDTO getUserById(@PathVariable int userId){
        try {
            UserDTO user = accountDAO.getUserById(userId);
            return user;
        } catch (Exception e){
            return null;
        }
    }
    
    
}
