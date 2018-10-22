/*
 * To change this license header, choose License Headers in ProjectDTO Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereview.client.DTO;

/**
 *
 * @author S
 */
public class ProjectDTO {

    private int projectid;
    private String projectname;

    public ProjectDTO(int projectid, String projectname) {
        this.projectid = projectid;
        this.projectname = projectname;
    }

    public ProjectDTO() {
        this.projectid = projectid;
        this.projectname = projectname;
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
     * @return the projectname
     */
    public String getProjectname() {
        return projectname;
    }

    /**
     * @param projectname the projectname to set
     */
    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

}
