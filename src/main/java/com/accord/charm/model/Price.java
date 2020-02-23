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
public class Price {
    private Integer priceid;
    private Integer barid;
    private Integer productid;
    private double currentprice;
    
    public boolean isNew() {
		return (this.priceid == null);
    }

    public Integer getPriceid() {
        return priceid;
    }

    public void setPriceid(Integer priceid) {
        this.priceid = priceid;
    }

    public Integer getBarid() {
        return barid;
    }

    public void setBarid(Integer barid) {
        this.barid = barid;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public double getCurrentprice() {
        return currentprice;
    }

    public void setCurrentprice(double currentprice) {
        this.currentprice = currentprice;
    }

    
    
    
    
}
