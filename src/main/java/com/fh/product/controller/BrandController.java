package com.fh.product.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fh.product.bean.brand.BrandPo;
import com.fh.product.service.BrandService;
import com.fh.shiro.bean.user.PageBean;
import com.fh.util.AliyunOSSUtil;
import com.fh.util.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @RequestMapping("queryBrandList")
    public PageBean queryBrandList(PageBean pageBean){
        IPage<BrandPo> productVoIPage = brandService.queryBrandList(pageBean);
        return new PageBean(productVoIPage.getRecords(),productVoIPage.getTotal());
    }
    /**上传图片*/
    @RequestMapping("upLoad")
    public Map<String,Object> upLoad(@RequestParam("file") MultipartFile file, HttpServletResponse response) throws IOException {
        String filename = file.getOriginalFilename();
        String images = AliyunOSSUtil.uploadFile(file.getInputStream(), filename, "images");
        Map<String,Object> map = new HashMap<>();
        map.put("code","0");
        map.put("img",images);
        return map;
    }

    @RequestMapping("addBrand")
    public ResponseServer addBrand(BrandPo brandPo){
        brandService.addBrand(brandPo);
        return ResponseServer.successWithoutData();
    }

    @RequestMapping("queryBrandById")
    public BrandPo queryBrandById(Integer id){
        return brandService.queryBrandById(id);
    }

    @RequestMapping("deleteById")
    public ResponseServer deleteById(Integer id){
        brandService.deleteById(id);
        return ResponseServer.successWithoutData();
    }

    @RequestMapping("updateBrandStatus")
    public void  updateBrandStatus(Integer id){
        brandService.updateBrandStatus(id);
    }

    @RequestMapping("updateBrandFactoryStatus")
    public  void updateBrandFactoryStatus(Integer id){
        brandService.updateBrandFactoryStatus(id);
    }

    @RequestMapping("queryBrandOption")
    public List<BrandPo> queryBrandOption(){
        return brandService.queryBrandOption();
    }
}
