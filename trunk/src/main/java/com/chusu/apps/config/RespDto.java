package com.chusu.apps.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.google.gson.GsonBuilder;

/**
 * Created by magicjian on 15/7/22.
 */
public class RespDto {

    /*private static RespDto instance;

    private RespDto() {
    }

    public static  static RespDto getInstance() { //对获取实例的方法进行同步
        if (instance == null) {
            synchronized (RespDto.class) {
                if (instance == null)
                    instance = new RespDto();
            }
        }
        return instance;
    }*/

    /**
     * 成功，不需要返回任何消息
     * 
     * @return
     */
    public static String success() {
        return write(ErrorCode.SUCCESS[0], ErrorCode.SUCCESS[1], null, null);
    }

    /**
     * 未知错误，系统异常提示
     * 
     * @return
     */
    public static String error() {
        return write(ErrorCode.ERROR[0], ErrorCode.ERROR[1], null, null);
    }

    /**
     * 普通警告
     * 
     * @return
     */
    public static String warn(String msg) {
        return write(ErrorCode.WARN[0], "警告：" + msg, null, null);
    }

    /**
     * 其他提示消息
     * 
     * @return
     */
    public static String write(String code, String msg) {
        return write(code, msg, null, null);
    }

    /**
     * 成功，默认返回list
     * 
     * @return
     */
    public static String successList(List<?> list) {
        return write(ErrorCode.SUCCESS[0], ErrorCode.SUCCESS[1], null, list);
    }

    public static String successPage(PageInfo<?> page) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", ErrorCode.SUCCESS[0]);
        map.put("msg", ErrorCode.SUCCESS[1]);
        map.put("total", page.getTotal());
        map.put("rows", page.getList());
        GsonBuilder gson = new GsonBuilder().serializeNulls();
        return gson.create().toJson(map);
    }

    /**
     * 成功，默认返回object
     * 
     * @return
     */
    public static String success(Object obj) {
        return write(ErrorCode.SUCCESS[0], ErrorCode.SUCCESS[1], obj);
    }

    /**
     * 成功，默认返回object
     * 
     * @return
     */
	public static String write(String[] obj) {
        return write(obj[0], obj[1]);
    }

    /**
     * 带消息提示的对象
     * 
     * @param code
     * @param msg
     * @param object
     * @return
     */
    public static String write(String code, String msg, Object object) {
        return write(code, msg, object, null);
    }

    /**
     * 带消息提示的list
     * 
     * @param code
     * @param msg
     * @param object
     * @return
     */
    public static String write(String code, String msg, List<?> list) {
        return write(code, msg, null, list);
    }

    /**
     * 带消息提示的object,list
     * 
     * @param code
     * @param msg
     * @param object
     * @return
     */
    public static String write(String code, String msg, Object object, List<?> list) {
        GsonBuilder gson = new GsonBuilder().serializeNulls();
        return gson.create().toJson(setMap(code, msg, object, list));
    }

    private static Map<String, Object> setMap(String code, String msg, Object object, List<?> list) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", code);
        map.put("msg", msg);
        map.put("daoResult", object);
        map.put("rows", list);
        return map;
    }

}
