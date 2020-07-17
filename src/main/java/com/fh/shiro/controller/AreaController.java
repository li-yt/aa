package com.fh.shiro.controller;

import com.fh.shiro.bean.area.Area;
import com.fh.shiro.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Lenovo
 */
@Controller
@RequestMapping("area")
public class AreaController {

    @Autowired
    private AreaService areaService;
    /**查询三级联动的省*/
    @RequestMapping("queryProvince")
    @ResponseBody
    public List<Area> queryProvince(){
        return areaService.queryProvince();
    }
    /**查询三级联动的市*/
    @RequestMapping("queryArea")
    @ResponseBody
    public List<Area> queryArea(Area area){
        return areaService.queryArea(area);
    }
    /**查询三级联动的县/区*/
    @RequestMapping("queryCity")
    @ResponseBody
    public List<Area> queryCity(Area area){
        return areaService.queryCity(area);
    }

}
