package com.fh.shiro.service.Impl;

import com.fh.shiro.bean.roleRight.RoleRightPo;
import com.fh.shiro.mapper.RoleRightMapper;
import com.fh.shiro.service.RoleRightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleRightServiceImpl implements RoleRightService {

    @Autowired
    private RoleRightMapper roleRightMapper;

    @Override
    @Transactional
    public void addRoleRight(Integer roleId, String rightIds) {
        roleRightMapper.deleteRoleRightByRoleId(roleId);
        if (rightIds != "" && rightIds != null) {
            String[] split = rightIds.split(",");
            List<RoleRightPo> list = new ArrayList<>();
            for (int i = 0; i < split.length; i++) {
                RoleRightPo roleRightPo = new RoleRightPo();
                roleRightPo.setRoleId(roleId);
                roleRightPo.setRightId(Integer.valueOf(split[i]));
                list.add(roleRightPo);
            }
            roleRightMapper.addRoleRight(list);
        }

    }


}
