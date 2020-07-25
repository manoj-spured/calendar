package com.calendar.core.repository;

import com.calendar.core.model.AttendeeIndex;
import com.calendar.core.model.RecurringIndex;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecurringIndexRepository extends ElasticsearchRepository<RecurringIndex, String> {
    @Query("{\n" +
            "      \"bool\": {\n" +
            "         \"must\": [\n" +
            "            {\n" +
            "               \"match\": {\n" +
            "                  \"entityId\": ?0 \n" +
            "               }\n" +
            "            }\n" +
            "         ]\n" +
            "      }\n" +
            "   }")
    RecurringIndex findByEntityId(Long entityId);
}
