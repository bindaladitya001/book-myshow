package com.scaler.userservice.repositories;

import com.scaler.userservice.models.User;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Lazy
public interface  UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsUserByEmail(String email);




}

