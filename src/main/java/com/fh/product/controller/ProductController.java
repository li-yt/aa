package com.fh.product.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fh.product.bean.product.ProductVo;
import com.fh.product.service.ProductService;
import com.fh.shiro.bean.user.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("queryProductList")
    public PageBean queryProductList(PageBean pageBean){
        IPage<ProductVo> productList = productService.queryProductList(pageBean);
        return new PageBean(productList.getRecords(),productList.getTotal());
    }

}
