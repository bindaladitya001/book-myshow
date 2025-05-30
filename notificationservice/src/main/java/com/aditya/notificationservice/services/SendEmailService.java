package com.aditya.notificationservice.services;

import com.aditya.notificationservice.dtos.NotificationResponseDto;
import com.aditya.notificationservice.models.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@EnableAutoConfiguration
public class SendEmailService  {

   // private static final Logger LOGGER= LoggerFactory.getLogger(SendEmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    public NotificationResponseDto sendEmail(
            String fromMail,
            String toEmail,
            String subject,
            String body)
    {
        NotificationResponseDto notificationResponseDto=new NotificationResponseDto();
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromMail);
            message.setTo(toEmail);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);
           // LOGGER.info("Mail Sent Successfully........."+mailSender);
            notificationResponseDto.setStatus(String.valueOf(Status.DELIVERED));
        }catch(Exception ex)
        {
           // LOGGER.error("Error -->" + ex.getMessage());
            notificationResponseDto.setStatus(String.valueOf(Status.FAILED));
        }
        return notificationResponseDto;
    }


}
