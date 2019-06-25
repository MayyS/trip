package cn.wolfcode.trip.app.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther:S
 * @Date:19/6/21
 */
public class CheckLoginInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        Object currentUser=request.getSession().getAttribute("USER_IN_SESSION");
        if(currentUser==null){
            response.sendRedirect("/login.html");
            return false;
        }

        return true;
    }
}
