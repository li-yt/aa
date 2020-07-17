package com.fh.shiro.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Lenovo
 */
@Controller
@RequestMapping("main")
public class MainController {

    /**跳转至主页面*/
    @RequestMapping("toMain")
    public String toMain(){
        return "main";
    }

    /**跳转部门和用户展示*/
    @RequestMapping("toOrgan")
    @RequiresPermissions("admin:dept:user")
    public String toOrgan() {
        return "system/organ";
    }
    /**跳转用户角色*/
    @RequestMapping("toUserRole")
    @RequiresPermissions("admin:role:usee")
    public String toUserRole() {
        return "system/userRole";
    }
    /**跳转角色权限*/
    @RequestMapping("toRoleRight")
    @RequiresPermissions("admin:role:right")
    public String toRoleRight() {
        return "system/roleRight";
    }
    /**跳转权限*/
    @RequestMapping("toRight")
    @RequiresPermissions("shop:admin:right")
    public String toRight() {
        return "system/right";
    }

    @RequestMapping("toTest")
    public String toTest(){return "test";}


}
