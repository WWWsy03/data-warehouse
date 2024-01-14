package com.ssw331.warehousebackend.hiveMapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ssw331.warehousebackend.MySQLDTO.Movie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
@DS("hive")
public interface HiveMovieMapper extends BaseMapper<Movie> {
    @Select("SELECT m.* FROM Movie m " +
            "JOIN MovieProduct mp ON m.movie_id = mp.movie_id " +
            "JOIN Product p ON mp.product_id = p.product_id " +
            "WHERE p.grade > 4")
    List<Movie> findMoviesWithHighGradeProducts();
    @Select("SELECT movie_name FROM Movie WHERE Type LIKE CONCAT('%', #{type}, '%')")
    List<String> findMoviesByType(String type);
    @Select("SELECT COUNT(*) FROM Movie m JOIN Time t ON m.release_time_id = t.release_time_id " +
            "WHERE t.year = #{year} AND m.Type LIKE CONCAT('%', #{type}, '%')")
    int countMoviesByYearAndType(@Param("year") int year, @Param("type") String type);

    @Select("SELECT COUNT(*) FROM Movie m " +
            "JOIN Time t ON m.release_time_id = t.release_time_id " +
            "JOIN MovieDirector md ON m.movie_id = md.movie_id " +
            "WHERE t.year = #{year} AND md.director_name = #{directorName}")
    int countMoviesByYearAndDirectorName(@Param("year") int year, @Param("directorName") String directorName);
}

