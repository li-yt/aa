package com.fh.shiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fh.shiro.bean.user.UserPo;
import com.fh.shiro.bean.user.UserSearch;
import com.fh.shiro.bean.user.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lenovo
 */
@Repository
@Mapper
public interface UserMapper extends BaseMapper<UserPo> {
    IPage<UserVo> queryUser(Page<UserVo> userVos, @Param("search") UserSearch userSearch);

    List<UserPo> queryUserByName(String userName);
}
