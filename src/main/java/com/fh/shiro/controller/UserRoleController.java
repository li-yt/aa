package com.fh.shiro.controller;

import com.fh.shiro.service.UserRoleService;
import com.fh.util.ResponseServer;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("userRole")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping("queryOrganTree")
    public List<Map<String,Object>> queryOrganTree(){
        return userRoleService.queryOrganTree();
    }
    /**根据用户添加角色*/
    @RequestMapping("addUserRole")
    @RequiresPermissions("admin:user:create:role")
    public ResponseServer addUserRole(String userId,String roleIds){
        userRoleService.addUserRole(userId,roleIds);
        return ResponseServer.successWithoutData();
    }
    /**根据角色添加用户*/
    @RequestMapping("addRoleUser")
    @RequiresPermissions("admin:role:user:create")
    public ResponseServer addRoleUser(Integer roleId,String userIds){
        userRoleService.addRoleUser(roleId,userIds);
        return ResponseServer.successWithoutData();
    }
}
