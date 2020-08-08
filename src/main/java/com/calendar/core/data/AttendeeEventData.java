package com.calendar.core.data;

import com.calendar.core.model.enums.AcceptanceStatus;
import com.calendar.core.model.enums.Occurrence;
import com.calendar.core.model.enums.ResponseStatus;

public class AttendeeEventData {

    Long attendee_id;
    Long attendee_eventType;
    Long attendee_entityId;
    String attendee_eventNote;
    AcceptanceStatus attendee_acceptanceStatus;
    ResponseStatus attendee_responseStatus;
    String attendee_score;
    Long attendee_startTime;
    Long attendee_endTime;

    Long event_id;
    Long event_boardId;
    Long event_courseId;
    Long event_groupId;
    Long event_eventType;
    Long event_eventCategory;
    Long event_startTime;
    Long event_endTime;
    boolean event_isRecurring;
    Long event_entityStartTime;
    Long event_entityEndTime;
    Occurrence event_occurrenceType;
    String event_title;
    String event_text;
    Long event_hostId;
    String event_eventUrl;
    String event_location;
    String event_contactDetails;
    String event_siteUrl;
    String event_status;
    String event_targetURL;

    Long recurring_id;
    Long recurring_recurringEntityId;
    Long recurring_startTime;
    Long recurring_endTime;
    String recurring_status;

    Long recurringAttendee_id;
    Long recurringAttendee_attendee;
    AcceptanceStatus recurringAttendee_acceptanceStatus;
    String recurringAttendee_eventNote;

    public Long getAttendee_id() {
        return attendee_id;
    }

    public void setAttendee_id(Long attendee_id) {
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

    public Long getEvent_id() {
        return event_id;
    }

    public void setEvent_id(Long event_id) {
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

    public Long getEvent_startTime() {
        return event_startTime;
    }

    public void setEvent_startTime(Long event_startTime) {
        this.event_startTime = event_startTime;
    }

    public Long getEvent_endTime() {
        return event_endTime;
    }

    public void setEvent_endTime(Long event_endTime) {
        this.event_endTime = event_endTime;
    }

    public boolean isEvent_isRecurring() {
        return event_isRecurring;
    }

    public void setEvent_isRecurring(boolean event_isRecurring) {
        this.event_isRecurring = event_isRecurring;
    }

    public Long getEvent_boardId() {
        return event_boardId;
    }

    public void setEvent_boardId(Long event_boardId) {
        this.event_boardId = event_boardId;
    }

    public Long getEvent_courseId() {
        return event_courseId;
    }

    public void setEvent_courseId(Long event_courseId) {
        this.event_courseId = event_courseId;
    }

    public Long getEvent_groupId() {
        return event_groupId;
    }

    public void setEvent_groupId(Long event_groupId) {
        this.event_groupId = event_groupId;
    }

    public Long getEvent_entityStartTime() {
        return event_entityStartTime;
    }

    public void setEvent_entityStartTime(Long event_entityStartTime) {
        this.event_entityStartTime = event_entityStartTime;
    }

    public Long getEvent_entityEndTime() {
        return event_entityEndTime;
    }

    public void setEvent_entityEndTime(Long event_entityEndTime) {
        this.event_entityEndTime = event_entityEndTime;
    }

    public Occurrence getEvent_occurrenceType() {
        return event_occurrenceType;
    }

    public void setEvent_occurrenceType(Occurrence event_occurrenceType) {
        this.event_occurrenceType = event_occurrenceType;
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

    public Long getRecurring_id() {
        return recurring_id;
    }

    public void setRecurring_id(Long recurring_id) {
        this.recurring_id = recurring_id;
    }

    public Long getRecurring_recurringEntityId() {
        return recurring_recurringEntityId;
    }

    public void setRecurring_recurringEntityId(Long recurring_recurringEntityId) {
        this.recurring_recurringEntityId = recurring_recurringEntityId;
    }

    public Long getRecurring_startTime() {
        return recurring_startTime;
    }

    public void setRecurring_startTime(Long recurring_startTime) {
        this.recurring_startTime = recurring_startTime;
    }

    public Long getRecurring_endTime() {
        return recurring_endTime;
    }

    public void setRecurring_endTime(Long recurring_endTime) {
        this.recurring_endTime = recurring_endTime;
    }

    public String getRecurring_status() {
        return recurring_status;
    }

    public void setRecurring_status(String recurring_status) {
        this.recurring_status = recurring_status;
    }

    public Long getRecurringAttendee_id() {
        return recurringAttendee_id;
    }

    public void setRecurringAttendee_id(Long recurringAttendee_id) {
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
