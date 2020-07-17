package com.fh.product.bean.classify;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("t_classify")
@Data
public class ClassifyPo {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String classifyName;

    private Integer pid;

    private Integer status;

    private Integer level;

    private Integer productCount;

    private String productUnit;

    private Integer navStatus;

    private Integer sort;


}
