package com.fh.product.bean.brand;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_brand")
public class BrandPo  {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String brandName;

    private Integer sort;

    private Integer status;

    private String logo;

    private Integer  factoryStatus;

    private Integer productCount;

    private Integer productCommentCount;

    private String firstLetter;

}
