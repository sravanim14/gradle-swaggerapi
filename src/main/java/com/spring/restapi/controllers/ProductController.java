/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.restapi.controllers;

import com.spring.restapi.models.Product;
import com.spring.restapi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author didin
 */
@Api(basePath = "swagger-demo/person", value = "Person", description = "Operations with person", produces = "application/json")
@RestController
public class ProductController {
    
    //@Autowired
    ProductRepository productRepository;
	
	@Autowired
    public void setRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/products")
    public Iterable<Product> product() {
        return productRepository.findAll();
    } 
    
    @RequestMapping(method=RequestMethod.POST, value="/products")
    public String save(@RequestBody Product product) {
        productRepository.save(product);

        return product.getId();
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/products/{id}")
    public Product show(@PathVariable String id) {
        return productRepository.findOne(id);
    }
    
    @RequestMapping(method=RequestMethod.PUT, value="/products/{id}")
    public Product update(@PathVariable String id, @RequestBody Product product) {
        Product prod = productRepository.findOne(id);
        if(product.getProdName() != null)
            prod.setProdName(product.getProdName());
        if(product.getProdDesc() != null)
            prod.setProdDesc(product.getProdDesc());
        if(product.getProdPrice() != null)
            prod.setProdPrice(product.getProdPrice());
        if(product.getProdImage() != null)
            prod.setProdImage(product.getProdImage());
        productRepository.save(prod);
        return prod;
    }
    
    @RequestMapping(method=RequestMethod.DELETE, value="/products/{id}")
    public String delete(@PathVariable String id) {
        Product product = productRepository.findOne(id);
        productRepository.delete(product);

        return "product deleted";
    }

   @RequestMapping(value = "swagger-demo/person", produces = MediaType.APPLICATION_JSON_VALUE)
   class PersonController {
 
    @RequestMapping(method = RequestMethod.POST, value = "/create")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ApiOperation(value = "Get Person", notes = "Fetch List of Person")
    @ApiResponses(value = 
            @ApiResponse(code = 400, message = "Fields are with validation errors"),
            @ApiResponse(code = 201, message = "List"),
            @ApiResponse(code = 500, message = "Error occurred while fetching Person")
    )
    ResponseEntity create(@RequestBody PersonCO co) {
        return new ResponseEntity(new Person(firstName: co.firstName, lastName: co.lastName, age: co.age), HttpStatus.OK)
    }
    }
}