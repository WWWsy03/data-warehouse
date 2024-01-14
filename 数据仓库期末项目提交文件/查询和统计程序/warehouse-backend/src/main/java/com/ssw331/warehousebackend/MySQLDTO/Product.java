package com.ssw331.warehousebackend.MySQLDTO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("Product")
public class Product {
    @TableId("product_id")
    private String productId;
    @TableField("comment_number")
    private int commentNumber;
    @TableField("cost")
    private String cost;
    @TableField("format")
    private String format;
    @TableField("grade")
    private String grade;
}
