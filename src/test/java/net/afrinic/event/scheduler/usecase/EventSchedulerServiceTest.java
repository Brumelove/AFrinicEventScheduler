package net.afrinic.event.scheduler.usecase;

import net.afrinic.event.scheduler.usecase.exception.ErrorResponse;
import net.afrinic.event.scheduler.domain.TestErrorResponseAccess;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class EventSchedulerServiceTest {
    private EventSchedulerSessionService eventSchedulerSessionService = new EventSchedulerSessionService();
    private EventSchedulerService eventSchedulerService = new EventSchedulerService();
    private static int sessions;
    private static int minute;
    private static int hour;

    @BeforeAll
    static void init() {
        sessions = 2;
        hour = 3;
        minute = 50;

    }

    @Test
    void getEventDuration() {
        var testTime = "90min";
        var eventDuration = eventSchedulerSessionService.getEventDuration(testTime);

        Assertions.assertEquals(eventDuration, 90);
    }

    @Test
    void printEventScheduleResult() {

        var startTime = "09:00";
        var endTime = "10:00";
        var eventSpeaker = "brume";
        var eventTitle = "Title Test";

        var actualEventScheduleResult = "09:00-10:00: Title Test (brume)";
        var printEventScheduleResult = eventSchedulerService.printEventScheduleResult(startTime, endTime, eventTitle, eventSpeaker);

        Assertions.assertEquals(printEventScheduleResult, actualEventScheduleResult);

    }

    @Test
    void calculateLastSession() {

        var calculatedLastSession = eventSchedulerSessionService.calculateLastSession(sessions, hour, minute);

        Assertions.assertEquals(calculatedLastSession, "04:30");
    }

    @Test
    void timeToString() {
        var convertTimeTOString = eventSchedulerService.timeToString(hour, minute);

        Assertions.assertEquals(convertTimeTOString, "03:50");

    }

    @Test
    void scheduleEvent() {
        var filePath = "src/test/java/net/afrinic/event/scheduler/domain/test.csv";
        eventSchedulerService.scheduleEvent(filePath);
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

    @Test()
    void getSessionHour() {
        var getSessionHour = EventSchedulerSessionService.getSessionHour(sessions, hour, minute);

        Assertions.assertEquals(getSessionHour, 2);
        Assertions.assertNotEquals(getSessionHour, 1);
    }
}