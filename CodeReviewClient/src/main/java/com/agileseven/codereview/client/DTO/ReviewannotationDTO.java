/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereview.client.DTO;

/**
 *
 * @author S
 */
public class ReviewannotationDTO {

    private int annotid;
    private String annottext;
    private int reviewid;
    private int ruleid;

    public ReviewannotationDTO(int annotid, String annottext, int reviewid, int ruleid) {
        this.annotid = annotid;
        this.annottext = annottext;
        this.reviewid = reviewid;
        this.ruleid = ruleid;
    }

   

    /**
     * @return the annotid
     */
    public int getAnnotid() {
        return annotid;
    }

    /**
     * @param annotid the annotid to set
     */
    public void setAnnotid(int annotid) {
        this.annotid = annotid;
    }

    /**
     * @return the annottext
     */
    public String getAnnottext() {
        return annottext;
    }

    /**
     * @param annottext the annottext to set
     */
    public void setAnnottext(String annottext) {
        this.annottext = annottext;
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
     * @return the ruleid
     */
    public int getRuleid() {
        return ruleid;
    }

    /**
     * @param ruleid the ruleid to set
     */
    public void setRuleid(int ruleid) {
        this.ruleid = ruleid;
    }

}
