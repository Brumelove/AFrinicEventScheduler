package net.afrinic.event.scheduler.usecase.exception;

import net.afrinic.event.scheduler.usecase.EventSchedulerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

@SpringBootTest
public class EventExceptionTests {

    @Test()
    public void incorrectSessionValueException(){
        Assertions.assertThrows(IncorrectSessionValueException.class, () -> {
            EventSchedulerService.calculateLastSession(1, 3, 30);
        });

    }
    @Test()
    public void numberFormatException(){
        Assertions.assertThrows(EventDurationInvalidException.class, () -> {
            EventSchedulerService.getEventDuration("90 min");
        });

    }

    @Test
    void scheduleEventException() {
        FileInputStream fileInputStream;
        Scanner scanner = null;
        var filePath = "input.csv";
        try {
            fileInputStream = new FileInputStream(filePath);
            scanner = new Scanner(fileInputStream);
        } catch (FileNotFoundException ex) {
            Assertions.assertThrows(EventDurationInvalidException.class, () -> {
                EventSchedulerService.getEventDuration(filePath);
            });
        }finally {
            try {
                scanner.close();
            } catch (NullPointerException e) {
                Assertions.assertThrows(EventDurationInvalidException.class, () -> {
                    EventSchedulerService.getEventDuration(filePath);
                });            }
        }

    }
}
