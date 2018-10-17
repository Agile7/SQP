/*
 * To change this license header, choose License Headers in ProjectDTO Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereviewserver.DTO;

/**
 *
 * @author S
 */
public class ProjectDTO {

    private int projectId;
    private String projectName;

    public ProjectDTO(int projectid, String projectname) {
        this.projectId = projectid;
        this.projectName = projectname;
    }

   

    public ProjectDTO() {
        this.projectId = projectId;
        this.projectName = projectName;
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
     * @return the projectname
     */
    public String getProjectname() {
        return projectName;
    }

    /**
     * @param projectname the projectname to set
     */
    public void setProjectname(String projectname) {
        this.projectName = projectname;
    }

}
