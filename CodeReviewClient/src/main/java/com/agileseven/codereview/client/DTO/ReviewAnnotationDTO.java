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
public class ReviewAnnotationDTO {

    private int annotid;
    private String annottext;
    private int reviewid;
    private String ruleid;
    private ReviewDTO reviewDTO;
    private RuleDTO ruleDTO;

    public ReviewAnnotationDTO(int annotid, String annottext, int reviewid, String ruleid, ReviewDTO reviewDTO, RuleDTO ruleDTO) {
        this.annotid = annotid;
        this.annottext = annottext;
        this.reviewid = reviewid;
        this.ruleid = ruleid;
        this.reviewDTO = reviewDTO;
        this.ruleDTO = ruleDTO;
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
    public String getRuleid() {
        return ruleid;
    }

    /**
     * @param ruleid the ruleid to set
     */
    public void setRuleid(String ruleid) {
        this.ruleid = ruleid;
    }

    public ReviewDTO getReviewDTO() {
        return reviewDTO;
    }

    public RuleDTO getRuleDTO() {
        return ruleDTO;
    }

    public void setReviewDTO(ReviewDTO reviewDTO) {
        this.reviewDTO = reviewDTO;
    }

    public void setRuleDTO(RuleDTO ruleDTO) {
        this.ruleDTO = ruleDTO;
    }
    
    

}
