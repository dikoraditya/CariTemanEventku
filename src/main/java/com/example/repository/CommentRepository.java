package com.example.repository;

import com.example.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Diko Raditya on 12/10/2017.
 */
@Repository
public interface CommentRepository extends MongoRepository<Comment, String>{
    Comment findByCommentIdAndMarkForDeleteIsFalse(String commentId);

    List<Comment> findAllByCommentIdAndMarkForDeleteIsFalse(String commentId);

    List<Comment> findAllByThreadIdAndMarkForDeleteIsFalse(String threadId);
}
