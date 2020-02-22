/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accord.charm.controller;

/**
 *
 * @author laud.c.ochei
 */

import com.accord.charm.exception.MessageException;
import com.accord.charm.model.Bar;
import com.accord.charm.service.BarService;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Laud.Ochei
 */

@RestController
@RequestMapping(value = "/bar")
public class BarController {
    
        private BarService barService;
	@Autowired
	public void setBarService(BarService barService) {
		this.barService = barService;
	}

	    
        // list page
        @RequestMapping(value = "/barlist", method=GET)
        public List<Bar> displayAllBar(Model model) {
            return barService.findAll();
        }
        
        //display a single record
        @RequestMapping(value = "/{barid}", method = RequestMethod.GET)
	public ResponseEntity<String> getBar(@PathVariable("barid") Integer barid) {
           
		Bar bar = barService.findByNo(barid);
		if (bar == null) {
			return new ResponseEntity("No record found for bar ID " + barid, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(bar, HttpStatus.OK);
        }
        
        
        
        //add a single record
        @RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
        public ResponseEntity<Void> adddBar(@RequestBody Bar bar, UriComponentsBuilder ucb) throws MessageException {   
            int orgidstatus = barService.BarIDExists(bar.getBarid());     
            if (orgidstatus > 0) { 
                throw new MessageException("Record already exist for Bar ID: " + bar.getBarid());
            }
            barService.saveBar(bar);
            HttpHeaders headers = new HttpHeaders();
            URI companyUri = ucb.path("/bar/").path(String.valueOf(bar.getBarid())).build().toUri();
            headers.setLocation(companyUri);
            headers.add("BarNo", String.valueOf(bar.getBarid()));
            ResponseEntity<Void> responseEntity = new ResponseEntity<Void> (headers, HttpStatus.CREATED);
            return responseEntity;
        }
        
        
        //update a single record
        @RequestMapping(value = "/update/{barid}", method = RequestMethod.PUT, headers = "Accept=application/json")
        public ResponseEntity<Void> updateBar(@PathVariable("barid") Integer barid, @RequestBody Bar bar) throws MessageException {
            Bar orgstatus = barService.findByNo(barid);
            if (orgstatus == null) {
                throw new MessageException("No record found for bar ID: " + bar.getBarid());
            } 
            barService.update(bar);
            String Msg ="Record updated for Bar ID: " + bar.getBarid();
            HttpHeaders headers = new HttpHeaders();
            headers.add("SuccessMsg", Msg);
            ResponseEntity<Void> responseEntity = new ResponseEntity<Void> (headers, HttpStatus.CREATED);
            //return responseEntity;
            throw new MessageException("Record updated for bar ID: " + bar.getBarid());
        }
        
        
        //delete a single record
        @RequestMapping(value = "/delete/{barid}", method = RequestMethod.GET)
        public ResponseEntity<Bar>  deleteBar(@PathVariable("barid") Integer barid) throws MessageException {
            Bar bar = barService.findByNo(barid);
            if (bar == null) {
                throw new MessageException("No record found for barid: " + barid);
            }  
            /*
            int orgidstatus = barService.checkForeignKey(bar.getBarid());     
            if (orgidstatus > 0) { 
                throw new MessageException("Record cannot be deleted: " + barid);
            }
            */
            barService.delete(barid);
            throw new MessageException("Record deleted for bar ID: " + bar.getBarid());
        } 
        
        @ExceptionHandler(MessageException.class)
	public ResponseEntity<com.accord.charm.model.UserMessage> exceptionMsgHandler(Exception ex) {
		com.accord.charm.model.UserMessage msg = new com.accord.charm.model.UserMessage();
		msg.setMessage(ex.getMessage());
		return new ResponseEntity<com.accord.charm.model.UserMessage>(msg, HttpStatus.OK);
	}
        
        
    
}
