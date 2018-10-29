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
    private String ruleId;
    private ReviewDTO reviewDTO;
    private RuleDTO ruleDTO;
    private int lineNumber;

    public ReviewAnnotationDTO(int annotid, String annottext, int reviewid, String ruleid, ReviewDTO reviewDTO, RuleDTO ruleDTO, int lineNumber) {
        this.annotId = annotid;
        this.annotText = annottext;
        this.reviewId = reviewid;
        this.ruleId = ruleid;
        this.reviewDTO = reviewDTO;
        this.ruleDTO = ruleDTO;
        this.lineNumber = lineNumber;
    }

    public ReviewAnnotationDTO() {
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

    public String getRuleId() {
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

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public void setReviewDTO(ReviewDTO reviewDTO) {
        this.reviewDTO = reviewDTO;
    }

    public RuleDTO getRuleDTO() {
        return ruleDTO;
    }

    public void setRuleDTO(RuleDTO ruleDTO) {
        this.ruleDTO = ruleDTO;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

}
