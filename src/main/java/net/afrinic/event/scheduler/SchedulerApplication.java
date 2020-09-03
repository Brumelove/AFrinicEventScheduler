package net.afrinic.event.scheduler;

import net.afrinic.event.scheduler.usecase.EventSchedulerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SchedulerApplication {

	public static void main(String[] args) {
		var filePath = "src/main/java/net/afrinic/event/scheduler/domain/input.csv";
		EventSchedulerService.scheduleEvent(filePath);
	}
}
