package com.ssw331.warehousebackend.hiveService;

import org.springframework.stereotype.Service;

@Service
public interface HiveDataTraceService {
    int getNonMovieCounts();
    int getNumberOfHarryMovies();
    int getNumberOfHarryVersions();
    int getNumberOfMergedWebpages();
}
