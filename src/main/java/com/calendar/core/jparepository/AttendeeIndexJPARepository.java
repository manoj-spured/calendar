package com.calendar.core.jparepository;

import com.calendar.core.model.AttendeeIndex;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendeeIndexJPARepository extends CrudRepository<AttendeeIndex, String> {

    @Query(value = "FROM AttendeeIndex ai WHERE ((ai.attendee = :attendee) and ((ai.startTime >= :startTime AND ai.startTime <= :endTime) OR (ai.endTime >= :startTime OR ai.endTime <= :endTime) OR (ai.startTime <= :startTime AND ai.endTime >= :endTime)))")
    List<AttendeeIndex> findAllByAttendeeId(@Param("attendee") Long attendee,@Param("startTime") Long startTime,@Param("endTime") Long endTime);
}
