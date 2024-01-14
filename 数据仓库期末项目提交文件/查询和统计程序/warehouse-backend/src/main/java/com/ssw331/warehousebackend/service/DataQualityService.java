package com.ssw331.warehousebackend.service;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public interface DataQualityService {
    Map<String, Object> getDataStatistics();
}
