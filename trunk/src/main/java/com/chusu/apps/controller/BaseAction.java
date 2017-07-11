package com.chusu.apps.controller;

import java.beans.PropertyEditorSupport;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.chusu.platform.util.DateUtil;

/**
 * @project: oes
 * @description: TODO(the role of this class)
 * @author gujiannn@qq.com
 * @date 2015-10-25 22:23
 * @singature When it has is lost, brave to give up.
 */
@Controller
public abstract class BaseAction {

    protected Logger log = Logger.getLogger(this.getClass());

    protected HttpServletRequest request;

    protected HttpServletResponse response;

    protected HttpSession session;

    protected String[] codeArray = null;

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession();
        this.response.setHeader("Access-Control-Allow-Origin","*");
        this.response.setHeader("Access-Control-Allow-Headers","Access-Crntrol");
    }

    /**
     * 初始化数据绑定 1. 将所有传递进来的String进行HTML编码，防止XSS攻击 2. 将字段中Date类型转换为String类型
     */
    @InitBinder           
    protected void initBinder(WebDataBinder binder) {
        // String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
        binder.registerCustomEditor(String.class, new PropertyEditorSupport() {

            public void setAsText(String text) {
                setValue(text == null ? null : text.trim());
            }

            public String getAsText() {
                Object value = getValue();
                return value != null ? value.toString() : "";
            }
        });
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {

            public void setAsText(String text) {
                setValue(DateUtil.parseDate(text));
            }
        });
    } 
    

    @ModelAttribute("url")
    public String base(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":"
                + request.getServerPort() + request.getContextPath() + "/";
    }

    public static final String PRODUCES = "application/json;charset=utf-8";
}
