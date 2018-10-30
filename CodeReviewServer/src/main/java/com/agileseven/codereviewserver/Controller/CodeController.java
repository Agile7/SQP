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

import org.springframework.web.bind.annotation.*;

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
    public int pushCode(@RequestBody CodeDTO code) {
        try{
            int pushCodeResult = codeDAO.pushCodeToDB(code);
            // After the processing of the push code request,
            // a CodeDTO object of the new code must be created in order to send warning emails
            if(pushCodeResult > 0){
//                CodeDTO codeDTOForTesting = new CodeDTO();
//                //codeDTOForTesting.setCodeId(5); // CodeId = pushCodeResult;
//                codeDTOForTesting.setCodeId(pushCodeResult);
//                codeDTOForTesting.setUserId(10);
                EmailNotificationService emailNotificationService = new EmailNotificationService(code);

                emailNotificationService.sendNotification();
                return -1;
            }
            else{
                
            }
        }
        catch(Exception ex){
//            System.err.println("Got an exception!");
//            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return 0;
    }
    
    @RequestMapping(path = "/codes/test", method=RequestMethod.GET)
    public int test(){
        CodeDTO codeDTOForTesting = new CodeDTO();
        codeDTOForTesting.setCodeId(5);
        codeDTOForTesting.setUserId(10);
        codeDTOForTesting.setUserStoryId("10");
        EmailNotificationService emailNotificationService = new EmailNotificationService(codeDTOForTesting);

        emailNotificationService.sendNotification();
        return 5;
    }
    
    
    @RequestMapping(path = "/codes/unreviewed/{projectId}", method=RequestMethod.GET)
    public ArrayList<CodeDTO> getUnreadCode(@PathVariable int projectId){
        return codeDAO.getUnreadCodes(projectId);
    }
    
    @RequestMapping(path = "/codes/{codeId}", method=RequestMethod.GET)
    public CodeDTO getCode(@PathVariable int codeId){
        return codeDAO.getCodeById(codeId);
    }
    
    @RequestMapping(path = "/codes/status", method=RequestMethod.PUT)
    public int setCodeReading(@RequestParam(value="codeId", defaultValue="") int codeId,
                                                    @RequestParam(value="status", defaultValue="") int status)
    {
        return codeDAO.changeStatusOfCode(codeId, status);
    }
    
}
