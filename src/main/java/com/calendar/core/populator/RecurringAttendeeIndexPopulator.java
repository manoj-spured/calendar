package com.calendar.core.populator;

import com.calendar.core.data.AttendeeEventDTO;
import com.calendar.core.data.AttendeeResponseDTO;
import com.calendar.core.data.RecurringEventInfoDTO;
import com.calendar.core.model.RecurringAttendeeIndex;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RecurringAttendeeIndexPopulator {

    public void populate(RecurringAttendeeIndex recurringAttendeeIndex, AttendeeEventDTO attendeeEventDTO)
    {
        AttendeeResponseDTO attendeeResponseDTO = new AttendeeResponseDTO();
        attendeeResponseDTO.set_type("CalendarEventUserResponse");
        attendeeResponseDTO.setAttendeeStatus(Objects.nonNull(recurringAttendeeIndex.getAcceptanceStatus()) ? recurringAttendeeIndex.getAcceptanceStatus().toString() : null);
        attendeeResponseDTO.setEventNote(recurringAttendeeIndex.getEventNote());
        List<RecurringEventInfoDTO> recurringEventInfoDTOList = attendeeEventDTO.getRecurringEventInfoDTOList();
        for (RecurringEventInfoDTO recurringEventInfoDTO : recurringEventInfoDTOList)
        {
            recurringEventInfoDTO.setAttendeeResponseDTO(attendeeResponseDTO);
        }
        attendeeEventDTO.setRecurringEventInfoDTOList(recurringEventInfoDTOList);
    }
}
