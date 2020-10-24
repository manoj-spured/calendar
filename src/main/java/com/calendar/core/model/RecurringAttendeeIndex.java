package com.calendar.core.model;

import com.calendar.core.model.enums.AcceptanceStatus;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

// Elastic search annotation.
@Document(indexName = "recurringattendee", type = "RecurringAttendeeIndex")
@Entity
@Table(name = "recurring_attendee_index")
@SequenceGenerator(name="seq", initialValue=100000, allocationSize=10)
public class RecurringAttendeeIndex extends GenericItem {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq")
    Long id;
    @Column(unique = true)
    Long recurringEntityId;
    Long attendee;
    AcceptanceStatus acceptanceStatus;
    String eventNote;
    Long responseTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRecurringEntityId() {
        return recurringEntityId;
    }

    public void setRecurringEntityId(Long recurringEntityId) {
        this.recurringEntityId = recurringEntityId;
    }

    public Long getAttendee() {
        return attendee;
    }

    public void setAttendee(Long attendee) {
        this.attendee = attendee;
    }

    public AcceptanceStatus getAcceptanceStatus() {
        return acceptanceStatus;
    }

    public void setAcceptanceStatus(AcceptanceStatus acceptanceStatus) {
        this.acceptanceStatus = acceptanceStatus;
    }

    public String getEventNote() {
        return eventNote;
    }

    public void setEventNote(String eventNote) {
        this.eventNote = eventNote;
    }

    public Long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Long responseTime) {
        this.responseTime = responseTime;
    }
}
