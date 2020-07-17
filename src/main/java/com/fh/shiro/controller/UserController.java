package com.fh.shiro.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fh.shiro.bean.user.PageBean;
import com.fh.shiro.bean.user.UserPo;
import com.fh.shiro.bean.user.UserSearch;
import com.fh.shiro.bean.user.UserVo;
import com.fh.shiro.service.UserService;
import com.fh.util.*;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Lenovo
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**分页和条件查询*/
    @RequestMapping("queryUserList")
    @ResponseBody
    public PageBean queryUserList(PageBean pageBean, UserSearch userSearch){
        IPage<UserVo> productVoIPage = userService.queryUser(pageBean,userSearch);
        return new PageBean(productVoIPage.getRecords(),productVoIPage.getTotal());
    }
    /**上传图片*/
    @RequestMapping("upLoad")
    @ResponseBody
    public Map<String,Object> upLoad(@RequestParam("file")MultipartFile file, HttpServletResponse response) throws IOException {
        String filename = file.getOriginalFilename();
        String images = AliyunOSSUtil.uploadFile(file.getInputStream(), filename, "images");
        Map<String,Object> map = new HashMap<>();
        map.put("code","0");
        map.put("img",images);
        return map;
    }
    /**新增和修改*/
    @RequestMapping("addUser")
    @ResponseBody
    @RequiresPermissions(value={"admin:user:create","admin:user:update"},logical = Logical.OR)
    public ResponseServer addUser(UserPo po){
        Map<String,Object> map = null;
        if (po.getId() == null){
            userService.addUser(po);
        }else {
            if (po != null){
                List<UserPo> userPos = userService.queryUserByName(po.getUserName());
                map = new ConcurrentHashMap<>();
                if (po.getUserName().equals("")){
                    map.put("code", 1);
                    map.put("codeMsg1", CrowdConstant.MESSAGE_STRING_INVALIDATE);
                }else if (!userPos.isEmpty()){
                    map.put("code",2);
                    map.put("codeMsg2","用户名已经存在");
                }
            }
        }
        return ResponseServer.successWithoutData();
    }
    /**根据id查询单个数据*/
    @RequestMapping("queryUserById")
    @ResponseBody
    public UserPo queryUserById(Integer id){
        return userService.queryUserById(id);
    }
    /**根据id删除id*/
    //aaaaa
    @RequestMapping("deleteById")
    @ResponseBody
    @RequiresPermissions("admin:user:delete")
    public ResponseServer deleteById(Integer id){
        userService.deleteById(id);
        return ResponseServer.successWithoutData();
    }
    /**重置用户密码*/
    @RequestMapping("resetPassword")
    @ResponseBody
    @RequiresPermissions("admin:user:reset")
    public ResponseServer resetPassword(Integer id){
        userService.updatePass(id);
        return ResponseServer.successWithoutData();
    }

    @RequestMapping("queryPasswordById")
    @ResponseBody
    public ResponseServer queryPasswordById(Integer id,String newPassword,String oldPassword){
        UserPo userPo = userService.queryUserById(id);
        /*密码加盐加密处理*/
        String s = MD5Util.MD5Encode(MD5Util.MD5Encode(oldPassword, "utf-8"), "utf-8");
        String s1 = SalfUtil.md5(s, userPo.getSaltCode());
        if (s1.equals(userPo.getPassword())){
            String s2 = MD5Util.MD5Encode(MD5Util.MD5Encode(newPassword, "utf-8"), "utf-8");
            String saltCode= UUID.randomUUID().toString().replace("-","");
            System.out.println(saltCode);
            String s3 = SalfUtil.md5(s2, saltCode);
            System.out.println(s3);
            userPo.setPassword(s3);
            userPo.setSaltCode(saltCode);
            userService.addUser(userPo);
            return ResponseServer.successWithoutData();
        }else {
            return ResponseServer.failedWithoutData();
        }
    }
}
