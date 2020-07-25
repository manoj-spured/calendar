package com.calendar.core.repository;

import com.calendar.core.model.AttendeeIndex;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendeeIndexRepository extends ElasticsearchRepository<AttendeeIndex, String> {

    @Query("{\n" +
            "      \"bool\": {\n" +
            "         \"must\": [\n" +
            "            {\n" +
            "               \"match\": {\n" +
            "                  \"id\": \"?0\"\n" +
            "               }\n" +
            "            },\n" +
            "            {\n" +
            "               \"bool\": {\n" +
            "                  \"should\": [\n" +
            "                     {\n" +
            "                        \"range\": {\n" +
            "                           \"startTime\": {\n" +
            "                              \"from\":  ?1 , \n" +
            "                              \"to\": ?2 , \n" +
            "                              \"include_lower\": true, \n" +
            "                              \"include_upper\": true\n" +
            "                           }\n" +
            "                        }\n" +
            "                     },\n" +
            "                     {\n" +
            "                        \"range\": {\n" +
            "                           \"endTime\": {\n" +
            "                              \"from\":  ?1 , \n" +
            "                              \"to\": ?2 , \n" +
            "                              \"include_lower\": true, \n" +
            "                              \"include_upper\": true\n" +
            "                           }\n" +
            "                        }\n" +
            "                     },\n" +
            "                     {\n" +
            "                        \"bool\": {\n" +
            "                           \"must\": [\n" +
            "                              {\n" +
            "                                 \"range\": {\n" +
            "                                    \"startTime\": {\n" +
            "                                       \"from\": null,\n" +
            "                                       \"to\": ?1,\n" +
            "                                       \"include_lower\": true,\n" +
            "                                       \"include_upper\": true\n" +
            "                                    }\n" +
            "                                 }\n" +
            "                              },\n" +
            "                              {\n" +
            "                                 \"range\": {\n" +
            "                                    \"endTime\": {\n" +
            "                                       \"from\": ?2,\n" +
            "                                       \"to\": null,\n" +
            "                                       \"include_lower\": true,\n" +
            "                                       \"include_upper\": true\n" +
            "                                    }\n" +
            "                                 }\n" +
            "                              }\n" +
            "                           ]\n" +
            "                        }\n" +
            "                     }\n" +
            "                  ]\n" +
            "               }\n" +
            "            }\n" +
            "         ]\n" +
            "      }\n" +
            "   }")
    Page<AttendeeIndex> findByIdAndStartTimeAndEndTime(String id, Long startTime,Long endTime, Pageable pageable);
}
