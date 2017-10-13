package com.example.controller;

import com.example.model.*;
import com.example.model.Postingan;
import com.example.repository.CommentRepository;
import com.example.repository.EventRepository;
import com.example.repository.PostinganRepository;
import com.example.request.EventRequest;
import com.example.utility.BeanMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
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
    public Boolean deleteEventByEventId(@RequestParam String eventId) throws Exception {
        Event event = this.eventRepository.findByEventIdAndMarkForDeleteIsFalse(eventId);
        List<Postingan> postinganList = this.postinganRepository.findAllByEventIdAndMarkForDeleteIsFalse(event.getEventId());
        postinganList.forEach(postinganInEvent -> {
            List<Comment> commentList =
                    this.commentRepository.findAllByThreadIdAndMarkForDeleteIsFalse(postinganInEvent.getThreadId());
            commentList.forEach(comment -> comment.setMarkForDelete(true));
            commentRepository.save(commentList);

            postinganInEvent.setMarkForDelete(true);
            postinganRepository.save(postinganInEvent);
        });
        event.setMarkForDelete(true);
        return true;
    }

    @PostMapping(value = "/createEvent")
    @ApiOperation(value = "Create New Event", notes =  "Create new event")
    public Boolean createNewEvent(@RequestBody EventRequest event) throws Exception
    {
        Event newEvent = new Event();
        BeanUtils.copyProperties(event, newEvent);
        String[] eventDates = event.getEventDate().split("-");
        String[] eventDateTime = event.getEventDateHour().split(":");
        Date eventDate = new Date(Integer.parseInt(eventDates[0]),
                Integer.parseInt(eventDates[1]), Integer.parseInt(eventDates[2]),
                Integer.parseInt(eventDateTime[0]), Integer.parseInt(eventDateTime[1]));
        newEvent.setEventDate(eventDate);
        this.eventRepository.save(newEvent);
        
        
        return true;
    }

    @PostMapping(value = "/updateEvent")
    @ApiOperation(value = "Update Event", notes =  "Update event")
    public Boolean updateEvent(@RequestBody EventRequest event, @RequestParam String eventId) throws Exception
    {
        Event existing = this.findEventByEventId(eventId);
        if(existing == null)
            throw new IllegalStateException("Event does not exist. Failed to update event");

        BeanUtils.copyProperties(event, existing, "id", "markForDelete");
        this.eventRepository.save(existing);

        return true;
    }

}
