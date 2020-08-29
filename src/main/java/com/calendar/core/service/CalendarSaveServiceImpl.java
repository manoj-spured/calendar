package com.calendar.core.service;

import com.calendar.core.data.CalendarSaveRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

public class CalendarSaveServiceImpl implements CalendarSaveService
{
    @Override
    public ResponseEntity<HttpStatus> saveCalendarEvent(CalendarSaveRequestDTO calendarSaveRequestDTO)
    {
        switch (calendarSaveRequestDTO.getEventType())
        {
            case ASSIGNMENT:
            case POLL:
            case QUIZ:
            case EXAM:
            case MEETING:
                if (Objects.nonNull(calendarSaveRequestDTO.getPostId())
                        && Objects.nonNull(calendarSaveRequestDTO.getPostSection()))
                {
                    return new ResponseEntity(HttpStatus.BAD_REQUEST);
                }
                //TODO: Fetch the post info from core/get-post get
                //TODO: Fetch the user and faculty info from course/course-users
                //saveEvent(postInfo, usersInfo)
                break;

            case COURSE:
                if (Objects.nonNull(calendarSaveRequestDTO.getCourseId())
                        && Objects.nonNull(calendarSaveRequestDTO.getBoardId()))
                {
                    return new ResponseEntity(HttpStatus.BAD_REQUEST);
                }
                //TODO: Fetch the post info from core/get-post get
                //TODO: Fetch the course info from course/course-info
                //TODO: Fetch the user and faculty info from course/course-users
                //saveEvent(postInfo, usersInfo)
                break;

            case TIMETABLE:
                if (Objects.nonNull(calendarSaveRequestDTO.getCourseId())
                        && Objects.nonNull(calendarSaveRequestDTO.getBoardId())
                        && Objects.nonNull(calendarSaveRequestDTO.getScheduleId()))
                {
                    return new ResponseEntity(HttpStatus.BAD_REQUEST);
                }
                //TODO: Fetch the post info from core/get-post get
                //TODO: Fetch the course info from course/course-info
                //TODO: Fetch the user and faculty info from course/course-users
                //TODO: Fetch the schedule info from scheduleInfo by passing scheduleId + boardId + courseId
                //saveEvent(postInfo, usersInfo)
                break;
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}