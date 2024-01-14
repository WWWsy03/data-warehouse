package com.ssw331.warehousebackend.MySQLDTO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("StaticActorActor")
public class StaticActorActor {
    @TableField("actor_name1")
    private String actorName1;
    @TableField("actor_name2")
    private String actorName2;
    @TableField("collaboration_number")
    private int collaborationNumber;
}
