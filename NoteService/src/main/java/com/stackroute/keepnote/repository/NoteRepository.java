package com.stackroute.keepnote.repository;

import com.stackroute.keepnote.model.NoteUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends MongoRepository<NoteUser, String> {


}
