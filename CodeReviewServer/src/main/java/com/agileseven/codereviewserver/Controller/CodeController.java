/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereviewserver.Controller;

import com.agileseven.codereviewserver.DAO.CodeDAO;
import com.agileseven.codereviewserver.DAO.CodeDAOImpl;
import com.agileseven.codereviewserver.DTO.CodeDTO;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class maps the URL to the methods to 
 * perform operations on the code.
 * 
 * @author vilosh_na
 * @version 1.0
 * @date created : 13.10.2018
 */

@RestController
@RequestMapping(path="/CodeReviewer")
public class CodeController {
    
    CodeDAO codeDAO = new CodeDAOImpl();
    
    @RequestMapping(path = "/pushCode", method=RequestMethod.GET)
    public int pushCode(){
             
         return -1;
    }
    
    @RequestMapping(path = "/codes/unreviewed", method=RequestMethod.GET)
    public ArrayList<CodeDTO> getUnreadCode(){
                     
         return codeDAO.getUnreadCodes();
}
    
}
