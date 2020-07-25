package com.calendar.core.repository;

import com.calendar.core.model.AttendeeIndex;
import com.calendar.core.model.RecurringAttendeeIndex;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

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
    RecurringAttendeeIndex findByRecurringEntityId(Long recurringEntityId);
}
