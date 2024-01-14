package com.ssw331.warehousebackend.MySQLDTO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("MovieProduct")
public class MovieProduct {
    @TableId("movie_id")
    private int movieId;
    @TableField("product_id")
    private String productId;
}
