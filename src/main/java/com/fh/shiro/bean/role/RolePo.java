package com.fh.shiro.bean.role;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Lenovo
 * 角色实体类
 * 对应数据库t_tole表
 */
@Data
@TableName("t_role")
public class RolePo {
        /**角色id*/
        @TableId(value = "id",type = IdType.AUTO)
        private Integer id;

        /**角色关键字*/
        private String roleKey;

        /**角色名称*/
        private String roleName;


}
