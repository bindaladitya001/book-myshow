package com.aditya.notificationservice.repositories;

import com.aditya.notificationservice.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {

    Optional<Notification> findAllByStatus(String status);
}
