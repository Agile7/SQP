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
public class ReviewannotationDTO {

    private int annotId;
    private String annotText;
    private int reviewId;
    private int ruleId;
    private ReviewDTO reviewDTO;

    public ReviewannotationDTO(int annotid, String annottext, int reviewid, int ruleid, ReviewDTO reviewDTO) {
        this.annotId = annotid;
        this.annotText = annottext;
        this.reviewId = reviewid;
        this.ruleId = ruleid;
        this.reviewDTO = reviewDTO;
    }

    

   

    /**
     * @return the annotid
     */
    public int getAnnotid() {
        return annotId;
    }

    /**
     * @param annotid the annotid to set
     */
    public void setAnnotid(int annotid) {
        this.annotId = annotid;
    }

    /**
     * @return the annottext
     */
    public String getAnnottext() {
        return annotText;
    }

    /**
     * @param annottext the annottext to set
     */
    public void setAnnottext(String annottext) {
        this.annotText = annottext;
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
     * @return the ruleid
     */
    public int getRuleid() {
        return ruleId;
    }

    /**
     * @param ruleid the ruleid to set
     */
    public void setRuleid(int ruleid) {
        this.ruleId = ruleid;
    }

}
