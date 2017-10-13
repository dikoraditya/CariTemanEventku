package com.example.model;

import net.karneim.pojobuilder.GeneratePojoBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * Created by diko.raditya on 31/08/2017.
 */
@GeneratePojoBuilder
@Document
public class Todo implements Serializable{

    private static final long serialVersionUID = 4595815189235196646L;

    @Id
    @Field(value = "toDoId")
    private String todoId;

    @Field(value = "content")
    private String content;

    @Field(value = "active")
    private boolean active;

    @Field(value = "markForDelete")
    private boolean markForDelete;

    public String getTodoId() {
        return todoId;
    }

    public void setTodoId(String todoId) {
        this.todoId = todoId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isMarkForDelete() {
        return markForDelete;
    }

    public void setMarkForDelete(boolean markForDelete) {
        this.markForDelete = markForDelete;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "todoId='" + todoId + '\'' +
                ", content='" + content + '\'' +
                ", active='" + active + '\'' +
                '}';
    }
}
