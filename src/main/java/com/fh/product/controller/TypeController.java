package com.fh.product.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fh.product.bean.type.TypePo;
import com.fh.product.service.TypeService;
import com.fh.shiro.bean.user.PageBean;
import com.fh.util.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @RequestMapping("queryTypeList")
    public PageBean queryTypeList(PageBean pageBean){
        IPage<TypePo> typePoIPage = typeService.queryTypeList(pageBean);
        return new PageBean(typePoIPage.getRecords(),typePoIPage.getTotal());
    }


    @RequestMapping("addType")
    public ResponseServer addType(TypePo typePo){
        typeService.addType(typePo);
        return ResponseServer.successWithoutData();
    }

    @RequestMapping("queryTypeById")
    public TypePo queryTypeById(Integer id){
        return typeService.queryTypeById(id);
    }

    @RequestMapping("deleteById")
    public ResponseServer deleteById(Integer id){
        typeService.deleteById(id);
        return ResponseServer.successWithoutData();
    }

    @RequestMapping("updateTypeStatus")
    public void updateTypeStatus(Integer id){
        typeService.updateTypeStatus(id);
    }

}
