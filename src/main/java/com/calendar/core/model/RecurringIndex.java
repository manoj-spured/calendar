package com.calendar.core.model;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.sql.Timestamp;

// Elastic search annotation.
@Document(indexName = "recurring", type = "RecurringIndex")
@Entity
@Table(name = "recurring_index")
@SequenceGenerator(name="seq", initialValue=100000, allocationSize=10)
public class RecurringIndex extends GenericItem {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq")
    String id;
    Long recurringEntityId;
    Long entityId;
    Timestamp startTimestamp;
    Timestamp startEndstamp;
    String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getRecurringEntityId() {
        return recurringEntityId;
    }

    public void setRecurringEntityId(Long recurringEntityId) {
        this.recurringEntityId = recurringEntityId;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public Timestamp getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(Timestamp startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public Timestamp getStartEndstamp() {
        return startEndstamp;
    }

    public void setStartEndstamp(Timestamp startEndstamp) {
        this.startEndstamp = startEndstamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
