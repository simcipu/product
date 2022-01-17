/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.mongo.repository.rest.controller;

import it.mongo.repository.dto.ProductDto;
import it.mongo.repository.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author simonecipullo
 */
@RestController
@RequestMapping("/api/v1")
public class ProductController /*extends WebMvcConfigurerAdapter*/{

    @Autowired
    private ProductService service;

    @RequestMapping(value = {"/mongo/get/type/{type}"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<ProductDto>> getProducts(@PathVariable("type") String type) {

        List<ProductDto> dto = service.li(type);

        return new ResponseEntity<>(dto, HttpStatus.OK);

    }

    @RequestMapping(value = {"/mongo/save"}, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Boolean> saveProducts(@RequestBody ProductDto dto) {

        service.save(dto);

        return new ResponseEntity<>(true, HttpStatus.OK);

    }

    @RequestMapping(value = {"/mongo/delete/{id}"}, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Boolean> deleteProducts(@PathVariable("id") String id) {

        boolean result = service.delete(id);

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

      @RequestMapping(value = {"/mongo/update"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Boolean> updateProducts(@RequestBody ProductDto dto) {

        boolean result = service.update(dto);

        return new ResponseEntity<>(result, HttpStatus.OK);

    }
    
    
     @RequestMapping(value = {"/mongo/surname/{surname}"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<ProductDto>> ProductFromSurname(@PathVariable("surname") String surname) {

      List<ProductDto> li=service.list(surname);

      return new ResponseEntity<>(li, HttpStatus.OK);

    }
    
    
        
     @RequestMapping(value = {"/mongo/all"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<ProductDto>> ProductAll() {

      List<ProductDto> li=service.listAll();

      return new ResponseEntity<>(li, HttpStatus.OK);

    }


    @RequestMapping(value = {"/mongo/get/id/{id}"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ProductDto> ProductById(@PathVariable("id") String id) {

        ProductDto li=service.findById(id);

        return new ResponseEntity<>(li, HttpStatus.OK);

    }
}
