package com.calendar.core.service;

import com.calendar.core.data.CalendarSaveRequestDTO;
import com.calendar.core.jparepository.AttendeeIndexJPARepository;
import com.calendar.core.jparepository.EventIndexJPARepository;
import com.calendar.core.jparepository.RecurringAttendeeIndexJPARepository;
import com.calendar.core.jparepository.RecurringIndexJPARepository;
import com.calendar.core.model.AttendeeIndex;
import com.spured.core.models.post.Post;
import com.spured.core.response.BasePostsResponse;
import com.spured.core.service.client.CoreServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

public class CalendarSaveServiceImpl implements CalendarSaveService
{
    @Autowired
    AttendeeIndexJPARepository attendeeIndexJPARepository;
    @Autowired
    EventIndexJPARepository eventIndexJPARepository;
    @Autowired
    RecurringIndexJPARepository recurringIndexJPARepository;
    @Autowired
    RecurringAttendeeIndexJPARepository recurringAttendeeIndexJPARepository;

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
                Post post = CoreServiceClient.getUnifiedPost(calendarSaveRequestDTO.getPostSection(), Math.toIntExact(calendarSaveRequestDTO.getPostId())).getPosts().get(0);


                //TODO: Fetch the user and faculty info from course/course-users
                //TODO: saveEvent(postInfo, usersInfo)
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
                //saveEvent(postInfo, courseInfo, usersInfo)
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
                //saveEvent(postInfo, courseInfo, usersInfo, scheduleInfo)
                break;
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    /**
     * Create Event for ASSIGNMENT, POLL, QUIZ, EXAM, MEETING
     * @param postInfo Post related info
     * @param userInfo Students and Faculty info
     */
    private void createMeetingEvent(String postInfo, List<String> userInfo)
    {
        for (String user: userInfo)
        {
            AttendeeIndex attendeeIndex = new AttendeeIndex();
            attendeeIndex.setAttendee(1l);
        }
    }
}