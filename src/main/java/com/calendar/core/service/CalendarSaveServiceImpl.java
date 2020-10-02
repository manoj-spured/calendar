package com.calendar.core.service;

import com.calendar.core.data.CalendarSaveRequestDTO;
import com.calendar.core.jparepository.AttendeeIndexJPARepository;
import com.calendar.core.jparepository.EventIndexJPARepository;
import com.calendar.core.jparepository.RecurringAttendeeIndexJPARepository;
import com.calendar.core.jparepository.RecurringIndexJPARepository;
import com.calendar.core.model.AttendeeIndex;
import com.calendar.core.model.EventIndex;
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
import java.util.Map;
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
/*

        no starttime for quiz and assignment -> consider created time
        they are mandatory for meeting
        compare start and end time to create separate events - then 2 different entityIds
*/

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

                CurriculumRequest curriculumRequest = new CurriculumRequest();
                CourseBoardProfileRequest courseBoardProfileRequest = new CourseBoardProfileRequest();

                //check if board post or group post
                if (postResponse.getPosts().get(0) instanceof BoardPost)
                {
                    BoardPost boardPost = (BoardPost) postResponse.getPosts().get(0);
                    boardPost.getBoardId();
                    boardPost.getCourseId();

                    courseBoardProfileRequest.setBoardId(String.valueOf(boardPost.getBoardId()));
                    courseBoardProfileRequest.setCourseId(String.valueOf(boardPost.getCourseId()));
                    //TODO: pass boardId and courseId to get users
                }
                else if (postResponse.getPosts().get(0) instanceof GroupPost)
                {
                    GroupPost groupPost = (GroupPost) postResponse.getPosts().get(0);
                    groupPost.getGroupId();
                    //TODO: pass only groupId to get users
                }

                //TODO: Fetch the user and faculty info from course/course-users by passing boardId OR boardId+courseID OR only groupId - 3 different services
                curriculumRequest.setCourseBoardProfileRequest(courseBoardProfileRequest);
                CurriculumResponse curriculumResponse = CurriculumServiceClient.getNotificationSettings(curriculumRequest);

                CourseBoardData courseBoardData = new CourseBoardData();
                if (Objects.nonNull(curriculumResponse.getError()))
                {
                   courseBoardData = (CourseBoardData) curriculumResponse.getResponseObject();
                }

                List<CourseFacultyMappingData> courseFacultyMappingDataSet = courseBoardData.getCourseFacultyMappingDataSet();
                List<CourseStudentMappingData> courseStudentMappingDataSet = courseBoardData.getCourseStudentMappingDataSet();

                Set<Integer> userIds = courseFacultyMappingDataSet.stream().map(c -> Integer.parseInt(c.getFacultyId())).collect(Collectors.toSet());
                userIds.addAll(courseStudentMappingDataSet.stream().map(c -> Integer.parseInt(c.getStudentId())).collect(Collectors.toSet()));

                GetProfilesResponse getProfilesResponse = UserProfileServiceClient.getProfilesWithUserIds(userIds);

                List<UserBasicProfile> userBasicProfileList = new ArrayList<>(getProfilesResponse.getUserIdProfilesMap().values());


                BoardPost tempBoardPost = new BoardPost();
                tempBoardPost.setPostId(2343);
                tempBoardPost.setBoardId(1234);
                tempBoardPost.setCourseId(8765);
                tempBoardPost.setPostType(PostType.ASSIGNMENT);
                tempBoardPost.setPostTitle("testPostTitle");
                tempBoardPost.setPostText("testPostText");
                tempBoardPost.setCreateTime(new Timestamp(123423225));

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
        EventIndex eventIndex = new EventIndex();
        eventIndex.setBoardId(Long.valueOf(postInfo.getBoardId()));
        eventIndex.setCourseId(Long.valueOf(postInfo.getCourseId()));
        if (postInfo.getPostType().equals(PostType.ASSIGNMENT) || postInfo.getPostType().equals(PostType.QUIZ))
        {
            eventIndex.setStartTime(postInfo.getCreateTime().getTime());
        }
        else if (postInfo.getPostType().equals(PostType.MEET))
        {
            eventIndex.setStartTime(postInfo.getMeeting().getStartTime().getTime());
            eventIndex.setEndTime(postInfo.getMeeting().getEndTime().getTime());
        }

        eventIndex.setEventType(postInfo.getPostType());
        eventIndex.setTitle(postInfo.getPostTitle());
        eventIndex.setText(postInfo.getPostText());
        eventIndex.setEntityReferenceId(Long.valueOf(postInfo.getPostId()));
        eventIndexJPARepository.save(eventIndex);

        Long entityId = eventIndex.getEntityId();

        for (UserBasicProfile user: userBasicProfileList)
        {
            AttendeeIndex attendeeIndex = new AttendeeIndex();
            attendeeIndex.setAttendee(Long.valueOf(user.getUserId()));
            attendeeIndex.setEventType(postInfo.getPostType());
            attendeeIndex.setEntityId(entityId);
            attendeeIndex.setStartTime(eventIndex.getStartTime());
            attendeeIndex.setEndTime(eventIndex.getEndTime());
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