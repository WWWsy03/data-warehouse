package com.ssw331.warehousebackend.MySQLDTO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("StaticDirectorActor")
public class StaticDirectorActor {
    @TableField("director_name")
    private String directorName;
    @TableField("actor_name")
    private String actorName;
    @TableField("collaboration_number")
    private int collaborationNumber;
}
