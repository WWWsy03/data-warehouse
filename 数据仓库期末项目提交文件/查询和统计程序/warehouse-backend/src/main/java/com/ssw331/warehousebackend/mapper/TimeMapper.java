package com.ssw331.warehousebackend.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ssw331.warehousebackend.MySQLDTO.Time;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@DS("mysql")
public interface TimeMapper extends BaseMapper<Time> {
}

