/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.mongo.repository.dto;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author simonecipullo
 */
@Data
public class ProductDto {

    private String serialNumber;

    private String type;

    private String name;
    
     private List<CustomerDto> customer=new ArrayList<>();
}
