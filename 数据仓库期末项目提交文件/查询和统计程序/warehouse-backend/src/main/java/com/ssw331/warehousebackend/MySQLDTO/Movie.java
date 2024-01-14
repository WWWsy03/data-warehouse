package com.ssw331.warehousebackend.MySQLDTO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;

@Data
@TableName("Movie")
public class Movie {
    @TableId("movie_id")
    @Getter
    private int movieId;
    @TableField("Type")
    @Getter
    private String Type;
    @TableField("movie_name")
    @Getter
    private String movieName;
    @TableField("release_time_id")
    @Getter
    private int releaseTimeId;
    @TableField("time")
    @Getter
    private String time;
}