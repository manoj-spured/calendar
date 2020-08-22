package com.calendar.core.jparepository;

import com.calendar.core.model.EventIndex;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventIndexJPARepository extends CrudRepository<EventIndex, String> {

    @Query(value = "SELECT ei FROM EventIndex ei WHERE ei.entityId IN (:entityIdList)")
    List<EventIndex> findAllByEntityIdIn(List<Long> entityIdList);
}
