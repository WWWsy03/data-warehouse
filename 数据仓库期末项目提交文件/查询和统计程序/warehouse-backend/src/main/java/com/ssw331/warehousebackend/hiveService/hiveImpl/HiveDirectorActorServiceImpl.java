package com.ssw331.warehousebackend.hiveService.hiveImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ssw331.warehousebackend.MySQLDTO.StaticDirectorActor;
import com.ssw331.warehousebackend.hiveService.HiveDirectorActorService;
import com.ssw331.warehousebackend.mapper.StaticActorActorMapper;
import com.ssw331.warehousebackend.mapper.StaticDirectorActorMapper;
import com.ssw331.warehousebackend.mapper.StaticDirectorDirectorMapper;
import com.ssw331.warehousebackend.service.DirectorActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HiveDirectorActorServiceImpl implements HiveDirectorActorService {
        @Autowired
        private StaticDirectorActorMapper staticDirectorActorMapper;
        @Autowired
        private StaticDirectorDirectorMapper staticDirectorDirectorMapper;
        @Autowired
        private StaticActorActorMapper staticActorActorMapper;

        @Override
        public List<String> getActorNamesByDirectorName(String directorName) {
            QueryWrapper<StaticDirectorActor> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("director_name", directorName);
            List<StaticDirectorActor> staticDirectorActors = staticDirectorActorMapper.selectList(queryWrapper);

            List<String> actorNames = new ArrayList<>();
            for (StaticDirectorActor staticDirectorActor : staticDirectorActors) {
                actorNames.add(staticDirectorActor.getActorName());
            }
            return actorNames;
        }

        @Override
        public List<String> getDirectorNamesByDirectorName(String directorName) {
            return staticDirectorDirectorMapper.selectDirectorNamesByDirectorName(directorName);
        }

        @Override
        public List<String> getActorNamesByActorName(String actorName) {
            return staticActorActorMapper.selectActorNamesByActorName(actorName);
        }


    @Override
        public List<String> getDirectorNamesByActorName(String actorName) {
            QueryWrapper<StaticDirectorActor> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("actor_name", actorName);
            List<StaticDirectorActor> staticDirectorActors = staticDirectorActorMapper.selectList(queryWrapper);

            List<String> directorNames = new ArrayList<>();
            for (StaticDirectorActor staticDirectorActor : staticDirectorActors) {
                directorNames.add(staticDirectorActor.getDirectorName());
            }
            return directorNames;
        }
    }

