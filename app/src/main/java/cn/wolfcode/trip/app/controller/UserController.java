package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.app.util.JsonResult;
import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther:S
 * @Date:19/6/20
 */

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping
    public JsonResult register(User user){
        JsonResult result=new JsonResult();

        try {
            userService.saveOrUpdate(user);
        } catch (Exception e) {
            e.printStackTrace();
            e.printStackTrace();
            result.setSuccess(false);
            result.setErrorMsg(e.getMessage());

        }
        return result;
    }

    @PutMapping("/{id}")
    public JsonResult update(User user){
        JsonResult result=new JsonResult();
        try {
//            System.out.println(user);
            userService.saveOrUpdate(user);
            User logU=userService.get(user.getId());
            result.setObj(logU);
            System.out.println(logU);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
