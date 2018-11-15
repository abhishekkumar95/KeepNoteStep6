package com.stackroute.keepnote.repository;

import com.stackroute.keepnote.model.Reminder;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReminderRepository extends MongoRepository<Reminder, String> {


}
