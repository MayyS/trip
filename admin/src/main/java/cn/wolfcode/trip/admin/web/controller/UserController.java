package cn.wolfcode.trip.admin.web.controller;

import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.service.IUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther:S
 * @Date:19/6/20
 */

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService service;

    @RequestMapping("/list")
    public String list(Model model, QueryObject obj){
       PageInfo pageInfo=service.query(obj);
       model.addAttribute(pageInfo);
       model.addAttribute("qo",obj);
       return "user/list";
    }
}
