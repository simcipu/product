/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.mongo.repository.persistence;

import com.google.gson.Gson;
import com.mongodb.assertions.Assertions;
import it.mongo.repository.ApplicationTest;
import it.mongo.repository.document.Customer;
import it.mongo.repository.document.Product;
import it.mongo.repository.dto.ProductDto;
import it.mongo.repository.service.ProductService;
import it.mongo.repository.utils.Mapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Objects;

/**
 * @author simonecipullo
 */
@SpringBootTest(classes = ApplicationTest.class)
public class ProductRepositoryTest {


    @Autowired
    private MongoTemplate mongoTemplate;

    private Product pr = null;

    private Customer cu = null;

    @Autowired
    private Mapper map;

    @Autowired
    private ProductRepository repo;

    @Autowired
    private ProductService productService;


    @BeforeEach
    public void setUp() {

        pr = new Product();
        cu = new Customer();

        cu.setCompany("myCopany");
        cu.setName("rossi");
        cu.setSurname("cipullo");


        pr.setSerialNumber("01");
        pr.setName("pomodori");
        pr.setType("legumi");
        pr.getProductCustumer().add(cu);

    }

    @Test
    public void getTypeTest() {

        repo.save(pr);

        List<Product> list = repo.findByType("legumi");

        org.junit.jupiter.api.Assertions.assertTrue(!list.isEmpty());
    }


    @Test
    public void getSurnameTest() {

        repo.save(pr);

        Query query = new Query();
        query.addCriteria(Criteria.where("productCustumer.surname").is("cipullo"));
        query.fields().include("productCustumer.$");
        query.fields().include("type");
        query.fields().include("name");

        List<Product> list = mongoTemplate.find(query, Product.class);

        org.junit.jupiter.api.Assertions.assertTrue(!list.isEmpty());
    }


    @Test
    public void simpleTest() {

        repo.save(pr);

        Product pr1 = repo.findById("01").get();

        org.junit.jupiter.api.Assertions.assertTrue(!Objects.isNull(pr1));

        List<ProductDto> list = productService.list("cipullo");

        org.junit.jupiter.api.Assertions.assertTrue(!list.isEmpty());

        List<Product> list1 = repo.findAll();
        org.junit.jupiter.api.Assertions.assertTrue(!list1.isEmpty());
    }
    
    
    @Test
    public void jsonSchema()
    {
    
      ProductDto dto=  map.map(pr);
    
      Gson g1 = new Gson();
       String obj=g1.toJson(dto,ProductDto.class);
        System.out.print(obj);
    }
    
    
    
}
