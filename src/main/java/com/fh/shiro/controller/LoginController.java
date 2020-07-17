package com.fh.shiro.controller;

import com.fh.shiro.bean.user.LoginUser;
import com.fh.shiro.bean.user.UserPo;
import com.fh.shiro.service.UserService;
import com.fh.util.AccountVerifyException;
import com.fh.util.MD5Util;
import com.fh.util.ResponseServer;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    private UserService userService;


    @RequestMapping("doLogin")
    @ResponseBody
    public ResponseServer doLogin(LoginUser user){
        if (StringUtils.isBlank(user.getUserName()) || StringUtils.isBlank(user.getPassword())){
            return ResponseServer.failedWithoutData();
        }
        String s = MD5Util.MD5Encode(MD5Util.MD5Encode(user.getPassword(), "utf-8"), "utf-8");
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),s);
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            UserPo userPo = (UserPo) SecurityUtils.getSubject().getPrincipal();
            return ResponseServer.successWithData(userPo);
        }catch (AccountVerifyException a){
            if (a.getCode() == 3001){
                token.clear();
                return ResponseServer.failedWithoutData();
            }else if(a.getCode() == 3002){
                token.clear();
                return ResponseServer.failedWithoutData();
            }
        }catch (IllegalArgumentException i){
            token.clear();
            return ResponseServer.failedWithoutData();
        }catch (IncorrectCredentialsException i){
            token.clear();
            return ResponseServer.failedWithoutData();
        }
        return null;
    }


}
