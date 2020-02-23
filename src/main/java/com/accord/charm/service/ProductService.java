/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accord.charm.service;

import com.accord.charm.model.Product;
import java.util.List;

/**
 *
 * @author laud.c.ochei
 */


import com.accord.charm.model.Product;
import java.util.List;

/**
 *
 * @author Laud.Ochei
 */
public interface ProductService {
        Product findByNo(Integer productid);
	List<Product> findAll();
        void save(Product product);
        void update(Product product);
	void delete(Integer productid);
        int ProductIDExists(Integer productid);
        int ProductNameExists(String name);
}
