package com.fh.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fh.product.bean.product.ProductPo;
import com.fh.product.bean.product.ProductVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ProductMapper extends BaseMapper<ProductPo> {
    IPage<ProductVo> queryProductList(Page<ProductVo> productVoPage);
}
