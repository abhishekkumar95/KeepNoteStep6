package com.stackroute.keepnote.service;

import com.stackroute.keepnote.exception.ReminderNotCreatedException;
import com.stackroute.keepnote.exception.ReminderNotFoundException;
import com.stackroute.keepnote.model.Reminder;
import com.stackroute.keepnote.repository.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ReminderServiceImpl implements ReminderService {

    private ReminderRepository reminderRepository;

    @Autowired
    public ReminderServiceImpl(ReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    @Override
    public Reminder createReminder(Reminder reminder) throws ReminderNotCreatedException {

        reminder.setReminderCreationDate(new Date());
        Reminder reminder1 = reminderRepository.insert(reminder);
        if (reminder1 == null) {
            throw new ReminderNotCreatedException("Unable to create Reminder!!!! Please try again");
        }
        return reminder1;
    }

    @Override
    public boolean deleteReminder(String reminderId) throws ReminderNotFoundException {

        boolean status = false;
        Reminder fetchedReminder = null;

        try {
            fetchedReminder = reminderRepository.findById(reminderId).get();
            reminderRepository.delete(fetchedReminder);
            status = true;
        } catch (NoSuchElementException exception) {
            throw new ReminderNotFoundException("Reminder does not exists");
        }


        return status;
    }

    @Override
    public Reminder updateReminder(Reminder reminder, String reminderId) throws ReminderNotFoundException {
        Reminder fetchedReminder = null;

        try {

            fetchedReminder = reminderRepository.findById(reminderId).get();
            fetchedReminder.setReminderName(reminder.getReminderName());
            fetchedReminder.setReminderDescription(reminder.getReminderDescription());
            fetchedReminder.setReminderCreatedBy(reminder.getReminderCreatedBy());
            fetchedReminder.setReminderType(reminder.getReminderType());
            fetchedReminder.setReminderCreationDate(new Date());
            reminderRepository.save(fetchedReminder);

        } catch (NoSuchElementException exception) {
            throw new ReminderNotFoundException("Reminder does not exists");
        }

        return fetchedReminder;
    }

    @Override
    public Reminder getReminderById(String reminderId) throws ReminderNotFoundException {

        Reminder fetchedReminder = reminderRepository.findById(reminderId).get();
        if (fetchedReminder == null) {
            throw new ReminderNotFoundException("Reminder with id:- " + reminderId + "does not exists");
        }

        return fetchedReminder;
    }

    @Override
    public List<Reminder> getAllReminders() {
        return reminderRepository.findAll();
    }
}
