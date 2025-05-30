package com.aditya.notificationservice.repositories;

import com.aditya.notificationservice.models.UserContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends JpaRepository<UserContact,Long> {


}
