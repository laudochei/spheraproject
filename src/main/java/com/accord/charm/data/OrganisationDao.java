/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accord.charm.data;

import com.accord.charm.model.Organisation;
import java.util.List;

/**
 *
 * @author Laud.Ochei
 */




public interface OrganisationDao {
        Organisation findByNo(Integer orgno);
	List<Organisation> findAll();
        void saveOrganisation(Organisation organisation);
        void update(Organisation organisation);
	void delete(Integer orgno);
        int OrganIDExists(String orgid);
        int OrganNameExists(String orgname);
        int checkForeignKey(String orgid);
        
}