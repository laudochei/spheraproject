/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accord.charm.repository;

/**
 *
 * @author laud.c.ochei
 */
import org.springframework.data.jpa.repository.JpaRepository;

import com.accord.charm.model.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>{

}