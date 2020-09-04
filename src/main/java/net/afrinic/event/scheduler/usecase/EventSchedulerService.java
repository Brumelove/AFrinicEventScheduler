package net.afrinic.event.scheduler.usecase;


import lombok.extern.slf4j.Slf4j;
import net.afrinic.event.scheduler.domain.EventTimeParameters;
import net.afrinic.event.scheduler.usecase.exception.FilePathInvalidException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static net.afrinic.event.scheduler.usecase.EventSchedulerSessionService.calculateLastSession;

@Slf4j
class EventSchedulerService {

    public static void scheduleEvent(String filePath) {
        String REGEX = ";";

        FileInputStream fileInputStream;
        Scanner scanner;

        int sessions = 0; //Number of sessions opened
        int capacity = 0; //minutes
        int hour = 0; //hour at an instance
        int minute = 0; //minute at an instance

        String[] event;

        try {
            fileInputStream = new FileInputStream(filePath);
            scanner = new Scanner(fileInputStream);
            while (scanner.hasNextLine()) {
                event = scanner.nextLine().split(REGEX);


                var fileReaderEvent = event[0];
                var fileReaderTime = event[1];
                var fileReaderSpeaker = event[2];


                //Parse duration of event in minutes
                var duration = EventSchedulerSessionService.getEventDuration(fileReaderTime);

                var eventTimeParameters = new EventTimeParameters(sessions, capacity, hour, minute, duration).invoke();
                sessions = eventTimeParameters.getSessions();
                capacity = eventTimeParameters.getCapacity();
                hour = eventTimeParameters.getHour();
                minute = eventTimeParameters.getMinute();
                //Calculate start and end time of this event and print relevant information about it
                capacity -= duration;
                var startTime = timeToString(hour, minute);
                minute += duration;
                hour += minute / 60;
                minute %= 60;
                String endTime = timeToString(hour, minute);
                printEventScheduleResult(startTime, endTime, fileReaderEvent, fileReaderSpeaker);


            }
            //calculate last session
            calculateLastSession(sessions, hour, minute);
            scanner.close();

        } catch (FileNotFoundException e) {
            throw new FilePathInvalidException();
        }


    }


    public static String printEventScheduleResult(String startTime, String endTime, String
            fileReaderEvent, String fileReaderSpeaker) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append(startTime)
                .append("-").append(endTime).append(": ").append(fileReaderEvent).append(" (").append(fileReaderSpeaker).append(")");
        System.out.println(stringBuilder);

        return stringBuilder.toString();
    }


    public static String timeToString(int hour, int minute) {
        return String.format("%02d", hour) + ":" + String.format("%02d", minute);
    }


}