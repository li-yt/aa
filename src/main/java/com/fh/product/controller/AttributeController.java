package com.fh.product.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fh.product.bean.attr.AttributePo;
import com.fh.product.bean.attr.AttributeSearch;
import com.fh.product.service.AttributeService;
import com.fh.shiro.bean.user.PageBean;
import com.fh.util.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("attribute")
public class AttributeController {

    @Autowired
    private AttributeService attributeService;

    @RequestMapping("queryAttributeList")
    public PageBean queryAttributeList(PageBean pageBean, AttributeSearch search){
        IPage<AttributePo> attributePoIPage = attributeService.queryAttributeList(pageBean,search);
        return new PageBean(attributePoIPage.getRecords(),attributePoIPage.getTotal());
    }

    @RequestMapping("addAttribute")
    public ResponseServer addAttribute(AttributePo attributePo){
        attributeService.addAttribute(attributePo);
        return ResponseServer.successWithoutData();
    }
    //aa
    @RequestMapping("queryAttributeById")
    public AttributePo queryAttributeById(Integer id){
        return attributeService.queryAttributeById(id);
    }

}
