package com.stackroute.keepnote.controller;

import com.stackroute.keepnote.exception.ReminderNotCreatedException;
import com.stackroute.keepnote.exception.ReminderNotFoundException;
import com.stackroute.keepnote.model.Reminder;
import com.stackroute.keepnote.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reminder")
public class ReminderController {

    private ReminderService reminderService;

    @Autowired
    public ReminderController(ReminderService reminderService) {
        this.reminderService = reminderService;

    }

    @PostMapping
    public ResponseEntity createReminder(@RequestBody Reminder reminder) {

        ResponseEntity responseEntity = null;

        try {
            reminderService.createReminder(reminder);
            responseEntity = new ResponseEntity(reminder, HttpStatus.CREATED);
        } catch (ReminderNotCreatedException e) {
            responseEntity = new ResponseEntity("Unable to create reminder please try again", HttpStatus.CONFLICT);
        }

        return responseEntity;
    }


    @DeleteMapping("/{reminderId}")
    public ResponseEntity deleteReminder(@PathVariable() String reminderId) {

        ResponseEntity responseEntity = null;

        try {
            reminderService.deleteReminder(reminderId);
            responseEntity = new ResponseEntity("Deleted Successfully", HttpStatus.OK);
        } catch (ReminderNotFoundException exception) {
            responseEntity = new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }

    @PutMapping("/{reminderId}")
    public ResponseEntity updateReminder(@PathVariable() String reminderId, @RequestBody Reminder reminder) {

        ResponseEntity responseEntity = null;


        try {

            Reminder updatedReminder = reminderService.updateReminder(reminder, reminderId);
            responseEntity = new ResponseEntity(updatedReminder, HttpStatus.OK);
        } catch (ReminderNotFoundException exception) {
            responseEntity = new ResponseEntity("unable to update Reminder", HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }


    @GetMapping("/{reminderId}")
    public ResponseEntity getReminderById(@PathVariable() String reminderId) {

        ResponseEntity responseEntity = null;

        try {
            Reminder fetchedReminder = reminderService.getReminderById(reminderId);
            responseEntity = new ResponseEntity(fetchedReminder, HttpStatus.OK);
        } catch (ReminderNotFoundException exception) {
            responseEntity = new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }

    @GetMapping()
    public ResponseEntity getAllReminderById() {

        ResponseEntity responseEntity = null;
        List<Reminder> allReminders = reminderService.getAllReminders();
        if (!allReminders.isEmpty()) {
            responseEntity = new ResponseEntity(allReminders, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity("You dont have any reminders now", HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }
}
