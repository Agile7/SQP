package com.agileseven.codereviewserver.Utilities;

import com.agileseven.codereviewserver.DAO.AccountDAO;
import com.agileseven.codereviewserver.DAO.AccountDAOImpl;
import com.agileseven.codereviewserver.DTO.CodeDTO;
import com.agileseven.codereviewserver.DTO.UserDTO;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Mahmoud AL NAJAR
 */
public abstract class BaseNotificationService {

    private CodeDTO notificationSource;
    private List<UserDTO> notificationRecipients;

    public BaseNotificationService(CodeDTO notificationSource){
        this.notificationSource = notificationSource;
        this.notificationRecipients = retrieveRecipientsList();
        if (this.notificationRecipients != null) {
            this.notificationRecipients.remove(this.notificationRecipients.stream().
                    filter(u -> u.getUserId() == notificationSource.getUserId()).findFirst().get());
        }
    }

    public CodeDTO getNotificationSource() {
        return notificationSource;
    }

    public List<UserDTO> getNotificationRecipients() {
        return notificationRecipients;
    }

    private List<UserDTO> retrieveRecipientsList() {
        try {
            AccountDAO accountDAO = new AccountDAOImpl();
            int projectIdOfMembersToBeNotified = accountDAO.getUserById(notificationSource.getUserId()).getProjectId();

            return accountDAO.getMembersOfProject(projectIdOfMembersToBeNotified);
        } catch (SQLException e){
            e.printStackTrace();

            return null;
        }
    }

    abstract public void sendNotification();

}
