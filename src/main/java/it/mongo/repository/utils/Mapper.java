/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.mongo.repository.utils;

import it.mongo.repository.document.Customer;
import it.mongo.repository.document.Product;
import it.mongo.repository.dto.CustomerDto;
import it.mongo.repository.dto.ProductDto;

/**
 *
 * @author simonecipullo
 */
public class Mapper {
    
    
    public Customer map(CustomerDto dto){
    
    Customer cu=new Customer();
    
    cu.setCompany(dto.getCompany());
    cu.setName(dto.getName());
    cu.setSurname(dto.getSurname());
    
    
    return cu;
    
    }
    
    
    public Product map(ProductDto dto){
    
    Product pr=new Product();
    
    pr.setName(dto.getName());
    pr.setSerialNumber(dto.getSerialNumber());
    pr.setType(dto.getType());
    
    if(!dto.getCustomer().isEmpty()){
        dto.getCustomer().stream().forEach((d) -> {
            pr.getProductCustumer().add(map(d));
        });
    
    }
    
    return pr;
    
    }
    
    
     public CustomerDto map(Customer doc){
    
    CustomerDto cu=new CustomerDto();
    
    cu.setCompany(doc.getCompany());
    cu.setName(doc.getName());
    cu.setSurname(doc.getSurname());

    
    return cu;
    
    }
     
       public ProductDto map(Product doc){
    
    ProductDto pr=new ProductDto();
    
    pr.setName(doc.getName());
    pr.setSerialNumber(doc.getSerialNumber());
    pr.setType(doc.getType());
    
    if(!doc.getProductCustumer().isEmpty()){
        doc.getProductCustumer().stream().forEach((d) -> {
            pr.getCustomer().add(map(d));
        });
    
    }
    
    return pr;
    
    }
    
}
