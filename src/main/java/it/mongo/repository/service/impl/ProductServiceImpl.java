/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.mongo.repository.service.impl;

import it.mongo.repository.document.Product;
import it.mongo.repository.dto.ProductDto;
import it.mongo.repository.exception.ServiceRestException;
import it.mongo.repository.persistence.ProductRepository;
import it.mongo.repository.service.ProductService;
import it.mongo.repository.utils.Mapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 *
 * @author simonecipullo
 */
@Service("ProductService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repo;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private Mapper mapper;

    @Override
    public void save(ProductDto dto) {

        if (repo.existsById(dto.getSerialNumber())) {

            throw new ServiceRestException("serialnumber already exits!!!");
        }

        Product dco = mapper.map(dto);

        repo.save(dco);

    }

    @Override
    public List<ProductDto> li(String type) {

        List<ProductDto> list = new ArrayList<>();

        List<Product> l = repo.findByType(type);

        if (!l.isEmpty()) {

            l.stream().forEach((d) -> {
                list.add(mapper.map(d));
            });

        }

        return list;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;

        if (repo.existsById(id)) {
            repo.deleteById(id);

            return true;
        }

        return result;

    }

    @Override
    public boolean update(ProductDto dto) {

        if (!repo.existsById(dto.getSerialNumber())) {

            throw new ServiceRestException("serialnumber not exits!!!");
        }

        Product dco = mapper.map(dto);

        repo.save(dco);

        return true;
    }

    @Override
    public List<ProductDto> list(String surname) {
        List<ProductDto> li = new ArrayList<>();
        List<Product> list = query(surname);

        if (!list.isEmpty()) {

            list.stream().forEach((l) -> {
                li.add(mapper.map(l));

            });

        }

        return li;
    }

    @Override
    public List<ProductDto> listAll() {
        List<ProductDto> li = new ArrayList<>();
        List<Product> list = repo.findAll();

        if (!list.isEmpty()) {

            list.stream().forEach((l) -> {
                li.add(mapper.map(l));

            });

        }

        return li;
    }

    @Override
    public ProductDto findById(String id) {

        ProductDto dto=new ProductDto();

         if(repo.existsById(id)){

             dto=mapper.map(repo.findById(id).get());

         }

        return dto;
    }

    public List<Product> query(String surname) {
        Query query = new Query();
        query.addCriteria(Criteria.where("productCustumer.surname").is(surname));
        query.fields().include("productCustumer.$");
        query.fields().include("type");
        query.fields().include("name");

        return mongoTemplate.find(query, Product.class);
    }

}
