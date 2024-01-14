package com.ssw331.warehousebackend.MySQLDTO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("Time")
public class Time {
    @TableId("release_time_id")
    private int releaseTimeId;
    @TableField("year")
    private int year;
    @TableField("month")
    private int month;
    @TableField("day")
    private int day;
    @TableField("week")
    private String week;
    @TableField("season")
    private int season;
    @TableField("movie_number")
    private int movieNumber;
}
