package com.fh.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fh.product.bean.classify.ClassifyPo;
import com.fh.product.bean.classify.ClassifySearch;
import com.fh.product.mapper.ClassifyMapper;
import com.fh.product.service.ClassifyService;
import com.fh.shiro.bean.user.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassifyServiceImpl implements ClassifyService {

    @Autowired
    private ClassifyMapper classifyMapper;
    @Override
    public void addClassify(ClassifyPo classifyPo) {
        if (classifyPo.getId() == null){
            if (classifyPo.getPid() == 0){
                classifyPo.setLevel(0);
            }else {
                classifyPo.setLevel(1);
            }
            classifyMapper.insert(classifyPo);
        }else {
            classifyMapper.updateById(classifyPo);
        }
    }

    @Override
    public ClassifyPo queryClassifyById(Integer id) {
        return classifyMapper.selectById(id);
    }

    @Override
    public void deleteClassify(Integer id) {
        ClassifyPo classifyPo = new ClassifyPo();
        classifyPo.setId(id);
        classifyPo.setStatus(2);
        classifyMapper.updateById(classifyPo);
    }

    @Override
    public IPage<ClassifyPo> queryClassifyList(PageBean pageBean, ClassifySearch search) {
        Page<ClassifyPo> classifyPoPage = new Page<>(pageBean.getPage(),pageBean.getLimit());
        IPage<ClassifyPo> classifyPoIPage = classifyMapper.queryClassifyList(classifyPoPage,search);
        return classifyPoIPage;
    }

    @Override
    public List<ClassifyPo> queryLevelClassify() {
        return classifyMapper.selectList(new QueryWrapper<ClassifyPo>().eq("level",0));
    }

    @Override
    public void updateClasifyStatus(Integer id) {
        ClassifyPo classifyPo = classifyMapper.selectById(id);
        if (classifyPo.getStatus() == 1){
            classifyPo.setStatus(2);
        }else {
            classifyPo.setStatus(1);
        }
        classifyMapper.updateById(classifyPo);
    }

    @Override
    public void updateClassifyNavStatus(Integer id) {
        ClassifyPo classifyPo = classifyMapper.selectById(id);
        if (classifyPo.getNavStatus() == 1){
            classifyPo.setNavStatus(2);
        }else {
            classifyPo.setNavStatus(1);
        }
        classifyMapper.updateById(classifyPo);
    }

    @Override
    public List<ClassifyPo> queryClassifyOption() {
        return classifyMapper.selectList(null);
    }
}
