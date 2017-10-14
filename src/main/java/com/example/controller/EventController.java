package com.example.controller;

import com.example.model.*;
import com.example.model.Postingan;
import com.example.repository.CommentRepository;
import com.example.repository.EventRepository;
import com.example.repository.PostinganRepository;
import com.example.request.EventRequest;
import com.example.utility.ImageUploader;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @GetMapping
    @RequestMapping(value = "/getAllEvent", method = RequestMethod.GET)
    @ApiOperation(value = "Find All Event", notes = "find all Event info from DB")
    public EventResponse findAllEvent() throws Exception {
        EventResponse eventResponse = new EventResponse();
        try {
            List<Event> result = this.eventRepository.findAllByMarkForDeleteIsFalse();
            eventResponse.setResults(result);
            eventResponse.setCount(result.size());
        }
        catch (Exception e){
            eventResponse.setStatus("failed");
            eventResponse.setMessage(e.getMessage());
            return eventResponse;
        }
        eventResponse.setStatus("success");
        eventResponse.setMessage("success");
        return eventResponse;
    }

    @GetMapping
    @RequestMapping(value = "/detail/{eventId}", method = RequestMethod.GET)
    @ApiOperation(value = "Find Event By Id", notes = "find Event By Id")
    public EventResponseSingle findEventByEventId(@PathVariable String eventId) throws Exception {
        EventResponseSingle eventResponseSingle = new EventResponseSingle();
        try{
            Event event = this.eventRepository.findByEventIdAndMarkForDeleteIsFalse(eventId);
            eventResponseSingle.setResult(event);
        } catch (Exception e) {
            eventResponseSingle.setStatus("Failed");
            eventResponseSingle.setMessage(e.getMessage());
            return  eventResponseSingle;
        }
        System.out.println(System.getProperty("catalina.home"));
        eventResponseSingle.setStatus("success");
        eventResponseSingle.setMessage("success");
        return eventResponseSingle;
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
    public Boolean createNewEvent(@ModelAttribute EventRequest event, @RequestParam MultipartFile backgroundImage,
                                  @RequestParam MultipartFile hostImage) throws Exception
    {
        Event newEvent = new Event();
        BeanUtils.copyProperties(event, newEvent);
        String[] eventDates = event.getEventDate().split("-");
        String[] eventDateTime = event.getEventDateHour().split(":");
        String neew = event.getEventDate()+":"+event.getEventDateHour();
        Date eventDate = new SimpleDateFormat("yyyy-MM-dd:HH:mm").parse(neew);
        newEvent.setEventDate(eventDate);
        Calendar calTomorrow = Calendar.getInstance();
        calTomorrow.setTime(new Date());
        SimpleDateFormat sdfDay = new SimpleDateFormat("EEEE");
        SimpleDateFormat sdfMonth = new SimpleDateFormat("MMMM");
        Date date = new Date();
        if(date.getDate()==(Integer.parseInt(eventDates[2]))) {
                        newEvent.setDateResponse("Today, at " + event.getEventDateHour());
        }
        else if (date.getDate()==(Integer.parseInt(eventDates[2])-1)) {
            newEvent.setDateResponse("Tomorrow, at " + event.getEventDateHour());
        }
        else if (date.getDate()==(Integer.parseInt(eventDates[2])+1)) {
            newEvent.setDateResponse("Yesterday, at " + event.getEventDateHour());
        }
        else{
            newEvent.setDateResponse(sdfDay.format(eventDate) + ", "+ sdfMonth.format(eventDate) + " " + eventDates[2] + " at " + event.getEventDateHour());
        }
        ImageUploader.uploadEventImage(backgroundImage, hostImage, newEvent);
        this.eventRepository.save(newEvent);
        return true;
    }

    @PostMapping(value = "/updateEvent")
    @ApiOperation(value = "Update Event", notes =  "Update event")
    public Boolean updateEvent(@RequestBody EventRequest event, @RequestParam String eventId) throws Exception
    {
        Event existing = this.findEventByEventId(eventId).getResult();
        if(existing == null)
            throw new IllegalStateException("Event does not exist. Failed to update event");

        BeanUtils.copyProperties(event, existing, "id", "markForDelete");
        this.eventRepository.save(existing);

        return true;
    }

    @PostMapping(value = "/testUpload")
    public Boolean testUpload(@RequestParam MultipartFile file) throws Exception
    {
        Event a = new Event();
        a.setEventName("Taylor Swift");
        a.setHostedBy("Kevin");
        ImageUploader.uploadEventImage(file, file, a);
        return true;
    }

}
