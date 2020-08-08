package com.calendar.core.jparepository;

import com.calendar.core.model.RecurringAttendeeIndex;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecurringAttendeeIndexJPARepository extends CrudRepository<RecurringAttendeeIndex, String> {

    @Query(value = "SELECT rai FROM RecurringAttendeeIndex rai WHERE (rai.id = :recurringEntityId)")
    RecurringAttendeeIndex findByRecurringEntityId(Long recurringEntityId);
}
