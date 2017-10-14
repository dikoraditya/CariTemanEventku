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
public class Comment implements Serializable{

    private static final long serialVersionUID = -4929518077669574317L;

    @Id
    @Field(value = "commentId")
    private String commentId;

    @Field(value = "threadId")
    private String threadId;

    @Field(value = "createdDate")
    private Date createdDate;

    @Field(value = "memberId")
    private String memberId;

    @Field(value = "markForDelete")
    private boolean markForDelete;

    @Field(value = "message")
    private String message;

    @Field(value = "commentDate")
    private String commentDate;

    @Field(value = "modifiedDate")
    private Date modifiedDate;

    public Comment()
    {
        this.setMarkForDelete(false);
        this.setCreatedDate(new Date());
        this.setModifiedDate(null);
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public boolean isMarkForDelete() {
        return markForDelete;
    }

    public void setMarkForDelete(boolean markForDelete) {
        this.markForDelete = markForDelete;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId='" + commentId + '\'' +
                ", threadId='" + threadId + '\'' +
                ", createdDate=" + createdDate +
                ", memberId='" + memberId + '\'' +
                ", markForDelete=" + markForDelete +
                ", message='" + message + '\'' +
                ", commentDate='" + commentDate + '\'' +
                ", modifiedDate=" + modifiedDate +
                '}';
    }
}
