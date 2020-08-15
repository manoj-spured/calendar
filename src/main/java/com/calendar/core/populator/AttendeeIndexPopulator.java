package com.calendar.core.populator;

import com.calendar.core.data.AttendeeEventDTO;
import com.calendar.core.data.AttendeeProfileDTO;
import com.calendar.core.data.ResponseDTO;
import com.calendar.core.model.AttendeeIndex;
import org.springframework.stereotype.Service;

@Service
public class AttendeeIndexPopulator {

    public void populate(AttendeeIndex attendeeIndex, AttendeeEventDTO attendeeEventDTO)
    {
        AttendeeProfileDTO attendeeProfileDTO = new AttendeeProfileDTO();
        attendeeProfileDTO.set_type("UserBasicProfile");
        attendeeProfileDTO.setUserId(attendeeIndex.getId().toString());
        attendeeEventDTO.setAttendeeProfileDTO(attendeeProfileDTO);

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setAttendeeStatus(attendeeIndex.getAcceptanceStatus().toString());
        responseDTO.setResponseStatus(attendeeIndex.getResponseStatus().toString());
        responseDTO.setGrade("");
        attendeeEventDTO.setResponseDTO(responseDTO);

        AttendeeProfileDTO hostProfileDTO = new AttendeeProfileDTO();
        hostProfileDTO.set_type("UserBasicProfile");
        hostProfileDTO.setUserId(attendeeIndex.getId().toString());
        attendeeEventDTO.setAttendeeProfileDTO(hostProfileDTO);
    }
}
