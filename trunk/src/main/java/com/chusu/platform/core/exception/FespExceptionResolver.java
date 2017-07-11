package com.chusu.platform.core.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class FespExceptionResolver implements HandlerExceptionResolver {

    private static Logger logger = Logger.getLogger(FespExceptionResolver.class);

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception ex) {
        logger.error("FespException: ", ex);
        ModelAndView mv = new ModelAndView();
        response.setStatus(HttpStatus.OK.value()); //设置状态码  
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); //设置ContentType  
        response.setCharacterEncoding("UTF-8"); //避免乱码  
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        String message = ex.getMessage();
        if (message == null) {
            message = "系统错误";
        }
        //         if(ex instanceof FespBusinessException){
        // 			logService.addExceptionLog("SystemException", message, null, ex);
        // 		}else if(ex instanceof FespException){
        // 			logService.addExceptionLog("SystemException", message, null, ex);
        // 		}else {
        ex.printStackTrace();
        // 			logService.addExceptionLog("SystemException", message, null, ex);
        // 		}
        try {
            response.getWriter().write("{\"code\":500,\"msg\":\"" + message + "\"}");
        } catch (IOException e) {
            logger.error("与客户端通讯异常:" + e.getMessage(), e);
        }

        logger.debug("异常:" + ex.getMessage(), ex);
        return mv;
    }

}
