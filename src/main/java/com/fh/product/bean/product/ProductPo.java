package com.fh.product.bean.product;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_product")
public class ProductPo {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String productName;

    private Integer classifyId;


    private Integer brandId;

    private Double price;

    private Integer stock;

    private String productImg;

    private String unit;

    private Double weight;


}
