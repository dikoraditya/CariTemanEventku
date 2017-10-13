package com.example.controller;

import com.example.model.Comment;
import com.example.repository.CommentRepository;
import com.example.utility.BeanMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by diko.raditya on 30/08/2017.
 */
@RestController
@RequestMapping(value = "/api/Comment")
@Api(value = "Test", description = "Test")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    /**
     * <a href="https://cloud.google.com/appengine/docs/flexible/java/how-instances-are-managed#health_checking">
     * App Engine health checking</a> requires responding with 200 to {@code /_ah/health}.
     */
    @GetMapping
    @RequestMapping(value = "/_ah/health", method = RequestMethod.GET)
    @ApiOperation(value = "hc", notes = "healthcheck")
    public String healthy() {
        // Message body required though ignored
        return "Still surviving.";
    }

    @PostMapping
    @RequestMapping(value = "/saveComment", method = RequestMethod.POST)
    @ApiOperation(value = "Save comment", notes = "save comment info to DB")
    public void saveThread(@RequestBody @Valid Comment comment) throws Exception{
        this.commentRepository.save(BeanMapper.map(comment, Comment.class));
    }

    @GetMapping
    @RequestMapping(value = "/getAllThreadByEventId", method = RequestMethod.GET)
    @ApiOperation(value = "Find All Comment By Event Id", notes = "find all Comment By EventID from DB")
    public List<Comment> findCommentByCommentId(@RequestParam String commentId) throws Exception {
        return this.commentRepository.findAllByCommentIdAndMarkForDeleteIsFalse(commentId);
    }

    @PostMapping
    @RequestMapping(value = "/deleteCommentByCommentId", method = RequestMethod.POST)
    @ApiOperation(value = "Delete Comment By commentId", notes = "Delete Comment By Comment Id")
    public void deleteCommentByCommentId(@RequestParam String commentId) throws Exception {
        Comment comment = this.commentRepository.findByCommentIdAndMarkForDeleteIsFalse(commentId);
        comment.setMarkForDelete(true);
        this.commentRepository.save(comment);
    }

//    @PostMapping
//    @RequestMapping(value = "/updateThreadByThreadId", method = RequestMethod.POST)
//    @ApiOperation(value = "Update Postingan By ThreadId", notes = "UpdateThreadByThreadId")
//    public void updateThreadByThreadId(@RequestBody Postingan threadReq) throws Exception {
//        Postingan threadUpdate = this.threadRepository.findByEventIdAndMarkForDeleteIsFalse(threadReq.getThreadId());
//        thread.setMarkForDelete(true);
//        this.threadRepository.save(thread);
//    }
}