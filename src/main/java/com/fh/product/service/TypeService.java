package com.fh.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fh.product.bean.type.TypePo;
import com.fh.shiro.bean.user.PageBean;

public interface TypeService {
    IPage<TypePo> queryTypeList(PageBean pageBean);

    void addType(TypePo typePo);



    TypePo queryTypeById(Integer id);

    void deleteById(Integer id);

    void updateTypeStatus(Integer id);
}
