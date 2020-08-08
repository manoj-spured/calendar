package com.calendar.core.populator;

import com.calendar.core.data.AttendeeEventData;
import com.calendar.core.model.EventIndex;

public class EventIndexPopulator {

    public void populate(EventIndex eventIndex, AttendeeEventData attendeeEventData)
    {
        attendeeEventData.setEvent_id(eventIndex.getId());
        attendeeEventData.setEvent_boardId(eventIndex.getBoardId());
        attendeeEventData.setEvent_courseId(eventIndex.getCourseId());
        attendeeEventData.setEvent_groupId(eventIndex.getGroupId());
        attendeeEventData.setEvent_eventType(eventIndex.getEventType());
        attendeeEventData.setEvent_eventCategory(eventIndex.getEventCategory());
        attendeeEventData.setEvent_startTime(eventIndex.getStartTime());
        attendeeEventData.setEvent_endTime(eventIndex.getEndTime());
        attendeeEventData.setEvent_isRecurring(eventIndex.isRecurring());
        attendeeEventData.setEvent_entityStartTime(eventIndex.getEntityStartTime());
        attendeeEventData.setEvent_entityEndTime(eventIndex.getEntityEndTime());
        attendeeEventData.setEvent_occurrenceType(eventIndex.getOccurrenceType());
        attendeeEventData.setEvent_title(eventIndex.getTitle());
        attendeeEventData.setEvent_text(eventIndex.getText());
        attendeeEventData.setEvent_hostId(eventIndex.getHostId());
        attendeeEventData.setEvent_eventUrl(eventIndex.getEventUrl());
        attendeeEventData.setEvent_location(eventIndex.getLocation());
        attendeeEventData.setEvent_contactDetails(eventIndex.getContactDetails());
        attendeeEventData.setEvent_siteUrl(eventIndex.getSiteUrl());
        attendeeEventData.setEvent_status(eventIndex.getStatus());
        attendeeEventData.setEvent_targetURL(eventIndex.getTargetURL());
    }
}
