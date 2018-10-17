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

    private int positionid;
    private String role;

    public PositionDTO(int positionid, String role) {
        this.positionid = positionid;
        this.role = role;
    }

    public PositionDTO() {
        
    }

    /**
     * @return the positionid
     */
    public int getPositionid() {
        return positionid;
    }

    /**
     * @param positionid the positionid to set
     */
    public void setPositionid(int positionid) {
        this.positionid = positionid;
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
