package com.fh.shiro.mapper;

import com.fh.shiro.bean.area.Area;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AreaMapper {


    List<Area> queryProvince();

    List<Area> queryArea(Area area);

    List<Area> queryCity(Area area);
}
