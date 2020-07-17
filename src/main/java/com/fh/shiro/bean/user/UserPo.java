package com.fh.shiro.bean.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Lenovo
 * 用户实体类
 * 对应数据库的t_user表
 */
@Data
@TableName("t_user")
@AllArgsConstructor
@NoArgsConstructor
public class UserPo implements Serializable {

    /**id*/
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**用户名*/
    private String userName;

    /**真实姓名*/
    private String realName;

    /**密码*/
    private String password;

    /**生日*/
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    /**性别*/
    private Integer sex;

    /**联系电话*/
    private String phone;

    /**邮箱*/
    private String email;

    /**身份证号*/
    private String idCard;

    /**省的编码*/
    private String provinceCode;

    /**市的编码*/
    private String cityCode;

    /**县/区的编码*/
    private String areaCode;

    /**头像*/
    private String imgUrl;

    /**状态1.启用2.禁用*/
    private Integer status;

    /**登录时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date  loginTime;

    /**登录错误时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loginErrorTime;

    /**登录错误次数*/
    private Integer loginErrorCount;

    /**创建时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date  createTime;

    /**部门id*/
    private Integer deptId;

    private String saltCode;


}
