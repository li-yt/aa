package com.fh.shiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.shiro.bean.userRole.UserRolePo;
import com.fh.shiro.bean.userRole.UserRoleVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRolePo> {

    List<UserRoleVo> queryOrganTree();

    void addUserRole(List<UserRolePo> list);

    void deleteUserRoleByUserId(String userId);

    void deleteUserRoleByRoleId(Integer roleId);
}
