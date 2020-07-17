package com.fh.shiro.service;

import com.fh.shiro.bean.right.RightPo;

import java.util.List;
import java.util.Map;

/**
 * @author Lenovo
 */
public interface RightService {
    List<Map<String, Object>> queryRightTree(Integer id);

    List<Map<String, Object>> queryRoleShowTree(Integer userId);

    void addRight(RightPo rightPo);

    RightPo queryRightById(Integer id);

    List<Map<String,Object>> queryRightTreeByUserId(Integer userId);
}
