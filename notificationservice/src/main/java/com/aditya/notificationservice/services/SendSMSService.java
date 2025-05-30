package com.aditya.notificationservice.services;

import com.aditya.notificationservice.config.TwilioConfig;
import com.aditya.notificationservice.dtos.NotificationResponseDto;
import com.aditya.notificationservice.models.Status;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendSMSService {

    @Autowired
    TwilioConfig twilioConfig;

    //private static final Logger LOGGER= LoggerFactory.getLogger(SendSMSService.class);

    public NotificationResponseDto sendSMS(String to, String sms) {
        NotificationResponseDto notificationResponseDto=new NotificationResponseDto();
        try {
            Message  message = Message
                    .creator(new PhoneNumber(to)
                            , new PhoneNumber(twilioConfig.decodeValue(twilioConfig.getFromNumber()))
                            , sms)
                    .create();
            notificationResponseDto.setStatus(String.valueOf(Status.DELIVERED));
            //LOGGER.info("SMS Sent successfully....." + message.getStatus());
        }catch (Exception ex)
        {
            notificationResponseDto.setStatus(String.valueOf(Status.FAILED));
           // LOGGER.error("Kindly check if you mobile number is in verified account.....Error-->" + ex.getMessage());
        }
    return notificationResponseDto;
    }
}
