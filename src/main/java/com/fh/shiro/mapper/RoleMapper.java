package com.fh.shiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.shiro.bean.role.RolePo;
import com.fh.shiro.bean.userRole.UserRoleVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Lenovo
 */
@Repository
@Mapper
public interface RoleMapper extends BaseMapper<RolePo> {
    List<Map<String, Object>> queryRoleTree(Integer userId);

    List<UserRoleVo> queryOrganTree(Integer roleId);

    List<Map<String, Object>> queryRoleTreeByRightId(Integer rightId);

    List<RolePo> queryRoleListByUserId(Integer id);
}
