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

    private int ruleid;
    private String ruletext;

    public RuleDTO(int ruleid, String ruletext) {
        this.ruleid = ruleid;
        this.ruletext = ruletext;
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

    /**
     * @return the ruletext
     */
    public String getRuletext() {
        return ruletext;
    }

    /**
     * @param ruletext the ruletext to set
     */
    public void setRuletext(String ruletext) {
        this.ruletext = ruletext;
    }

}
