/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accord.charm.service;

import com.accord.charm.data.BarDao;

/**
 *
 * @author laud.c.ochei
 */


import com.accord.charm.model.Bar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("barService")
public class BarServiceImpl implements BarService {
	BarDao barDao;

	@Autowired
	public void setBarDao(BarDao barDao) {
		this.barDao = barDao;
	}

	@Override
	public Bar findByNo(Integer barid) {
		return barDao.findByNo(barid);
	}
        
        @Override
	public List<Bar> findAll() {
		return barDao.findAll();
	}
        
        @Override
	public void saveBar(Bar bar) {
            barDao.saveBar(bar);
	}
       
        @Override
        public int BarIDExists(Integer barid) {
            return barDao.BarIDExists(barid);
        }
        
        
        @Override
        public int BarNameExists(String name) {
            return barDao.BarNameExists(name);
        }
        
        @Override
	public void update(Bar bar) {
            barDao.update(bar);
	}
        
        
        @Override
        public void delete(Integer barid) {    
            barDao.delete(barid);
        }
           
}
