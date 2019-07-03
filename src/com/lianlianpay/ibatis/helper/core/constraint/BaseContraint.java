package com.lianlianpay.ibatis.helper.core.constraint;

import com.lianlianpay.ibatis.helper.core.domain.DbPrimaryKeyData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenrs on 2017/11/16.
 */
public class BaseContraint {


    protected List<DbPrimaryKeyData> getDbPrimaryKeyData(ResultSet result) throws SQLException {
        List<DbPrimaryKeyData> dbPrimaryKeyDataList = new ArrayList<DbPrimaryKeyData>();
        Map<String,List<String>> dbPrimaryKeyDataMap = new HashMap<String,List<String>>();
        while (result.next()) {//判断是否还有下一行
            String constraintName = result.getString("CONSTRAINT_NAME");
            String columnName = result.getString("COLUMN_NAME");

            if (dbPrimaryKeyDataMap.containsKey(constraintName)) {
                dbPrimaryKeyDataMap.get(constraintName).add(columnName);
            } else {
                List<String> colums = new ArrayList<String>();
                colums.add(columnName);
                dbPrimaryKeyDataMap.put(constraintName,colums);
            }
        }

        for (String key : dbPrimaryKeyDataMap.keySet()) {
            DbPrimaryKeyData data = new DbPrimaryKeyData();
            data.setPrimaryKeyName(key);
            data.setColName(dbPrimaryKeyDataMap.get(key));
            dbPrimaryKeyDataList.add(data);
        }
        return dbPrimaryKeyDataList;
    }
}
