/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accord.charm.service;

import com.accord.charm.data.OrderedDao;
import com.accord.charm.model.Ordered;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author laud.c.ochei
 */




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import com.accord.charm.data.OrderedDao;
import com.accord.charm.model.Ordered;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


import com.accord.charm.model.Ordered;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("orderedService")
public class OrderedServiceImpl implements OrderedService {
	OrderedDao orderedDao;

	@Autowired
	public void setOrderedDao(OrderedDao orderedDao) {
		this.orderedDao = orderedDao;
	}

	@Override
	public Ordered findByNo(Integer orderedid) {
		return orderedDao.findByNo(orderedid);
	}
        
        @Override
	public List<Ordered> findAll() {
		return orderedDao.findAll();
	}
        
        @Override
	public void save(Ordered ordered) {
            orderedDao.save(ordered);
	}
       
        @Override
        public int OrderedIDExists(Integer orderedid) {
            return orderedDao.OrderedIDExists(orderedid);
        }
        
        
        
        @Override
	public void update(Ordered ordered) {
            orderedDao.update(ordered);
	}
        
        
        @Override
        public void delete(Integer orderedid) {    
            orderedDao.delete(orderedid);
        }
           
}
