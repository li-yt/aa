package com.fh.shiro.service;

import com.fh.shiro.bean.role.RolePo;

import java.util.List;
import java.util.Map;

/**
 * @author Lenovo
 */
public interface RoleService {
    List<Map<String, Object>> queryRoleTree(Integer userId);

    void addRole(RolePo po);

    RolePo queryRoleById(Integer id);

    List<Map<String, Object>> queryOrganTree(Integer roleId);

    List<Map<String, Object>> queryRoleTreeByRightId(Integer rightId);
}
