package com.luospace.blog.interceptor;

import com.luospace.blog.common.Constants;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception{
        String requestServletPath = request.getServletPath();
        if( requestServletPath.startsWith("/admin") && null == request.getSession().getAttribute(Constants.USER_SESSION_KEY)){
            response.sendRedirect(request.getContextPath() + "/admin/login");
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
