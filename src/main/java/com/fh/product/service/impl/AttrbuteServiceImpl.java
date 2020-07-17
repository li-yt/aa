package com.fh.product.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fh.product.bean.attr.AttributePo;
import com.fh.product.bean.attr.AttributeSearch;
import com.fh.product.mapper.AttrbuteMapper;
import com.fh.product.service.AttributeService;
import com.fh.shiro.bean.user.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttrbuteServiceImpl implements AttributeService  {

    @Autowired
    private AttrbuteMapper attrbuteMapper;

    @Override
    public IPage<AttributePo> queryAttributeList(PageBean pageBean, AttributeSearch search) {
        Page<AttributePo> poPage = new Page<>(pageBean.getPage(),pageBean.getLimit());
        return attrbuteMapper.queryAttributeList(poPage,search);
    }

    @Override
    public void addAttribute(AttributePo attributePo) {
        if (attributePo.getId() == null){
            attrbuteMapper.insert(attributePo);
        }else {
            attrbuteMapper.updateById(attributePo);
        }
    }

    @Override
    public AttributePo queryAttributeById(Integer id) {
        return attrbuteMapper.selectById(id);
    }
}
