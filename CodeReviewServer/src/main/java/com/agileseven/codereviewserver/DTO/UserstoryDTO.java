/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereviewserver.DTO;

/**
 *
 * @author S
 */
public class UserstoryDTO {

    private String description;
    private int projectId;
    private String title;
    private String userstoryId;
    private ProjectDTO projectDTO;

    public UserstoryDTO(String description, int projectid, String title, String userstoryid) {
        this.description = description;
        this.projectId = projectid;
        this.title = title;
        this.userstoryId = userstoryid;
        this.projectDTO = projectDTO;
    }

    

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
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
        return projectId;
    }

    /**
     * @param projectid the projectid to set
     */
    public void setProjectid(int projectid) {
        this.projectId = projectid;
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
        return userstoryId;
    }

    /**
     * @param userstoryid the userstoryid to set
     */
    public void setUserstoryid(String userstoryid) {
        this.userstoryId = userstoryid;
    }

}
