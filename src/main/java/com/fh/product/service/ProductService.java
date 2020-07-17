package com.fh.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fh.product.bean.product.ProductVo;
import com.fh.shiro.bean.user.PageBean;

public interface ProductService {
    IPage<ProductVo> queryProductList(PageBean pageBean);
}
