package com.fh.shiro.bean.userRole;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_user_role")
public class UserRolePo {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private Integer roleId;

}
