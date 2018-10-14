/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereviewserver.DTO;

import java.awt.Image;

/**
 *
 * @author S
 */
public class UserDTO {

    private String email;
    private String fname;
    private String lastname;
    private Image photo;
    private int positionid;
    private int projectid;
    private int userid;

    public UserDTO(String email, String fname, String lastname, Image photo, int positionid, int projectid, int userid) {
        this.email = email;
        this.fname = fname;
        this.lastname = lastname;
        this.photo = photo;
        this.positionid = positionid;
        this.projectid = projectid;
        this.userid = userid;
    }

   

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the fname
     */
    public String getFname() {
        return fname;
    }

    /**
     * @param fname the fname to set
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the photo
     */
    public Image getPhoto() {
        return photo;
    }

    /**
     * @param photo the photo to set
     */
    public void setPhoto(Image photo) {
        this.photo = photo;
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
     * @return the userid
     */
    public int getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(int userid) {
        this.userid = userid;
    }

}
