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
    private int codeId;
    private int reviewerId;
    private int reviewId;
    private Time startTime;
    private Time submitTime;
    private CodeDTO codeDTO;

    public ReviewDTO(int approved, int codeid, int reviewerid, int reviewid, Time starttime, Time submittime, CodeDTO codeDTO) {
        this.approved = approved;
        this.codeId = codeid;
        this.reviewerId = reviewerid;
        this.reviewId = reviewid;
        this.startTime = starttime;
        this.submitTime = submittime;
        this.codeDTO = codeDTO;
    }
   

    

    public ReviewDTO() {
        this.approved = approved;
        this.codeId = codeId;
        this.reviewerId = reviewerId;
        this.reviewId = reviewId;
        this.startTime = startTime;
        this.submitTime = submitTime;
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
        return codeId;
    }

    /**
     * @param codeid the codeid to set
     */
    public void setCodeid(int codeid) {
        this.codeId = codeid;
    }

    /**
     * @return the reviewerid
     */
    public int getReviewerid() {
        return reviewerId;
    }

    /**
     * @param reviewerid the reviewerid to set
     */
    public void setReviewerid(int reviewerid) {
        this.reviewerId = reviewerid;
    }

    /**
     * @return the reviewid
     */
    public int getReviewid() {
        return reviewId;
    }

    /**
     * @param reviewid the reviewid to set
     */
    public void setReviewid(int reviewid) {
        this.reviewId = reviewid;
    }

    /**
     * @return the starttime
     */
    public Time getStarttime() {
        return startTime;
    }

    /**
     * @param starttime the starttime to set
     */
    public void setStarttime(Time starttime) {
        this.startTime = starttime;
    }

    /**
     * @return the submittime
     */
    public Time getSubmittime() {
        return submitTime;
    }

    /**
     * @param submittime the submittime to set
     */
    public void setSubmittime(Time submittime) {
        this.submitTime = submittime;
    }

}
