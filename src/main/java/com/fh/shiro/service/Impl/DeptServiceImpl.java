package com.fh.shiro.service.Impl;

import com.fh.shiro.bean.dept.DeptPo;
import com.fh.shiro.mapper.DeptMapper;
import com.fh.shiro.service.DeptService;
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
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    /**数据库的查询方法*/
    @Override
    public List<Map<String, Object>> queryDeptTree() {
        List<DeptPo> deptList = deptMapper.queryDeptTree();
        return getTree(deptList,0);
    }
    /**新增或修改*/
    @Override
    public void addDept(DeptPo deptPo) {
        deptPo.setStatus(1);
        if (deptPo.getId() != null){
            deptMapper.updateById(deptPo);
        }else {
            deptMapper.insert(deptPo);
        }
    }
    /**根据id查询*/
    @Override
    public DeptPo queryDeptById(Integer id) {
        return deptMapper.selectById(id);
    }

    @Override
    public void updateStatusById(Integer id) {
        DeptPo deptPo = deptMapper.selectById(id);
        List<DeptPo> list = deptMapper.queryDeptByPid(deptPo);
        for (DeptPo po : list) {
            po.setStatus(2);
            deptMapper.updateById(po);
        }
        deptPo.setStatus(2);
        deptMapper.updateById(deptPo);
    }

    /**递归的方法 */
    public List<Map<String, Object>> getTree(List<DeptPo> deptList,Integer pid){
        List<Map<String, Object>> list = new ArrayList<>();
        deptList.forEach(dept -> {
            Map<String, Object> map = null;
            if (dept.getPid().equals(pid)){
                map = new HashMap<>();
                map.put("id",dept.getId());
                map.put("name",dept.getDeptName());
                map.put("pid",dept.getPid());
                if (pid == 0){
                    map.put("iconClose","/static/orgimg/3.png");
                    map.put("iconOpen","/static/orgimg/2.png");
                }else {
                    map.put("icon","/static/orgimg/4.png");
                }
                map.put("children",getTree(deptList,dept.getId()));
            }
            if (map != null){
                list.add(map);
            }
        });
        return list;
    }
}
