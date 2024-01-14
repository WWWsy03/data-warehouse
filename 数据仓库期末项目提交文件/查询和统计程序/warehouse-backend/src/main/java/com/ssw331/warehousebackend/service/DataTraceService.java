package com.ssw331.warehousebackend.service;

import org.springframework.stereotype.Service;

@Service
public interface DataTraceService {
    int getNonMovieCounts();
    int getNumberOfHarryMovies();
    int getNumberOfHarryVersions();
    int getNumberOfMergedWebpages();
}
