package com.fh.shiro.service;

import java.util.List;
import java.util.Map;

public interface UserRoleService {
    List<Map<String, Object>> queryOrganTree();

    void addUserRole(String userId, String roleIds);

    void addRoleUser(Integer roleId, String userIds);
}
