package com.fh.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fh.product.bean.attr.AttributePo;
import com.fh.product.bean.attr.AttributeSearch;
import com.fh.shiro.bean.user.PageBean;

public interface AttributeService {
    IPage<AttributePo> queryAttributeList(PageBean pageBean, AttributeSearch search);

    void addAttribute(AttributePo attributePo);

    AttributePo queryAttributeById(Integer id);
}
