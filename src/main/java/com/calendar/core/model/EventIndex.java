package com.calendar.core.model;

import com.calendar.core.model.enums.Occurrence;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

// Elastic search annotation.
@Document(indexName= "event", type= "EventIndex")
@Entity
@Table(name = "event_index")
@SequenceGenerator(name="seq", initialValue=100000, allocationSize=10)
public class EventIndex {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq")
    Long id;
    Long boardId;
    Long courseId;
    Long groupId;
    Long eventType;
    Long eventCategory;
    Long startTime;
    Long endTime;
    boolean isRecurring;
    Long entityStartTime;
    Long entityEndTime;
    Occurrence occurrenceType;
    Long entityId;
    String title;
    String text;
    Long hostId;
    String eventUrl;
    String location;
    String contactDetails;
    String siteUrl;
    String status;
    String targetURL;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getEventType() {
        return eventType;
    }

    public void setEventType(Long eventType) {
        this.eventType = eventType;
    }

    public Long getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(Long eventCategory) {
        this.eventCategory = eventCategory;
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

    public boolean isRecurring() {
        return isRecurring;
    }

    public void setRecurring(boolean recurring) {
        isRecurring = recurring;
    }

    public Long getEntityStartTime() {
        return entityStartTime;
    }

    public void setEntityStartTime(Long entityStartTime) {
        this.entityStartTime = entityStartTime;
    }

    public Long getEntityEndTime() {
        return entityEndTime;
    }

    public void setEntityEndTime(Long entityEndTime) {
        this.entityEndTime = entityEndTime;
    }

    public Occurrence getOccurrenceType() {
        return occurrenceType;
    }

    public void setOccurrenceType(Occurrence occurrenceType) {
        this.occurrenceType = occurrenceType;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getHostId() {
        return hostId;
    }

    public void setHostId(Long hostId) {
        this.hostId = hostId;
    }

    public String getEventUrl() {
        return eventUrl;
    }

    public void setEventUrl(String eventUrl) {
        this.eventUrl = eventUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTargetURL() {
        return targetURL;
    }

    public void setTargetURL(String targetURL) {
        this.targetURL = targetURL;
    }
}
