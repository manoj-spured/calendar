package com.calendar.core.service;

import com.calendar.core.model.AttendeeIndex;
import com.calendar.core.model.EventIndex;
import com.calendar.core.model.RecurringAttendeeIndex;
import com.calendar.core.model.RecurringIndex;
import com.calendar.core.repository.AttendeeIndexRepository;
import com.calendar.core.repository.EventIndexRepository;
import com.calendar.core.repository.RecurringAttendeeIndexRepository;
import com.calendar.core.repository.RecurringIndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarIndexServiceImpl implements CalendarIndexService {

    @Autowired
    AttendeeIndexRepository attendeeIndexRepository;
    @Autowired
    EventIndexRepository eventIndexRepository;
    @Autowired
    RecurringAttendeeIndexRepository recurringAttendeeIndexRepository;
    @Autowired
    RecurringIndexRepository recurringIndexRepository;

    @Override
    public Iterable<AttendeeIndex> findAllAttendees() {
        return attendeeIndexRepository.findAll();
    }

    @Override
    public void saveAttendees(List<AttendeeIndex> attendeeIndexList) {
        attendeeIndexRepository.saveAll(attendeeIndexList);
    }

    @Override
    public Iterable<EventIndex> findAllEvents() {
        return eventIndexRepository.findAll();
    }

    @Override
    public void saveEvents(List<EventIndex> eventIndexList) {
        eventIndexRepository.saveAll(eventIndexList);
    }

    @Override
    public Iterable<RecurringAttendeeIndex> findAllRecurringAttendees() {
        return recurringAttendeeIndexRepository.findAll();
    }

    @Override
    public void saveRecurringAttendees(List<RecurringAttendeeIndex> recurringAttendeeIndexList) {
        recurringAttendeeIndexRepository.saveAll(recurringAttendeeIndexList);
    }

    @Override
    public Iterable<RecurringIndex> findAllRecurringIndices() {
        return recurringIndexRepository.findAll();
    }

    @Override
    public void saveRecurringIndices(List<RecurringIndex> recurringIndexList) {
        recurringIndexRepository.saveAll(recurringIndexList);
    }

    @Override
    public Iterable<AttendeeIndex> findAllAttendees(String attendeeId, Long startTime, Long endTime) {

        return attendeeIndexRepository.findByIdAndStartTimeAndEndTime(attendeeId, startTime, endTime, Pageable.unpaged());
    }

    @Override
    public EventIndex findEvent(Long entityId) {
        return eventIndexRepository.findByEntityId(entityId);
    }

    @Override
    public RecurringIndex findRecurringIndex(Long entityId) {
        return recurringIndexRepository.findByEntityId(entityId);
    }

    @Override
    public RecurringAttendeeIndex findRecurringAttendee(Long recurringEntityId) {
        return recurringAttendeeIndexRepository.findByRecurringEntityId(recurringEntityId);
    }
}
