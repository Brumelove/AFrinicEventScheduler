package net.afrinic.event.scheduler.usecase.exception;

import net.afrinic.event.scheduler.usecase.EventSchedulerService;
import net.afrinic.event.scheduler.usecase.EventSchedulerSessionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EventExceptionTests {
    private EventSchedulerSessionService eventSchedulerSessionService = new EventSchedulerSessionService();
    private EventSchedulerService eventSchedulerService = new EventSchedulerService();


    @Test()
    public void incorrectSessionValueException() {
        Assertions.assertThrows(IncorrectSessionValueException.class, () -> {
            eventSchedulerSessionService.calculateLastSession(1, 3, 30);
        });

    }

    @Test()
    public void numberFormatException() {
        Assertions.assertThrows(EventDurationInvalidException.class, () -> {
            eventSchedulerSessionService.getEventDuration("90 min");
        });

    }

    @Test()
    public void filePathInvalidException() {
        var filePath = "input.csv";
        Assertions.assertThrows(FilePathInvalidException.class, () -> {
            eventSchedulerService.scheduleEvent(filePath);
        });

    }



}

