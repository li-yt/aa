package com.fh.product.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fh.product.bean.classify.ClassifyPo;
import com.fh.product.bean.classify.ClassifySearch;
import com.fh.product.service.ClassifyService;
import com.fh.shiro.bean.user.PageBean;
import com.fh.util.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("classify")
public class ClassifyController {

    @Autowired
    private ClassifyService classifyService;


    @RequestMapping("queryClassifyList")
    public PageBean queryBrandList(PageBean pageBean, ClassifySearch search){
        IPage<ClassifyPo> productVoIPage = classifyService.queryClassifyList(pageBean,search);
        return new PageBean(productVoIPage.getRecords(),productVoIPage.getTotal());
    }
    @RequestMapping("addClassify")
    public ResponseServer addClassify(ClassifyPo classifyPo){
        classifyService.addClassify(classifyPo);
        return  ResponseServer.successWithData(classifyPo);
    }

    @RequestMapping("queryClassifyById")
    public ResponseServer queryClassifyById(Integer id){
       ClassifyPo classifyPo =  classifyService.queryClassifyById(id);
       return ResponseServer.successWithData(classifyPo);
    }

    @RequestMapping("deleteClassify")
    public ResponseServer deleteClassify(Integer id){
        classifyService.deleteClassify(id);
        return ResponseServer.successWithoutData();
    }

    @RequestMapping("queryLevelClassify")
    public List<ClassifyPo> queryLevelClassify(){
        return classifyService.queryLevelClassify();
    }

    @RequestMapping("updateClasifyStatus")
    public void updateClasifyStatus(Integer id){
        classifyService.updateClasifyStatus(id);
    }

    @RequestMapping("updateClassifyNavStatus")
    private void updateClassifyNavStatus(Integer id){
        classifyService.updateClassifyNavStatus(id);
    }

    @RequestMapping("queryClassifyOption")
    public List<ClassifyPo> queryClassifyOption(){
        return classifyService.queryClassifyOption();
    }

}
