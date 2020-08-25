package com.calendar.core.data;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "_type")
public class AttendeeEventDTO
{
    String _type;
    AttendeeProfileDTO attendeeProfileDTO;
    String eventType;
    String eventCategory;
    ResponseDTO responseDTO;
    String startTime;
    String endTime;
    String entityStartTime;
    String entityEndTime;
    String occurenceType;
    boolean isRecurring;
    String entityId;
    String title;
    String text;
    AttendeeProfileDTO hostProfile;
    String location;
    String contactDetails;
    String siteUrl;
    String status;
    String targetUrl;
    List<RecurringEventInfoDTO> recurringEventInfoDTOList;

    public String get_type() {
        return _type;
    }

    public void set_type(String _type) {
        this._type = _type;
    }

    public AttendeeProfileDTO getAttendeeProfileDTO() {
        return attendeeProfileDTO;
    }

    public void setAttendeeProfileDTO(AttendeeProfileDTO attendeeProfileDTO) {
        this.attendeeProfileDTO = attendeeProfileDTO;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(String eventCategory) {
        this.eventCategory = eventCategory;
    }

    public ResponseDTO getResponseDTO() {
        return responseDTO;
    }

    public void setResponseDTO(ResponseDTO responseDTO) {
        this.responseDTO = responseDTO;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEntityStartTime() {
        return entityStartTime;
    }

    public void setEntityStartTime(String entityStartTime) {
        this.entityStartTime = entityStartTime;
    }

    public String getEntityEndTime() {
        return entityEndTime;
    }

    public void setEntityEndTime(String entityEndTime) {
        this.entityEndTime = entityEndTime;
    }

    public String getOccurenceType() {
        return occurenceType;
    }

    public void setOccurenceType(String occurenceType) {
        this.occurenceType = occurenceType;
    }

    public boolean getIsRecurring() {
        return isRecurring;
    }

    public void setIsRecurring(boolean isRecurring) {
        this.isRecurring = isRecurring;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
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

    public AttendeeProfileDTO getHostProfile() {
        return hostProfile;
    }

    public void setHostProfile(AttendeeProfileDTO hostProfile) {
        this.hostProfile = hostProfile;
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

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public List<RecurringEventInfoDTO> getRecurringEventInfoDTOList() {
        return recurringEventInfoDTOList;
    }

    public void setRecurringEventInfoDTOList(List<RecurringEventInfoDTO> recurringEventInfoDTOList) {
        this.recurringEventInfoDTOList = recurringEventInfoDTOList;
    }
}
