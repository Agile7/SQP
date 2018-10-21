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
    }

    /**
     * @return the projectid
     */
    public int getProjectId() {
        return projectId;
    }

    /**
     * @param projectid the projectid to set
     */
    public void setProjectId(int projectid) {
        this.projectId = projectid;
    }

    /**
     * @return the projectname
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * @param projectname the projectname to set
     */
    public void setProjectName(String projectname) {
        this.projectName = projectname;
    }

}
