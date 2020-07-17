package com.fh.product.bean.attr;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_attribute")
public class AttributePo {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private Integer typeId;

    private String attrName;

    private Integer selectType;

    private Integer inputType;

    private String inputList;

    private Integer sort;

    private Integer filterType;

    private Integer searchType;

    private Integer relatedStatus;

    private Integer handAddStatus;

    private Integer attrType;
}
