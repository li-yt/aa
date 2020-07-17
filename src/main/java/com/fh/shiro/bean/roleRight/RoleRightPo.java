package com.fh.shiro.bean.roleRight;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_role_right")
public class RoleRightPo {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private Integer roleId;

    private Integer rightId;

}
