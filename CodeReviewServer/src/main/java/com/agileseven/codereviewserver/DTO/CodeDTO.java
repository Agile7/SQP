/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereviewserver.DTO;

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
    private Date pushDate;
    private int userId;
    private String userStoryId;
    private UserstoryDTO userStoryDTO;
    private UserDTO userDTO;

    public UserstoryDTO getUserStoryDTO() {
        return userStoryDTO;
    }

    public void setUserStoryDTO(UserstoryDTO userStoryDTO) {
        this.userStoryDTO = userStoryDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public CodeDTO(int codeid, String codetext, String comment, int numlines, Date pushdate, int userid, String userstoryid) {
        this.codeId = codeid;
        this.codeText = codetext;
        this.comment = comment;
        this.numLines = numlines;
        this.pushDate = pushdate;
        this.userId = userid;
        this.userStoryId = userstoryid;
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

}
