/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereviewserver.DTO;

import java.sql.Time;

/**
 *
 * @author S
 */
public class VersionDTO {

    private int codeid;
    private String codetext;
    private Time datemodofied;
    private Time versionid;

    public VersionDTO(int codeid, String codetext, Time datemodofied, Time versionid) {
        this.codeid = codeid;
        this.codetext = codetext;
        this.datemodofied = datemodofied;
        this.versionid = versionid;
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
     * @return the datemodofied
     */
    public Time getDatemodofied() {
        return datemodofied;
    }

    /**
     * @param datemodofied the datemodofied to set
     */
    public void setDatemodofied(Time datemodofied) {
        this.datemodofied = datemodofied;
    }

    /**
     * @return the versionid
     */
    public Time getVersionid() {
        return versionid;
    }

    /**
     * @param versionid the versionid to set
     */
    public void setVersionid(Time versionid) {
        this.versionid = versionid;
    }

}
