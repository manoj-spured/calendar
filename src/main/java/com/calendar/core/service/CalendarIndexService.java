package com.calendar.core.service;

import com.calendar.core.data.AttendeeEventDTO;
import com.calendar.core.model.AttendeeIndex;
import com.calendar.core.model.EventIndex;
import com.calendar.core.model.RecurringAttendeeIndex;
import com.calendar.core.model.RecurringIndex;

import java.util.List;

public interface CalendarIndexService
{

    List<AttendeeEventDTO> getAttendeeEventData(Long id, Long startTime, Long endTime);

    Iterable<AttendeeIndex> findAllAttendees();

    void saveAttendees(List<AttendeeIndex> attendeeIndexList);

    void saveAttendeesToDB(List<AttendeeIndex> attendeeIndexList);

    Iterable<EventIndex> findAllEvents();

    void saveEvents(List<EventIndex> eventIndexList);

    void saveEventsToDB(List<EventIndex> eventIndexList);

    Iterable<RecurringAttendeeIndex> findAllRecurringAttendees();

    void saveRecurringAttendees(List<RecurringAttendeeIndex> recurringAttendeeIndexList);

    void saveRecurringAttendeesToDB(List<RecurringAttendeeIndex> recurringAttendeeIndexList);

    Iterable<RecurringIndex> findAllRecurringIndices();

    void saveRecurringIndices(List<RecurringIndex> recurringIndexList);

    void saveRecurringIndicesToDB(List<RecurringIndex> recurringIndexList);
}
