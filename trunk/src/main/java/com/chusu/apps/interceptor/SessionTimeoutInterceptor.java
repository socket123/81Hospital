package com.chusu.apps.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.chusu.apps.config.Constants;
import com.chusu.apps.model.sys.SysAdmin;

public class SessionTimeoutInterceptor implements HandlerInterceptor {

    private Logger logger = Logger.getLogger(SessionTimeoutInterceptor.class);

    @Override
    public void afterCompletion(HttpServletRequest arg0,
            HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {

    }

    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler, ModelAndView arg3) throws Exception {
        preHandle(request, response, handler);
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        System.out.println("LastAccessedTime:" + session.getLastAccessedTime());
        System.out.println("getCreationTime:" + session.getCreationTime());
        System.out.println("getMaxInactiveInterval:" + session.getMaxInactiveInterval());
        SysAdmin admin = (SysAdmin) session.getAttribute(Constants.key.BACKEND_USER_SESSION_KEY);
        if (admin == null) {
            String jsCode = "<script type='text/javascript'>" +
                    "var p=window;while(p!=p.parent){p=p.parent; } p.location.href='" +
                    request.getContextPath() +
                    "/back/login'</script>";
            PrintWriter writer = response.getWriter();
            writer.print(jsCode);
            writer.flush();
            writer.close();
            logger.log(Level.INFO, "user not login");
            return false;
        } else {
            logger.log(Level.INFO, "用户session还存在");
        }
        return true;

    }

}
