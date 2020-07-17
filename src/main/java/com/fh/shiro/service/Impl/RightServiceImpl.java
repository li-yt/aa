package com.fh.shiro.service.Impl;

import com.fh.shiro.bean.right.RightPo;
import com.fh.shiro.mapper.RightMapper;
import com.fh.shiro.service.RightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lenovo
 */
@Service
public class RightServiceImpl implements RightService {

    @Autowired
    private RightMapper rightMapper;

    @Override
    public List<Map<String, Object>> queryRightTree(Integer id) {
        List<Map<String, Object>> list = rightMapper.queryRightTree(id);
        return list;
    }

    @Override
    public List<Map<String, Object>> queryRoleShowTree(Integer userId) {
        return rightMapper.queryRoleShowTree(userId);
    }

    @Override
    public void addRight(RightPo rightPo) {
        if (rightPo.getId()!= null ){
            rightMapper.updateById(rightPo);
        }else {
            rightMapper.insert(rightPo);
        }
    }

    @Override
    public RightPo queryRightById(Integer id) {
        return rightMapper.selectById(id);
    }

    @Override
    public List<Map<String,Object>> queryRightTreeByUserId(Integer userId) {
        List<RightPo> rightPoList = rightMapper.queryRightTreeByUserId(userId);
        return getTree(rightPoList,1);
    }

    public List<Map<String,Object>> getTree(List<RightPo> rightPoList,Integer pid){
        List<Map<String,Object>> list = new ArrayList<>();
        rightPoList.forEach(rightPo -> {
            Map<String,Object> map =null;
            if (pid.equals(rightPo.getPid())){
            map = new HashMap<>();
            map.put("id",rightPo.getId());
            map.put("name",rightPo.getRightName());
            map.put("pid",rightPo.getPid());
            map.put("pathUrl",rightPo.getPathUrl());
            map.put("children",getTree(rightPoList,rightPo.getId()));
            }
            if (map != null){
                list.add(map);
            }
        });
        return list;
    }
}
