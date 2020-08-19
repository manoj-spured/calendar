package com.calendar.core.populator;

import com.calendar.core.data.AttendeeEventDTO;
import com.calendar.core.data.AttendeeProfileDTO;
import com.calendar.core.model.EventIndex;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EventIndexPopulator {

    public void populate(EventIndex eventIndex, AttendeeEventDTO attendeeEventDTO)
    {
        attendeeEventDTO.setEventType(Objects.nonNull(eventIndex.getEventType()) ? eventIndex.getEventType().toString() : null);
        attendeeEventDTO.setEventCategory(Objects.nonNull(eventIndex.getEventCategory()) ? eventIndex.getEventCategory().toString() : null);
        attendeeEventDTO.setStartTime(Objects.nonNull(eventIndex.getStartTime()) ? eventIndex.getStartTime().toString() : null);
        attendeeEventDTO.setEndTime(Objects.nonNull(eventIndex.getEndTime()) ? eventIndex.getEndTime().toString() : null);
        attendeeEventDTO.setEntityStartTime(Objects.nonNull(eventIndex.getEntityStartTime()) ? eventIndex.getEntityStartTime().toString() : null);
        attendeeEventDTO.setEntityEndTime(Objects.nonNull(eventIndex.getEntityEndTime()) ? eventIndex.getEntityEndTime().toString() : null);
        attendeeEventDTO.setOccurenceType(Objects.nonNull(eventIndex.getOccurrenceType()) ? eventIndex.getOccurrenceType().toString() : null);
        attendeeEventDTO.setIsRecurring(eventIndex.isRecurring());
        attendeeEventDTO.setEntityId(Objects.nonNull(eventIndex.getEntityId()) ? eventIndex.getEntityId().toString() : null);
        attendeeEventDTO.setTitle(eventIndex.getTitle());
        attendeeEventDTO.setText(eventIndex.getText());
        attendeeEventDTO.setLocation(eventIndex.getLocation());
        attendeeEventDTO.setContactDetails(eventIndex.getContactDetails());
        attendeeEventDTO.setSiteUrl(eventIndex.getSiteUrl());
        attendeeEventDTO.setStatus(eventIndex.getStatus());
        attendeeEventDTO.setTargetUrl(eventIndex.getTargetURL());

        AttendeeProfileDTO hostProfileDTO = new AttendeeProfileDTO();
        hostProfileDTO.set_type("UserBasicProfile");
        hostProfileDTO.setUserId(Objects.nonNull(eventIndex.getId()) ? eventIndex.getId().toString() : null);
        attendeeEventDTO.setHostProfile(hostProfileDTO);
    }
}
