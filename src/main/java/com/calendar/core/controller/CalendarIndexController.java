package com.calendar.core.controller;

import com.calendar.core.data.AttendeeEventDTO;
import com.calendar.core.data.CalendarSaveRequestDTO;
import com.calendar.core.model.AttendeeIndex;
import com.calendar.core.model.EventIndex;
import com.calendar.core.model.RecurringAttendeeIndex;
import com.calendar.core.model.RecurringIndex;
import com.calendar.core.service.CalendarIndexService;
import com.calendar.core.service.CalendarSaveService;
import com.spured.profiles.service.client.UserProfileServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping(value = "/attendee")
public class CalendarIndexController
{
    @Autowired
    CalendarIndexService calendarIndexService;
    @Autowired
    CalendarSaveService calendarSaveService;

    @PostMapping(value = "/attendeeevent")
    @ResponseBody
    public List<AttendeeEventDTO> getAttendeeEventData(@RequestBody String token)
    {
        int profileId = Integer.parseInt(token);
        Long startTime = Long.valueOf(token);
        Long endTime = Long.valueOf(token);
        Integer attendeeId = UserProfileServiceClient.getProfilesWithUserIds(new HashSet<>(profileId)).getUserIdProfilesMap().get(0).getUserId();
        return calendarIndexService.getAttendeeEventData(Long.valueOf(attendeeId), startTime, endTime);
    }

    @PostMapping(value = "/save-calendar-event")
    public String saveCalendarEvent(@RequestBody CalendarSaveRequestDTO calendarSaveRequestDTO) throws Exception
    {
        if (!calendarSaveService.saveCalendarEvent(calendarSaveRequestDTO).getStatusCode().equals(HttpStatus.ACCEPTED))
        {
            return "Bad Request";
        }
        return "Success";
    }

    /**
     * Method to fetch all attendees from the database.
     *
     * @return AttendeeIndex list
     */
    @GetMapping(value = "/getallattendees")
    public Iterable<AttendeeIndex> getAllAttendees()
    {
        return calendarIndexService.findAllAttendees();
    }

    /**
     * Method to save the attendees to Elastic search
     *
     * @param attendeeIndexList attendeeIndexList
     * @return Save response
     */
    @PostMapping(value = "/saveattendees")
    public String saveAttendees(@RequestBody List<AttendeeIndex> attendeeIndexList)
    {
        calendarIndexService.saveAttendees(attendeeIndexList);
        return "Records saved to ElasticSearch";
    }

    /**
     * Method to fetch all Events from the database.
     *
     * @return EventIndex list
     */
    @GetMapping(value = "/getallevents")
    public Iterable<EventIndex> getAllEvents()
    {
        return calendarIndexService.findAllEvents();
    }

    /**
     * Method to save the Events to Elastic search
     *
     * @param eventIndexList eventIndexList
     * @return Save response
     */
    @PostMapping(value = "/saveevents")
    public String saveEvents(@RequestBody List<EventIndex> eventIndexList)
    {
        calendarIndexService.saveEvents(eventIndexList);
        return "Records saved to ElasticSearch";
    }

    /**
     * Method to fetch all RecurringAttendees from the database.
     *
     * @return RecurringAttendeeIndex list
     */
    @GetMapping(value = "/getallrecurringattendees")
    public Iterable<RecurringAttendeeIndex> getAllRecurringAttendees()
    {
        return calendarIndexService.findAllRecurringAttendees();
    }

    /**
     * Method to save the Events to Elastic search
     *
     * @param recurringAttendeeIndexList recurringAttendeeIndexList
     * @return Save response
     */
    @PostMapping(value = "/saverecurringattendees")
    public String saveRecurringAttendees(@RequestBody List<RecurringAttendeeIndex> recurringAttendeeIndexList)
    {
        calendarIndexService.saveRecurringAttendees(recurringAttendeeIndexList);
        return "Records saved to ElasticSearch";
    }

    /**
     * Method to fetch all RecurringIndices from the database.
     *
     * @return RecurringIndex list
     */
    @GetMapping(value = "/getallrecurringindices")
    public Iterable<RecurringIndex> getAllRecurringIndices()
    {
        return calendarIndexService.findAllRecurringIndices();
    }

    /**
     * Method to save the Events to Elastic search
     *
     * @param recurringIndexList recurringIndexList
     * @return Save response
     */
    @PostMapping(value = "/saverecurringindices")
    public String saveRecurringIndices(@RequestBody List<RecurringIndex> recurringIndexList)
    {
        calendarIndexService.saveRecurringIndices(recurringIndexList);
        return "Records saved to ElasticSearch";
    }

    /**
     * Method to save the attendees in the database.
     *
     * @param attendeeIndexList attendeeIndexList
     * @return Save response
     */
    @PostMapping(value = "/saveAttendeesToDB")
    public String saveAttendeesToDB(@RequestBody List<AttendeeIndex> attendeeIndexList)
    {
        calendarIndexService.saveAttendeesToDB(attendeeIndexList);
        return "Records saved in the DB";
    }

    /**
     * Method to save the Events in the database.
     *
     * @param eventIndexList eventIndexList
     * @return Save response
     */
    @PostMapping(value = "/saveEventsToDB")
    public String saveEventsToDB(@RequestBody List<EventIndex> eventIndexList)
    {
        calendarIndexService.saveEventsToDB(eventIndexList);
        return "Records saved in the DB";
    }

    /**
     * Method to save the Events in the database.
     *
     * @param recurringAttendeeIndexList recurringAttendeeIndexList
     * @return Save response
     */
    @PostMapping(value = "/saveRecurringAttendeesToDB")
    public String saveRecurringAttendeesToDB(@RequestBody List<RecurringAttendeeIndex> recurringAttendeeIndexList)
    {
        calendarIndexService.saveRecurringAttendeesToDB(recurringAttendeeIndexList);
        return "Records saved in the DB";
    }

    /**
     * Method to save the Events in the database.
     *
     * @param recurringIndexList recurringIndexList
     * @return Save response
     */
    @PostMapping(value = "/saveRecurringIndicesToDB")
    public String saveRecurringIndicesToDB(@RequestBody List<RecurringIndex> recurringIndexList)
    {
        calendarIndexService.saveRecurringIndicesToDB(recurringIndexList);
        return "Records saved in the DB";
    }
}
