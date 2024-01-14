package com.ssw331.warehousebackend.hiveMapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ssw331.warehousebackend.MySQLDTO.StaticActorActor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
@DS("hive")
public interface HiveActorActorMapper extends BaseMapper<StaticActorActor> {
    @Select("SELECT * FROM StaticActorActor ORDER BY collaboration_number DESC LIMIT 20")
    List<StaticActorActor> findTop20ActorActorCollaborations();

}
