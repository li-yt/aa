package com.fh.shiro.controller;

import com.fh.shiro.bean.role.RolePo;
import com.fh.shiro.service.RoleService;
import com.fh.util.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Lenovo
 */
@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("queryRoleTree")
    public List<Map<String,Object>> queryRoleTree(Integer userId){
        return roleService.queryRoleTree(userId);
    }

    @RequestMapping("addRole")
    public ResponseServer addRole(RolePo po){
        roleService.addRole(po);
        return ResponseServer.successWithData(po);
    }

    @RequestMapping("queryRoleById")
    public ResponseServer queryRoleById(Integer id){
      return  ResponseServer.successWithData(roleService.queryRoleById(id)) ;
    }

    @RequestMapping("queryOrganTree")
    public List<Map<String,Object>> queryOrganTree(Integer roleId){
        return roleService.queryOrganTree(roleId);
    }

    @RequestMapping("queryRoleTreeByRightId")
    public List<Map<String,Object>> queryRoleTreeByRightId(Integer rightId){
        return roleService.queryRoleTreeByRightId(rightId);
    }

}
