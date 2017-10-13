package com.example.controller;

import com.example.model.Comment;
import com.example.model.Postingan;
import com.example.repository.CommentRepository;
import com.example.repository.ThreadRepository;
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
@RequestMapping(value = "/api/Thread")
@Api(value = "Test", description = "Test")
public class ThreadController {

    @Autowired
    private ThreadRepository threadRepository;

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
    @RequestMapping(value = "/saveThread", method = RequestMethod.POST)
    @ApiOperation(value = "Save postingan", notes = "save postingan info to DB")
    public void saveThread(@RequestBody @Valid Postingan postingan) throws Exception{
        this.threadRepository.save(BeanMapper.map(postingan, Postingan.class));
    }

    @GetMapping
    @RequestMapping(value = "/getAllThreadByEventId", method = RequestMethod.GET)
    @ApiOperation(value = "Find All Postingan By Event Id", notes = "find all Postingan By EventID from DB")
    public List<Postingan> findThreadByEventId(@RequestParam String eventId) throws Exception {
        return this.threadRepository.findAllByEventIdAndMarkForDeleteIsFalse(eventId);
    }

    @PostMapping
    @RequestMapping(value = "/deleteThreadByThreadId", method = RequestMethod.POST)
    @ApiOperation(value = "Delete Postingan By ThreadId", notes = "Delete Postingan By ThreadId")
    public void deleteThreadByThreadId(@RequestParam String threadId) throws Exception {
        Postingan postingan = this.threadRepository.findByEventIdAndMarkForDeleteIsFalse(threadId);
        postingan.setMarkForDelete(true);
        List<Comment> commentList = this.commentRepository.findAllByThreadIdAndMarkForDeleteIsFalse(threadId);
        commentList.forEach(comment -> {
            comment.setMarkForDelete(true);
            this.commentRepository.save(comment);
        });
        this.threadRepository.save(postingan);
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
