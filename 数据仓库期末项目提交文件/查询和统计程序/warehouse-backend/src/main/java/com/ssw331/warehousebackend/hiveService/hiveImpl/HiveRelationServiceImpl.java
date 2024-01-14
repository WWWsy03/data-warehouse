package com.ssw331.warehousebackend.hiveService.hiveImpl;

import com.ssw331.warehousebackend.MySQLDTO.StaticActorActor;
import com.ssw331.warehousebackend.MySQLDTO.StaticDirectorActor;
import com.ssw331.warehousebackend.MySQLDTO.StaticDirectorDirector;
import com.ssw331.warehousebackend.hiveService.HiveRelationService;
import com.ssw331.warehousebackend.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class HiveRelationServiceImpl implements HiveRelationService {

    @Autowired
    private DirectorActorMapper directorActorMapper;
    private ActorActorMapper actorActorMapper;
    private DirectorDirectorMapper directorDirectorMapper;
    private MovieMapper movieMapper;
    private MovieActorMapper movieActorMapper;
    private StaticActorActorMapper staticActorActorMapper;

    public HiveRelationServiceImpl(ActorActorMapper actorActorMapper, DirectorDirectorMapper directorDirectorMapper, MovieMapper movieMapper, StaticActorActorMapper staticActorActorMapper, MovieActorMapper movieActorMapper) {
        this.actorActorMapper = actorActorMapper;
        this.staticActorActorMapper = staticActorActorMapper;
        this.movieMapper = movieMapper;
        this.movieActorMapper = movieActorMapper;
        this.directorDirectorMapper = directorDirectorMapper;
    }


    @Override
    public List<StaticDirectorActor> getTop20DirectorActorCollaborations() {
        return directorActorMapper.findTop20DirectorActorCollaborations();
    }

    @Override
    public List<StaticActorActor> getTop20ActorActorCollaborations() {
        return actorActorMapper.findTop20ActorActorCollaborations();
    }

    @Override
    public List<StaticDirectorDirector> getTop20DirectorDirectorCollaborations() {
        return directorDirectorMapper.findTop20DirectorDirectorCollaborations();
    }

    @Override
    public List<Map<String, Object>> getMostCommentedActorPairByMovieType(String movieType) {
        return staticActorActorMapper.selectMostCommentedActorPairByMovieType(movieType);
    }
}
