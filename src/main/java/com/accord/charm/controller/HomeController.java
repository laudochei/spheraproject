/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accord.charm.controller;

import com.accord.charm.model.Employee;
import com.accord.charm.repository.EmployeeRepository;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

import com.accord.charm.model.Message;
import com.accord.charm.repository.MessageRepository;



/**
 *
 * @author laud.c.ochei
 */

@Controller
// @RequestMapping("/")
public class HomeController {
        
        @Autowired
        private MessageRepository messageRepository;

/*
        @GetMapping("/home")
	@RequestMapping(method=RequestMethod.GET)
	public String home(Map<String,Object>model){
            List<Employee> employees = employeeRepository.findAll();
            model.put("employees", employees);
            return "userhome";
	}
        
  */     
        
        @GetMapping("/home")
        public String userhome(Model model) {
            model.addAttribute("msgs", messageRepository.findAll());
            return "userhome";
        }


    
        
        @PostMapping("/messages")
        public String saveMessage(Message message) {
            messageRepository.save(message);
            return "redirect:/home";
        }

    
    
}
