package com.calendar.core.data;

public class ResponseDTO
{
    String _type;
    String note;
    String attendeeStatus;
    String responseStatus;
    String grade;

    public String get_type() {
        return _type;
    }

    public void set_type(String _type) {
        this._type = _type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAttendeeStatus() {
        return attendeeStatus;
    }

    public void setAttendeeStatus(String attendeeStatus) {
        this.attendeeStatus = attendeeStatus;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
