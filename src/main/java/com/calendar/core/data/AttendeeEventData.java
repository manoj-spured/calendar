package com.calendar.core.data;

import com.calendar.core.model.GenericItem;
import com.calendar.core.model.enums.AcceptanceStatus;
import com.calendar.core.model.enums.ResponseStatus;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class AttendeeEventData {

    String attendee_id;
    Long attendee_eventType;
    Long attendee_entityId;
    String attendee_eventNote;
    AcceptanceStatus attendee_acceptanceStatus;
    ResponseStatus attendee_responseStatus;
    String attendee_score;
    Long attendee_startTime;
    Long attendee_endTime;

    String event_id;
    Long event_eventType;
    Long event_eventCategory;
    Timestamp event_startTime;
    Timestamp event_endTime;
    boolean event_isRecurring;
    Date event_eventStartDate;
    Date event_eventEndDate;
    String event_title;
    String event_text;
    Long event_hostId;
    String event_eventUrl;
    String event_location;
    String event_contactDetails;
    String event_siteUrl;
    String event_status;
    String event_targetURL;

    String recurring_id;
    Long recurring_recurringEntityId;
    Timestamp recurring_startTimestamp;
    Timestamp recurring_startEndstamp;
    String recurring_status;

    String recurringAttendee_id;
    Long recurringAttendee_attendee;
    AcceptanceStatus recurringAttendee_acceptanceStatus;
    String recurringAttendee_eventNote;

    public String getAttendee_id() {
        return attendee_id;
    }

    public void setAttendee_id(String attendee_id) {
        this.attendee_id = attendee_id;
    }

    public Long getAttendee_eventType() {
        return attendee_eventType;
    }

    public void setAttendee_eventType(Long attendee_eventType) {
        this.attendee_eventType = attendee_eventType;
    }

    public Long getAttendee_entityId() {
        return attendee_entityId;
    }

    public void setAttendee_entityId(Long attendee_entityId) {
        this.attendee_entityId = attendee_entityId;
    }

    public String getAttendee_eventNote() {
        return attendee_eventNote;
    }

    public void setAttendee_eventNote(String attendee_eventNote) {
        this.attendee_eventNote = attendee_eventNote;
    }

    public AcceptanceStatus getAttendee_acceptanceStatus() {
        return attendee_acceptanceStatus;
    }

    public void setAttendee_acceptanceStatus(AcceptanceStatus attendee_acceptanceStatus) {
        this.attendee_acceptanceStatus = attendee_acceptanceStatus;
    }

    public ResponseStatus getAttendee_responseStatus() {
        return attendee_responseStatus;
    }

    public void setAttendee_responseStatus(ResponseStatus attendee_responseStatus) {
        this.attendee_responseStatus = attendee_responseStatus;
    }

    public String getAttendee_score() {
        return attendee_score;
    }

    public void setAttendee_score(String attendee_score) {
        this.attendee_score = attendee_score;
    }

    public Long getAttendee_startTime() {
        return attendee_startTime;
    }

    public void setAttendee_startTime(Long attendee_startTime) {
        this.attendee_startTime = attendee_startTime;
    }

    public Long getAttendee_endTime() {
        return attendee_endTime;
    }

    public void setAttendee_endTime(Long attendee_endTime) {
        this.attendee_endTime = attendee_endTime;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public Long getEvent_eventType() {
        return event_eventType;
    }

    public void setEvent_eventType(Long event_eventType) {
        this.event_eventType = event_eventType;
    }

    public Long getEvent_eventCategory() {
        return event_eventCategory;
    }

    public void setEvent_eventCategory(Long event_eventCategory) {
        this.event_eventCategory = event_eventCategory;
    }

    public Timestamp getEvent_startTime() {
        return event_startTime;
    }

    public void setEvent_startTime(Timestamp event_startTime) {
        this.event_startTime = event_startTime;
    }

    public Timestamp getEvent_endTime() {
        return event_endTime;
    }

    public void setEvent_endTime(Timestamp event_endTime) {
        this.event_endTime = event_endTime;
    }

    public boolean isEvent_isRecurring() {
        return event_isRecurring;
    }

    public void setEvent_isRecurring(boolean event_isRecurring) {
        this.event_isRecurring = event_isRecurring;
    }

    public Date getEvent_eventStartDate() {
        return event_eventStartDate;
    }

    public void setEvent_eventStartDate(Date event_eventStartDate) {
        this.event_eventStartDate = event_eventStartDate;
    }

    public Date getEvent_eventEndDate() {
        return event_eventEndDate;
    }

    public void setEvent_eventEndDate(Date event_eventEndDate) {
        this.event_eventEndDate = event_eventEndDate;
    }

    public String getEvent_title() {
        return event_title;
    }

    public void setEvent_title(String event_title) {
        this.event_title = event_title;
    }

    public String getEvent_text() {
        return event_text;
    }

    public void setEvent_text(String event_text) {
        this.event_text = event_text;
    }

    public Long getEvent_hostId() {
        return event_hostId;
    }

    public void setEvent_hostId(Long event_hostId) {
        this.event_hostId = event_hostId;
    }

    public String getEvent_eventUrl() {
        return event_eventUrl;
    }

    public void setEvent_eventUrl(String event_eventUrl) {
        this.event_eventUrl = event_eventUrl;
    }

    public String getEvent_location() {
        return event_location;
    }

    public void setEvent_location(String event_location) {
        this.event_location = event_location;
    }

    public String getEvent_contactDetails() {
        return event_contactDetails;
    }

    public void setEvent_contactDetails(String event_contactDetails) {
        this.event_contactDetails = event_contactDetails;
    }

    public String getEvent_siteUrl() {
        return event_siteUrl;
    }

    public void setEvent_siteUrl(String event_siteUrl) {
        this.event_siteUrl = event_siteUrl;
    }

    public String getEvent_status() {
        return event_status;
    }

    public void setEvent_status(String event_status) {
        this.event_status = event_status;
    }

    public String getEvent_targetURL() {
        return event_targetURL;
    }

    public void setEvent_targetURL(String event_targetURL) {
        this.event_targetURL = event_targetURL;
    }

    public String getRecurring_id() {
        return recurring_id;
    }

    public void setRecurring_id(String recurring_id) {
        this.recurring_id = recurring_id;
    }

    public Long getRecurring_recurringEntityId() {
        return recurring_recurringEntityId;
    }

    public void setRecurring_recurringEntityId(Long recurring_recurringEntityId) {
        this.recurring_recurringEntityId = recurring_recurringEntityId;
    }

    public Timestamp getRecurring_startTimestamp() {
        return recurring_startTimestamp;
    }

    public void setRecurring_startTimestamp(Timestamp recurring_startTimestamp) {
        this.recurring_startTimestamp = recurring_startTimestamp;
    }

    public Timestamp getRecurring_startEndstamp() {
        return recurring_startEndstamp;
    }

    public void setRecurring_startEndstamp(Timestamp recurring_startEndstamp) {
        this.recurring_startEndstamp = recurring_startEndstamp;
    }

    public String getRecurring_status() {
        return recurring_status;
    }

    public void setRecurring_status(String recurring_status) {
        this.recurring_status = recurring_status;
    }

    public String getRecurringAttendee_id() {
        return recurringAttendee_id;
    }

    public void setRecurringAttendee_id(String recurringAttendee_id) {
        this.recurringAttendee_id = recurringAttendee_id;
    }

    public Long getRecurringAttendee_attendee() {
        return recurringAttendee_attendee;
    }

    public void setRecurringAttendee_attendee(Long recurringAttendee_attendee) {
        this.recurringAttendee_attendee = recurringAttendee_attendee;
    }

    public AcceptanceStatus getRecurringAttendee_acceptanceStatus() {
        return recurringAttendee_acceptanceStatus;
    }

    public void setRecurringAttendee_acceptanceStatus(AcceptanceStatus recurringAttendee_acceptanceStatus) {
        this.recurringAttendee_acceptanceStatus = recurringAttendee_acceptanceStatus;
    }

    public String getRecurringAttendee_eventNote() {
        return recurringAttendee_eventNote;
    }

    public void setRecurringAttendee_eventNote(String recurringAttendee_eventNote) {
        this.recurringAttendee_eventNote = recurringAttendee_eventNote;
    }
}
