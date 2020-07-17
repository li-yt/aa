package com.fh.shiro.mapper;

import com.fh.shiro.bean.roleRight.RoleRightPo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RoleRightMapper {


    void addRoleRight(List<RoleRightPo> list);

    void deleteRoleRightByRoleId(Integer roleId);
}
