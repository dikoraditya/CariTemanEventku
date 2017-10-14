package com.example.controller;

import com.example.model.Comment;
import com.example.model.Postingan;
import com.example.model.PostinganResponse;
import com.example.repository.CommentRepository;
import com.example.repository.PostinganRepository;
import com.example.request.PostinganRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by diko.raditya on 30/08/2017.
 */
@RestController
@RequestMapping(value = "/api/Thread")
@Api(value = "Test", description = "Test")
public class PostinganController {

    @Autowired
    private PostinganRepository postinganRepository;

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
    public void saveThread(@RequestBody @Valid PostinganRequest postingan, @RequestParam String eventId) throws Exception{
        Postingan newPostingan = new Postingan();
        BeanUtils.copyProperties(postingan, newPostingan);
        newPostingan.setEventId(eventId);
        String[] eventDates = postingan.getPostinganDate().split("-");
        String[] eventDateTime = postingan.getPostinganHour().split(":");
        String neew = postingan.getPostinganDate()+":"+postingan.getPostinganHour();
        Date eventDate = new SimpleDateFormat("yyyy-MM-dd:HH:mm").parse(neew);
        Date date = new Date();
        if(TimeUnit.MILLISECONDS.toDays(date.getTime() - eventDate.getTime())==1) {
            newPostingan.setPostinganDate("Yesterday");
        }
        else {
            if(TimeUnit.MILLISECONDS.toSeconds(date.getTime() - eventDate.getTime())<60) {
                newPostingan.setPostinganDate(Long.toString(TimeUnit.MILLISECONDS.toSeconds(date.getTime() - eventDate.getTime())) + " seconds ago");
            }
            else if(TimeUnit.MILLISECONDS.toMinutes(date.getTime() - eventDate.getTime())<60) {
                newPostingan.setPostinganDate(Long.toString(TimeUnit.MILLISECONDS.toMinutes(date.getTime() - eventDate.getTime())) + " minutes ago");
            }
            else if(TimeUnit.MILLISECONDS.toHours(date.getTime() - eventDate.getTime())<24) {
                newPostingan.setPostinganDate(Long.toString(TimeUnit.MILLISECONDS.toHours(date.getTime() - eventDate.getTime())) + " hours ago");
            }
            else {
                newPostingan.setPostinganDate(Long.toString(TimeUnit.MILLISECONDS.toDays(date.getTime() - eventDate.getTime())) + " days ago");
            }
        }
        this.postinganRepository.save(newPostingan);
    }

    @GetMapping
    @RequestMapping(value = "/id/{eventId}", method = RequestMethod.GET)
    @ApiOperation(value = "Find All Postingan By Event Id", notes = "find all Postingan By EventID from DB")
    public PostinganResponse findThreadByEventId(@PathVariable String eventId) throws Exception {
        PostinganResponse PostinganResponse = new PostinganResponse();
        try {
            List<Postingan> result = this.postinganRepository.findAllByEventIdAndMarkForDeleteIsFalse(eventId);
            PostinganResponse.setResults(result);
            PostinganResponse.setCount(result.size());
        }
        catch (Exception e){
            PostinganResponse.setStatus("failed");
            PostinganResponse.setMessage(e.getMessage());
            return PostinganResponse;
        }
        PostinganResponse.setStatus("success");
        PostinganResponse.setMessage("success");
        return PostinganResponse;    
    }

    @PostMapping
    @RequestMapping(value = "/deleteThreadByThreadId", method = RequestMethod.POST)
    @ApiOperation(value = "Delete Postingan By ThreadId", notes = "Delete Postingan By ThreadId")
    public void deleteThreadByThreadId(@RequestParam String threadId) throws Exception {
        Postingan postingan = this.postinganRepository.findByEventIdAndMarkForDeleteIsFalse(threadId);
        postingan.setMarkForDelete(true);
        List<Comment> commentList = this.commentRepository.findAllByThreadIdAndMarkForDeleteIsFalse(threadId);
        commentList.forEach(comment -> {
            comment.setMarkForDelete(true);
            this.commentRepository.save(comment);
        });
        this.postinganRepository.save(postingan);
    }

    @PostMapping
    @RequestMapping(value = "/updateThreadByThreadId", method = RequestMethod.POST)
    @ApiOperation(value = "Update Postingan By ThreadId", notes = "UpdateThreadByThreadId")
    public Boolean updateThreadByThreadId(@RequestBody PostinganRequest threadReq, @RequestParam String threadId) throws Exception {
        Postingan threadUpdate = this.postinganRepository.findByEventIdAndMarkForDeleteIsFalse(threadId);
        if(threadUpdate == null)
            throw new IllegalStateException("Postingan does not exist. Failed to Update");
        threadUpdate.setMemberId(threadReq.getMemberId());
        threadUpdate.setMessage(threadReq.getMessage());
        this.postinganRepository.save(threadUpdate);
        return true;
    }

    @PostMapping
    @RequestMapping(value = "/like/id/{threadId}", method = RequestMethod.POST)
    @ApiOperation(value = "Like a post", notes = "Like a post")
    public Boolean likePost(@PathVariable String threadId) {
        Postingan postingan = this.postinganRepository.findByThreadIdAndMarkForDeleteIsFalse(threadId);
        postingan.setLikes(postingan.getLikes()+1);
        this.postinganRepository.save(postingan);
        return true;
    }

    @PostMapping
    @RequestMapping(value = "/unlike/id/{threadId}", method = RequestMethod.POST)
    @ApiOperation(value = "unLike a post", notes = "unLike a post")
    public Boolean unlikePost(@PathVariable String threadId) {
        Postingan postingan = this.postinganRepository.findByThreadIdAndMarkForDeleteIsFalse(threadId);
        postingan.setLikes(postingan.getLikes()-1);
        this.postinganRepository.save(postingan);
        return true;
    }
}
