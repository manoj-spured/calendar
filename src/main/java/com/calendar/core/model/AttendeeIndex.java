package com.calendar.core.model;

import com.calendar.core.model.enums.AcceptanceStatus;
import com.calendar.core.model.enums.EventType;
import com.calendar.core.model.enums.ResponseStatus;
import com.spured.shared.model.post.PostType;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

// Elastic search annotation.
@Document(indexName= "attendee", type= "AttendeeIndex")
@Entity
@Table(name = "attendee_index")
@SequenceGenerator(name="seq", initialValue=100000, allocationSize=10)
public class AttendeeIndex {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq")
    Long id;
    Long attendee;
    EventType eventType;
    @Column(unique = true)
    Long entityId;
    String eventNote;
    AcceptanceStatus acceptanceStatus;
    ResponseStatus responseStatus;
    String score;
    Long startTime;
    Long endTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAttendee()
    {
        return attendee;
    }

    public void setAttendee(Long attendee)
    {
        this.attendee = attendee;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getEventNote() {
        return eventNote;
    }

    public void setEventNote(String eventNote) {
        this.eventNote = eventNote;
    }

    public AcceptanceStatus getAcceptanceStatus() {
        return acceptanceStatus;
    }

    public void setAcceptanceStatus(AcceptanceStatus acceptanceStatus) {
        this.acceptanceStatus = acceptanceStatus;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }
}
