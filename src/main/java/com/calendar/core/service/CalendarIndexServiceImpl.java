package com.calendar.core.service;

import com.calendar.core.jparepository.AttendeeIndexJPARepository;
import com.calendar.core.jparepository.EventIndexJPARepository;
import com.calendar.core.jparepository.RecurringAttendeeIndexJPARepository;
import com.calendar.core.jparepository.RecurringIndexJPARepository;
import com.calendar.core.model.AttendeeIndex;
import com.calendar.core.model.EventIndex;
import com.calendar.core.model.RecurringAttendeeIndex;
import com.calendar.core.model.RecurringIndex;
import com.calendar.core.repository.AttendeeIndexRepository;
import com.calendar.core.repository.EventIndexRepository;
import com.calendar.core.repository.RecurringAttendeeIndexRepository;
import com.calendar.core.repository.RecurringIndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Autowired
    AttendeeIndexJPARepository attendeeIndexJPARepository;
    @Autowired
    EventIndexJPARepository eventIndexJPARepository;
    @Autowired
    RecurringIndexJPARepository recurringIndexJPARepository;
    @Autowired
    RecurringAttendeeIndexJPARepository recurringAttendeeIndexJPARepository;

    @Value("${useElasticSearch}")
    private boolean useElasticSearch;

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
    public Iterable<AttendeeIndex> findAllAttendees(Long attendeeId, Long startTime, Long endTime) {

        if(useElasticSearch) {
            return attendeeIndexRepository.findByIdAndStartTimeAndEndTime(attendeeId, startTime, endTime, Pageable.unpaged());
        } else {
            return attendeeIndexJPARepository.findByIdAndStartTimeAndEndTime(attendeeId, startTime, endTime, Pageable.unpaged());
        }
    }

    @Override
    public Iterable<EventIndex> findEvent(List<Long> entityIdList) {
        if(useElasticSearch) {
            return eventIndexRepository.findByEntityId(entityIdList, Pageable.unpaged());
        } else {
            return eventIndexJPARepository.findByEntityId(entityIdList, Pageable.unpaged());
        }
    }

    @Override
    public Iterable<RecurringIndex> findRecurringIndex(List<Long> entityIdList) {
        if(useElasticSearch) {
            return recurringIndexRepository.findByEntityId(entityIdList, Pageable.unpaged());
        } else {
            return recurringIndexJPARepository.findByEntityId(entityIdList, Pageable.unpaged());
        }
    }

    @Override
    public Iterable<RecurringAttendeeIndex> findRecurringAttendee(List<Long> recurringEntityIdList) {
        if(useElasticSearch) {
            return recurringAttendeeIndexRepository.findByRecurringEntityId(recurringEntityIdList, Pageable.unpaged());
        } else {
            return recurringAttendeeIndexJPARepository.findByRecurringEntityId(recurringEntityIdList, Pageable.unpaged());
        }
    }

    @Override
    public void saveAttendeesToDB(List<AttendeeIndex> attendeeIndexList) {
        attendeeIndexJPARepository.saveAll(attendeeIndexList);
    }

    @Override
    public void saveEventsToDB(List<EventIndex> eventIndexList) {
        eventIndexJPARepository.saveAll(eventIndexList);
    }

    @Override
    public void saveRecurringAttendeesToDB(List<RecurringAttendeeIndex> recurringAttendeeIndexList) {
        recurringAttendeeIndexJPARepository.saveAll(recurringAttendeeIndexList);
    }

    @Override
    public void saveRecurringIndicesToDB(List<RecurringIndex> recurringIndexList) {
        recurringIndexJPARepository.saveAll(recurringIndexList);
    }
}
