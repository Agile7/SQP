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
public class RuleDTO {

    private int ruleId;
    private String ruleText;

    public RuleDTO(int ruleid, String ruletext) {
        this.ruleId = ruleid;
        this.ruleText = ruletext;
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

}