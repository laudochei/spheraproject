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

import com.accord.charm.exception.MessageException;
import com.accord.charm.model.Product;
import com.accord.charm.service.ProductService;

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
@RequestMapping(value = "/product")
public class ProductController {
    
        private ProductService productService;
	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	    
        // list page
        @RequestMapping(value = "/productlist", method=GET)
        public List<Product> displayAllProduct(Model model) {
            return productService.findAll();
        }
        
        //display a single record
        @RequestMapping(value = "/{productid}", method = RequestMethod.GET)
	public ResponseEntity<String> getProduct(@PathVariable("productid") Integer productid) {
           
		Product product = productService.findByNo(productid);
		if (product == null) {
			return new ResponseEntity("No record found for product ID " + productid, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(product, HttpStatus.OK);
        }
        
        
        
        //add a single record
        @RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
        public ResponseEntity<Void> adddProduct(@RequestBody Product product, UriComponentsBuilder ucb) throws MessageException {   
            int orgidstatus = productService.ProductIDExists(product.getProductid());     
            if (orgidstatus > 0) { 
                throw new MessageException("Record already exist for Product ID: " + product.getProductid());
            }
            productService.save(product);
            HttpHeaders headers = new HttpHeaders();
            URI companyUri = ucb.path("/product/").path(String.valueOf(product.getProductid())).build().toUri();
            headers.setLocation(companyUri);
            headers.add("ProductNo", String.valueOf(product.getProductid()));
            ResponseEntity<Void> responseEntity = new ResponseEntity<Void> (headers, HttpStatus.CREATED);
            return responseEntity;
        }
        
        
        //update a single record
        @RequestMapping(value = "/update/{productid}", method = RequestMethod.PUT, headers = "Accept=application/json")
        public ResponseEntity<Void> updateProduct(@PathVariable("productid") Integer productid, @RequestBody Product product) throws MessageException {
            Product orgstatus = productService.findByNo(productid);
            if (orgstatus == null) {
                throw new MessageException("No record found for product ID: " + product.getProductid());
            } 
            productService.update(product);
            String Msg ="Record updated for Product ID: " + product.getProductid();
            HttpHeaders headers = new HttpHeaders();
            headers.add("SuccessMsg", Msg);
            ResponseEntity<Void> responseEntity = new ResponseEntity<Void> (headers, HttpStatus.CREATED);
            //return responseEntity;
            throw new MessageException("Record updated for product ID: " + product.getProductid());
        }
        
        
        //delete a single record
        @RequestMapping(value = "/delete/{productid}", method = RequestMethod.GET)
        public ResponseEntity<Product>  deleteProduct(@PathVariable("productid") Integer productid) throws MessageException {
            Product product = productService.findByNo(productid);
            if (product == null) {
                throw new MessageException("No record found for productid: " + productid);
            }  
            /*
            int orgidstatus = productService.checkForeignKey(product.getProductid());     
            if (orgidstatus > 0) { 
                throw new MessageException("Record cannot be deleted: " + productid);
            }
            */
            productService.delete(productid);
            throw new MessageException("Record deleted for product ID: " + product.getProductid());
        } 
        
        @ExceptionHandler(MessageException.class)
	public ResponseEntity<com.accord.charm.model.UserMessage> exceptionMsgHandler(Exception ex) {
		com.accord.charm.model.UserMessage msg = new com.accord.charm.model.UserMessage();
		msg.setMessage(ex.getMessage());
		return new ResponseEntity<com.accord.charm.model.UserMessage>(msg, HttpStatus.OK);
	}
        
        
    
}
