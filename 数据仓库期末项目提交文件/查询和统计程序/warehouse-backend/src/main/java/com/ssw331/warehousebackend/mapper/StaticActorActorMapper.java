package com.ssw331.warehousebackend.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ssw331.warehousebackend.MySQLDTO.StaticActorActor;
import com.ssw331.warehousebackend.MySQLDTO.StaticDirectorActor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
@DS("mysql")
public interface StaticActorActorMapper extends BaseMapper<StaticActorActor> {
    @Select("SELECT actor_name2 FROM StaticActorActor WHERE actor_name1 = #{actorName}")
    List<String> selectActorNamesByActorName(@Param("actorName") String actorName);

    @Select("SELECT actor_name1, actor_name2, SUM(collaboration_number) AS total_comments " +
            "FROM StaticActorActor " +
            "JOIN MovieActor ON StaticActorActor.actor_name1 = MovieActor.actor_name " +
            "JOIN Movie ON MovieActor.movie_id = Movie.movie_id " +
            "WHERE Movie.Type = #{movieType} " +
            "GROUP BY actor_name1, actor_name2 " +
            "ORDER BY total_comments DESC " +
            "LIMIT 1")
    List<Map<String, Object>> selectMostCommentedActorPairByMovieType(@Param("movieType") String movieType);


}
