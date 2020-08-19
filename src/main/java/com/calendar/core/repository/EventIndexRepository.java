package com.calendar.core.repository;

import com.calendar.core.model.EventIndex;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventIndexRepository extends ElasticsearchRepository<EventIndex, String> {

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
    Page<EventIndex> findByEntityId(List<Long> entityId, Pageable pageable);
}
