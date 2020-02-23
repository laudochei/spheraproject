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
public class Ordered {
    
    private Integer orderid;
    private Integer roundid;
    private Integer productid;
    private double actualprice;
    
    public boolean isNew() {
		return (this.orderid == null);
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getRoundid() {
        return roundid;
    }

    public void setRoundid(Integer roundid) {
        this.roundid = roundid;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public double getActualprice() {
        return actualprice;
    }

    public void setActualprice(double actualprice) {
        this.actualprice = actualprice;
    }
    
    
    
    
    
}
