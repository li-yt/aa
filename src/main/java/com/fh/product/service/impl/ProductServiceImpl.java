package com.fh.product.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fh.product.bean.product.ProductVo;
import com.fh.product.mapper.ProductMapper;
import com.fh.product.service.ProductService;
import com.fh.shiro.bean.user.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public IPage<ProductVo> queryProductList(PageBean pageBean) {
        Page<ProductVo> productVoPage = new Page<>(pageBean.getPage(),pageBean.getLimit());
        IPage<ProductVo> productVoIPage = productMapper.queryProductList(productVoPage);
        return productVoIPage;
    }
}
