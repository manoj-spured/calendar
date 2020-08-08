package com.calendar.core.jparepository;

import com.calendar.core.model.AttendeeIndex;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendeeIndexJPARepository extends CrudRepository<AttendeeIndex, String> {

    @Query(value = "SELECT ai FROM AttendeeIndex ai WHERE ((ai.id = :id) and ((ai.startTime >= :startTime AND ai.startTime <= :endTime) OR (ai.endTime >= :startTime OR ai.endTime <= :endTime) OR (ai.startTime <= :startTime AND ai.endTime >= :endTime)))")
    Page<AttendeeIndex> findByIdAndStartTimeAndEndTime(Long id, Long startTime, Long endTime, Pageable pageable);
}
