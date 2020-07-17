package com.fh.shiro.bean.user;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Lenovo
 */
@Data
public class UserVo {

    private Integer id;

    private String userName;

    private String realName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private String sexName;

    private String phone;

    private String idCard;

    private String deptName;
    private String deptId;

    private String email;

    private String provinceName;

    private String cityName;

    private String areaName;

    private String imgUrl;

    private String statusName;

}
