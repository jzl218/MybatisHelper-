package com.lianlianpay.ibatis.helper.utils;

import com.lianlianpay.ibatis.helper.core.domain.DbPrimaryKeyData;

import java.sql.Connection;
import java.util.List;

/**
 * Created by chenrs on 2017/11/16.
 */
public class DbUtils {

    //其他数据库不需要这个方法 oracle和db2需要
    public static String getSchema(Connection conn) throws Exception {
        String schema;
        schema = conn.getMetaData().getUserName();
        if ((schema == null) || (schema.length() == 0)) {
            throw new Exception("ORACLE数据库模式不允许为空");
        }
        return schema.toUpperCase().toString();

    }


    public static String formateJaveColName(String colName) {
        String[] colNames = colName.trim().toLowerCase().split("_");
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < colNames.length; i++) {
            if (i == 0) {
                stringBuilder.append(colNames[i]);
            } else {
                stringBuilder.append(toUpperFristChar(colNames[i]));
            }
        }

        return stringBuilder.toString();

    }

    public static String toUpperFristChar(String string) {
        return string.substring(0,1).toUpperCase().concat(string.substring(1));
    }

    public static String toLowFristChar(String string) {
        return string.substring(0,1).toLowerCase().concat(string.substring(1));
    }

    public static String methodName(List<String> colNames) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < colNames.size(); i++) {
            sb.append(toUpperFristChar(formateJaveColName(colNames.get(i))));
            if (colNames.size() <= 2 && i != colNames.size() - 1) {
                sb.append("And");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        String l = "12345";
        System.out.println(l.substring(0,l.length()-1).concat("0"));
    }
}
