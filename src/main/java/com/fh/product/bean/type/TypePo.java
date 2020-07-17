package com.fh.product.bean.type;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_type")
public class TypePo {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String typeName;

    private Integer attributeCount;

    private Integer paramCount;

    private Integer status;

}
