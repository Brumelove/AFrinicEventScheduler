package net.afrinic.event.scheduler.usecase;

import net.afrinic.event.scheduler.usecase.exception.ErrorResponse;
import net.afrinic.event.scheduler.usecase.exception.TestErrorResponseAccess;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Scanner;

@SpringBootTest
class EventSchedulerServiceTest {


    @Test
    void getEventDuration() {
        var testTime = "90min";
        var eventDuration = EventSchedulerService.getEventDuration(testTime);

        Assertions.assertEquals(eventDuration, 90);
    }

    @Test
    void printEventScheduleResult() {

        var startTime = "09:00";
        var endTime = "10:00";
        var eventSpeaker = "brume";
        var eventTitle = "Title Test";

        var actualEventScheduleResult = "09:00-10:00: Title Test (brume)";
        var printEventScheduleResult = EventSchedulerService.printEventScheduleResult(startTime, endTime, eventTitle, eventSpeaker);

        Assertions.assertEquals(printEventScheduleResult, actualEventScheduleResult);

    }

    @Test
    void calculateLastSession() {
        var sessions = 2;
        var hour = 1;
        var minute = 30;

        var calculatedLastSession = EventSchedulerService.calculateLastSession(sessions, hour, minute);

        Assertions.assertEquals(calculatedLastSession, "04:30");
    }

    @Test
    void timeToString() {
        var hour = 9;
        var minute = 30;

        var convertTimeTOString = EventSchedulerService.timeToString(hour, minute);

        Assertions.assertEquals(convertTimeTOString, "09:30");

    }

    @Test
    void scheduleEvent() {
        var filePath = "src/test/java/net/afrinic/event/scheduler/domain/test.csv";
        EventSchedulerService.scheduleEvent(filePath);
        Mockito.doNothing();
    }
    @Test()
    void getErrorResponse() {
        var errorResponse = new ErrorResponse();
        errorResponse.setErrorDescription("test Error");
        errorResponse.setResponseTime(LocalDateTime.now());

        var testErrorResponse = new TestErrorResponseAccess();
        testErrorResponse.setErrorResponse(errorResponse);

        Assertions.assertEquals(testErrorResponse.getErrorResponse(), errorResponse);
    }
}