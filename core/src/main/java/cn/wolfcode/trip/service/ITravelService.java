package cn.wolfcode.trip.service;

import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

/**
 * @Auther:S
 * @Date:19/6/26
 */
public interface ITravelService {

    void saveOrUpdate(Travel travel);

    Travel get(Long id);

    PageInfo query(QueryObject obj);

    Travel getTravelsContentById(Long id);

}
