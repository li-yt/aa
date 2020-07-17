package com.fh.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("productMain")
public class ProcductMain {

    @RequestMapping("toClassify")
    public String toClassify(){
        return "product/classify";
    }

    @RequestMapping("toBrand")
    public String toBrand(){
        return "product/brand";
    }

    @RequestMapping("toType")
    public String toType(){
        return "product/type";
    }

    @RequestMapping("toAttribute")
    public String toAttribute(){
        return "product/attribute";
    }

    @RequestMapping("toProduct")
    public String toProduct(){
        return "product/product";
    }

}
