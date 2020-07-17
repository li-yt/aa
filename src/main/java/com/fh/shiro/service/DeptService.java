package com.fh.shiro.service;

import com.fh.shiro.bean.dept.DeptPo;

import java.util.List;
import java.util.Map;

/**
 * @author Lenovo
 */
public interface DeptService {
    List<Map<String, Object>> queryDeptTree();

    void addDept(DeptPo deptPo);


    DeptPo queryDeptById(Integer id);

    void updateStatusById(Integer id);
}
