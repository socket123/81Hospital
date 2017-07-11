/**
 *
 */
package com.chusu.platform.interceptor;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.chusu.platform.util.GsonUtils;

/**
 * @author gujiannn@qq.com
 * @project: bjrcweb
 * @description: TODO(the role of this class)
 * @date 2015-12-06
 * @singature When it has is lost, brave to give up.
 */
public class UserInterceptor implements HandlerInterceptor {

    public final static String SEESION_USER = "user"; //用户信息

    public final static String COOKIE_KEY = "cookie";

    public final static String SEESION_USERID = "userId";

    public final static String SEESION_USERUNFUNCTIONS = "userUnFunctions";

    public final static String SEESION_USERFUNCTIONS = "userFunctions";

    public final static String SESSION_USERMENUS = "menus";

    public final static String SEESION_USERNAME = "username";

    public final static String SEESION_TEAMID = "teamId";

    public final static String SEESION_USERLEVEL = "level";

    public final static String SEESION_USERSTATUS = "userstatus";

    public final static String SEESION_USERPERMISSION = "userPermission";

    public final static String SEESION_COMPANYID = "companyId";

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
     */
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
            Exception arg3) throws Exception {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
     */
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
            ModelAndView arg3) throws Exception {
        // TODO Auto-generated method stub

    }

    private String[] loginMenuList = {
            "/getLoginerInfoSession", "/getUserMenus", "/sysUserAccount/modifyPassword"
    };

    private List<String> menuList = new ArrayList<String>();

    /**
     *
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
        boolean flag = false;

        String requestURI = request.getRequestURI();
        String referer = request.getHeader("referer");
        Integer userId = (Integer) request.getSession().getAttribute(SEESION_USERID); //获取session 中的用户
        if (userId != null) {
            /*
            if (Arrays.asList(loginMenuList).contains(requestURI)) {
                return true;
            }
            if (menuList.isEmpty()) {
                menuList.add("index.html");
                //根据用户查询出用户可访问的资源
                List<SysFunction> sysFunctions = (List<SysFunction>) request.getSession()
                        .getAttribute(SEESION_USERFUNCTIONS);
                if (null != sysFunctions && !sysFunctions.isEmpty()) {
                    for (SysFunction sysFunction : sysFunctions) {
                        if (!StringUtils.isEmpty(sysFunction.getFun_url())) {
                            menuList.add(sysFunction.getFun_url());
                        }
                    }
                }
                List<SysFunction> sysMenuList = (List<SysFunction>) request.getSession()
                        .getAttribute(SESSION_USERMENUS);
                if (null != sysMenuList && !sysMenuList.isEmpty()) {
                    for (SysFunction sysMenus : sysMenuList) {
                        if (!StringUtils.isEmpty(sysMenus.getFun_url())) {
                            menuList.add(sysMenus.getFun_url());
                        }
                    }
                }
            }

            if (!StringUtils.isEmpty(referer)) {
            	int endIndex = referer.length();
            	if(referer.indexOf("?") > -1){
            		endIndex = referer.indexOf("?");
            	}
                //拥有进入页面权限
                String ext = referer.substring(referer.lastIndexOf("/") + 1, endIndex);
                if (menuList.contains(ext)) {
                    flag = true;
                } 
            } 
            
            if (flag && menuList.contains(requestURI)) {
                //拥有操作权限
                flag = true;
            }
            */
            flag = true;
        }

        if (!flag) {
            String msg = "您没有" + referer + "或" + requestURI + "菜单的访问权限！";

            if (requestURI.indexOf("logout") > -1) {

            }

            PrintWriter writer = response.getWriter();
            writer.print(GsonUtils.toJson(msg));
            writer.flush();
            writer.close();
        }
        return flag;
    }
}
