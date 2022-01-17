/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.mongo.repository.persistence;

import it.mongo.repository.document.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author simonecipullo
 */
@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    @Query("{ 'type' : ?0 }")
    List<Product> findByType(String type);

    @Query("{ 'serialNumber' : ?0 }")
    Optional<Product> findById(String id);

}
