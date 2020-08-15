package com.calendar.core.populator;

import com.calendar.core.data.AttendeeEventDTO;
import com.calendar.core.data.RecurringEventInfoDTO;
import com.calendar.core.model.RecurringIndex;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RecurringIndexPopulator {

    public void populate(RecurringIndex recurringIndex, AttendeeEventDTO attendeeEventDTO)
    {
        RecurringEventInfoDTO recurringEventInfoDTO = new RecurringEventInfoDTO();
        recurringEventInfoDTO.set_type("RecurringEvent");
        recurringEventInfoDTO.setRecurringEntityId(Objects.nonNull(recurringIndex.getRecurringEntityId()) ? recurringIndex.getRecurringEntityId().toString() : null);
        recurringEventInfoDTO.setStartTime(Objects.nonNull(recurringIndex.getStartTime()) ? recurringIndex.getStartTime().toString() : null);
        recurringEventInfoDTO.setEndTime(Objects.nonNull(recurringIndex.getEndTime()) ? recurringIndex.getEndTime().toString() : null);
        recurringEventInfoDTO.setStatus(recurringIndex.getStatus());
        attendeeEventDTO.setRecurringEventInfoDTO(recurringEventInfoDTO);
    }
}
