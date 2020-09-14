package com.calendar.core.service;

import com.calendar.core.data.CalendarSaveRequestDTO;
import com.calendar.core.jparepository.AttendeeIndexJPARepository;
import com.calendar.core.jparepository.EventIndexJPARepository;
import com.calendar.core.jparepository.RecurringAttendeeIndexJPARepository;
import com.calendar.core.jparepository.RecurringIndexJPARepository;
import com.calendar.core.model.AttendeeIndex;
import com.calendar.core.model.EventIndex;
import com.spured.core.models.post.BoardPost;
import com.spured.core.response.BasePostsResponse;
import com.spured.core.service.client.CoreServiceClient;
import com.spured.profile.model.UserBasicProfile;
import com.spured.shared.model.post.PostType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

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
            //case POLL:
            case QUIZ:
            //case EXAM:
            case MEETING:
                if (Objects.isNull(calendarSaveRequestDTO.getPostId())
                        || Objects.isNull(calendarSaveRequestDTO.getPostSection()))
                {
                    return new ResponseEntity(HttpStatus.BAD_REQUEST);
                }
                //TODO: Fetch the post info from core/get-post get
                BasePostsResponse postResponse = CoreServiceClient.getUnifiedPost(calendarSaveRequestDTO.getPostSection(), Math.toIntExact(calendarSaveRequestDTO.getPostId()));
                if(Objects.isNull(postResponse) || Objects.nonNull(postResponse.getError()))
                {
                    return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
                }
                if (CollectionUtils.isEmpty(postResponse.getPosts()) || postResponse.getPosts().size() > 1)
                {
                    return new ResponseEntity(HttpStatus.NOT_FOUND);
                }
                //check if board post or group post
                BoardPost boardPost = (BoardPost) postResponse.getPosts().get(0);
                //If group post - we get only groupId

                BoardPost tempBoardPost = new BoardPost();
                tempBoardPost.setBoardId(1234);
                tempBoardPost.setPostType(PostType.ASSIGNMENT);
                tempBoardPost.setPostTitle("testPostTitle");
                tempBoardPost.setPostText("testPostText");
                tempBoardPost.setCreateTime(new Timestamp(12342322));

                //TODO: Fetch the user and faculty info from course/course-users by passing boardId OR boardId+courseID OR only groupId - 3 different services
                List<UserBasicProfile> tempUserBasicProfileList = new ArrayList<>();
                tempUserBasicProfileList.add(createUser(234543));
                tempUserBasicProfileList.add(createUser(544565));

                //TODO: saveEvent(postInfo, usersInfo)
                createMeetingEvent(tempBoardPost, tempUserBasicProfileList);
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
     * @param userBasicProfileList Students and Faculty info
     */
    private void createMeetingEvent(BoardPost postInfo, List<UserBasicProfile> userBasicProfileList)
    {
        Long entityId = (long) new Random().nextInt();

        EventIndex eventIndex = new EventIndex();
        eventIndex.setEntityId(entityId);
        eventIndex.setBoardId(Long.valueOf(postInfo.getBoardId()));
        eventIndex.setStartTime(postInfo.getCreateTime().getTime());
        eventIndex.setEventType(postInfo.getPostType());
        eventIndex.setTitle(postInfo.getPostTitle());
        eventIndex.setText(postInfo.getPostText());
        eventIndexJPARepository.save(eventIndex);

        for (UserBasicProfile user: userBasicProfileList)
        {
            AttendeeIndex attendeeIndex = new AttendeeIndex();
            attendeeIndex.setAttendee(Long.valueOf(user.getUserId()));
            attendeeIndex.setEventType(postInfo.getPostType());
            attendeeIndex.setStartTime(postInfo.getCreateTime().getTime());
            attendeeIndex.setEntityId(entityId);
            attendeeIndexJPARepository.save(attendeeIndex);
        }
    }

    private UserBasicProfile createUser(int userId)
    {
        UserBasicProfile testUserBasicProfile = new UserBasicProfile();
        testUserBasicProfile.setUserId(userId);
        return testUserBasicProfile;
    }
}