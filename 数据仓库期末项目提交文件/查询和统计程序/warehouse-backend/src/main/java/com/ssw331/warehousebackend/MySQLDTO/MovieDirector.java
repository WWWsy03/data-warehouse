package com.ssw331.warehousebackend.MySQLDTO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("MovieDirector")
public class MovieDirector {
    @TableId("movie_id")
    private int movieId;
    @TableField("director_name")
    private String directorName;
}
