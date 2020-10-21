package se.ecutb.loffe.webservices.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import se.ecutb.loffe.webservices.entities.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
