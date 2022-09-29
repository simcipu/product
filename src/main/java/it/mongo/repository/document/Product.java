/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.mongo.repository.document;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author simonecipullo
 */
@Data
@Document(collection = "Product")
public class Product {
    
    @Id
    private String serialNumber;
    private String type;
    private String name;
    private List<Customer> productCustumer=new ArrayList<>();

}
