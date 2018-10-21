/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereview.client.DTO;

/**
 *
 * @author S
 */
public class UserstoryDTO {

    private String description;
    private int projectid;
    private String title;
    private String userstoryid;

    public UserstoryDTO(String description, int projectid, String title, String userstoryid) {
        this.description = description;
        this.projectid = projectid;
        this.title = title;
        this.userstoryid = userstoryid;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    public UserstoryDTO() {
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the projectid
     */
    public int getProjectid() {
        return projectid;
    }

    /**
     * @param projectid the projectid to set
     */
    public void setProjectid(int projectid) {
        this.projectid = projectid;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the userstoryid
     */
    public String getUserstoryid() {
        return userstoryid;
    }

    /**
     * @param userstoryid the userstoryid to set
     */
    public void setUserstoryid(String userstoryid) {
        this.userstoryid = userstoryid;
    }

}
