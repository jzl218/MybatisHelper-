package com.lianlianpay.ibatis.helper.core.column;


import com.lianlianpay.ibatis.helper.core.domain.DbData;
import com.lianlianpay.ibatis.helper.utils.DbUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.*;

/**
 * <p>Description: 获取数据库基本信息的工具类</p>
 *
 * @author qxl
 * @date 2016年7月22日 下午1:00:34
 */
public class ColumnQueryServiceImpl implements ColumnQueryService {

    /**
     * 表信息缓存
     */
    public static Map<String, List<DbData>> tableColumnCache = new HashMap<String, List<DbData>>();

    @Override
    public List<DbData> getTableInfo(Connection conn, String table) {

        //从缓存中取
        if (tableColumnCache.containsKey(table)) {
            return tableColumnCache.get(table);
        }

        //数据库中查询
        return getDbColumnData(conn, table);
    }

    private List<DbData> getDbColumnData(Connection conn, String table) {
        List result = new ArrayList();

        DatabaseMetaData dbmd = null;

        try {
            dbmd = conn.getMetaData();
            ResultSet resultSet = dbmd.getTables(null, "%", table, new String[]{"TABLE"});

            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");
                System.out.println(tableName);

                if (tableName.equalsIgnoreCase(table)) {
                    ResultSet rs = conn.getMetaData().getColumns(null, DbUtils.getSchema(conn), tableName, "%");

                    while (rs.next()) {
                        DbData dbData = new DbData();
                        String colName = rs.getString("COLUMN_NAME");
                        dbData.setColName(colName);
                        String remarks = rs.getString("REMARKS");
                        if (remarks == null || remarks.equals("")) {
                            remarks = colName;
                        }
                        dbData.setRemarks(remarks);
                        String dbType = rs.getString("TYPE_NAME");
                        dbData.setDbType(dbType.replace("2", "")
                                .replace("DATETIME", "DATE")
                              ); //VARCHAR2 ==> VARCHAR

                        if ("TEXT".equalsIgnoreCase(dbData.getDbType())) {
                            dbData.setDbType("LONGVARCHAR");
                        }
                        if ("INT".equalsIgnoreCase(dbData.getDbType())) {
                            dbData.setDbType("INTEGER");
                        }
                        dbData.setType(changeDbType(dbType));
                        result.add(dbData);
                    }

                }
                return result;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    private String changeDbType(String dbType) {
        dbType = dbType.toUpperCase();
        switch (dbType) {
            case "VARCHAR":
            case "VARCHAR2":
            case "CHAR":
                return "String";
            case "NUMBER":
                return "BigDecimal";
            case "DECIMAL":
            case "BIGINT":
                return "Long";
            case "INT":
            case "SMALLINT":
            case "INTEGER":
                return "Integer";
            case "DATETIME":
            case "TIMESTAMP":
            case "DATE":
                return "Date";
            default:
                return "String";
        }
    }

    //获取连接
    private static Connection getConnections(String driver, String url, String user, String pwd) throws Exception {
        Connection conn = null;
        try {
            Properties props = new Properties();
            props.put("remarksReporting", "true");
            props.put("user", user);
            props.put("password", pwd);
            Class.forName(driver);
            conn = DriverManager.getConnection(url, props);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return conn;
    }


    public static void main(String[] args) throws Exception {

        //这里是Oracle连接方法

//        String driver = "oracle.jdbc.driver.OracleDriver";
//        String url = "jdbc:oracle:thin:@192.168.12.44:1521:orcl";
//        String user = "bdc";
//        String pwd = "bdc123";
//        //String table = "FZ_USER_T";
//        String table = "FZ_USER_T";

        //mysql
        String driver = "com.mysql.jdbc.Driver";
        String user = "root";
        String pwd = "crsstcanywn";
        String url = "jdbc:mysql://localhost/shandong";
        String table = "SD_USER";

    }

}
