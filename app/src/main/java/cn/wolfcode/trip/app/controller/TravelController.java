package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.app.util.JsonResult;
import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.service.ITravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Auther:S
 * @Date:19/6/26
 */
@Controller
@RequestMapping("/travels")
public class TravelController {

    @Autowired
    private ITravelService travelService;



    @GetMapping("/{id}")
    @ResponseBody
    public Travel getTravelContentById(@PathVariable("id") Long id){
        Travel travel=travelService.getTravelsContentById(id);
        System.out.println(travel);
        return travel;
    }

    @ResponseBody
    @PostMapping
    public JsonResult save(Travel travel){

        Object currentUser=getSession().getAttribute("USER_IN_SESSION");
        //设置作者
        travel.setAuthor((User) currentUser);
        //travelService.saveOrUpdate(travel);
        return new JsonResult();
    }

    private static HttpSession getSession(){
        HttpSession session=null;
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        session=attrs.getRequest().getSession();
        return session;
    }


    @PatchMapping
    public JsonResult update(Travel travel, @RequestParam String content) {
        JsonResult jsonResult = new JsonResult();

        try {
            travelService.saveOrUpdate(travel, content);
//            jsonResult.setData(travel.getId());
        } catch (Exception var5) {
            var5.printStackTrace();
//            jsonResult.mark("更新失败！");
        }

        return jsonResult;
    }


    /*
    @PutMapping("/{travelId}")
    public JsonResult updateShowNum(Long travelId){
        travelService.updateShowNum(travelId);
        return new JsonResult();
    }*/

}
