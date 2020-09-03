package net.afrinic.event.scheduler.usecase;


import lombok.extern.slf4j.Slf4j;
import net.afrinic.event.scheduler.usecase.exception.EventDurationInvalidException;
import net.afrinic.event.scheduler.usecase.exception.IncorrectSessionValueException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

@Slf4j
public class EventSchedulerService {
    private static final String PECHAKUCHA = "PechaKucha";
    private static final String REGEX = ";";


    public static void scheduleEvent(String filePath) {
        FileInputStream fileInputStream;
        Scanner scanner = null;

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
                var duration = getEventDuration(fileReaderTime);

                if (duration > capacity) { //If there is not enough time remaining in last opened session, new session should be opened
                    sessions++;
                    capacity = 210; //Total time a session has
                    if ((sessions % 2) == 1) { // Morning session
                        if (sessions != 1) { //If session is not the first session, networking event that comes after last afternoon session should be printed
                            if (hour < 4) {
                                hour = 4;
                                minute = 30;
                            }
                            var networkingTime = timeToString(hour, minute);
                            System.out.println(networkingTime + ": Networking\n");
                        }
                        System.out.println("Day " + ((sessions / 2) + 1) + ":\n");
                        System.out.println("Morning session:");
                        hour = 9;

                    } else { // Afternoon session
                        System.out.println("\n12:30-02:00: Lunch\n");
                        System.out.println("Afternoon session:");
                        hour = 2;
                    }
                    minute = 0;
                }
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

        } catch (FileNotFoundException fileNotFoundException) {

            System.out.println("No such file or directory ");
        } finally {
            try {
                scanner.close();
            } catch (NullPointerException e) {
                System.out.println("Scanner Input is Empty, please confirm you are passing the right file or directory ");

            }
        }



    }

    public static int getEventDuration(String fileReaderTime) {
        int duration = 0;
        try {

            if (fileReaderTime.contains(PECHAKUCHA)) {
                duration = 5;
            } else {
                duration = Integer.parseInt(fileReaderTime.substring(0, fileReaderTime.length() - 3));
            }
        }catch (NumberFormatException e){

            throw new EventDurationInvalidException();
        }

        return duration;  //minutes

    }

    public static String printEventScheduleResult(String startTime, String endTime, String
            fileReaderEvent, String fileReaderSpeaker) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append(startTime)
                .append("-").append(endTime).append(": ").append(fileReaderEvent).append(" (").append(fileReaderSpeaker).append(")");
        System.out.println(stringBuilder);

        return stringBuilder.toString();
    }

    public static String calculateLastSession(int sessions, int hour, int minute) {
        String networkingTime = null;
        if (sessions < 2) {
            throw new  IncorrectSessionValueException();
        }
        else if ((sessions % 2) == 0) //If last session is an afternoon session then networking event that comes after it should be printed
        {
            if (hour < 4 || ((hour == 4) && minute < 30)) {
                hour = 4;
                minute = 30;
            }
            networkingTime = timeToString(hour, minute);

            System.out.println(networkingTime + ": Networking\n");
        }


        return networkingTime;
    }

    public static String timeToString(int hour, int minute) {
        return String.format("%02d", hour) + ":" + String.format("%02d", minute);
    }
}