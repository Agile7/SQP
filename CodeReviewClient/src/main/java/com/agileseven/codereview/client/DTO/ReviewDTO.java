/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereviewserver.DTO;

import java.sql.Time;

/**
 *
 * @author S
 */
public class ReviewDTO {

    private int approved;
    private int codeid;
    private int reviewerid;
    private int reviewid;
    private Time starttime;
    private Time submittime;

    public ReviewDTO(int approved, int codeid, int reviewerid, int reviewid, Time starttime, Time submittime) {
        this.approved = approved;
        this.codeid = codeid;
        this.reviewerid = reviewerid;
        this.reviewid = reviewid;
        this.starttime = starttime;
        this.submittime = submittime;
    }

    public ReviewDTO() {
        this.approved = approved;
        this.codeid = codeid;
        this.reviewerid = reviewerid;
        this.reviewid = reviewid;
        this.starttime = starttime;
        this.submittime = submittime;
    }

    /**
     * @return the approved
     */
    public int getApproved() {
        return approved;
    }

    /**
     * @param approved the approved to set
     */
    public void setApproved(int approved) {
        this.approved = approved;
    }

    /**
     * @return the codeid
     */
    public int getCodeid() {
        return codeid;
    }

    /**
     * @param codeid the codeid to set
     */
    public void setCodeid(int codeid) {
        this.codeid = codeid;
    }

    /**
     * @return the reviewerid
     */
    public int getReviewerid() {
        return reviewerid;
    }

    /**
     * @param reviewerid the reviewerid to set
     */
    public void setReviewerid(int reviewerid) {
        this.reviewerid = reviewerid;
    }

    /**
     * @return the reviewid
     */
    public int getReviewid() {
        return reviewid;
    }

    /**
     * @param reviewid the reviewid to set
     */
    public void setReviewid(int reviewid) {
        this.reviewid = reviewid;
    }

    /**
     * @return the starttime
     */
    public Time getStarttime() {
        return starttime;
    }

    /**
     * @param starttime the starttime to set
     */
    public void setStarttime(Time starttime) {
        this.starttime = starttime;
    }

    /**
     * @return the submittime
     */
    public Time getSubmittime() {
        return submittime;
    }

    /**
     * @param submittime the submittime to set
     */
    public void setSubmittime(Time submittime) {
        this.submittime = submittime;
    }

}
