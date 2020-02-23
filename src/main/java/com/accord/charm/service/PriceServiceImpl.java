/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accord.charm.service;

import com.accord.charm.data.PriceDao;
import com.accord.charm.model.Price;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author laud.c.ochei
 */


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author laud.c.ochei
 */


import com.accord.charm.model.Price;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("priceService")
public class PriceServiceImpl implements PriceService {
	PriceDao priceDao;

	@Autowired
	public void setPriceDao(PriceDao priceDao) {
		this.priceDao = priceDao;
	}

	@Override
	public Price findByNo(Integer priceid) {
		return priceDao.findByNo(priceid);
	}
        
        @Override
	public List<Price> findAll() {
		return priceDao.findAll();
	}
        
        @Override
	public void save(Price price) {
            priceDao.save(price);
	}
       
        @Override
        public int PriceIDExists(Integer priceid) {
            return priceDao.PriceIDExists(priceid);
        }
        
        
        
        
        @Override
	public void update(Price price) {
            priceDao.update(price);
	}
        
        
        @Override
        public void delete(Integer priceid) {    
            priceDao.delete(priceid);
        }
           
}
