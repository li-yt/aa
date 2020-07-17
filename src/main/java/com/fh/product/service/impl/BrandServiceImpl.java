package com.fh.product.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fh.product.bean.brand.BrandPo;
import com.fh.product.mapper.BrandMapper;
import com.fh.product.service.BrandService;
import com.fh.shiro.bean.user.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public IPage<BrandPo> queryBrandList(PageBean pageBean) {
        Page<BrandPo> brandPoPage = new Page<>(pageBean.getPage(),pageBean.getLimit());
        return brandMapper.queryBrandList(brandPoPage);
    }

    @Override
    public void addBrand(BrandPo brandPo) {
        if (brandPo.getId() == null){
            brandPo.setProductCommentCount(0);
            brandPo.setProductCount(0);
            brandMapper.insert(brandPo);
        }else {
            brandMapper.updateById(brandPo);
        }
    }

    @Override
    public BrandPo queryBrandById(Integer id) {
        return brandMapper.selectById(id);
    }

    @Override
    public void deleteById(Integer id) {
        BrandPo brandPo = new BrandPo();
        brandPo.setId(id);
        brandPo.setStatus(2);
        brandMapper.updateById(brandPo);
    }

    @Override
    public void updateBrandStatus(Integer id) {
        BrandPo brandPo = brandMapper.selectById(id);
        if (brandPo.getStatus() == 1){
            brandPo.setStatus(2);
        }else {
            brandPo.setStatus(1);
        }
        brandMapper.updateById(brandPo);
    }

    @Override
    public void updateBrandFactoryStatus(Integer id) {
        BrandPo brandPo = brandMapper.selectById(id);
        if (brandPo.getFactoryStatus() == 1){
            brandPo.setFactoryStatus(2);
        }else {
            brandPo.setFactoryStatus(1);
        }
        brandMapper.updateById(brandPo);
    }

    @Override
    public List<BrandPo> queryBrandOption() {
        return brandMapper.selectList(null);
    }


}

