package com.example.controller;

import com.example.model.Comment;
import com.example.model.CommentResponse;
import com.example.model.Postingan;
import com.example.repository.CommentRepository;
import com.example.repository.PostinganRepository;
import com.example.request.CommentRequest;
import com.example.utility.BeanMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping(value = "/api/Comment")
@Api(value = "Test", description = "Test")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostinganRepository postinganRepository;

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
    public void saveComment(@RequestBody @Valid CommentRequest comment, @RequestParam String threadId, @RequestParam String memberId) throws Exception{
        Comment newComment = new Comment();
        newComment.setThreadId(threadId);
        newComment.setMemberId(memberId);
        newComment.setMessage(comment.getMessage());
        String[] eventDates = comment.getCommentDate().split("-");
        String[] eventDateTime = comment.getCommentDateHour().split(":");
        String neew = comment.getCommentDate()+":"+comment.getCommentDateHour();
        Date eventDate = new SimpleDateFormat("yyyy-MM-dd:HH:mm").parse(neew);
        Date date = new Date();
        if(TimeUnit.MILLISECONDS.toDays(date.getTime() - eventDate.getTime())==1) {
            newComment.setCommentDate("Yesterday");
        }
        else {
            if(TimeUnit.MILLISECONDS.toSeconds(date.getTime() - eventDate.getTime())<60) {
                newComment.setCommentDate(Long.toString(TimeUnit.MILLISECONDS.toSeconds(date.getTime() - eventDate.getTime())) + " seconds ago");
            }
            else if(TimeUnit.MILLISECONDS.toMinutes(date.getTime() - eventDate.getTime())<60) {
                newComment.setCommentDate(Long.toString(TimeUnit.MILLISECONDS.toMinutes(date.getTime() - eventDate.getTime())) + " minutes ago");
            }
            else if(TimeUnit.MILLISECONDS.toHours(date.getTime() - eventDate.getTime())<24) {
                newComment.setCommentDate(Long.toString(TimeUnit.MILLISECONDS.toHours(date.getTime() - eventDate.getTime())) + " hours ago");
            }
            else {
                newComment.setCommentDate(Long.toString(TimeUnit.MILLISECONDS.toDays(date.getTime() - eventDate.getTime())) + " days ago");
            }
        }
        this.commentRepository.save(newComment);
    }

    @GetMapping
    @RequestMapping(value = "/id/{threadId}", method = RequestMethod.GET)
    @ApiOperation(value = "Find All Comment By Thread Id", notes = "find all Comment By EventID from DB")
    public CommentResponse findCommentByThreadId(@PathVariable String threadId) throws Exception {
        CommentResponse CommentResponse = new CommentResponse();
        try {
            List<Comment> result = this.commentRepository.findAllByThreadIdAndMarkForDeleteIsFalse(threadId);
            CommentResponse.setResults(result);
            CommentResponse.setCount(result.size());
        }
        catch (Exception e){
            CommentResponse.setStatus("failed");
            CommentResponse.setMessage(e.getMessage());
            return CommentResponse;
        }
        CommentResponse.setStatus("success");
        CommentResponse.setMessage("success");
        return CommentResponse;}

    @PostMapping
    @RequestMapping(value = "/deleteCommentByCommentId", method = RequestMethod.POST)
    @ApiOperation(value = "Delete Comment By commentId", notes = "Delete Comment By Comment Id")
    public void deleteCommentByCommentId(@RequestParam String commentId) throws Exception {
        Comment comment = this.commentRepository.findByCommentIdAndMarkForDeleteIsFalse(commentId);
        comment.setMarkForDelete(true);
        this.commentRepository.save(comment);
    }

    @PostMapping
    @RequestMapping(value = "/updateCommentByCommentId", method = RequestMethod.POST)
    @ApiOperation(value = "Update Comment By CommentId", notes = "UpdateCommentByCommentId")
    public void updateCommentByCommentId(@RequestBody CommentRequest request, @RequestParam String commentId) throws Exception {
        Comment existing = this.commentRepository.findByCommentIdAndMarkForDeleteIsFalse(commentId);
        existing.setMessage(request.getMessage());
        existing.setModifiedDate(new Date());
        this.commentRepository.save(existing);
    }
}
