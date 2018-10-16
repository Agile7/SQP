/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereviewserver.Controller;

import com.agileseven.codereviewserver.DAO.CodeDAO;
import com.agileseven.codereviewserver.DAO.CodeDAOImpl;
import com.agileseven.codereviewserver.DTO.CodeDTO;
import com.agileseven.codereviewserver.Utilities.EmailNotificationService;

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
    
    private CodeDAO codeDAO = new CodeDAOImpl();
    
    @RequestMapping(path = "/code", method=RequestMethod.POST)
    public int pushCode(CodeDTO code) {
        try{
            // TODO: Implementation for receiving a push code request
            int pushCodeResult = codeDAO.pushCodeToDB(code);

            // After the processing of the push code request,
            // a CodeDTO object of the new code must be created in order to send warning emails
            if(pushCodeResult > 0){
                CodeDTO codeDTOForTesting = new CodeDTO();
                //codeDTOForTesting.setCodeId(5); // CodeId = pushCodeResult;
                codeDTOForTesting.setCodeId(pushCodeResult);
                codeDTOForTesting.setUserId(10);
                EmailNotificationService emailNotificationService = new EmailNotificationService(codeDTOForTesting);

                emailNotificationService.sendNotification();
                return -1;
            }
            else{
                
            }
        }
        catch(Exception ex){
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
        return 0;
    }
    
    @RequestMapping(path = "/codes/unreviewed", method=RequestMethod.GET)
    public ArrayList<CodeDTO> getUnreadCode(){
        return codeDAO.getUnreadCodes();
    }
    
}
