/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accord.charm.data;

import com.accord.charm.model.Round;
import java.util.List;

/**
 *
 * @author laud.c.ochei
 */



public interface RoundDao {
        Round findByNo(Integer roundid);
	List<Round> findAll();
        void save(Round round);
        void update(Round round);
	void delete(Integer roundid);
        int RoundIDExists(Integer roundid);
        
}