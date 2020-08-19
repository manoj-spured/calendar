package com.calendar.core.jparepository;

import com.calendar.core.model.RecurringIndex;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecurringIndexJPARepository extends CrudRepository<RecurringIndex, String> {

    @Query(value = "SELECT ri FROM RecurringIndex ri WHERE ri.id IN (:recurringEntityId)")
    Page<RecurringIndex> findByEntityId(List<Long> entityId, Pageable pageable);
}
