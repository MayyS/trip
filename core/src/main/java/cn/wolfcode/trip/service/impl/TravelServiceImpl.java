package cn.wolfcode.trip.service.impl;

import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.mapper.TravelMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.service.ITravelService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther:S
 * @Date:19/6/26
 */
@Service
public class TravelServiceImpl implements ITravelService {

    @Autowired
    private TravelMapper mapper;

    @Override
    public void saveOrUpdate(Travel travel) {
        if(travel.getId()!=null){
            mapper.updateByPrimaryKey(travel);
        }else{
           int res=mapper.insert(travel);
           if(res==0){
               throw new RuntimeException("save fail");
           }
        }
    }

    @Override
    public Travel get(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo query(QueryObject obj) {
        PageHelper.startPage(obj.getCurrentPage(),obj.getPageSize());
        List<Travel> travelList=mapper.selectForList(obj);
        return new PageInfo(travelList);
    }

    @Override
    public Travel getTravelsContentById(Long id) {
        Travel travel=mapper.getContentById(id);
        return travel;
    }
}
