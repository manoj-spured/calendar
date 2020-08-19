package com.calendar.core.repository;

import com.calendar.core.model.AttendeeIndex;
import com.calendar.core.model.RecurringAttendeeIndex;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecurringAttendeeIndexRepository extends ElasticsearchRepository<RecurringAttendeeIndex, String> {
    @Query("{\n" +
            "      \"bool\": {\n" +
            "         \"must\": [\n" +
            "            {\n" +
            "               \"match\": {\n" +
            "                  \"recurringEntityId\": ?0 \n" +
            "               }\n" +
            "            }\n" +
            "         ]\n" +
            "      }\n" +
            "   }")
    Page<RecurringAttendeeIndex> findByRecurringEntityId(List<Long> recurringEntityId, Pageable pageable);
}
