package com.agileseven.codereviewserver.Utilities;

import com.agileseven.codereviewserver.DAO.AccountDAO;
import com.agileseven.codereviewserver.DAO.AccountDAOImpl;
import com.agileseven.codereviewserver.DAO.ProjectDAO;
import com.agileseven.codereviewserver.DAO.ProjectDAOImpl;
import com.agileseven.codereviewserver.DTO.CodeDTO;
import com.agileseven.codereviewserver.DTO.ProjectDTO;
import com.agileseven.codereviewserver.DTO.UserDTO;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Mahmoud AL NAJAR
 */
public abstract class BaseReminderService {
    private List<UserDTO> reminderRecipients;
    private ProjectDTO reminderProject;

    public BaseReminderService(ProjectDTO reminderProject){
        this.reminderProject = reminderProject;
        this.reminderRecipients = retrieveRecipientsList();
    }

    public List<UserDTO> getReminderRecipients() {
        return reminderRecipients;
    }
    
    private List<UserDTO> retrieveRecipientsList() {
        try {
            AccountDAO accountDAO = new AccountDAOImpl();
            int projectIdOfMembersToBeNotified = reminderProject.getProjectId();
            return accountDAO.getMembersOfProject(projectIdOfMembersToBeNotified);
        } catch (SQLException e){
            e.printStackTrace();

            return null;
        }
    }

    abstract public void sendReminder(ProjectDTO project);
    

}
