package com.calendar.core.populator;

import com.calendar.core.data.AttendeeEventDTO;
import com.calendar.core.data.RecurringEventInfoDTO;
import com.calendar.core.model.RecurringIndex;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class RecurringIndexPopulator {

    public void populate(List<RecurringIndex> recurringIndexList, AttendeeEventDTO attendeeEventDTO)
    {
        List<RecurringEventInfoDTO> recurringEventInfoDTOList = new ArrayList<>();
        for (RecurringIndex recurringIndex: recurringIndexList)
        {
            RecurringEventInfoDTO recurringEventInfoDTO = new RecurringEventInfoDTO();
            recurringEventInfoDTO.set_type("RecurringEvent");
            recurringEventInfoDTO.setRecurringEntityId(Objects.nonNull(recurringEventInfoDTO.getRecurringEntityId()) ? recurringEventInfoDTO.getRecurringEntityId() : null);
            recurringEventInfoDTO.setStartTime(Objects.nonNull(recurringEventInfoDTO.getStartTime()) ? recurringEventInfoDTO.getStartTime() : null);
            recurringEventInfoDTO.setEndTime(Objects.nonNull(recurringEventInfoDTO.getEndTime()) ? recurringEventInfoDTO.getEndTime() : null);
            recurringEventInfoDTO.setStatus(recurringEventInfoDTO.getStatus());
            recurringEventInfoDTOList.add(recurringEventInfoDTO);
        }
        attendeeEventDTO.setRecurringEventInfoDTOList(recurringEventInfoDTOList);
    }
}
