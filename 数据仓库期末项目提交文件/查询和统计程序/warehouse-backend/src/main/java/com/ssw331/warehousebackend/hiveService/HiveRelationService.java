package com.ssw331.warehousebackend.hiveService;

import com.ssw331.warehousebackend.MySQLDTO.StaticActorActor;
import com.ssw331.warehousebackend.MySQLDTO.StaticDirectorActor;
import com.ssw331.warehousebackend.MySQLDTO.StaticDirectorDirector;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface HiveRelationService {
    List<StaticDirectorActor> getTop20DirectorActorCollaborations();
    List<StaticActorActor> getTop20ActorActorCollaborations();
    List<StaticDirectorDirector> getTop20DirectorDirectorCollaborations();
    List<Map<String, Object>> getMostCommentedActorPairByMovieType(String movieType);

}
