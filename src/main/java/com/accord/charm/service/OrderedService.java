/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accord.charm.service;

import com.accord.charm.model.Ordered;
import java.util.List;

/**
 *
 * @author laud.c.ochei
 */

public interface OrderedService {
        Ordered findByNo(Integer orderedid);
	List<Ordered> findAll();
        void save(Ordered ordered);
        void update(Ordered ordered);
	void delete(Integer orderedid);
        int OrderedIDExists(Integer orderedid);
        
}

