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

    private int codeidrrrrrr;
    private String codetext;
    private String comment;
    private int numlines;
    private Date pushdate;
    private int userid;
    private char userstoryid;

    public CodeDTO(int codeid, String codetext, String comment, int numlines, Date pushdate, int userid, char userstoryid) {
        this.codeid = codeid;
        this.codetext = codetext;
        this.comment = comment;
        this.numlines = numlines;
        this.pushdate = pushdate;
        this.userid = userid;
        this.userstoryid = userstoryid;
    }

    public CodeDTO() {
        this.codeid = codeid;
        this.codetext = codetext;
        this.comment = comment;
        this.numlines = numlines;
        this.pushdate = pushdate;
        this.userid = userid;
        this.userstoryid = userstoryid;
    }

    /**
     * @return the codeid
     */
    public int getCodeid() {
        return codeid;
    }

    /**
     * @param codeid the codeid to set
     */
    public void setCodeid(int codeid) {
        this.codeid = codeid;
    }

    /**
     * @return the codetext
     */
    public String getCodetext() {
        return codetext;
    }

    /**
     * @param codetext the codetext to set
     */
    public void setCodetext(String codetext) {
        this.codetext = codetext;
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
    public int getNumlines() {
        return numlines;
    }

    /**
     * @param numlines the numlines to set
     */
    public void setNumlines(int numlines) {
        this.numlines = numlines;
    }

    /**
     * @return the pushdate
     */
    public Date getPushdate() {
        return pushdate;
    }

    /**
     * @param pushdate the pushdate to set
     */
    public void setPushdate(Date pushdate) {
        this.pushdate = pushdate;
    }

    /**
     * @return the userid
     */
    public int getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(int userid) {
        this.userid = userid;
    }

    /**
     * @return the userstoryid
     */
    public int getUserstoryid() {
        return userstoryid;
    }

    /**
     * @param userstoryid the userstoryid to set
     */
    public void setUserstoryid(char userstoryid) {
        this.userstoryid = userstoryid;
    }

}
