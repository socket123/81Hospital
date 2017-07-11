package com.chusu.platform.util.table;

import java.util.List;

import lombok.Data;

@Data
public class MyTable {
    //表名
    private String tableName;

    //注释
    private String comment;

    //字符设置
    private String collAtion;

    //创建时间
    private String createTime;

    //列数
    private String rows;

    //表引擎
    private String engine;

    //字段列表
    private List<Filed> fileds;

    @Data
    public class Filed {
        private String filed;

        private String type;

        private String isNull;

        private String isPrimaryKey;

        private String defaultValue;

        private String extra;

        private String comment;
    }
}
