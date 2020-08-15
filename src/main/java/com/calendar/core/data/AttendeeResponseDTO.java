package com.calendar.core.data;

public class AttendeeResponseDTO
{
    String _type;
    String attendeeStatus;
    String eventNote;

    public String get_type() {
        return _type;
    }

    public void set_type(String _type) {
        this._type = _type;
    }

    public String getAttendeeStatus() {
        return attendeeStatus;
    }

    public void setAttendeeStatus(String attendeeStatus) {
        this.attendeeStatus = attendeeStatus;
    }

    public String getEventNote() {
        return eventNote;
    }

    public void setEventNote(String eventNote) {
        this.eventNote = eventNote;
    }
}
