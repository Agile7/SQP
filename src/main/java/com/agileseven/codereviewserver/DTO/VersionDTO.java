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

    private int codeId;
    private String codeText;
    private Time dateModofied;
    private Time versionId;
    private CodeDTO codeDTO;

    public VersionDTO(int codeid, String codetext, Time datemodofied, Time versionid, CodeDTO codeDTO) {
        this.codeId = codeid;
        this.codeText = codetext;
        this.dateModofied = datemodofied;
        this.versionId = versionid;
        this.codeDTO = codeDTO;
    }

   

    /**
     * @return the codeid
     */
    public int getCodeid() {
        return codeId;
    }

    /**
     * @param codeid the codeid to set
     */
    public void setCodeid(int codeid) {
        this.codeId = codeid;
    }

    /**
     * @return the codetext
     */
    public String getCodetext() {
        return codeText;
    }

    /**
     * @param codetext the codetext to set
     */
    public void setCodetext(String codetext) {
        this.codeText = codetext;
    }

    /**
     * @return the datemodofied
     */
    public Time getDatemodofied() {
        return dateModofied;
    }

    /**
     * @param datemodofied the datemodofied to set
     */
    public void setDatemodofied(Time datemodofied) {
        this.dateModofied = datemodofied;
    }

    /**
     * @return the versionid
     */
    public Time getVersionid() {
        return versionId;
    }

    /**
     * @param versionid the versionid to set
     */
    public void setVersionid(Time versionid) {
        this.versionId = versionid;
    }

}
