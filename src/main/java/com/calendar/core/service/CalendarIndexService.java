package com.calendar.core.service;

import com.calendar.core.model.AttendeeIndex;
import com.calendar.core.model.EventIndex;
import com.calendar.core.model.RecurringAttendeeIndex;
import com.calendar.core.model.RecurringIndex;

import java.util.List;

public interface CalendarIndexService {

    Iterable<AttendeeIndex> findAllAttendees();

    void saveAttendees(List<AttendeeIndex> attendeeIndexList);

    Iterable<EventIndex> findAllEvents();

    void saveEvents(List<EventIndex> eventIndexList);

    Iterable<RecurringAttendeeIndex> findAllRecurringAttendees();

    void saveRecurringAttendees(List<RecurringAttendeeIndex> recurringAttendeeIndexList);

    Iterable<RecurringIndex> findAllRecurringIndices();

    void saveRecurringIndices(List<RecurringIndex> recurringIndexList);

    Iterable<AttendeeIndex> findAllAttendees(String attendeeId, Long startTime, Long endTime);

    EventIndex findEvent(Long entityId);

    RecurringIndex findRecurringIndex(Long entityId);

    RecurringAttendeeIndex findRecurringAttendee(Long recurringEntityId);
}
