package com.calendar.core.service;

import com.calendar.core.data.CalendarSaveRequestDTO;
import com.calendar.core.jparepository.AttendeeIndexJPARepository;
import com.calendar.core.jparepository.EventIndexJPARepository;
import com.calendar.core.jparepository.RecurringAttendeeIndexJPARepository;
import com.calendar.core.jparepository.RecurringIndexJPARepository;
import com.calendar.core.model.AttendeeIndex;
import com.calendar.core.model.EventIndex;
import com.calendar.core.model.enums.EventType;
import com.calendar.core.model.enums.OccurrenceType;
import com.curriculum.core.data.CourseBoardData;
import com.curriculum.core.data.CourseBoardProfileRequest;
import com.curriculum.core.data.CourseFacultyMappingData;
import com.curriculum.core.data.CourseInfoData;
import com.curriculum.core.data.CourseStudentMappingData;
import com.curriculum.core.data.CurriculumRequest;
import com.curriculum.core.data.CurriculumResponse;
import com.curriculum.service.client.CurriculumServiceClient;
import com.spured.boards.service.client.BoardsServiceClient;
import com.spured.core.models.boards.BoardResponse;
import com.spured.core.models.post.BoardPost;
import com.spured.core.models.post.GroupPost;
import com.spured.core.models.post.question.QuestionGroupResponse;
import com.spured.core.response.BasePostsResponse;
import com.spured.core.response.GroupResponse;
import com.spured.core.service.client.CoreServiceClient;
import com.spured.groups.service.client.GroupsServiceClient;
import com.spured.profile.model.UserBasicProfile;
import com.spured.shared.model.post.PostType;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
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
    public ResponseEntity<HttpStatus> saveCalendarEvent(CalendarSaveRequestDTO calendarSaveRequestDTO) throws Exception
    {
        Set<Integer> tempUserBasicProfileList = getTempUserBasicProfiles();

        CourseBoardProfileRequest courseBoardProfileRequest = new CourseBoardProfileRequest();
        CourseBoardData courseBoardData;
        Set<Integer> userBasicProfileList = new HashSet<>();

        switch (calendarSaveRequestDTO.getEventType())
        {
            case ASSIGNMENT:
            //case POLL:
            case QUIZ:
            //case EXAM:
            case MEETING:
                BoardPost tempBoardPost = getTempBoardPost();
                if (Objects.isNull(calendarSaveRequestDTO.getPostId())
                        || Objects.isNull(calendarSaveRequestDTO.getPostSection()))
                {
                    return new ResponseEntity(HttpStatus.BAD_REQUEST);
                }

                //Fetch the post info from core/get-post get
                BasePostsResponse postResponse = CoreServiceClient.getUnifiedPost(calendarSaveRequestDTO.getPostSection()
                        , Math.toIntExact(calendarSaveRequestDTO.getPostId()));

                if(Objects.isNull(postResponse) || Objects.nonNull(postResponse.getError()))
                {
                    return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
                }
                if (CollectionUtils.isEmpty(postResponse.getPosts()) || postResponse.getPosts().size() > 1)
                {
                    return new ResponseEntity(HttpStatus.NOT_FOUND);
                }

                //Check if board post or group post and get the Users profile Ids to pass to create event
                userBasicProfileList = checkBoardOrGroupPost(courseBoardProfileRequest, postResponse);

                //Save Assignment//Quiz/Meeting Event
                createMeetingEvent(tempBoardPost, tempUserBasicProfileList);
                break;

            case COURSE:
                if (Objects.isNull(calendarSaveRequestDTO.getCourseId())
                        || Objects.isNull(calendarSaveRequestDTO.getBoardId()))
                {
                    return new ResponseEntity(HttpStatus.BAD_REQUEST);
                }

                //Fetch the course info from course/course-info
                //Fetch the user and faculty info from course/course-users
                courseBoardProfileRequest.setBoardId(String.valueOf(calendarSaveRequestDTO.getBoardId()));
                courseBoardProfileRequest.setCourseId(String.valueOf(calendarSaveRequestDTO.getCourseId()));
                courseBoardData = getCourseBoardData(courseBoardProfileRequest);
                userBasicProfileList = getUserBasicProfileList(courseBoardData);

                //Save Course Event
                createCourseEvent(courseBoardData, tempUserBasicProfileList);
                break;

            case TIMETABLE:
                if (Objects.isNull(calendarSaveRequestDTO.getCourseId())
                        || Objects.isNull(calendarSaveRequestDTO.getBoardId())
                        || Objects.isNull(calendarSaveRequestDTO.getScheduleId()))
                {
                    return new ResponseEntity(HttpStatus.BAD_REQUEST);
                }
                //Fetch the course info from course/course-info
                //Fetch the user and faculty info from course/course-users
                courseBoardProfileRequest.setBoardId(String.valueOf(calendarSaveRequestDTO.getBoardId()));
                courseBoardProfileRequest.setCourseId(String.valueOf(calendarSaveRequestDTO.getCourseId()));
                courseBoardData = getCourseBoardData(courseBoardProfileRequest);
                userBasicProfileList = getUserBasicProfileList(courseBoardData);
                //TODO: Fetch the schedule info from scheduleInfo by passing scheduleId + boardId + courseId
                //TODO: saveEvent(postInfo, courseInfo, usersInfo, scheduleInfo)
                break;
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    /**
     * Create Event for ASSIGNMENT, POLL, QUIZ, EXAM, MEETING
     * @param postInfo Post related info
     * @param userBasicProfileList Students and Faculty info
     */
    private void createMeetingEvent(BoardPost postInfo, Set<Integer> userBasicProfileList) throws Exception
    {
        Timestamp startTimeStamp;
        Timestamp endTimeStamp;

        QuestionGroupResponse questionGroupResponse = postInfo.getQuestionGroupResponse();

        if (Objects.nonNull(questionGroupResponse));
        {
            startTimeStamp = questionGroupResponse.getStartTime();
            endTimeStamp = questionGroupResponse.getStartTime();
        }

        if (postInfo.getPostType().equals(PostType.MEET))
        {
            if (Objects.nonNull(postInfo.getMeeting()))
            {
                startTimeStamp = postInfo.getMeeting().getStartTime();
                endTimeStamp = postInfo.getMeeting().getEndTime();
            }
            else
            {
                throw new Exception("Incorrect meeting details.");
            }
        }

        //Check if end date exists and falls on different days, then create 2 events
        if (Objects.nonNull(startTimeStamp) && Objects.nonNull(endTimeStamp))
        {
            Long startTime = startTimeStamp.getTime();
            Long endTime = endTimeStamp.getTime();

            //TODO: Check the dates difference instead of times difference to see if they fall on different days
            if (!DateUtils.isSameDay(startTimeStamp, endTimeStamp))
            {
                int eventDuration = 30*60*1000;
                createMeetingEvent(postInfo, userBasicProfileList, startTime, startTime + eventDuration
                        , OccurrenceType.BEGIN);
                createMeetingEvent(postInfo, userBasicProfileList, endTime - eventDuration, endTime
                        , OccurrenceType.END);
            }
            else
            {
                createMeetingEvent(postInfo, userBasicProfileList, startTime, endTime, null);
            }
        }
        //If only start time is available
        else if (Objects.nonNull(startTimeStamp))
        {
            Long startTime = startTimeStamp.getTime();
            createMeetingEvent(postInfo, userBasicProfileList, startTime, null, OccurrenceType.BEGIN);
        }
        //If start is not available make start time as 00:00 of the creation date
        else
        {
            Timestamp postCreatedTimeStamp = postInfo.getCreateTime();
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(postCreatedTimeStamp.getTime());
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
            postCreatedTimeStamp.setTime(c.getTimeInMillis());
            createMeetingEvent(postInfo, userBasicProfileList, postCreatedTimeStamp.getTime(),null , null);
        }
    }

    private void createMeetingEvent(BoardPost postInfo, Set<Integer> userBasicProfileList, Long startTime, Long endTime, OccurrenceType occurrenceType)
    {
        EventIndex eventIndex = new EventIndex();
        eventIndex.setStartTime(startTime);
        eventIndex.setEndTime(endTime);
        eventIndex.setBoardId(Long.valueOf(postInfo.getBoardId()));
        eventIndex.setCourseId(Long.valueOf(postInfo.getCourseId()));
        //Check the post type and set the event type accordingly
        switch (postInfo.getPostType())
        {
            case QUIZ:
                eventIndex.setEventType(EventType.QUIZ);
                break;
            case MEET:
                eventIndex.setEventType(EventType.MEETING);
                break;
            case ASSIGNMENT:
                eventIndex.setEventType(EventType.ASSIGNMENT);
                break;
            default:
                break;
        }
        eventIndex.setOccurrenceType(occurrenceType);
        eventIndex.setTitle(postInfo.getPostTitle());
        eventIndex.setText(postInfo.getPostText());
        eventIndex.setEntityReferenceId(Long.valueOf(postInfo.getPostId()));
        eventIndex.setCreatedTs(new Date().getTime());
        eventIndexJPARepository.save(eventIndex);

        createAttendeesForEvent(userBasicProfileList, eventIndex);
    }

    private void createCourseEvent(CourseBoardData courseBoardData, Set<Integer> userBasicProfileList) throws Exception
    {
        if (Objects.nonNull(courseBoardData.getCourseStartDate()) && Objects.nonNull(courseBoardData.getCourseEndDate()))
        {
            //TODO: Get the date and set time to 00000 and for both events only start date and end dates... no end times... (Full day events).... Just like meeting events
            createCourseEvent(courseBoardData, userBasicProfileList, OccurrenceType.BEGIN);
            createCourseEvent(courseBoardData, userBasicProfileList, OccurrenceType.END);
        }
        //If only 1 is available create only 1 entry
        else if (Objects.nonNull(courseBoardData.getCourseStartDate()))
        {
            createCourseEvent(courseBoardData, userBasicProfileList, OccurrenceType.BEGIN);
        }
        else if (Objects.nonNull(courseBoardData.getCourseEndDate()))
        {
            createCourseEvent(courseBoardData, userBasicProfileList, OccurrenceType.END);
        }
        //If start and end dates are not available, log and return error as these are mandatory
        else
        {
            //TODO: throw new Exception();
            throw new Exception("Incorrect course details.");
        }
    }

    private void createCourseEvent(CourseBoardData courseBoardData, Set<Integer> userBasicProfileList, OccurrenceType occurrenceType)
    {
        EventIndex eventIndex = new EventIndex();
        eventIndex.setBoardId(Long.valueOf(courseBoardData.getBoardId()));
        eventIndex.setCourseId(Long.valueOf(courseBoardData.getCourseId()));
        //TODO: Need to change time formats
        switch(occurrenceType)
        {
            case BEGIN:
                eventIndex.setOccurrenceType(OccurrenceType.BEGIN);
                break;
            case END:
                eventIndex.setOccurrenceType(OccurrenceType.END);
                break;
            default:
                break;
        }
        eventIndex.setStartTime(Long.valueOf(courseBoardData.getCourseStartDate()));
        eventIndex.setEndTime(Long.valueOf(courseBoardData.getCourseEndDate()));
        eventIndex.setEventType(EventType.COURSE);
        //TODO: Need course title - ask vikas to add (new call to get course info with course id)

        CurriculumResponse curriculumResponse = getCourseTitle(courseBoardData);
        if (Objects.nonNull(curriculumResponse.getError()))
        {
            CourseInfoData courseInfoData = (CourseInfoData) curriculumResponse.getResponseObject();
            eventIndex.setTitle(courseInfoData.getCourseName());
        }
        else
        {
            //TODO: Log error
        }

        eventIndex.setEntityReferenceId(Long.valueOf(courseBoardData.getCourseBoardUID()));
        eventIndex.setCreatedTs(new Date().getTime());
        eventIndexJPARepository.save(eventIndex);

        createAttendeesForEvent(userBasicProfileList, eventIndex);
    }

    private CurriculumResponse getCourseTitle(CourseBoardData courseBoardData)
    {
        CourseBoardProfileRequest courseBoardProfileRequest = new CourseBoardProfileRequest();
        courseBoardProfileRequest.setCourseId(String.valueOf(courseBoardData.getCourseId()));
        CurriculumRequest curriculumRequest = new CurriculumRequest();
        curriculumRequest.setCourseBoardProfileRequest(courseBoardProfileRequest);
        return CurriculumServiceClient.getNotificationSettings(curriculumRequest);
    }

    private void createAttendeesForEvent(Set<Integer> tempUserBasicProfileList, EventIndex eventIndex)
    {
        Long entityId = eventIndex.getEntityId();

        for (Integer user : tempUserBasicProfileList)
        {
            AttendeeIndex attendeeIndex = new AttendeeIndex();
            attendeeIndex.setAttendee(Long.valueOf(user));
            attendeeIndex.setEventType(eventIndex.getEventType());
            attendeeIndex.setEntityId(entityId);
            attendeeIndex.setStartTime(eventIndex.getStartTime());
            attendeeIndex.setEndTime(eventIndex.getEndTime());
            attendeeIndex.setCreatedTs(new Date().getTime());
            attendeeIndexJPARepository.save(attendeeIndex);
        }
    }

    private Set<Integer> checkBoardOrGroupPost(CourseBoardProfileRequest courseBoardProfileRequest, BasePostsResponse postResponse)
    {
        if (postResponse.getPosts().get(0) instanceof BoardPost)
        {
            BoardPost boardPost = (BoardPost) postResponse.getPosts().get(0);
            boardPost.getBoardId();
            boardPost.getCourseId();

            if (Objects.isNull(boardPost.getCourseId()))
            {
                BoardResponse boardResponse = BoardsServiceClient.getUsersInClosedBoard(new HashSet<>(boardPost.getBoardId()),
                        true,true,false,false);
                return boardResponse.getUserProfiles().stream().map(UserBasicProfile::getUserId).collect(Collectors.toSet());
            }

            //Pass boardId and courseId to get users
            courseBoardProfileRequest.setBoardId(String.valueOf(boardPost.getBoardId()));
            courseBoardProfileRequest.setCourseId(String.valueOf(boardPost.getCourseId()));
            return getUserBasicProfileList(getCourseBoardData(courseBoardProfileRequest));
        }
        else if (postResponse.getPosts().get(0) instanceof GroupPost)
        {
            GroupPost groupPost = (GroupPost) postResponse.getPosts().get(0);
            //Pass only groupId to get users
            GroupResponse groupResponse = GroupsServiceClient.getMembersOfGroup(groupPost.getGroupId());

            if (Objects.nonNull(groupResponse) && CollectionUtils.isNotEmpty(groupResponse.getUserProfiles()))
            {
                return groupResponse.getUserProfiles().stream().map(UserBasicProfile::getUserId).collect(Collectors.toSet());
            }
        }
        return null;
    }

    private CourseBoardData getCourseBoardData(CourseBoardProfileRequest courseBoardProfileRequest)
    {
        CurriculumResponse curriculumResponse;
        CourseBoardData courseBoardData = new CourseBoardData();
        CurriculumRequest curriculumRequest = new CurriculumRequest();

        curriculumRequest.setCourseBoardProfileRequest(courseBoardProfileRequest);
        curriculumResponse = CurriculumServiceClient.getNotificationSettings(curriculumRequest);

        if (Objects.nonNull(curriculumResponse.getError()))
        {
            courseBoardData = (CourseBoardData) curriculumResponse.getResponseObject();
        }
        else
        {
            //TODO: Log error
        }
        return courseBoardData;
    }

    private Set<Integer> getUserBasicProfileList(CourseBoardData courseBoardData)
    {
        List<CourseFacultyMappingData> courseFacultyMappingDataSet = courseBoardData.getCourseFacultyMappingDataSet();
        List<CourseStudentMappingData> courseStudentMappingDataSet = courseBoardData.getCourseStudentMappingDataSet();

        Set<Integer> userIds = courseFacultyMappingDataSet.stream().map(c -> Integer.parseInt(c.getFacultyId())).collect(Collectors.toSet());
        userIds.addAll(courseStudentMappingDataSet.stream().map(c -> Integer.parseInt(c.getStudentId())).collect(Collectors.toSet()));

        return userIds;
    }

    private Set<Integer> getTempUserBasicProfiles()
    {
        List<UserBasicProfile> tempUserBasicProfileList = new ArrayList<>();
        tempUserBasicProfileList.add(createUser(234543));
        tempUserBasicProfileList.add(createUser(544565));
        Set<Integer> userIds = new HashSet<>();
        userIds.add(234543);
        userIds.add(544565);
        return userIds;
    }

    private BoardPost getTempBoardPost()
    {
        BoardPost tempBoardPost = new BoardPost();
        tempBoardPost.setPostId(2343);
        tempBoardPost.setBoardId(1234);
        tempBoardPost.setCourseId(8765);
        tempBoardPost.setPostType(PostType.ASSIGNMENT);
        tempBoardPost.setPostTitle("testPostTitle");
        tempBoardPost.setPostText("testPostText");
        tempBoardPost.setCreateTime(new Timestamp(123423225));
        return tempBoardPost;
    }

    private UserBasicProfile createUser(int userId)
    {
        UserBasicProfile testUserBasicProfile = new UserBasicProfile();
        testUserBasicProfile.setUserId(userId);
        return testUserBasicProfile;
    }
}