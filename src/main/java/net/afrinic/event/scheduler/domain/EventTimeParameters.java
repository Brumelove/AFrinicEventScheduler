package net.afrinic.event.scheduler.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import static net.afrinic.event.scheduler.usecase.EventSchedulerSessionService.getSessionHour;

@AllArgsConstructor
@Getter
@Setter
public
class EventTimeParameters {
    private int sessions;
    private int capacity;
    private int hour;
    private int minute;
    private int duration;


    public  EventTimeParameters invoke() {
        if (duration > capacity) { //If there is not enough time remaining in last opened session, new session should be opened
            sessions++;
            capacity = 210; //Total time a session has
            hour = getSessionHour(sessions, hour, minute);
            minute = 0;
        }
        return this;
    }
}