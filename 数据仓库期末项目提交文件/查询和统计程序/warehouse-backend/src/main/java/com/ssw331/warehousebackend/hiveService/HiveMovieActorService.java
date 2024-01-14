package com.ssw331.warehousebackend.hiveService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HiveMovieActorService {
    List<String> getMovieNamesByActorName(String actorName);
}
