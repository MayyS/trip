package cn.wolfcode.trip.service.impl;

import cn.wolfcode.trip.base.domain.Region;
import cn.wolfcode.trip.base.mapper.RegionMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.service.IRegionService;
import com.alibaba.druid.support.spring.stat.annotation.Stat;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther:S
 * @Date:19/6/26
 */

@Service
public class RegionServiceImpl implements IRegionService {

    @Autowired
    private RegionMapper mapper;
    @Override
    public void saveOrUpdate(Region region) throws Exception {
        if(region.getId()!=null){
            mapper.updateByPrimaryKey(region);
        }else{
            mapper.insert(region);
        }
    }


    @Override
    public Region get(Long id) {
        Region region=mapper.selectByPrimaryKey(id);
        return region;
    }

    @Override
    public List<Region> listAll(Integer state) {
        List<Region>list=mapper.selectAll(state);
        return list;
    }

    @Override
    public List<Region> getRegionMapByParentId(Long parentId) {
        List<Region>regions=mapper.getRegionByParentId(parentId);
        return regions;
    }

}
