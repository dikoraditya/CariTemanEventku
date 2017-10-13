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

    public CommentRequest(){}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
