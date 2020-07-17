package com.fh.shiro.service.Impl;

import com.fh.shiro.bean.userRole.UserRolePo;
import com.fh.shiro.bean.userRole.UserRoleVo;
import com.fh.shiro.mapper.UserRoleMapper;
import com.fh.shiro.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<Map<String, Object>> queryOrganTree() {
        List<UserRoleVo> userRoleVos = userRoleMapper.queryOrganTree();
        return getTree(userRoleVos,"dept_0");
    }

    @Override
    @Transactional
    public void addUserRole(String userId, String roleIds) {
        userRoleMapper.deleteUserRoleByUserId(userId);
        if (roleIds!="" && roleIds != null) {
            String[] split = roleIds.split(",");
            List<UserRolePo> list = new ArrayList<>();

            for (int i = 0; i < split.length; i++) {
                UserRolePo userRolePo = new UserRolePo();
                userRolePo.setRoleId(Integer.valueOf(split[i]));
                userRolePo.setUserId(Integer.valueOf(userId));
                list.add(userRolePo);
            }
            userRoleMapper.addUserRole(list);
        }
    }

    @Override
    @Transactional
    public void addRoleUser(Integer roleId, String userIds) {
        userRoleMapper.deleteUserRoleByRoleId(roleId);
        if (userIds!="" && userIds != null) {
            String[] split = userIds.split(",");
            List<UserRolePo> list = new ArrayList<>();
            for (int i = 0; i < split.length; i++) {
                UserRolePo userRolePo = new UserRolePo();
                userRolePo.setRoleId(roleId);
                userRolePo.setUserId(Integer.valueOf(split[i]));
                list.add(userRolePo);
            }
            userRoleMapper.addUserRole(list);
        }
    }

    public List<Map<String, Object>> getTree( List<UserRoleVo> userRoleVos , String pid){
        List<Map<String, Object>> list = new ArrayList<>();
        userRoleVos.forEach(vo->{
            Map<String, Object> map = null;
            if (pid.equals(vo.getPid())){
                map = new HashMap<>();
                map.put("id",vo.getId());
                map.put("name",vo.getName());
                map.put("pid",vo.getPid());
                map.put("type",vo.getType());
                map.put("sex",vo.getSex());
                if (pid.equals("dept_0")){
                    map.put("iconClose","/static/orgimg/3.png");
                    map.put("iconOpen","/static/orgimg/2.png");
                }else if (vo.getType() == 1){
                    map.put("icon","/static/orgimg/4.png");
                }else {
                    if (vo.getSex() == 1){
                        map.put("icon","/static/orgimg/nan.png");
                    }else {
                        map.put("icon","/static/orgimg/nv.png");
                    }
                }
                map.put("children",getTree(userRoleVos,vo.getId()));
            }
            if (map != null){
                list.add(map);
            }
        });
          return  list;
    }

}
