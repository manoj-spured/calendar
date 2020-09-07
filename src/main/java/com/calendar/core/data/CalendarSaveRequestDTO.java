package com.calendar.core.data;

import com.calendar.core.model.enums.EventType;
import com.spured.shared.model.post.PostSection;

public class CalendarSaveRequestDTO
{
    EventType eventType;
    Long postId;
    PostSection postSection;
    Long boardId;
    Long courseId;
    Long groupId;
    Long scheduleId;

    public EventType getEventType()
    {
        return eventType;
    }

    public void setEventType(EventType eventType)
    {
        this.eventType = eventType;
    }

    public Long getPostId()
    {
        return postId;
    }

    public void setPostId(Long postId)
    {
        this.postId = postId;
    }

    public PostSection getPostSection()
    {
        return postSection;
    }

    public void setPostSection(PostSection postSection)
    {
        this.postSection = postSection;
    }

    public Long getBoardId()
    {
        return boardId;
    }

    public void setBoardId(Long boardId)
    {
        this.boardId = boardId;
    }

    public Long getCourseId()
    {
        return courseId;
    }

    public void setCourseId(Long courseId)
    {
        this.courseId = courseId;
    }

    public Long getGroupId()
    {
        return groupId;
    }

    public void setGroupId(Long groupId)
    {
        this.groupId = groupId;
    }

    public Long getScheduleId()
    {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId)
    {
        this.scheduleId = scheduleId;
    }
}
