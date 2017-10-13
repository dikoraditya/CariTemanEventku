package com.example.controller;

import com.example.model.*;
import com.example.model.Postingan;
import com.example.repository.CommentRepository;
import com.example.repository.EventRepository;
import com.example.repository.ThreadRepository;
import com.example.utility.BeanMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by diko.raditya on 30/08/2017.
 */
@RestController
@RequestMapping(value = "/api/Event")
@Api(value = "Test", description = "Test")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

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
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    @ApiOperation(value = "Save user", notes = "save user info to DB")
    public void saveEvent(@RequestBody @Valid Event event) throws Exception{
        this.eventRepository.save(BeanMapper.map(event, Event.class));
    }

    @GetMapping
    @RequestMapping(value = "/getAllEvent", method = RequestMethod.GET)
    @ApiOperation(value = "Find All Event", notes = "find all Event info from DB")
    public List<Event> findAllEvent() throws Exception {
        return this.eventRepository.findAllByMarkForDeleteIsFalse();
    }

    @GetMapping
    @RequestMapping(value = "/getEventById", method = RequestMethod.GET)
    @ApiOperation(value = "Find Event By Id", notes = "find Event By Id")
    public Event findEventByEventId(@RequestParam String eventId) throws Exception {
        return this.eventRepository.findByEventIdAndMarkForDeleteIsFalse(eventId);
    }

    @PostMapping
    @RequestMapping(value = "/deleteEvent", method = RequestMethod.POST)
    @ApiOperation(value = "Delete Event By Id", notes =  "Delete event by eventId")
    public void deleteEventByEventId(@RequestParam String eventId) throws Exception {
        Event event = this.eventRepository.findByEventIdAndMarkForDeleteIsFalse(eventId);
        List<Postingan> postinganList = this.threadRepository.findAllByEventIdAndMarkForDeleteIsFalse(event.getEventId());
        List<Comment> commentList = new ArrayList<>();
        postinganList.forEach(threadInEvent -> {
            this.commentRepository.findAllByThreadIdAndMarkForDeleteIsFalse(threadInEvent.getThreadId());
        });
    }
}
