package com.ssw331.warehousebackend.hiveService;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface HiveMovieDirectorService {
    List<String> getMovieNamesByDirectorName(String directorName);
}
