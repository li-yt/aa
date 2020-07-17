package com.fh.shiro.controller;

import com.fh.shiro.service.RoleRightService;
import com.fh.util.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("roleRight")
public class RoleRightController {

    @Autowired
    private RoleRightService roleRightService;


    @RequestMapping("addRoleRight")
    public ResponseServer addRoleRight(Integer roleId,String rightIds){
        roleRightService.addRoleRight(roleId,rightIds);
        return ResponseServer.successWithoutData();
    }

}
