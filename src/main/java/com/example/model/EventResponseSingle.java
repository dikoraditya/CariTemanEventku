package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Diko Raditya on 14/10/2017.
 */
@JsonIgnoreProperties
public class EventResponseSingle implements Serializable{
    private static final long serialVersionUID = -7818696638456877478L;

    private Event result;
    private String status;
    private String message;

    public Event getResult() {
        return result;
    }

    public void setResult(Event result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "EventResponse{" +
                ", result=" + result +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
