package aditya.unittest;

import com.aditya.notificationservice.dtos.NotificationHistoryResponseDto;
import com.aditya.notificationservice.dtos.NotificationRequestDto;
import com.aditya.notificationservice.services.NotificationHistoryService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes= NotificationHistoryService.class)
public class NotificationHistoryServiceTest {

    @MockBean
    NotificationHistoryService notificationHistoryService;
    @Test
    void VerifyGetNotificationHistoryDetails()
    {
        List<NotificationHistoryResponseDto> notificationHistoryResponseDtos=new ArrayList<>();
        when(notificationHistoryService.getNotificationHistory())
                .thenReturn(notificationHistoryResponseDtos);
        assertEquals(notificationHistoryResponseDtos,notificationHistoryService.getNotificationHistory());
    }

}
