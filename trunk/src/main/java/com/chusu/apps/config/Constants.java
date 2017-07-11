package com.chusu.apps.config;

/**
 * Created by Administrator on 2017/3/8.
 */
public interface Constants {

    public interface page {
        public static final Integer PAGE_NO = 0;

        public static final Integer PAGE_SIZE = 10;
    }

    public interface type {
        public static final Integer ORDER_TYPE_ONE = 1;//1:医院内部采购，2:医院外部采购

        public static final Integer ORDER_TYPE_TWO = 2;//1:医院内部采购，2:医院外部采购
        public static final Integer TYPE_STATID = 1;//id 种类 1

        public static final String ORDER_STATUS_ONE = "1";//状态1库存报警2正常

        public static final String ORDER_STATUS_TWO = "2";//1状态1库存报警2正常


        public static final String TYPE_STATUS_ONE = "1";//种类类别 1、消耗品 2、非消耗品

        public static final String TYPE_STATUS_TWO = "2";//种类类别 1、消耗品 2、非消耗品


        public static final String TYPE_STATUS_THREE = "3";// 采购计划


        public static final Boolean TYPE_BOOKEAN_TRUE = true;//是全选还是分页
        public static final Boolean TYPE_BOOKEAN_FALSE = false;//是全选还是分页

    }

    public interface key {
        public static final String PERSON_USER_SESSION_KEY = "personUser";

        public static final String PERSON_COOKIE_REMEMBERME_KEY = "person.cookie.rememberme";

        public static final String BACKEND_USER_SESSION_KEY = "backendUser";

        public static final String BACKEND_COOKIE_REMEMBERME_KEY = "backend.cookie.rememberme";
    }

    public interface sms {
        // 用户平台API账号(非登录账号,示例:N1234567)
        public static final String SMS_ACCOUNT = "N6664837";
        // 用户平台API密码(非登录密码)
        public static final String SMS_PSWD = "6ryS3cDq9M1d96";
        //短信验证码
        public static final String SMS_CODE = "smsCode";
    }
}
