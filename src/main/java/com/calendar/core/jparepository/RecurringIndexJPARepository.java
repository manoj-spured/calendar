package com.calendar.core.jparepository;

import com.calendar.core.model.RecurringIndex;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecurringIndexJPARepository extends CrudRepository<RecurringIndex, String> {

    @Query(value = "SELECT ri FROM RecurringIndex ri WHERE (ri.id = :recurringEntityId)")
    RecurringIndex findByEntityId(Long entityId);
}
