package com.ssw331.warehousebackend.service.Impl;

import com.ssw331.warehousebackend.MySQLDTO.*;
import com.ssw331.warehousebackend.mapper.*;
import com.ssw331.warehousebackend.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RelationServiceImpl implements RelationService {

    @Autowired
    private DirectorActorMapper directorActorMapper;
    private ActorActorMapper actorActorMapper;
    private DirectorDirectorMapper directorDirectorMapper;
    private MovieMapper movieMapper;
    private MovieActorMapper movieActorMapper;
    private StaticActorActorMapper staticActorActorMapper;

    public RelationServiceImpl(ActorActorMapper actorActorMapper, DirectorDirectorMapper directorDirectorMapper, MovieMapper movieMapper, StaticActorActorMapper staticActorActorMapper, MovieActorMapper movieActorMapper) {
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
