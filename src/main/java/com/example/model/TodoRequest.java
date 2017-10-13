package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.karneim.pojobuilder.GeneratePojoBuilder;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by diko.raditya on 31/08/2017.
 */
@GeneratePojoBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class TodoRequest implements Serializable{
    private static final long serialVersionUID = 1L;

    @NotNull
    private String todoId;

    @NotNull
    private String content;

    private boolean active;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getTodoId() {
        return todoId;
    }

    public void setTodoId(String todoId) {
        this.todoId = todoId;
    }

    @Override
    public String toString() {
        return "TodoRequest{" +
                "todoId='" + todoId + '\'' +
                ", content='" + content + '\'' +
                ", active=" + active +
                '}';
    }
}
