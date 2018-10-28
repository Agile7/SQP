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
public class ReviewAnnotationDTO {

    private int annotId;
    private String annotText;
    private int reviewId;
    private int ruleId;
    private ReviewDTO reviewDTO;

    public ReviewAnnotationDTO(int annotid, String annottext, int reviewid, int ruleid, ReviewDTO reviewDTO) {
        this.annotId = annotid;
        this.annotText = annottext;
        this.reviewId = reviewid;
        this.ruleId = ruleid;
        this.reviewDTO = reviewDTO;
    }

    public int getAnnotId() {
        return annotId;
    }

    public String getAnnotText() {
        return annotText;
    }

    public int getReviewId() {
        return reviewId;
    }

    public int getRuleId() {
        return ruleId;
    }

    public ReviewDTO getReviewDTO() {
        return reviewDTO;
    }

    public void setAnnotId(int annotId) {
        this.annotId = annotId;
    }

    public void setAnnotText(String annotText) {
        this.annotText = annotText;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public void setRuleId(int ruleId) {
        this.ruleId = ruleId;
    }

    public void setReviewDTO(ReviewDTO reviewDTO) {
        this.reviewDTO = reviewDTO;
    }

    

   


}
