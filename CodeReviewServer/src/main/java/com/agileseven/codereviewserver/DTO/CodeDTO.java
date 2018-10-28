/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereviewserver.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

/**
 *
 * @author S
 */
public class CodeDTO {

    private int codeId;
    private String codeText;
    private String comment;
    private int numLines;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date pushDate;
    private int userId;
    private String userStoryId;
    private UserstoryDTO userStory;
    private UserDTO user;
    private String status;

    public UserstoryDTO getUserStory() {
        return userStory;
    }

    public void setUserStory(UserstoryDTO userStoryDTO) {
        this.userStory = userStoryDTO;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO userDTO) {
        this.user = userDTO;
    }

    public CodeDTO(int codeid, String codetext, String comment, int numlines, Date pushdate, int userid, String userstoryid, String status) {
        this.codeId = codeid;
        this.codeText = codetext;
        this.comment = comment;
        this.numLines = numlines;
        this.pushDate = pushdate;
        this.userId = userid;
        this.userStoryId = userstoryid;
        this.status = status;

    }

    public CodeDTO() {

    }

    /**
     * @return the codeid
     */
    public int getCodeId() {
        return codeId;
    }

    /**
     * @param codeid the codeid to set
     */
    public void setCodeId(int codeid) {
        this.codeId = codeid;
    }

    /**
     * @return the codetext
     */
    public String getCodeText() {
        return codeText;
    }

    /**
     * @param codetext the codetext to set
     */
    public void setCodeText(String codetext) {
        this.codeText = codetext;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the numlines
     */
    public int getNumLines() {
        return numLines;
    }

    /**
     * @param numlines the numlines to set
     */
    public void setNumLines(int numlines) {
        this.numLines = numlines;
    }

    /**
     * @return the pushdate
     */
    public Date getPushDate() {
        return pushDate;
    }

    /**
     * @param pushdate the pushdate to set
     */
    public void setPushDate(Date pushdate) {
        this.pushDate = pushdate;
    }

    /**
     * @return the userid
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserId(int userid) {

        this.userId = userid;
    }

    /**
     * @return the userstoryid
     */
    public String getUserStoryId() {
        return userStoryId;
    }

    /**
     * @param userstoryid the userstoryid to set
     */
    public void setUserStoryId(String userstoryid) {
        this.userStoryId = userstoryid;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the status
     */
    /**
     * @return the Status
     */
}
