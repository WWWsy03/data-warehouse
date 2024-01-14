package com.ssw331.warehousebackend.hiveMapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ssw331.warehousebackend.MySQLDTO.StaticDirectorDirector;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
@DS("hive")
public interface HiveDirectorDirectorMapper extends BaseMapper<StaticDirectorDirector> {
    @Select("SELECT * FROM StaticDirectorDirector ORDER BY collaboration_number DESC LIMIT 20")
    List<StaticDirectorDirector> findTop20DirectorDirectorCollaborations();
}
