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

    private String ruleid;
    private String ruletext;

    public RuleDTO(String ruleid, String ruletext) {
        this.ruleid = ruleid;
        this.ruletext = ruletext;
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
