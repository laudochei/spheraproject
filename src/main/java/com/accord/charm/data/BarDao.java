/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accord.charm.data;


import com.accord.charm.model.Bar;
import java.util.List;

/**
 *
 * @author laud.c.ochei
 */


public interface BarDao {
        Bar findByNo(Integer barid);
	List<Bar> findAll();
        void saveBar(Bar bar);
        void update(Bar bar);
	void delete(Integer barid);
        int BarIDExists(Integer barid);
        int BarNameExists(String name);
}