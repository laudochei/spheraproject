/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accord.charm.controller;

import com.accord.charm.exception.MessageException;
import com.accord.charm.model.Price;
import com.accord.charm.service.PriceService;
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
 * @author laud.c.ochei
 */




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.accord.charm.exception.MessageException;
import com.accord.charm.model.Price;
import com.accord.charm.service.PriceService;

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
@RequestMapping(value = "/price")
public class PriceController {
    
        private PriceService priceService;
	@Autowired
	public void setPriceService(PriceService priceService) {
		this.priceService = priceService;
	}

	    
        // list page
        @RequestMapping(value = "/pricelist", method=GET)
        public List<Price> displayAllPrice(Model model) {
            return priceService.findAll();
        }
        
        //display a single record
        @RequestMapping(value = "/{priceid}", method = RequestMethod.GET)
	public ResponseEntity<String> getPrice(@PathVariable("priceid") Integer priceid) {
           
		Price price = priceService.findByNo(priceid);
		if (price == null) {
			return new ResponseEntity("No record found for price ID " + priceid, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(price, HttpStatus.OK);
        }
        
        
        
        //add a single record
        @RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
        public ResponseEntity<Void> adddPrice(@RequestBody Price price, UriComponentsBuilder ucb) throws MessageException {   
            int orgidstatus = priceService.PriceIDExists(price.getPriceid());     
            if (orgidstatus > 0) { 
                throw new MessageException("Record already exist for Price ID: " + price.getPriceid());
            }
            priceService.save(price);
            HttpHeaders headers = new HttpHeaders();
            URI companyUri = ucb.path("/price/").path(String.valueOf(price.getPriceid())).build().toUri();
            headers.setLocation(companyUri);
            headers.add("PriceNo", String.valueOf(price.getPriceid()));
            ResponseEntity<Void> responseEntity = new ResponseEntity<Void> (headers, HttpStatus.CREATED);
            return responseEntity;
        }
        
        
        //update a single record
        @RequestMapping(value = "/update/{priceid}", method = RequestMethod.PUT, headers = "Accept=application/json")
        public ResponseEntity<Void> updatePrice(@PathVariable("priceid") Integer priceid, @RequestBody Price price) throws MessageException {
            Price orgstatus = priceService.findByNo(priceid);
            if (orgstatus == null) {
                throw new MessageException("No record found for price ID: " + price.getPriceid());
            } 
            priceService.update(price);
            String Msg ="Record updated for Price ID: " + price.getPriceid();
            HttpHeaders headers = new HttpHeaders();
            headers.add("SuccessMsg", Msg);
            ResponseEntity<Void> responseEntity = new ResponseEntity<Void> (headers, HttpStatus.CREATED);
            //return responseEntity;
            throw new MessageException("Record updated for price ID: " + price.getPriceid());
        }
        
        
        //delete a single record
        @RequestMapping(value = "/delete/{priceid}", method = RequestMethod.GET)
        public ResponseEntity<Price>  deletePrice(@PathVariable("priceid") Integer priceid) throws MessageException {
            Price price = priceService.findByNo(priceid);
            if (price == null) {
                throw new MessageException("No record found for priceid: " + priceid);
            }  
            /*
            int orgidstatus = priceService.checkForeignKey(price.getPriceid());     
            if (orgidstatus > 0) { 
                throw new MessageException("Record cannot be deleted: " + priceid);
            }
            */
            priceService.delete(priceid);
            throw new MessageException("Record deleted for price ID: " + price.getPriceid());
        } 
        
        @ExceptionHandler(MessageException.class)
	public ResponseEntity<com.accord.charm.model.UserMessage> exceptionMsgHandler(Exception ex) {
		com.accord.charm.model.UserMessage msg = new com.accord.charm.model.UserMessage();
		msg.setMessage(ex.getMessage());
		return new ResponseEntity<com.accord.charm.model.UserMessage>(msg, HttpStatus.OK);
	}
        
        
    
}

