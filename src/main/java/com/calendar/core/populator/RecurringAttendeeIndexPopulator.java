package com.calendar.core.populator;

import com.calendar.core.data.AttendeeEventData;
import com.calendar.core.model.AttendeeIndex;
import com.calendar.core.model.RecurringAttendeeIndex;

public class RecurringAttendeeIndexPopulator {

    public void populate(RecurringAttendeeIndex recurringAttendeeIndex, AttendeeEventData attendeeEventData)
    {
        attendeeEventData.setRecurringAttendee_id(recurringAttendeeIndex.getId());
        attendeeEventData.setRecurringAttendee_attendee(recurringAttendeeIndex.getAttendee());
        attendeeEventData.setRecurringAttendee_acceptanceStatus(recurringAttendeeIndex.getAcceptanceStatus());
        attendeeEventData.setRecurringAttendee_eventNote(recurringAttendeeIndex.getEventNote());
    }
}
