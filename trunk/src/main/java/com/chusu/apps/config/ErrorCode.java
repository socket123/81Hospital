package com.chusu.apps.config;

public interface ErrorCode {

    /**
     * 成功
     */
    public static final String[] SUCCESS = {
            "200", "success"
    };

    /**
     * 未知错误
     */
    public static final String[] ERROR = {
            "500", "error"
    };

    /**
     * 普通警告
     */
    public static final String[] WARN = {
            "300", "warning"
    };

    /**
     * 非法的请求参数
     */
    public static final String[] REQ_ERR = {
            "1000", "非法的请求参数"
    };

    /**
     * 用户或授权服务器拒绝授予数据访问权限
     */
    public static final String[] ACCESS_ERR = {
            "1001", "用户或授权服务器拒绝授予数据访问权限"
    };

    /**
     * 提供的更新令牌已过期
     */
    public static final String[] TOKEN_ERR = {
            "1002", "提供的更新令牌已过期"
    };

    /**
     * 数据库操作出错，请重试
     */
    public static final String[] DB_ERR = {
            "1003", "数据库操作出错，请重试"
    };

    /**
     * 访问的应用不存在
     */
    public static final String[] APP_ERR = {
            "1004", "访问的应用不存在"
    };

    /**
     * 不合法的文件类型
     */
    public static final String[] FILE_TYPE_ERR = {
            "2001", "不合法的文件类型"
    };

    /**
     * 不合法的文件大小
     */
    public static final String[] FILE_SIZE_ERR = {
            "2002", "不合法的文件大小"
    };

}
