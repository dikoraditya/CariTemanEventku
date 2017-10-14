package com.example.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by kevinnkurniawan on 10/13/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentRequest implements Serializable {

    private static final long serialVersionUID = -7489071370563853200L;

    private String message;
    private String commentDate;
    private String commentDateHour;


    public CommentRequest(){}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public String getCommentDateHour() {
        return commentDateHour;
    }

    public void setCommentDateHour(String commentDateHour) {
        this.commentDateHour = commentDateHour;
    }

    @Override
    public String toString() {
        return "CommentRequest{" +
                "message='" + message + '\'' +
                ", commentDate='" + commentDate + '\'' +
                ", commentDateHour='" + commentDateHour + '\'' +
                '}';
    }
}
