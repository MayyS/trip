package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.service.ITravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther:S
 * @Date:19/6/26
 */
@Controller
public class TravelController {

    @Autowired
    private ITravelService travelService;


    @RequestMapping("/travels/{id}")
    @ResponseBody
    public Travel getTravelContentById(@PathVariable("id") Long id){
        Travel travel=travelService.getTravelsContentById(id);
        System.out.println(travel);
        return travel;
    }

}
