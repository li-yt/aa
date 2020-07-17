package com.fh.shiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.shiro.bean.right.RightPo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Lenovo
 */
@Repository
@Mapper
public interface RightMapper extends BaseMapper<RightPo> {
    List<Map<String, Object>> queryRightTree(Integer id);

    List<Map<String, Object>> queryRoleShowTree(Integer userId);

    List<RightPo> queryRightListByUserId(Integer id);

    List<RightPo> queryRightTreeByUserId(Integer userId);
}
