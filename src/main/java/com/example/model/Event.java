package com.example.model;

import net.karneim.pojobuilder.GeneratePojoBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by diko.raditya on 31/08/2017.
 */
@GeneratePojoBuilder
@Document
public class Event implements Serializable{

    private static final long serialVersionUID = 1298278642962273525L;

    @Id
    @Field(value = "eventId")
    private String eventId;

    @Field(value = "category")
    private String category;

    @Field(value = "createdDate")
    private Date createdDate;

    @Field(value = "eventDate")
    private Date eventDate;

    @Field(value = "status")
    private String status;

    @Field(value = "isPrivate")
    private boolean isPrivate;

    @Field(value = "eventName")
    private String eventName;

    @Field(value = "location")
    private String location;

    @Field(value = "totalPeople")
    private int totalPeople;

    @Field(value = "quota")
    private int quota;

    @Field(value = "backgroundImg")
    private String backgroundImg;

    @Field(value = "hostImg")
    private String hostImg;

    @Field(value = "hostedBy")
    private String hostedBy;

    @Field(value = "dateResponse")
    private String dateResponse;

    @Field(value = "markForDelete")
    private boolean markForDelete;

    public Event()
    {
        this.setCreatedDate(new Date());
        this.setMarkForDelete(false);
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
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

    public boolean isMarkForDelete() {
        return markForDelete;
    }

    public void setMarkForDelete(boolean markForDelete) {
        this.markForDelete = markForDelete;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public String getBackgroundImg() {
        return backgroundImg;
    }

    public void setBackgroundImg(String backgroundImg) {
        this.backgroundImg = backgroundImg;
    }

    public String getHostImg() {
        return hostImg;
    }

    public void setHostImg(String hostImg) {
        this.hostImg = hostImg;
    }

    public String gethostedBy() {
        return hostedBy;
    }

    public void sethostedBy(String hostedBy) {
        this.hostedBy = hostedBy;
    }

    public String getDateResponse() {
        return dateResponse;
    }

    public void setDateResponse(String dateResponse) {
        this.dateResponse = dateResponse;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId='" + eventId + '\'' +
                ", category='" + category + '\'' +
                ", createdDate=" + createdDate +
                ", eventDate=" + eventDate +
                ", status='" + status + '\'' +
                ", isPrivate=" + isPrivate +
                ", eventName='" + eventName + '\'' +
                ", location='" + location + '\'' +
                ", totalPeople=" + totalPeople +
                ", quota=" + quota +
                ", backgroundImg='" + backgroundImg + '\'' +
                ", hostImg='" + hostImg + '\'' +
                ", hostedBy='" + hostedBy + '\'' +
                ", dateResponse='" + dateResponse + '\'' +
                ", markForDelete=" + markForDelete +
                '}';
    }
}
