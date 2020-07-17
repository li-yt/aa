package com.fh.shiro.bean.right;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Lenovo
 * 权限实体类
 * 对应数据库的t_right表
 */
@Data
@TableName("t_right")
public class RightPo {
        /**权限id*/
        @TableId(value = "id",type = IdType.AUTO)
        private Integer id;

        /**权限名称*/
        private String rightName;

        /**菜单路径*/
        private String  pathUrl;

        /**权限类型*/
        private Integer type;

        /**权限所属*/
        private Integer pid;

        /**权限关键字*/
        private String rightKey;

        /**权限状态*/
        private Integer status;


}
