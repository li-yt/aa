package com.fh.shiro.service.Impl;

import com.fh.shiro.bean.role.RolePo;
import com.fh.shiro.bean.userRole.UserRoleVo;
import com.fh.shiro.mapper.RoleMapper;
import com.fh.shiro.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lenovo
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Map<String, Object>> queryRoleTree(Integer userId) {
        return roleMapper.queryRoleTree(userId);
    }

    @Override
    public void addRole(RolePo po) {
        if (po.getId()!=null){
            roleMapper.updateById(po);
        }else {
            roleMapper.insert(po);
        }
    }

    @Override
    public RolePo queryRoleById(Integer id) {
        return roleMapper.selectById(id);
    }

    @Override
    public List<Map<String, Object>> queryOrganTree(Integer roleId) {
        List<UserRoleVo> list = roleMapper.queryOrganTree(roleId);
        return getTree(list,"dept_0");
    }

    @Override
    public List<Map<String, Object>> queryRoleTreeByRightId(Integer rightId) {
        return roleMapper.queryRoleTreeByRightId(rightId);
    }

    public List<Map<String, Object>> getTree(List<UserRoleVo> userRoleVos , String pid){
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
                map.put("checked",vo.getChecked());
                if (pid.equals("dept_0")){
                    map.put("iconClose","/static/orgimg/3.png");
                    map.put("iconOpen","/static/orgimg/2.png");
                    map.put("nocheck",true);
                }else if (vo.getType() == 1){
                    map.put("icon","/static/orgimg/4.png");
                    map.put("nocheck",true);
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
