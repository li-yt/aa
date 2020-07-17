package com.fh.shiro.componect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.shiro.bean.right.RightPo;
import com.fh.shiro.bean.role.RolePo;
import com.fh.shiro.bean.user.UserPo;
import com.fh.shiro.mapper.RightMapper;
import com.fh.shiro.mapper.RoleMapper;
import com.fh.shiro.mapper.UserMapper;
import com.fh.util.AccountVerifyException;
import com.fh.util.MyEnum;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserRealm extends AuthorizingRealm {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RightMapper rightMapper;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取当前登录的用户
        UserPo userPo = (UserPo) SecurityUtils.getSubject().getPrincipal();
        //获取当前用户所拥有的角色
        List<RolePo> rolePoList = roleMapper.queryRoleListByUserId(userPo.getId());
        //获取当前用户所拥有的权限
        List<RightPo> rightPoList = rightMapper.queryRightListByUserId(userPo.getId());
        //给用户授权
        Set<String> roleSet = rolePoList.stream().filter(role -> role.getRoleKey() != null).map(RolePo::getRoleKey).collect(Collectors.toSet());
        Set<String> rightSet = rightPoList.stream().filter(rightPo -> rightPo.getRightKey() != null).map(RightPo::getRightKey).collect(Collectors.toSet());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(roleSet);
        simpleAuthorizationInfo.setStringPermissions(rightSet);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String userName = token.getUsername();
        UserPo user = userMapper.selectOne(new QueryWrapper<UserPo>().eq("userName", userName));
        if (user != null){
            if (user.getStatus() != 2){
                AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                        user,
                        user.getPassword(),
                        ByteSource.Util.bytes(user.getSaltCode()),
                        getName()
                );
                return authenticationInfo;
            }else {
                throw new AccountVerifyException(MyEnum.LOGIN_STATUS);
            }
        }else {
            throw new AccountVerifyException(MyEnum.LOGIN_USERNAME_NOTFIND);
        }

    }
}
