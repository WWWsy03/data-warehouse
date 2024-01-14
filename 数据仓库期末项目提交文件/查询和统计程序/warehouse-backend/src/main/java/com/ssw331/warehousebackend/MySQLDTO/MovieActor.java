package com.ssw331.warehousebackend.MySQLDTO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("MovieActor")
public class MovieActor {
    @TableId("movie_id")
    private int movieId;
    @TableField("actor_name")
    private String actorName;
}
