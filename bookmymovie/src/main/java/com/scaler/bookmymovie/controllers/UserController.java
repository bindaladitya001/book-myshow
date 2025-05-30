package com.scaler.bookmymovie.controllers;

import com.scaler.bookmymovie.dtos.AuthDto;
import com.scaler.bookmymovie.dtos.UserDto;
import com.scaler.bookmymovie.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    UserController(UserService userService){
        this.userService=userService;
    }


    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto){
        try {
            String result = userService.addUser(userDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody AuthDto authDto){
        try {
            String result = userService.login(authDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

}
