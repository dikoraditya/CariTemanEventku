package com.example.repository;

import com.example.model.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by diko.raditya on 31/08/2017.
 */
@Repository
public interface TodoRepository extends MongoRepository<Todo, String>{
    Todo findByTodoIdAndMarkForDeleteIsFalse(String todoId);

    List<Todo> findAllByMarkForDeleteIsFalse();
}
