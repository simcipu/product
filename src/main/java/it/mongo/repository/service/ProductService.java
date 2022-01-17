/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.mongo.repository.service;

import it.mongo.repository.dto.ProductDto;
import java.util.List;

/**
 *
 * @author simonecipullo
 */

public interface ProductService {
    
    
    void save(ProductDto dto);
    
    List<ProductDto> li(String type);
    
    boolean delete(String id);
    
    boolean update(ProductDto dto);
    
    List<ProductDto> list(String surname);
    
    List<ProductDto> listAll();

    ProductDto findById(String id);
    
}
