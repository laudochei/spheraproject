/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accord.charm.service;

import com.accord.charm.data.ProductDao;
import com.accord.charm.model.Product;
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


import com.accord.charm.model.Product;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("productService")
public class ProductServiceImpl implements ProductService {
	ProductDao productDao;

	@Autowired
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public Product findByNo(Integer productid) {
		return productDao.findByNo(productid);
	}
        
        @Override
	public List<Product> findAll() {
		return productDao.findAll();
	}
        
        @Override
	public void save(Product product) {
            productDao.save(product);
	}
       
        @Override
        public int ProductIDExists(Integer productid) {
            return productDao.ProductIDExists(productid);
        }
        
        
        @Override
        public int ProductNameExists(String name) {
            return productDao.ProductNameExists(name);
        }
        
        @Override
	public void update(Product product) {
            productDao.update(product);
	}
        
        
        @Override
        public void delete(Integer productid) {    
            productDao.delete(productid);
        }
           
}
