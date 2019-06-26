package cn.wolfcode.trip.app.filter;

import cn.wolfcode.trip.app.util.UploadUtil;
import org.apache.commons.io.FileUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @Auther:S
 * @Date:19/6/25
 */
public class ImageFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        String reqUrl=request.getRequestURI();
        String resourcePath= UploadUtil.commonPath+reqUrl;
        File image=new File(resourcePath);
        if(image.exists()){
            servletResponse.getOutputStream().write(FileUtils.readFileToByteArray(image));
        }else{
            System.out.println("image:"+resourcePath+"not found");
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
