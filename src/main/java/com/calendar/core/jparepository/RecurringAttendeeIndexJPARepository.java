package com.calendar.core.jparepository;

import com.calendar.core.model.RecurringAttendeeIndex;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecurringAttendeeIndexJPARepository extends CrudRepository<RecurringAttendeeIndex, String> {

    @Query(value = "SELECT rai FROM RecurringAttendeeIndex rai WHERE ((rai.attendee = :attendeeId) AND (rai.recurringEntityId IN (:recurringEntityIdList)))")
    List<RecurringAttendeeIndex> findAllByRecurringEntityIdInAndAttendee(List<Long> recurringEntityIdList, Long attendeeId);
}
