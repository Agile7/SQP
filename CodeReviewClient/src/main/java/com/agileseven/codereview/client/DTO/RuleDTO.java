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
public class RuleDTO {

    private String ruleId;
    private String ruleText;

    public RuleDTO(String ruleid, String ruletext) {
        this.ruleId = ruleid;
        this.ruleText = ruletext;
    }

    public RuleDTO() {
    }
    
        

    /**
     * @return the ruleid
     */
    public String getRuleid() {
        return ruleId;
    }

    /**
     * @param ruleid the ruleid to set
     */
    public void setRuleid(String ruleid) {
        this.ruleId = ruleid;
    }

    /**
     * @return the ruletext
     */
    public String getRuletext() {
        return ruleText;
    }

    /**
     * @param ruletext the ruletext to set
     */
    public void setRuletext(String ruletext) {
        this.ruleText = ruletext;
    }

    @Override
    public String toString() {
        return ruleId + ": " + ruleText;
    }

}
