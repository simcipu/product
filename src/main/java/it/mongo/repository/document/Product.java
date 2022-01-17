/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.mongo.repository.document;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author simonecipullo
 */
@Document(collection = "Product")
public class Product {
    
    @Id
    private String serialNumber;


    private String type;
    
    private String name;
    
       
    private List<Customer> productCustumer=new ArrayList<>();

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
 
    public List<Customer> getProductCustumer() {
        return productCustumer;
    }

    public void setProductCustumer(List<Customer> productCustumer) {
        this.productCustumer = productCustumer;
    }


    
    
    
    
    
}
