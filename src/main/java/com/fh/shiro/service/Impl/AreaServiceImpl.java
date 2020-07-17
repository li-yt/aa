package com.fh.shiro.service.Impl;

import com.fh.shiro.bean.area.Area;
import com.fh.shiro.mapper.AreaMapper;
import com.fh.shiro.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lenovo
 */
@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaMapper areaMapper;

    @Override
    public List<Area> queryProvince() {
        return areaMapper.queryProvince();
    }

    @Override
    public List<Area> queryArea(Area area) {
        return areaMapper.queryArea(area);
    }

    @Override
    public List<Area> queryCity(Area area) {
        return areaMapper.queryCity(area);
    }
}
