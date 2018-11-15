package com.stackroute.keepnote.repository;

import com.stackroute.keepnote.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAutheticationRepository extends JpaRepository<User, String> {

    User findByUserIdAndUserPassword(String userId, String userPassword);
}
