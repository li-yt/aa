package com.fh.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fh.product.bean.attr.AttributePo;
import com.fh.product.bean.attr.AttributeSearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AttrbuteMapper extends BaseMapper<AttributePo> {
    IPage<AttributePo> queryAttributeList(Page<AttributePo> poPage,@Param("search") AttributeSearch search);
}
