package com.fh.product.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fh.product.bean.type.TypePo;
import com.fh.product.mapper.TypeMapper;
import com.fh.product.service.TypeService;
import com.fh.shiro.bean.user.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeServiceImpl implements TypeService
{

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public IPage<TypePo> queryTypeList(PageBean pageBean) {
        Page<TypePo> poPage = new Page<>(pageBean.getPage(),pageBean.getLimit());
        return typeMapper.queryTypeList(poPage);
    }

    @Override
    public void addType(TypePo typePo) {
        if (typePo.getId() == null){
            typePo.setStatus(1);
            typeMapper.insert(typePo);
        }else {
            typeMapper.updateById(typePo);
        }
    }

    @Override
    public TypePo queryTypeById(Integer id) {
        return typeMapper.selectById(id);
    }

    @Override
    public void deleteById(Integer id) {
        TypePo typePo = typeMapper.selectById(id);
        typePo.setStatus(2);
        typeMapper.updateById(typePo);
    }

    @Override
    public void updateTypeStatus(Integer id) {
        TypePo typePo = typeMapper.selectById(id);
        if (typePo.getStatus() ==1){
            typePo.setStatus(2);
        }else {
            typePo.setStatus(1);
        }
        typeMapper.updateById(typePo);
    }
}
