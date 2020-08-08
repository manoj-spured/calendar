package com.calendar.core.jparepository;

import com.calendar.core.model.EventIndex;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventIndexJPARepository extends CrudRepository<EventIndex, String> {

    @Query(value = "SELECT ei FROM EventIndex ei WHERE (ei.id = :entityId)")
    EventIndex findByEntityId(Long entityId);
}
