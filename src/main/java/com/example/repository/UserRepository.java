package com.example.repository;

import com.example.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Diko Raditya on 12/10/2017.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String>{
    User findByUserIdAndMarkForDeleteIsFalse(String userId);

    List<User> findAllByMarkForDeleteIsFalse();
}
