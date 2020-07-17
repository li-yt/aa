package com.fh.shiro.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fh.shiro.bean.user.PageBean;
import com.fh.shiro.bean.user.UserPo;
import com.fh.shiro.bean.user.UserSearch;
import com.fh.shiro.bean.user.UserVo;

import java.util.List;

/**
 * @author Lenovo
 */
public interface UserService {
    IPage<UserVo> queryUser(PageBean pageBean, UserSearch userSearch);

    void addUser(UserPo po);

    UserPo queryUserById(Integer id);

    void deleteById(Integer id);

    void updatePass(Integer id);

    List<UserPo> queryUserByName(String userName);

    UserPo queryUserByNameOne(String userName);
}
