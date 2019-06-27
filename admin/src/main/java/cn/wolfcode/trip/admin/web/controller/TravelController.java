package cn.wolfcode.trip.admin.web.controller;

import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.util.JsonResult;
import cn.wolfcode.trip.service.ITravelService;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther:S
 * @Date:19/6/27
 */
@Controller
@RequestMapping("/travel")
public class TravelController {

    @Autowired
    private ITravelService travelService;

    @RequestMapping("/audit_list")
    public String list(Model model, QueryObject obj){
        PageInfo pageInfo =travelService.query(obj);
        model.addAttribute(pageInfo);
        model.addAttribute("qo",obj);
        return "travel/audit";
    }


    @RequestMapping({"changeState"})
    @ResponseBody
    public JsonResult angeState(Travel travel) throws Exception {
        travelService.changeState(travel);
        return new JsonResult();
    }

    @RequestMapping({"getContentById"})
    @ResponseBody
    public Travel getContentById(Long id) throws Exception {
        return travelService.getTravelsContentById(id);
    }


}
