/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereviewserver.DAO;

import com.agileseven.codereviewserver.DTO.CodeDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This interface contains the methods
 * - to push code
 * - to get the list of codes
 * - to send notifications
 * 
 * @author vilosh_na
 * @version 1.0
 * @date created : 13.10.2018
 */
public interface CodeDAO {
    
    public ArrayList<CodeDTO> getUnreadCodes(int projectId);
    public int pushCodeToDB(CodeDTO code);
    public CodeDTO getCodeById(int codeId);
    public int changeStatusOfCode(int codeId, int status);

    public List<CodeDTO> getListOfCodePushedByUserBetweenDates(int userId, String startDate, String endDate) throws SQLException;
    
}
