/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accord.charm.data;

import com.accord.charm.model.Price;
import java.util.List;

/**
 *
 * @author laud.c.ochei
 */

public interface PriceDao {
        Price findByNo(Integer priceid);
	List<Price> findAll();
        void save(Price price);
        void update(Price price);
	void delete(Integer priceid);
        int PriceIDExists(Integer priceid);
        
}
