package com.aditya.notificationservice.consumer;

import com.aditya.notificationservice.dtos.ConsumerResponseDto;
import com.aditya.notificationservice.dtos.NotificationRequestDto;
import com.aditya.notificationservice.dtos.NotificationResponseDto;
import com.aditya.notificationservice.dtos.UserDto;
import com.aditya.notificationservice.models.Notification;
import com.aditya.notificationservice.models.Status;
import com.aditya.notificationservice.models.UserContact;
import com.aditya.notificationservice.repositories.NotificationRepository;
import com.aditya.notificationservice.repositories.UserRepository;
import com.aditya.notificationservice.services.SendEmailService;
import com.aditya.notificationservice.services.SendSMSService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
public class RabbitMQJsonConsumner {

    private static final Logger LOGGER= LoggerFactory.getLogger(SendEmailService.class);
    @Autowired
    private  SendSMSService sendSMSService;

    private  String notificationStatus;

    @Value("${spring.mail.username}")
    private String fromMail;
    @Value("${spring.mail.subject}")
    private String emailSubject;
   @Autowired
    private  SendEmailService sendEmailService;

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private  NotificationRepository notificationRepository;


    public RabbitMQJsonConsumner(SendEmailService sendEmailService
                                 , SendSMSService sendSMSService
                                 , UserRepository userRepository
                                 , NotificationRepository notificationRepository)
    {
        this.sendEmailService = sendEmailService;
        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;
        this.sendSMSService = sendSMSService;
    }

    //Consumer is subscribed to the Queue
    @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
    public void consumeJsonMessage(NotificationRequestDto notificationDto){
        LOGGER.info(String .format("Json Message Received --> %s", notificationDto.toString()));
        NotificationResponseDto notificationResponseDto= new NotificationResponseDto();
        /*
        In request body I will receive a user id
        For that user id I will fetch the user contact (email address and mobile number) from the user data table
        Send sms and email to those contacts
        */

        UserDto userDto=new UserDto();

        ConsumerResponseDto consumerResponseDto=new ConsumerResponseDto();

        Optional<UserContact> userOptional;
        userOptional = userRepository.findById(Long.valueOf(notificationDto.getUserid()));

        //Check if user is present in DB or not
        if(userOptional.isEmpty()){
            notificationStatus= String.valueOf(Status.FAILED);
            LOGGER.info("User with "+notificationDto.getUserid()+" not present in DB");
        }
        else {
            userDto.setUserid(userOptional.get().getId());
            userDto.setEmail(userOptional.get().getEmail());
            userDto.setMobile(userOptional.get().getMobile());

            notificationResponseDto=sendEmailService.sendEmail(fromMail, userDto.getEmail(), emailSubject, notificationDto.getMessage());
            consumerResponseDto.setEmailStatus(notificationResponseDto.getStatus());
            String toNumber = "+917417331773"; // this need to be updated once i fetch the user mobile from the db
            //toNumber=userDto.getMobile(); //enable this when reading data from user table
            notificationResponseDto=sendSMSService.sendSMS(toNumber, notificationDto.getMessage());
            consumerResponseDto.setMobileStatus(notificationResponseDto.getStatus());
            Notification notification = new Notification();
            notification.setMessageDetails(notificationDto.getMessage());
            notification.setStatus((Status.DELIVERED));
            notification.setUser_id(notificationDto.getUserid());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            notification.setCreated_on(formatter.format(date));

            notificationRepository.save(notification);

            notificationStatus = String.valueOf(Status.DELIVERED);
        }

    }

}
