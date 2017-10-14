package com.example.request;

import com.example.model.Event;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by kevinnkurniawan on 10/13/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventRequest implements Serializable{
    private static final long serialVersionUID = -754705079627771682L;

    private String category;
    private String eventDate;
    private String eventDateHour;
    private String status;
    private boolean isPrivate;
    private String eventName;
    private String location;
    private int totalPeople;
    private int quota;
    private String hostedBy;

    public EventRequest(){}

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventDateHour() {
        return eventDateHour;
    }

    public void setEventDateHour(String eventDateHour) {
        this.eventDateHour = eventDateHour;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTotalPeople() {
        return totalPeople;
    }

    public void setTotalPeople(int totalPeople) {
        this.totalPeople = totalPeople;
    }

    public int getquota() {
        return quota;
    }

    public void setquota(int quota) {
        this.quota = quota;
    }

    public String getHostedBy() {
        return hostedBy;
    }

    public void setHostedBy(String hostedBy) {
        this.hostedBy = hostedBy;
    }

    @Override
    public String toString() {
        return "EventRequest{" +
                "category='" + category + '\'' +
                ", eventDate='" + eventDate + '\'' +
                ", eventDateHour='" + eventDateHour + '\'' +
                ", status='" + status + '\'' +
                ", isPrivate=" + isPrivate +
                ", eventName='" + eventName + '\'' +
                ", location='" + location + '\'' +
                ", totalPeople=" + totalPeople +
                ", quota=" + quota +
                ", hostedBy='" + hostedBy + '\'' +
                '}';
    }
}
