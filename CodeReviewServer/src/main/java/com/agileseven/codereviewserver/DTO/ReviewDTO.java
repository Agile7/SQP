/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereviewserver.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Date;

/**
 *
 * @author S
 */
public class ReviewDTO {

    private int approved;
    private int codeId;
    private int reviewerId;
    private int reviewId;
    private String startTime;
    private String submitTime;
    private CodeDTO code;

    public ReviewDTO(int approved, int codeid, int reviewerid, int reviewid, String starttime, String submittime, CodeDTO codeDTO) {
        this.approved = approved;
        this.codeId = codeid;
        this.reviewerId = reviewerid;
        this.reviewId = reviewid;
        this.startTime = starttime;
        this.submitTime = submittime;
        this.code = codeDTO;
    }
   

    

    public ReviewDTO() {
    
    }

    public int getApproved() {
        return approved;
    }

    public int getCodeId() {
        return codeId;
    }

    public int getReviewerId() {
        return reviewerId;
    }

    public int getReviewId() {
        return reviewId;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public CodeDTO getCode() {
        return code;
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }

    public void setCodeId(int codeId) {
        this.codeId = codeId;
    }

    public void setReviewerId(int reviewerId) {
        this.reviewerId = reviewerId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public void setCode(CodeDTO code) {
        this.code = code;
    }

    
}
