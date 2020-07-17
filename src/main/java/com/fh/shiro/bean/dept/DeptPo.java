package com.fh.shiro.bean.dept;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Lenovo
 * 部门实体类
 * 对应数据库的t_dept表
 */
@Data
@TableName("t_dept")
public class DeptPo {

        /**部门id*/
        @TableId(value = "id",type = IdType.AUTO)
        private Integer id;

        /**部门名称*/
        private String deptName;

        /**部门所属*/
        private Integer pid;

        /**部门状态*/
        private Integer status;


}
