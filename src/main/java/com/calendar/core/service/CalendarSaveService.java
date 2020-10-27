package com.calendar.core.service;

import com.calendar.core.data.CalendarSaveRequestDTO;
import org.springframework.http.ResponseEntity;

public interface CalendarSaveService
{
    /**
     * Validate the incoming request for the event
     * @param calendarSaveRequestDTO calendarSaveRequestDTO
     * @return Response
     */
    ResponseEntity saveCalendarEvent(CalendarSaveRequestDTO calendarSaveRequestDTO) throws Exception;
}
