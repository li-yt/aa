package com.fh.shiro.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fh.shiro.bean.user.PageBean;
import com.fh.shiro.bean.user.UserPo;
import com.fh.shiro.bean.user.UserSearch;
import com.fh.shiro.bean.user.UserVo;
import com.fh.shiro.mapper.UserMapper;
import com.fh.shiro.service.UserService;
import com.fh.util.MD5Util;
import com.fh.util.SalfUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Lenovo
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    /**条件和分页查询*/
    @Override
    public IPage<UserVo> queryUser(PageBean pageBean, UserSearch userSearch) {
        Page<UserVo> userVos = new Page<>(pageBean.getPage(),pageBean.getLimit());
        IPage<UserVo> iPage = userMapper.queryUser(userVos,userSearch);
        return iPage;
    }
    /**修改和新增*/
    @Override
    public void addUser(UserPo po) {
        if (po.getId() == null){
        po.setCreateTime(new Date());
        po.setStatus(1);
        String string = "123456";

        String s = MD5Util.MD5Encode(MD5Util.MD5Encode(string, "utf-8"), "utf-8");
    /*密码加盐加密处理*/
        String saltCode= UUID.randomUUID().toString().replace("-","");
        po.setPassword(SalfUtil.md5(s,saltCode));
        po.setSaltCode(saltCode);
        userMapper.insert(po);
        }else {
            userMapper.updateById(po);
        }
    }
    /**根据id查询单个数据*/
    @Override
    public UserPo queryUserById(Integer id) {
        return userMapper.selectById(id);
    }
    /**根据id删除用户*/
    @Override
    public void deleteById(Integer id) {
        UserPo userPo = userMapper.selectById(id);
        userPo.setStatus(2);
        userMapper.updateById(userPo);
    }
    /**重置用户密码*/
    @Override
    public void updatePass(Integer id) {
        UserPo po = new UserPo();
        po.setId(id);
        String s = MD5Util.MD5Encode(MD5Util.MD5Encode("123456", "utf-8"), "utf-8");
        /*密码加盐加密处理*/
        String saltCode= UUID.randomUUID().toString().replace("-","");
        po.setPassword(SalfUtil.md5(s,saltCode));
        po.setSaltCode(saltCode);
        userMapper.updateById(po);
    }

    @Override
    public List<UserPo> queryUserByName(String userName) {
        return userMapper.queryUserByName(userName);
    }

    @Override
    public UserPo queryUserByNameOne(String userName) {
        return userMapper.selectOne(new QueryWrapper<UserPo>().eq("userName",userName));
    }
}
