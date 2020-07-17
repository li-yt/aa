package com.fh.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fh.product.bean.classify.ClassifyPo;
import com.fh.product.bean.classify.ClassifySearch;
import com.fh.shiro.bean.user.PageBean;

import java.util.List;

public interface ClassifyService {

    void addClassify(ClassifyPo classifyPo);

    ClassifyPo queryClassifyById(Integer id);

    void deleteClassify(Integer id);

    IPage<ClassifyPo> queryClassifyList(PageBean pageBean, ClassifySearch search);

    List<ClassifyPo> queryLevelClassify();

    void updateClasifyStatus(Integer id);

    void updateClassifyNavStatus(Integer id);
}
