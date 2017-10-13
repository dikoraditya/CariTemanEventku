package com.example.model;

import net.karneim.pojobuilder.GeneratePojoBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by diko.raditya on 31/08/2017.
 */
@GeneratePojoBuilder
@Document
public class Postingan implements Serializable{

    private static final long serialVersionUID = 7316275754622016035L;

    @Id
    @Field(value = "threadId")
    private String threadId;

    @Field(value = "eventId")
    private String eventId;

    @Field(value = "createdDate")
    private Date createdDate;

    @Field(value = "message")
    private String message;

    @Field(value = "memberId")
    private String memberId;

    @Field(value = "comments")
    private List<String> comments;

    @Field(value = "markForDelete")
    private boolean markForDelete;

    public Postingan() {
        this.setMarkForDelete(false);
        this.setCreatedDate(new Date());
    }

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public boolean isMarkForDelete() {
        return markForDelete;
    }

    public void setMarkForDelete(boolean markForDelete) {
        this.markForDelete = markForDelete;
    }

    @Override
    public java.lang.String toString() {
        return "Postingan{" +
                "threadId=" + threadId +
                ", eventId=" + eventId +
                ", createdDate=" + createdDate +
                ", message=" + message +
                ", memberId=" + memberId +
                ", comments=" + comments +
                ", markForDelete=" + markForDelete +
                '}';
    }
}
