package com.example.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kevinnkurniawan on 10/13/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostinganRequest implements Serializable {

    private static final long serialVersionUID = 1717375068553074499L;
    private String message;
    private String memberId;

    public PostinganRequest() {}

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
}
