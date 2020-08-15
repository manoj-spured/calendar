package com.calendar.core.controller;

import com.calendar.core.data.AttendeeEventDTO;
import com.calendar.core.model.AttendeeIndex;
import com.calendar.core.model.EventIndex;
import com.calendar.core.model.RecurringAttendeeIndex;
import com.calendar.core.model.RecurringIndex;
import com.calendar.core.populator.AttendeeIndexPopulator;
import com.calendar.core.populator.EventIndexPopulator;
import com.calendar.core.populator.RecurringAttendeeIndexPopulator;
import com.calendar.core.populator.RecurringIndexPopulator;
import com.calendar.core.service.CalendarIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value= "/attendee")
public class CalendarIndexController {

    @Autowired
    CalendarIndexService calendarIndexService;
    @Autowired
    AttendeeIndexPopulator attendeeIndexPopulator;
    @Autowired
    EventIndexPopulator eventIndexPopulator;
    @Autowired
    RecurringAttendeeIndexPopulator recurringAttendeeIndexPopulator;
    @Autowired
    RecurringIndexPopulator recurringIndexPopulator;

    @PostMapping(value = "/attendeeevent")
    @ResponseBody
    public List<AttendeeEventDTO> getAttendeeEventData(@RequestBody AttendeeIndex attendee)
    {
        List<AttendeeEventDTO> attendeeEventDTOList = new ArrayList<>();
        Iterable<AttendeeIndex> attendeeIndices = calendarIndexService.findAllAttendees(attendee.getId(), attendee.getStartTime(),attendee.getEndTime());

        for (AttendeeIndex attendeeIndex : attendeeIndices)
        {
            AttendeeEventDTO attendeeEventDTO = new AttendeeEventDTO();

            EventIndex eventIndex = calendarIndexService.findEvent(attendeeIndex.getEntityId());

            if (eventIndex.isRecurring())
            {
                RecurringIndex recurringIndex = calendarIndexService.findRecurringIndex(attendeeIndex.getEntityId());
                RecurringAttendeeIndex recurringAttendeeIndex = calendarIndexService.findRecurringAttendee(recurringIndex.getRecurringEntityId());
                recurringIndexPopulator.populate(recurringIndex, attendeeEventDTO);
                recurringAttendeeIndexPopulator.populate(recurringAttendeeIndex, attendeeEventDTO);
            }
            eventIndexPopulator.populate(eventIndex, attendeeEventDTO);
            attendeeIndexPopulator.populate(attendeeIndex, attendeeEventDTO);

            attendeeEventDTOList.add(attendeeEventDTO);
        }

        return attendeeEventDTOList;
    }

    /**
     * Method to fetch all attendees from the database.
     * @return
     */
    @GetMapping(value= "/getallattendees")
    public Iterable<AttendeeIndex> getAllAttendees() {
        return calendarIndexService.findAllAttendees();
    }

    /**
     * Method to save the attendees in the database.
     * @param attendeeIndexList
     * @return
     */
    @PostMapping(value= "/saveattendees")
    public String saveAttendees(@RequestBody List<AttendeeIndex> attendeeIndexList) {
        calendarIndexService.saveAttendees(attendeeIndexList);
        return "Records saved in the db.";
    }

    /**
     * Method to fetch all Events from the database.
     * @return
     */
    @GetMapping(value= "/getallevents")
    public Iterable<EventIndex> getAllEvents() {
        return calendarIndexService.findAllEvents();
    }

    /**
     * Method to save the Events in the database.
     * @param eventIndexList
     * @return
     */
    @PostMapping(value= "/saveevents")
    public String saveEvents(@RequestBody List<EventIndex> eventIndexList) {
        calendarIndexService.saveEvents(eventIndexList);
        return "Records saved in the db.";
    }

    /**
     * Method to fetch all RecurringAttendees from the database.
     * @return
     */
    @GetMapping(value= "/getallrecurringattendees")
    public Iterable<RecurringAttendeeIndex> getAllRecurringAttendees() {
        return calendarIndexService.findAllRecurringAttendees();
    }

    /**
     * Method to save the Events in the database.
     * @param recurringAttendeeIndexList
     * @return
     */
    @PostMapping(value= "/saverecurringattendees")
    public String saveRecurringAttendees(@RequestBody List<RecurringAttendeeIndex> recurringAttendeeIndexList) {
        calendarIndexService.saveRecurringAttendees(recurringAttendeeIndexList);
        return "Records saved in the db.";
    }

    /**
     * Method to fetch all RecurringIndices from the database.
     * @return
     */
    @GetMapping(value= "/getallrecurringindices")
    public Iterable<RecurringIndex> getAllRecurringIndices() {
        return calendarIndexService.findAllRecurringIndices();
    }

    /**
     * Method to save the Events in the database.
     * @param recurringIndexList
     * @return
     */
    @PostMapping(value= "/saverecurringindices")
    public String saveRecurringIndices(@RequestBody List<RecurringIndex> recurringIndexList) {
        calendarIndexService.saveRecurringIndices(recurringIndexList);
        return "Records saved in the db.";
    }
}
