package cn.wolfcode.trip.admin.web.controller;

import cn.wolfcode.trip.base.domain.Region;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.service.IRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther:S
 * @Date:19/6/20
 */

@Controller
@RequestMapping("/region")
public class RegionController {
    @Autowired
    private IRegionService service;

    @RequestMapping("/list")
    public String list(Model model,QueryObject obj){
       return "region/list";
    }

    @RequestMapping("/getRegionMapByParentId")
    @ResponseBody
    public List<Map<String,Object>> getRegionMapByParentId(Model model,Long parentId){
        List<Region>list=service.getRegionMapByParentId(parentId);
        List<Map<String,Object>>datas=new ArrayList<>();

        for(Region reg:list){
            Map<String,Object>map=new HashMap<>();
            map.put("text",reg.getName());
            map.put("id",reg.getId());
            map.put("lazyLoad",true);

            if(reg.getState()!=null&&reg.getState()==1){
                map.put("tags",new String[]{"推荐"});
            }
            datas.add(map);
        }
        return datas;
    }
    @RequestMapping("/getRegionByParentId")
    @ResponseBody
    public List<Region> getRegionByParentId(Model model,Long parentId){
        List<Region>list=service.getRegionMapByParentId(parentId);
        return list;
    }

    @PostMapping("/saveOrUpdate")
    public String saveOrUpdate(Region region){
        try {

            System.out.println(region.toString());

            service.saveOrUpdate(region);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/region/list.do";
    }

    @RequestMapping("/updateState")
    public String updateState(Region region ){
        System.out.println(region);
        try {
            service.saveOrUpdate(region);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/region/list.do";
    }

}
