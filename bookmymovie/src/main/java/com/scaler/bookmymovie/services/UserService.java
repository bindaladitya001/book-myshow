package com.scaler.bookmymovie.services;

import com.scaler.bookmymovie.config.AES256;
import com.scaler.bookmymovie.dtos.AuthDto;
import com.scaler.bookmymovie.dtos.UserDto;
import com.scaler.bookmymovie.exceptions.UserAlreadyExist;
import com.scaler.bookmymovie.exceptions.UserDoesNotExists;
import com.scaler.bookmymovie.models.User;
import com.scaler.bookmymovie.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public String addUser(UserDto userDto)
    {

        Optional<User> users = userRepository.findByEmailId(userDto.getEmailId());

        if (users.isPresent()) {
            throw new UserAlreadyExist();
        }

        User user=new User();
        AES256 aes=new AES256();

        user.setName(userDto.getName());
        user.setAge(userDto.getAge());
        user.setGender(userDto.getGender());
        user.setEmailId(userDto.getEmailId());
        user.setAdress(userDto.getAddress());
        user.setMobileNo(userDto.getMobileNo());
        user.setRoles(userDto.getRoles());
        user.setPassword(aes.encrypt(userDto.getPassword()));

        userRepository.save(user);

        return "User Registration is successfull";

    }

    public String login(AuthDto userDto)
    {

        Optional<User> users = userRepository.findByEmailId(userDto.getEmailId());

        if (users.isEmpty()) {
            throw new UserDoesNotExists();
        }
        AES256 aes=new AES256();
        Optional<User> users1 = userRepository.findByEmailId(userDto.getEmailId());
        System.out.println("PASSWORD ====>>"+aes.encrypt(userDto.getPassword()));
        if(aes.encrypt(userDto.getPassword()).equals(users1.get().getPassword()))
        {
            return users1.get().getPassword();
        }
        return "User Password is Wrong!";

    }

}
