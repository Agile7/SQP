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
public class ProjectAssignDTO {

    private int projectId;
    private int userId;
    private UserDTO userDTO;

    public ProjectAssignDTO(int projectid, int userid, UserDTO userDTO) {
        this.projectId = projectid;
        this.userId = userid;
        this.userDTO = userDTO;
    }

    

    public ProjectAssignDTO() {
        this.projectId = projectId;
        this.userId = userId;
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
     * @return the userid
     */
    public int getUserid() {
        return userId;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(int userid) {
        this.userId = userid;
    }

}
