/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accord.charm.model;

/**
 *
 * @author laud.c.ochei
 */
public class Round {
    private Integer roundid;
    private Integer barid;
    private String orderedat;
    
    public boolean isNew() {
		return (this.roundid == null);
    }

    public Integer getRoundid() {
        return roundid;
    }

    public void setRoundid(Integer roundid) {
        this.roundid = roundid;
    }

    public Integer getBarid() {
        return barid;
    }

    public void setBarid(Integer barid) {
        this.barid = barid;
    }

    public String getOrderedat() {
        return orderedat;
    }

    public void setOrderedat(String orderedat) {
        this.orderedat = orderedat;
    }
    
  
     
}
