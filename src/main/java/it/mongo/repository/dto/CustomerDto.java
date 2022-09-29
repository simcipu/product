/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.mongo.repository.dto;

import lombok.Data;

/**
 * @author simonecipullo
 */
@Data
public class CustomerDto {

    private String name;

    private String surname;

    private String company;

}
