package com.ssw331.warehousebackend.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ssw331.warehousebackend.MySQLDTO.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
@DS("mysql")
public interface DirectorActorMapper extends BaseMapper<StaticDirectorActor> {
    @Select("SELECT * FROM StaticDirectorActor ORDER BY collaboration_number DESC LIMIT 20")
    List<StaticDirectorActor> findTop20DirectorActorCollaborations();
}
