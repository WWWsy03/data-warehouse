package com.ssw331.warehousebackend.MySQLDTO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("StaticDirectorDirector")
public class StaticDirectorDirector {
    @TableField("director_name1")
    private String directorName1;
    @TableField("director_name2")
    private String directorName2;
    @TableField("collaboration_number")
    private int collaborationNumber;
}
