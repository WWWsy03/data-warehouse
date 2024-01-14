package com.ssw331.warehousebackend.service;

import com.ssw331.warehousebackend.MySQLDTO.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface RelationService {
    List<StaticDirectorActor> getTop20DirectorActorCollaborations();
    List<StaticActorActor> getTop20ActorActorCollaborations();
    List<StaticDirectorDirector> getTop20DirectorDirectorCollaborations();
    List<Map<String, Object>> getMostCommentedActorPairByMovieType(String movieType);

}
