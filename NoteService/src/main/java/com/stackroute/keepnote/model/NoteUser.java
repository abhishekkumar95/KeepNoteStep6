package com.stackroute.keepnote.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

@Document
public class NoteUser {


    @Id
    private String userId;
    private List<Note> notes;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "NoteUser{" +
                "userId='" + userId + '\'' +
                ", notes=" + notes +
                '}';
    }
}


