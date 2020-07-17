package com.fh.shiro.controller;

import com.fh.shiro.bean.right.RightPo;
import com.fh.shiro.service.RightService;
import com.fh.util.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Lenovo
 */
@RestController
@RequestMapping("right")
public class RightController {

    @Autowired
    private RightService rightService;


    @RequestMapping("queryRightTree")
    public List<Map<String,Object>> queryRightTree(Integer id){
        return rightService.queryRightTree(id);
    }

    @RequestMapping("queryRoleShowTree")
    public List<Map<String,Object>> queryRoleShowTree(Integer userId){
        return rightService.queryRoleShowTree(userId);
    }

    @RequestMapping("addRight")
    public ResponseServer addRight(RightPo rightPo){
        rightService.addRight(rightPo);
        return ResponseServer.successWithData(rightPo);
    }

    @RequestMapping("queryRightById")
    public ResponseServer queryRightById(Integer id){
        RightPo rightPo = rightService.queryRightById(id);
        return ResponseServer.successWithData(rightPo);
    }

    @RequestMapping("queryRightTreeByUserId")
    public ResponseServer queryRightTreeByUserId(Integer userId){
        List<Map<String,Object>> rightPo = rightService.queryRightTreeByUserId(userId);
        return ResponseServer.successWithData(rightPo);
    }

}
