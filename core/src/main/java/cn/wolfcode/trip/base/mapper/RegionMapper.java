package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.Region;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Region record);

    Region selectByPrimaryKey(Long id);

    List<Region> selectAll();

    int updateByPrimaryKey(Region record);

    List<Region>getRegionByParentId(@Param("parentId") Long parentId);
}