package com.calendar.core.populator;

import com.calendar.core.data.AttendeeEventData;
import com.calendar.core.model.RecurringIndex;

public class RecurringIndexPopulator {

    public void populate(RecurringIndex recurringIndex, AttendeeEventData attendeeEventData)
    {
        attendeeEventData.setRecurring_id(recurringIndex.getId());
        attendeeEventData.setRecurring_recurringEntityId(recurringIndex.getRecurringEntityId());
        attendeeEventData.setRecurring_startTime(recurringIndex.getStartTime());
        attendeeEventData.setRecurring_endTime(recurringIndex.getEndTime());
        attendeeEventData.setRecurring_status(recurringIndex.getStatus());
        
    }
}
