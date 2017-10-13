package com.example.model;

import net.karneim.pojobuilder.GeneratePojoBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * Created by Diko Raditya on 12/10/2017.
 */
@GeneratePojoBuilder
@Document
public class User implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Field(value = "userId")
    private String userId;

    @Field(value = "fullName")
    private String fullName;

    @Field(value = "address")
    private String address;

    @Field(value = "email")
    private String email;

    @Field(value = "phoneNumber")
    private String phoneNumber;

    @Field(value = "markForDelete")
    private boolean markForDelete;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId= userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isMarkForDelete() {
        return markForDelete;
    }

    public void setMarkForDelete(boolean markForDelete) {
        this.markForDelete = markForDelete;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", markForDelete=" + markForDelete +
                '}';
    }
}
