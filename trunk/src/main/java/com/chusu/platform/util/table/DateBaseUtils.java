package com.chusu.platform.util.table;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DateBaseUtils {

    /**
     * 获得某个库中所有表名及注释
     * 
     * @param tableName
     * @return
     * @throws Exception
     */
    public List<MyTable> getTableAndColumns(String dbName) {
        List<MyTable> tableList = new ArrayList<MyTable>();
        String sql = "Select TABLE_NAME,ENGINE,TABLE_ROWS,CREATE_TIME,TABLE_COLLATION,TABLE_COMMENT "
                + "from INFORMATION_SCHEMA.TABLES Where table_schema = '" + dbName + "'";
        List<Map<String, Object>> list = DbUtil.getInstance().query(sql);

        //            while (rs.next()) {
        //                MyTable table = new MyTable();
        //                table.setCollAtion(rs.getString("TABLE_COLLATION"));
        //                table.setComment(rs.getString("TABLE_COMMENT"));
        //                table.setCreateTime(rs.getString("CREATE_TIME"));
        //                table.setEngine(rs.getString("ENGINE"));
        //                table.setRows(rs.getString("TABLE_ROWS"));
        //                table.setTableName(rs.getString("TABLE_NAME"));
        //
        //                table.setFileds(getMetaDataFromTable(table.getTableName()));
        //                tableList.add(table);
        //            }

        return tableList;
    }

    public static void main(String[] args) throws SQLException {
        String sql = "Select TABLE_NAME,ENGINE,TABLE_ROWS,CREATE_TIME,TABLE_COLLATION,TABLE_COMMENT "
                + "from INFORMATION_SCHEMA.TABLES Where table_schema = 'ADVANCED_COMPANY'";
        List<Map<String, Object>> list = DbUtil.getInstance().query(sql);
        System.out.println(list.toString());
    }

    private List<MyTable.Filed> getMetaDataFromTable(String tableName) throws SQLException {
        String sql = "show full columns from " + tableName.toUpperCase();
        List<Map<String, Object>> list = DbUtil.getInstance().query(sql);
        List<MyTable.Filed> list1 = new ArrayList<MyTable.Filed>();
        /* while (rs.next()) {
             MyTable.Filed filed = new MyTable().new Filed();
             filed.setFiled(rs.getString(1));
             filed.setType(rs.getString(2));
             filed.setIsNull(rs.getString(4));
             filed.setIsPrimaryKey(rs.getString(5));
             filed.setDefaultValue(rs.getString(6));
             filed.setExtra(rs.getString(7));
             filed.setComment(rs.getString(9));
             list1.add(filed);
         }*/
        return list1;
    }
}
