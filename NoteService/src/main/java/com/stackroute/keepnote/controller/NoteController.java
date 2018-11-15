package com.stackroute.keepnote.controller;

import com.stackroute.keepnote.exception.NoteNotFoundExeption;
import com.stackroute.keepnote.model.Note;
import com.stackroute.keepnote.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/note")
public class NoteController {

    private NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping()
    public ResponseEntity addNote(@RequestBody Note note) {

        ResponseEntity responseEntity = null;


        if (noteService.createNote(note)) {
            responseEntity = new ResponseEntity(note, HttpStatus.CREATED);
        } else {
            responseEntity = new ResponseEntity("Oops something went wrong !! try again", HttpStatus.CONFLICT);
        }


        return responseEntity;
    }


    @DeleteMapping("/{userId}/{id}")
    public ResponseEntity deleteNote(@PathVariable String userId, @PathVariable() int id) {
        ResponseEntity responseEntity = null;

        if (noteService.deleteNote(userId, id)) {
            responseEntity = new ResponseEntity<>("Successfully deleted Note", HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>("Unable to purge please try again", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity deleteAllNotes(@PathVariable() String userId) {

        ResponseEntity<?> responseEntity = null;


        try {
            noteService.deleteAllNotes(userId);
            responseEntity = new ResponseEntity<>("Successfully deleted all notes", HttpStatus.OK);
        } catch (NoteNotFoundExeption exception) {
            responseEntity = new ResponseEntity<>("Unable to purge please try again", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }


    @PutMapping("/{userId}/{id}")
    public ResponseEntity updateNote(@PathVariable() String userId, @PathVariable() int id, @RequestBody Note note) {

        ResponseEntity responseEntity = null;

        try {
            Note updatedNote = noteService.updateNote(note, id, userId);
            responseEntity = new ResponseEntity<>(updatedNote, HttpStatus.OK);
        } catch (NoteNotFoundExeption e) {

            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }

    @GetMapping("{userId}/{noteId}")
    public ResponseEntity getNoteById(@PathVariable() String userId, @PathVariable() int noteId) {
        ResponseEntity responseEntity = null;
        try {
            Note note = noteService.getNoteByNoteId(userId, noteId);
            responseEntity = new ResponseEntity(note, HttpStatus.OK);
        } catch (NoteNotFoundExeption exception) {
            System.out.println(111);
            responseEntity = new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }


    @GetMapping("/{userId}")
    public ResponseEntity getAllNoteByUserId(@PathVariable() String userId) {
        ResponseEntity responseEntity = null;
        List<Note> userNotes = noteService.getAllNoteByUserId(userId);

        if (userNotes != null) {
            responseEntity = new ResponseEntity(userNotes, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>("You don't have any notes added in your list", HttpStatus.OK);
        }


        return responseEntity;
    }


}
