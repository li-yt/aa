package com.fh.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fh.product.bean.brand.BrandPo;
import com.fh.shiro.bean.user.PageBean;

import java.util.List;

public interface BrandService  {
    IPage<BrandPo> queryBrandList(PageBean pageBean);

    void addBrand(BrandPo brandPo);

    BrandPo queryBrandById(Integer id);

    void deleteById(Integer id);

    void updateBrandStatus(Integer id);

    void updateBrandFactoryStatus(Integer id);

    List<BrandPo> queryBrandOption();
}
