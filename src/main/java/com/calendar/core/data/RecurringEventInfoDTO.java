package com.calendar.core.data;

public class RecurringEventInfoDTO
{
    String _type;
    String recurringEntityId;
    String startTime;
    String endTime;
    String status;
    AttendeeResponseDTO attendeeResponseDTO;

    public String get_type() {
        return _type;
    }

    public void set_type(String _type) {
        this._type = _type;
    }

    public String getRecurringEntityId() {
        return recurringEntityId;
    }

    public void setRecurringEntityId(String recurringEntityId) {
        this.recurringEntityId = recurringEntityId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AttendeeResponseDTO getAttendeeResponseDTO() {
        return attendeeResponseDTO;
    }

    public void setAttendeeResponseDTO(AttendeeResponseDTO attendeeResponseDTO) {
        this.attendeeResponseDTO = attendeeResponseDTO;
    }
}
