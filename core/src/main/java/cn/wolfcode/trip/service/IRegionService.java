package cn.wolfcode.trip.service;

import cn.wolfcode.trip.base.domain.Region;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Auther:S
 * @Date:19/6/20
 */
public interface IRegionService {

    void saveOrUpdate(Region region)throws Exception;
    Region get(Long id);
    List<Region>listAll();

    List<Region> getRegionMapByParentId(Long parentId);
}
