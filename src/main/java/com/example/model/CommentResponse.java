package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Diko Raditya on 14/10/2017.
 */
@JsonIgnoreProperties
public class CommentResponse implements Serializable{
    private static final long serialVersionUID = -7818696638456877478L;

    private int count;
    private List<Comment> results;
    private String status;
    private String message;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Comment> getResults() {
        return results;
    }

    public void setResults(List<Comment> results) {
        this.results = results;
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
        return "CommentResponse{" +
                "count=" + count +
                ", results=" + results +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
