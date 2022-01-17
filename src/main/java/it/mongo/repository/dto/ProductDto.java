/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.mongo.repository.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author simonecipullo
 */
public class ProductDto {

    private String serialNumber;

    private String type;

    private String name;
    
     private List<CustomerDto> customer=new ArrayList<>();

    public List<CustomerDto> getCustomer() {
        return customer;
    }

    public void setCustomer(List<CustomerDto> customer) {
        this.customer = customer;
    }


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

}
