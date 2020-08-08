package com.calendar.core.populator;

import com.calendar.core.data.AttendeeEventData;
import com.calendar.core.model.AttendeeIndex;
import org.springframework.stereotype.Service;

@Service
public class AttendeeIndexPopulator {

    public void populate(AttendeeIndex attendeeIndex, AttendeeEventData attendeeEventData)
    {
        attendeeEventData.setAttendee_id(attendeeIndex.getId());
        attendeeEventData.setAttendee_eventType(attendeeIndex.getEventType());
        attendeeEventData.setAttendee_entityId(attendeeIndex.getEntityId());
        attendeeEventData.setAttendee_eventNote(attendeeIndex.getEventNote());
        attendeeEventData.setAttendee_acceptanceStatus(attendeeIndex.getAcceptanceStatus());
        attendeeEventData.setAttendee_responseStatus(attendeeIndex.getResponseStatus());
        attendeeEventData.setAttendee_score(attendeeIndex.getScore());
        attendeeEventData.setAttendee_startTime(attendeeIndex.getStartTime());
        attendeeEventData.setAttendee_endTime(attendeeIndex.getEndTime());
    }
}
