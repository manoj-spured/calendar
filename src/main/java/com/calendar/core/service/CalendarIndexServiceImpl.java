package com.calendar.core.service;

import com.calendar.core.data.AttendeeEventDTO;
import com.calendar.core.jparepository.AttendeeIndexJPARepository;
import com.calendar.core.jparepository.EventIndexJPARepository;
import com.calendar.core.jparepository.RecurringAttendeeIndexJPARepository;
import com.calendar.core.jparepository.RecurringIndexJPARepository;
import com.calendar.core.model.AttendeeIndex;
import com.calendar.core.model.EventIndex;
import com.calendar.core.model.RecurringAttendeeIndex;
import com.calendar.core.model.RecurringIndex;
import com.calendar.core.populator.AttendeeIndexPopulator;
import com.calendar.core.populator.EventIndexPopulator;
import com.calendar.core.populator.RecurringAttendeeIndexPopulator;
import com.calendar.core.populator.RecurringIndexPopulator;
import com.calendar.core.repository.AttendeeIndexRepository;
import com.calendar.core.repository.EventIndexRepository;
import com.calendar.core.repository.RecurringAttendeeIndexRepository;
import com.calendar.core.repository.RecurringIndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CalendarIndexServiceImpl implements CalendarIndexService
{

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
    @Autowired
    AttendeeIndexPopulator attendeeIndexPopulator;
    @Autowired
    EventIndexPopulator eventIndexPopulator;
    @Autowired
    RecurringAttendeeIndexPopulator recurringAttendeeIndexPopulator;
    @Autowired
    RecurringIndexPopulator recurringIndexPopulator;

    @Value("${useElasticSearch}")
    private boolean useElasticSearch;

    @Override
    public List<AttendeeEventDTO> getAttendeeEventData(Long attendee, Long startTime, Long endTime)
    {
        List<AttendeeEventDTO> attendeeEventDTOList = new ArrayList<>();
        List<Long> enitityIdList = new ArrayList<>();
        List<Long> eventEnitityIdList = new ArrayList<>();
        List<Long> recurringEnitityIdList = new ArrayList<>();
        Map<Long, AttendeeEventDTO> attedeeIndexMap = new HashMap<>();
        Map<Long, Long> recurringAttendeeMap = new HashMap<>();

        Iterable<AttendeeIndex> attendeeIndices = findAttendees(attendee, startTime, endTime);
        for (AttendeeIndex attendeeIndex : attendeeIndices)
        {
            AttendeeEventDTO attendeeEventDTO = new AttendeeEventDTO();
            attendeeIndexPopulator.populate(attendeeIndex, attendeeEventDTO);
            enitityIdList.add(attendeeIndex.getEntityId());
            attedeeIndexMap.put(attendeeIndex.getEntityId(), attendeeEventDTO);
        }

        Iterable<EventIndex> eventIndices = findEvents(enitityIdList);

        for (EventIndex eventIndex : eventIndices)
        {
            eventIndexPopulator.populate(eventIndex, attedeeIndexMap.get(eventIndex.getEntityId()));
            if (eventIndex.isRecurring())
            {
                eventEnitityIdList.add(eventIndex.getEntityId());
            }
        }
        Iterable<RecurringIndex> recurringIndices = findRecurringIndices(eventEnitityIdList);

        Map<Long, List<RecurringIndex>> recurringIndexMap = new HashMap<>();

        for (RecurringIndex recurringIndex : recurringIndices)
        {
            recurringAttendeeMap.put(recurringIndex.getRecurringEntityId(), recurringIndex.getEntityId());
            recurringEnitityIdList.add(recurringIndex.getRecurringEntityId());
            recurringIndexMap.computeIfAbsent(recurringIndex.getEntityId(), recurringIndexList -> new ArrayList<>()).add(recurringIndex);
        }

        recurringIndexMap.forEach((entityId, recurringIndexList) -> {
            recurringIndexPopulator.populate(recurringIndexList, attedeeIndexMap.get(entityId));
        });

        Iterable<RecurringAttendeeIndex> recurringAttendeeIndexList = findRecurringAttendees(recurringEnitityIdList, attendee);

        for (RecurringAttendeeIndex recurringAttendeeIndex : recurringAttendeeIndexList)
        {
            recurringAttendeeIndexPopulator.populate(recurringAttendeeIndex, attedeeIndexMap.get(recurringAttendeeMap.get(recurringAttendeeIndex.getRecurringEntityId())));
        }

        attedeeIndexMap.forEach((entityId, dto) -> attendeeEventDTOList.add(dto));
        return attendeeEventDTOList;
    }

    @Override
    public Iterable<AttendeeIndex> findAllAttendees()
    {
        return attendeeIndexRepository.findAll();
    }

    @Override
    public void saveAttendees(List<AttendeeIndex> attendeeIndexList)
    {
        attendeeIndexRepository.saveAll(attendeeIndexList);
    }

    @Override
    public Iterable<EventIndex> findAllEvents()
    {
        return eventIndexRepository.findAll();
    }

    @Override
    public void saveEvents(List<EventIndex> eventIndexList)
    {
        eventIndexRepository.saveAll(eventIndexList);
    }

    @Override
    public Iterable<RecurringAttendeeIndex> findAllRecurringAttendees()
    {
        return recurringAttendeeIndexRepository.findAll();
    }

    @Override
    public void saveRecurringAttendees(List<RecurringAttendeeIndex> recurringAttendeeIndexList)
    {
        recurringAttendeeIndexRepository.saveAll(recurringAttendeeIndexList);
    }

    @Override
    public Iterable<RecurringIndex> findAllRecurringIndices()
    {
        return recurringIndexRepository.findAll();
    }

    @Override
    public void saveRecurringIndices(List<RecurringIndex> recurringIndexList)
    {
        recurringIndexRepository.saveAll(recurringIndexList);
    }

    private Iterable<AttendeeIndex> findAttendees(Long attendee, Long startTime, Long endTime)
    {

        if (useElasticSearch)
        {
            return attendeeIndexRepository.findByIdAndStartTimeAndEndTime(attendee, startTime, endTime, Pageable.unpaged());
        } else
        {
            return attendeeIndexJPARepository.findAllByAttendeeId(attendee, startTime, endTime);
        }
    }

    private Iterable<EventIndex> findEvents(List<Long> entityIdList)
    {
        if (useElasticSearch)
        {
            return eventIndexRepository.findByEntityId(entityIdList, Pageable.unpaged());
        } else
        {
            return eventIndexJPARepository.findAllByEntityIdIn(entityIdList);
        }
    }

    private Iterable<RecurringIndex> findRecurringIndices(List<Long> entityIdList)
    {
        if (useElasticSearch)
        {
            return recurringIndexRepository.findByEntityId(entityIdList, Pageable.unpaged());
        } else
        {
            return recurringIndexJPARepository.findAllByEntityIdIn(entityIdList, Pageable.unpaged());
        }
    }

    private Iterable<RecurringAttendeeIndex> findRecurringAttendees(List<Long> recurringEntityIdList, Long attendeeId)
    {
        if (useElasticSearch)
        {
            return recurringAttendeeIndexRepository.findByRecurringEntityId(recurringEntityIdList, attendeeId, Pageable.unpaged());
        } else
        {
            return recurringAttendeeIndexJPARepository.findAllByRecurringEntityIdInAndAttendee(recurringEntityIdList, attendeeId);
        }
    }

    @Override
    public void saveAttendeesToDB(List<AttendeeIndex> attendeeIndexList)
    {
        attendeeIndexJPARepository.saveAll(attendeeIndexList);
    }

    @Override
    public void saveEventsToDB(List<EventIndex> eventIndexList)
    {
        eventIndexJPARepository.saveAll(eventIndexList);
    }

    @Override
    public void saveRecurringAttendeesToDB(List<RecurringAttendeeIndex> recurringAttendeeIndexList)
    {
        recurringAttendeeIndexJPARepository.saveAll(recurringAttendeeIndexList);
    }

    @Override
    public void saveRecurringIndicesToDB(List<RecurringIndex> recurringIndexList)
    {
        recurringIndexJPARepository.saveAll(recurringIndexList);
    }
}
