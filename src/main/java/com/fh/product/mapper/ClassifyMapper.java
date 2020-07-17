package com.fh.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fh.product.bean.classify.ClassifyPo;
import com.fh.product.bean.classify.ClassifySearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ClassifyMapper extends BaseMapper<ClassifyPo> {
    IPage<ClassifyPo> queryClassifyList(Page<ClassifyPo> classifyPoPage,@Param("search") ClassifySearch search);
}
