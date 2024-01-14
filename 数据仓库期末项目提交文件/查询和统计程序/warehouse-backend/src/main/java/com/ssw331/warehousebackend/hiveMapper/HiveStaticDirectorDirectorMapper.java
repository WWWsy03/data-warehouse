package com.ssw331.warehousebackend.hiveMapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ssw331.warehousebackend.MySQLDTO.StaticDirectorActor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
@DS("hive")
public interface HiveStaticDirectorDirectorMapper extends BaseMapper<StaticDirectorActor> {
    @Select("SELECT director_name2 FROM StaticDirectorDirector WHERE director_name1 = #{directorName}")
    List<String> selectDirectorNamesByDirectorName(@Param("directorName") String directorName);
}
