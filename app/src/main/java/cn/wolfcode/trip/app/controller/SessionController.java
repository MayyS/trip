package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.app.util.JsonResult;
import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @Auther:S
 * @Date:19/6/20
 */

@RestController
@RequestMapping("/sessions")
public class SessionController {

    @Autowired
    private IUserService userService;

    @PostMapping
    public JsonResult login(String email, String password, HttpSession session){
        JsonResult result=new JsonResult();
        try {
            User user=userService.login(email,password);
            result.setObj(user);
            session.setAttribute("USER_IN_SESSION",user);
        }catch (Exception e){
//            e.printStackTrace();
            result.setSuccess(false);
            result.setErrorMsg(e.getMessage());
        }finally {
            return result;
        }

    }

}
