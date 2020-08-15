package com.calendar.core.populator;

import com.calendar.core.data.AttendeeEventDTO;
import com.calendar.core.data.AttendeeProfileDTO;
import com.calendar.core.data.ResponseDTO;
import com.calendar.core.model.AttendeeIndex;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AttendeeIndexPopulator {

    public void populate(AttendeeIndex attendeeIndex, AttendeeEventDTO attendeeEventDTO)
    {
        attendeeEventDTO.set_type("CalendarEvent");
        AttendeeProfileDTO attendeeProfileDTO = new AttendeeProfileDTO();
        attendeeProfileDTO.set_type("UserBasicProfile");
        attendeeProfileDTO.setUserId(Objects.nonNull(attendeeIndex.getId()) ? attendeeIndex.getId().toString() : null);
        attendeeEventDTO.setAttendeeProfileDTO(attendeeProfileDTO);

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setAttendeeStatus(Objects.nonNull(attendeeIndex.getAcceptanceStatus()) ? attendeeIndex.getAcceptanceStatus().toString() : null);
        responseDTO.setResponseStatus(Objects.nonNull(attendeeIndex.getResponseStatus()) ? attendeeIndex.getResponseStatus().toString() : null);
        responseDTO.setGrade("");
        attendeeEventDTO.setResponseDTO(responseDTO);

        AttendeeProfileDTO hostProfileDTO = new AttendeeProfileDTO();
        hostProfileDTO.set_type("UserBasicProfile");
        hostProfileDTO.setUserId(Objects.nonNull(attendeeIndex.getId()) ? attendeeIndex.getId().toString() : null);
        attendeeEventDTO.setHostProfile(hostProfileDTO);
    }
}
