package com.fh.shiro.service;

import com.fh.shiro.bean.area.Area;

import java.util.List;

/**
 * @author Lenovo
 */

public interface AreaService {
    List<Area> queryProvince();

    List<Area> queryArea(Area area);

    List<Area> queryCity(Area area);
}
