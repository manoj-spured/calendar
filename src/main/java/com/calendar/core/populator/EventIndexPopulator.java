package com.calendar.core.populator;

import com.calendar.core.data.AttendeeEventDTO;
import com.calendar.core.model.EventIndex;
import org.springframework.stereotype.Service;

@Service
public class EventIndexPopulator {

    public void populate(EventIndex eventIndex, AttendeeEventDTO attendeeEventDTO)
    {
        attendeeEventDTO.setEventType(eventIndex.getEventType().toString());
        attendeeEventDTO.setEventCategory(eventIndex.getEventCategory().toString());
        attendeeEventDTO.setStartTime(eventIndex.getStartTime().toString());
        attendeeEventDTO.setEndTime(eventIndex.getEndTime().toString());
        attendeeEventDTO.setEntityStartTime(eventIndex.getEntityStartTime().toString());
        attendeeEventDTO.setEntityEndTime(eventIndex.getEntityEndTime().toString());
        attendeeEventDTO.setOccurenceType(eventIndex.getOccurrenceType().toString());
        attendeeEventDTO.setIsRecurring(eventIndex.isRecurring());
        attendeeEventDTO.setEntityId(eventIndex.getEntityId().toString());
        attendeeEventDTO.setTitle(eventIndex.getTitle());
        attendeeEventDTO.setText(eventIndex.getText());
        attendeeEventDTO.setLocation(eventIndex.getLocation());
        attendeeEventDTO.setContactDetails(eventIndex.getContactDetails());
        attendeeEventDTO.setSiteUrl(eventIndex.getSiteUrl());
        attendeeEventDTO.setStatus(eventIndex.getStatus());
        attendeeEventDTO.setTargetUrl(eventIndex.getTargetURL());
    }
}
