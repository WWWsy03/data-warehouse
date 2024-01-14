package com.ssw331.warehousebackend.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieActorService {
    List<String> getMovieNamesByActorName(String actorName);
}
