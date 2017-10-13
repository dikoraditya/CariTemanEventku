package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.karneim.pojobuilder.GeneratePojoBuilder;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Diko Raditya on 12/10/2017.
 */
@GeneratePojoBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequest implements Serializable{
    private static final long serialVersionUID = 1L;

    @NotNull
    private String userId;

    @NotNull
    private String userName;

    @NotNull
    private String email;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
