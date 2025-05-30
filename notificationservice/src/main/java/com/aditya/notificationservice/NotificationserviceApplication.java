package com.aditya.notificationservice;


import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Configuration;

@SpringBootApplication()
@Configuration
public class NotificationserviceApplication {

	@Value("${twilio.accountSID}")
	private String accountSSID;

	@Value("${twilio.authToken}")
	private String auth;
	public static void main(String[] args) {
		SpringApplication.run(NotificationserviceApplication.class, args);
	}

	@PostConstruct
	public void setup()
	{

		Twilio.init(accountSSID,auth);
	}
}
