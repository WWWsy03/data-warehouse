package com.ssw331.warehousebackend.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ssw331.warehousebackend.MySQLDTO.StaticDirectorActor;
import com.ssw331.warehousebackend.MySQLDTO.StaticDirectorDirector;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
@DS("mysql")
public interface StaticDirectorDirectorMapper extends BaseMapper<StaticDirectorActor> {
    @Select("SELECT director_name2 FROM StaticDirectorDirector WHERE director_name1 = #{directorName}")
    List<String> selectDirectorNamesByDirectorName(@Param("directorName") String directorName);
}
