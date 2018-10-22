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

    private int projectId;
    private String projectName;

    public ProjectDTO(int projectId, String projectName) {
        this.projectId = projectId;
        this.projectName = projectName;
    }

    public ProjectDTO() {
    }

    public int getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    
}
