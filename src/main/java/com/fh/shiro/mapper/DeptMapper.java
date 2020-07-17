package com.fh.shiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.shiro.bean.dept.DeptPo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lenovo
 */
@Repository
@Mapper
public interface DeptMapper extends BaseMapper<DeptPo> {

    List<DeptPo> queryDeptTree();

    List<DeptPo> queryDeptByPid(DeptPo deptPo);
}
