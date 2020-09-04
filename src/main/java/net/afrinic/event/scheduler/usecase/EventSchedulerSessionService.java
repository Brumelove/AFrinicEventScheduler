package net.afrinic.event.scheduler.usecase;

import net.afrinic.event.scheduler.usecase.exception.EventDurationInvalidException;
import net.afrinic.event.scheduler.usecase.exception.IncorrectSessionValueException;

public class EventSchedulerSessionService {

     EventSchedulerService eventSchedulerService = new EventSchedulerService();

    public static int getSessionHour(int sessions, int hour, int minute) {
        if ((sessions % 2) == 1) { // Morning session
            hour = getMorningSessionHour(sessions, hour, minute);

        } else { // Afternoon session
            hour = getAfternoonSessionHour();
        }
        return hour;
    }

    public static int getAfternoonSessionHour() {
        int hour;
        System.out.println("\n12:30-02:00: Lunch\n");
        System.out.println("Afternoon session:");
        hour = 2;
        return hour;
    }

    public static int getMorningSessionHour(int sessions, int hour, int minute) {
        if (sessions != 1) { //If session is not the first session, networking event that comes after last afternoon session should be printed
            if (hour < 4) {
                hour = 4;
                minute = 30;
            }
            var eventSchedulerService = new EventSchedulerService();

            var networkingTime = eventSchedulerService.timeToString(hour, minute);
            System.out.println(networkingTime + ": Networking\n");
        }
        System.out.println("Day " + ((sessions / 2) + 1) + ":\n");
        System.out.println("Morning session:");
        hour = 9;
        return hour;
    }

    public  String calculateLastSession(int sessions, int hour, int minute) {
        String networkingTime = null;
        if (sessions < 2) {
            throw new IncorrectSessionValueException();
        } else if ((sessions % 2) == 0) //If last session is an afternoon session then networking event that comes after it should be printed
        {
            if (hour < 4 || ((hour == 4) && minute < 30)) {
                hour = 4;
                minute = 30;
            }
            networkingTime = eventSchedulerService.timeToString(hour, minute);

            System.out.println(networkingTime + ": Networking\n");
        }


        return networkingTime;
    }

    public  int getEventDuration(String fileReaderTime) {
        String PECHAKUCHA = "PechaKucha";

        int duration;
        try {

            if (fileReaderTime.contains(PECHAKUCHA)) {
                duration = 5;
            } else {
                duration = Integer.parseInt(fileReaderTime.substring(0, fileReaderTime.length() - 3));
            }
        } catch (NumberFormatException e) {

            throw new EventDurationInvalidException();
        }

        return duration;  //minutes

    }
}
