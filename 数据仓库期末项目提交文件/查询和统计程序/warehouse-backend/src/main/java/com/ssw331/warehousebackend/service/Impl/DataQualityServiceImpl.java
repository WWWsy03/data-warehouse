package com.ssw331.warehousebackend.service.Impl;

import com.ssw331.warehousebackend.service.DataQualityService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DataQualityServiceImpl implements DataQualityService {
    @Override
    public Map<String, Object> getDataStatistics() {
        Map<String, Object> statistics = new HashMap<>();
        statistics.put("totalProducts", 169573);
        statistics.put("commentsNulls", 10523);
        statistics.put("commentsNullRate", 6.2055869743414345);
        statistics.put("costNulls", 1);
        statistics.put("costNullRate", 5.897165232672655E-4);
        statistics.put("formatNulls", 128);
        statistics.put("formatNullRate", 0.07548371497820998);
        statistics.put("gradeNulls", 25593);
        statistics.put("gradeNullRate", 15.092614979979125);
        statistics.put("totalMovies", 110855);
        statistics.put("timeNulls", 9989);
        statistics.put("timeNullRate", 9.010870055477877);
        statistics.put("typeNulls", 514);
        statistics.put("typeNullRate", 0.46366875648369493);
        return statistics;
    }

}
