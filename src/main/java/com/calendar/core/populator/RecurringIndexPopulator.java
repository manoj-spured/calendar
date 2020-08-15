package com.calendar.core.populator;

import com.calendar.core.data.AttendeeEventDTO;
import com.calendar.core.data.RecurringEventInfoDTO;
import com.calendar.core.model.RecurringIndex;
import org.springframework.stereotype.Service;

@Service
public class RecurringIndexPopulator {

    public void populate(RecurringIndex recurringIndex, AttendeeEventDTO attendeeEventDTO)
    {
        RecurringEventInfoDTO recurringEventInfoDTO = new RecurringEventInfoDTO();
        recurringEventInfoDTO.set_type("RecurringEvent");
        recurringEventInfoDTO.setRecurringEntityId(recurringIndex.getRecurringEntityId().toString());
        recurringEventInfoDTO.setStartTime(recurringIndex.getStartTime().toString());
        recurringEventInfoDTO.setEndTime(recurringIndex.getEndTime().toString());
        recurringEventInfoDTO.setStatus(recurringIndex.getStatus());
        attendeeEventDTO.setRecurringEventInfoDTO(recurringEventInfoDTO);
    }
}
