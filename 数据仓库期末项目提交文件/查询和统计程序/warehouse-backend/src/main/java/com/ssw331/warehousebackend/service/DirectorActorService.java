package com.ssw331.warehousebackend.service;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface DirectorActorService {
    List<String> getActorNamesByDirectorName(String directorName);
    List<String> getDirectorNamesByDirectorName(String directorName);
    List<String> getActorNamesByActorName(String actorName);
    List<String> getDirectorNamesByActorName(String actorName);
}
