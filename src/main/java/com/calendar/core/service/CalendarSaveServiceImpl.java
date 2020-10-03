package com.calendar.core.service;

import com.calendar.core.data.CalendarSaveRequestDTO;
import com.calendar.core.jparepository.AttendeeIndexJPARepository;
import com.calendar.core.jparepository.EventIndexJPARepository;
import com.calendar.core.jparepository.RecurringAttendeeIndexJPARepository;
import com.calendar.core.jparepository.RecurringIndexJPARepository;
import com.calendar.core.model.AttendeeIndex;
import com.calendar.core.model.EventIndex;
import com.calendar.core.model.enums.EventType;
import com.calendar.core.model.enums.Occurrence;
import com.curriculum.core.data.CourseBoardData;
import com.curriculum.core.data.CourseBoardProfileRequest;
import com.curriculum.core.data.CourseFacultyMappingData;
import com.curriculum.core.data.CourseStudentMappingData;
import com.curriculum.core.data.CurriculumRequest;
import com.curriculum.core.data.CurriculumResponse;
import com.curriculum.service.client.CurriculumServiceClient;
import com.spured.core.models.post.BoardPost;
import com.spured.core.models.post.GroupPost;
import com.spured.core.response.BasePostsResponse;
import com.spured.core.service.client.CoreServiceClient;
import com.spured.profile.model.UserBasicProfile;
import com.spured.profile.response.GetProfilesResponse;
import com.spured.profiles.service.client.UserProfileServiceClient;
import com.spured.shared.model.post.PostType;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
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
    public ResponseEntity<HttpStatus> saveCalendarEvent(CalendarSaveRequestDTO calendarSaveRequestDTO)
    {
        List<UserBasicProfile> tempUserBasicProfileList = getTempUserBasicProfiles();

        CourseBoardProfileRequest courseBoardProfileRequest = new CourseBoardProfileRequest();
        CourseBoardData courseBoardData;
        List<UserBasicProfile> userBasicProfileList = new ArrayList<>();

        /* no starttime for quiz and assignment -> consider created time
        they are mandatory for meeting
        compare start and end time to create separate events - then 2 different entityIds */

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

                //TODO: Fetch the post info from core/get-post get
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

                //check if board post or group post
                checkBoardOrGroupPost(courseBoardProfileRequest, postResponse);

                //TODO: Fetch the user and faculty info from course/course-users by passing boardId OR boardId+courseID OR only groupId - 3 different services
                courseBoardData = getCourseBoardData(courseBoardProfileRequest);
                userBasicProfileList = getUserBasicProfileList(courseBoardData);

                //TODO: saveEvent(postInfo, usersInfo)
                createMeetingEvent(tempBoardPost, tempUserBasicProfileList);
                break;

            case COURSE:
                if (Objects.isNull(calendarSaveRequestDTO.getCourseId())
                        || Objects.isNull(calendarSaveRequestDTO.getBoardId()))
                {
                    return new ResponseEntity(HttpStatus.BAD_REQUEST);
                }

                //TODO: Fetch the course info from course/course-info
                //TODO: Fetch the user and faculty info from course/course-users
                courseBoardProfileRequest.setBoardId(String.valueOf(calendarSaveRequestDTO.getBoardId()));
                courseBoardProfileRequest.setCourseId(String.valueOf(calendarSaveRequestDTO.getCourseId()));
                courseBoardData = getCourseBoardData(courseBoardProfileRequest);
                userBasicProfileList = getUserBasicProfileList(courseBoardData);

                //TODO: saveEvent(postInfo, courseInfo, usersInfo)
                createCourseEvent(courseBoardData, tempUserBasicProfileList);
                break;

            case TIMETABLE:
                if (Objects.isNull(calendarSaveRequestDTO.getCourseId())
                        || Objects.isNull(calendarSaveRequestDTO.getBoardId())
                        || Objects.isNull(calendarSaveRequestDTO.getScheduleId()))
                {
                    return new ResponseEntity(HttpStatus.BAD_REQUEST);
                }
                //TODO: Fetch the course info from course/course-info
                //TODO: Fetch the user and faculty info from course/course-users
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
    private void createMeetingEvent(BoardPost postInfo, List<UserBasicProfile> userBasicProfileList)
    {
        EventIndex eventIndex = new EventIndex();
        eventIndex.setBoardId(Long.valueOf(postInfo.getBoardId()));
        eventIndex.setCourseId(Long.valueOf(postInfo.getCourseId()));
        if (postInfo.getPostType().equals(PostType.ASSIGNMENT) || postInfo.getPostType().equals(PostType.QUIZ))
        {
            //TODO: If start is not available make it start time as 00:00 of the creation date
            eventIndex.setStartTime(Objects.nonNull(postInfo.getQuestionGroupResponse().getStartTime())
                    ? postInfo.getQuestionGroupResponse().getStartTime().getTime() : postInfo.getCreateTime().getTime());

            //TODO: Check if end exists and falls on different days, then create 2 events, If end time is not available then add 30 mins to start time
            eventIndex.setEndTime(Objects.nonNull(postInfo.getQuestionGroupResponse().getEndTime())
                    ? postInfo.getQuestionGroupResponse().getEndTime().getTime() : postInfo.getCreateTime().getTime() + 30);
        }
        else if (postInfo.getPostType().equals(PostType.MEET))
        {
            eventIndex.setStartTime(postInfo.getMeeting().getStartTime().getTime());
            eventIndex.setEndTime(postInfo.getMeeting().getEndTime().getTime());
        }

        //TODO: Check the post type and set the event type accordingly
        eventIndex.setEventType(EventType.MEETING);
        eventIndex.setTitle(postInfo.getPostTitle());
        eventIndex.setText(postInfo.getPostText());
        eventIndex.setEntityReferenceId(Long.valueOf(postInfo.getPostId()));
        eventIndexJPARepository.save(eventIndex);

        createAttendeesForEvent(userBasicProfileList, eventIndex, EventType.MEETING);
    }

    private void createCourseEvent(CourseBoardData courseBoardData, List<UserBasicProfile> userBasicProfileList)
    {
        //TODO: If start and end dates are not available, log and return error as these are mandatory
        //If only 1 is available create only 1 entry
        List<EventIndex> eventIndices = new ArrayList<>();
        eventIndices.add(createCourseEvent(courseBoardData));
        eventIndices.add(createCourseEvent(courseBoardData));

        for (EventIndex eventIndex: eventIndices)
        {
            createAttendeesForEvent(userBasicProfileList, eventIndex, EventType.COURSE);
        }
    }

    private EventIndex createCourseEvent(CourseBoardData courseBoardData)
    {
        EventIndex eventIndex = new EventIndex();
        eventIndex.setBoardId(Long.valueOf(courseBoardData.getBoardId()));
        eventIndex.setCourseId(Long.valueOf(courseBoardData.getCourseId()));
        //TODO: Need to change time formats
        eventIndex.setStartTime(Long.valueOf(courseBoardData.getCourseStartDate()));
        eventIndex.setEndTime(Long.valueOf(courseBoardData.getCourseEndDate()));
        eventIndex.setOccurrenceType(Occurrence.START);
        eventIndex.setEventType(EventType.COURSE);
        //TODO: Need course title - ask vikas to add (new call to get course info with course id)
        eventIndex.setTitle(String.valueOf(courseBoardData.getCourseId()));
        eventIndex.setEntityReferenceId(Long.valueOf(courseBoardData.getCourseBoardUID()));
        eventIndexJPARepository.save(eventIndex);
        return eventIndex;
    }

    private void createAttendeesForEvent(List<UserBasicProfile> tempUserBasicProfileList, EventIndex eventIndex, EventType eventType)
    {
        Long entityId = eventIndex.getEntityId();

        for (UserBasicProfile user : tempUserBasicProfileList)
        {
            AttendeeIndex attendeeIndex = new AttendeeIndex();
            attendeeIndex.setAttendee(Long.valueOf(user.getUserId()));
            attendeeIndex.setEventType(eventType);
            attendeeIndex.setEntityId(entityId);
            attendeeIndex.setStartTime(eventIndex.getStartTime());
            attendeeIndex.setEndTime(eventIndex.getEndTime());
            attendeeIndexJPARepository.save(attendeeIndex);
        }
    }

    private void checkBoardOrGroupPost(CourseBoardProfileRequest courseBoardProfileRequest, BasePostsResponse postResponse)
    {
        if (postResponse.getPosts().get(0) instanceof BoardPost)
        {
            BoardPost boardPost = (BoardPost) postResponse.getPosts().get(0);
            boardPost.getBoardId();
            boardPost.getCourseId();

            //TODO: pass boardId and courseId to get users
            courseBoardProfileRequest.setBoardId(String.valueOf(boardPost.getBoardId()));
            courseBoardProfileRequest.setCourseId(String.valueOf(boardPost.getCourseId()));
        }
        else if (postResponse.getPosts().get(0) instanceof GroupPost)
        {
            GroupPost groupPost = (GroupPost) postResponse.getPosts().get(0);
            groupPost.getGroupId();
            //TODO: pass only groupId to get users
        }
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

    private List<UserBasicProfile> getUserBasicProfileList(CourseBoardData courseBoardData)
    {
        List<CourseFacultyMappingData> courseFacultyMappingDataSet = courseBoardData.getCourseFacultyMappingDataSet();
        List<CourseStudentMappingData> courseStudentMappingDataSet = courseBoardData.getCourseStudentMappingDataSet();

        Set<Integer> userIds = courseFacultyMappingDataSet.stream().map(c -> Integer.parseInt(c.getFacultyId())).collect(Collectors.toSet());
        userIds.addAll(courseStudentMappingDataSet.stream().map(c -> Integer.parseInt(c.getStudentId())).collect(Collectors.toSet()));

        GetProfilesResponse getProfilesResponse = UserProfileServiceClient.getProfilesWithUserIds(userIds);

        return new ArrayList<>(getProfilesResponse.getUserIdProfilesMap().values());
    }

    private List<UserBasicProfile> getTempUserBasicProfiles()
    {
        List<UserBasicProfile> tempUserBasicProfileList = new ArrayList<>();
        tempUserBasicProfileList.add(createUser(234543));
        tempUserBasicProfileList.add(createUser(544565));
        return tempUserBasicProfileList;
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