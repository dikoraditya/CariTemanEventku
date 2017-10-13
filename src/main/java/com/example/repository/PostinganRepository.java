package com.example.repository;

import com.example.model.Postingan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Diko Raditya on 12/10/2017.
 */
@Repository
public interface ThreadRepository extends MongoRepository<Postingan, String>{
    Postingan findByEventIdAndMarkForDeleteIsFalse(String eventId);

    List<Postingan> findAllByEventIdAndMarkForDeleteIsFalse(String eventId);
}
