package com.fh.shiro.controller;

import com.fh.shiro.bean.dept.DeptPo;
import com.fh.shiro.service.DeptService;
import com.fh.util.ResponseServer;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author Lenovo
 */
@Controller
@RequestMapping("dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**查询ztree树的方法*/
    @RequestMapping("queryDeptTree")
    @ResponseBody
    public List<Map<String,Object>> queryDeptTree(){
        return deptService.queryDeptTree();
    }
    /**新增或修改*/
    @RequestMapping("addDept")
    @ResponseBody
    @RequiresPermissions("")
    public ResponseServer addDept(DeptPo deptPo){
        deptService.addDept(deptPo);
        return ResponseServer.successWithData(deptPo);
    }
    /**根据id查询*/
    @RequestMapping("queryDeptById")
    @ResponseBody
    public ResponseServer queryDeptById(Integer id){
        DeptPo deptPo = deptService.queryDeptById(id);
        return ResponseServer.successWithData(deptPo);
    }

    /**根据id删除*/
    @RequestMapping("deleteDeptById")
    @ResponseBody
    @RequiresPermissions("admin:dept:delete")
    public ResponseServer deleteDeptById(Integer id){
        deptService.updateStatusById(id);
        return ResponseServer.successWithoutData();
    }
}
