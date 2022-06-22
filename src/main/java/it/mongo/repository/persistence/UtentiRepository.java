package it.mongo.repository.persistence;


import it.mongo.repository.document.Utenti;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UtentiRepository extends MongoRepository<Utenti, String> {

    @Query("{ 'userId' : ?0 }")
    Optional<Utenti> findByUserId(String UserId);
}
