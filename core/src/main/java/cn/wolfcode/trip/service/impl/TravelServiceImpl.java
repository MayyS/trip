package cn.wolfcode.trip.service.impl;

import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.domain.TravelContent;
import cn.wolfcode.trip.base.mapper.TravelContentMapper;
import cn.wolfcode.trip.base.mapper.TravelMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.service.ITravelService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Auther:S
 * @Date:19/6/26
 */
@Service
public class TravelServiceImpl implements ITravelService {

    @Autowired
    private TravelMapper mapper;

    @Autowired
    private TravelContentMapper contentMapper;

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

    @Override
    public void updateShowNum(Long travelId) {
        mapper.updateShowNum(travelId);
    }

    @Override
    public void saveOrUpdate(Travel travel, String content) {
        Date date = new Date();
        travel.setLastUpdateTime(date);
        TravelContent travelContent = new TravelContent();
        travelContent.setContent(content);
        if (travel.getId() != null) {
            mapper.updateByPrimaryKey(travel);
            travelContent.setId(travel.getId());
            contentMapper.updateByPrimaryKey(travelContent);
        } else {
            travel.setCreateTime(date);
            mapper.insert(travel);
            travelContent.setId(travel.getId());
            this.contentMapper.insert(travelContent);
        }
    }

    @Override
    public void changeState(Travel travel) {
        if (travel.getState() == 2) {
            travel.setReleaseTime(new Date());
        }
        mapper.changeState(travel);
    }
}
