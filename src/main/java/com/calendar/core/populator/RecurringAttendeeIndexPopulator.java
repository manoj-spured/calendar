package com.calendar.core.populator;

import com.calendar.core.data.AttendeeEventDTO;
import com.calendar.core.data.AttendeeResponseDTO;
import com.calendar.core.data.RecurringEventInfoDTO;
import com.calendar.core.model.RecurringAttendeeIndex;
import org.springframework.stereotype.Service;

@Service
public class RecurringAttendeeIndexPopulator {

    public void populate(RecurringAttendeeIndex recurringAttendeeIndex, AttendeeEventDTO attendeeEventDTO)
    {
        AttendeeResponseDTO attendeeResponseDTO = new AttendeeResponseDTO();
        attendeeResponseDTO.set_type("CalendarEventUserResponse");
        attendeeResponseDTO.setAttendeeStatus(recurringAttendeeIndex.getAcceptanceStatus().toString());
        attendeeResponseDTO.setEventNote(recurringAttendeeIndex.getEventNote());
        RecurringEventInfoDTO recurringEventInfoDTO = attendeeEventDTO.getRecurringEventInfoDTO();
        recurringEventInfoDTO.setAttendeeResponseDTO(attendeeResponseDTO);
        attendeeEventDTO.setRecurringEventInfoDTO(recurringEventInfoDTO);
    }
}
