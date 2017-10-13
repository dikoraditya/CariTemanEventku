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
    private Date createdDate;
    private String eventDate;
    private String eventDateHour;
    private String status;
    private boolean isPrivate;
    private String eventName;
    private String location;
    private int totalPeople;
    private int quantity;

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
