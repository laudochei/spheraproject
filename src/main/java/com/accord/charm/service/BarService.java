/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accord.charm.service;

import com.accord.charm.model.Bar;
import java.util.List;

/**
 *
 * @author Laud.Ochei
 */
public interface BarService {
        Bar findByNo(Integer barid);
	List<Bar> findAll();
        void saveBar(Bar bar);
        void update(Bar bar);
	void delete(Integer barid);
        int BarIDExists(Integer barid);
        int BarNameExists(String name);
}
