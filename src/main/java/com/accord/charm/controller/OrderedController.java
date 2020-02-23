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


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.accord.charm.exception.MessageException;
import com.accord.charm.model.Ordered;
import com.accord.charm.service.OrderedService;

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
@RequestMapping(value = "/ordered")
public class OrderedController {
    
        private OrderedService orderedService;
	@Autowired
	public void setOrderedService(OrderedService orderedService) {
		this.orderedService = orderedService;
	}

	    
        // list page
        @RequestMapping(value = "/orderedlist", method=GET)
        public List<Ordered> displayAllOrdered(Model model) {
            return orderedService.findAll();
        }
        
        //display a single record
        @RequestMapping(value = "/{orderedid}", method = RequestMethod.GET)
	public ResponseEntity<String> getOrdered(@PathVariable("orderedid") Integer orderedid) {
           
		Ordered ordered = orderedService.findByNo(orderedid);
		if (ordered == null) {
			return new ResponseEntity("No record found for ordered ID " + orderedid, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(ordered, HttpStatus.OK);
        }
        
        
        
        //add a single record
        @RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
        public ResponseEntity<Void> adddOrdered(@RequestBody Ordered ordered, UriComponentsBuilder ucb) throws MessageException {   
            int orgidstatus = orderedService.OrderedIDExists(ordered.getOrderid());     
            if (orgidstatus > 0) { 
                throw new MessageException("Record already exist for Ordered ID: " + ordered.getOrderid());
            }
            orderedService.save(ordered);
            HttpHeaders headers = new HttpHeaders();
            URI companyUri = ucb.path("/ordered/").path(String.valueOf(ordered.getOrderid())).build().toUri();
            headers.setLocation(companyUri);
            headers.add("OrderedNo", String.valueOf(ordered.getOrderid()));
            ResponseEntity<Void> responseEntity = new ResponseEntity<Void> (headers, HttpStatus.CREATED);
            return responseEntity;
        }
        
        
        //update a single record
        @RequestMapping(value = "/update/{orderedid}", method = RequestMethod.PUT, headers = "Accept=application/json")
        public ResponseEntity<Void> updateOrdered(@PathVariable("orderedid") Integer orderedid, @RequestBody Ordered ordered) throws MessageException {
            Ordered orgstatus = orderedService.findByNo(orderedid);
            if (orgstatus == null) {
                throw new MessageException("No record found for ordered ID: " + ordered.getOrderid());
            } 
            orderedService.update(ordered);
            String Msg ="Record updated for Ordered ID: " + ordered.getOrderid();
            HttpHeaders headers = new HttpHeaders();
            headers.add("SuccessMsg", Msg);
            ResponseEntity<Void> responseEntity = new ResponseEntity<Void> (headers, HttpStatus.CREATED);
            //return responseEntity;
            throw new MessageException("Record updated for ordered ID: " + ordered.getOrderid());
        }
        
        
        //delete a single record
        @RequestMapping(value = "/delete/{orderedid}", method = RequestMethod.GET)
        public ResponseEntity<Ordered>  deleteOrdered(@PathVariable("orderedid") Integer orderedid) throws MessageException {
            Ordered ordered = orderedService.findByNo(orderedid);
            if (ordered == null) {
                throw new MessageException("No record found for orderedid: " + orderedid);
            }  
            /*
            int orgidstatus = orderedService.checkForeignKey(ordered.getOrderedid());     
            if (orgidstatus > 0) { 
                throw new MessageException("Record cannot be deleted: " + orderedid);
            }
            */
            orderedService.delete(orderedid);
            throw new MessageException("Record deleted for ordered ID: " + ordered.getOrderid());
        } 
        
        @ExceptionHandler(MessageException.class)
	public ResponseEntity<com.accord.charm.model.UserMessage> exceptionMsgHandler(Exception ex) {
		com.accord.charm.model.UserMessage msg = new com.accord.charm.model.UserMessage();
		msg.setMessage(ex.getMessage());
		return new ResponseEntity<com.accord.charm.model.UserMessage>(msg, HttpStatus.OK);
	}
        
        
    
}

