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
public class PositionDTO {

    private int positionId;
    private String role;

    public PositionDTO(int positionid, String role) {
        this.positionId = positionid;
        this.role = role;
    }

    

    public PositionDTO() {
        this.positionId = positionId;
        this.role = role;
    }

    /**
     * @return the positionid
     */
    public int getPositionid() {
        return positionId;
    }

    /**
     * @param positionid the positionid to set
     */
    public void setPositionid(int positionid) {
        this.positionId = positionId;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

}