package com.lianlianpay.ibatis.helper.core.constraint;

import com.lianlianpay.ibatis.helper.core.domain.DbPrimaryKeyData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取mysql约束
 * Created by chenrs on 2017/11/16.
 */
public class MysqlConstraintServiceImpl extends BaseContraint implements ConstraintService {

    private final static String SQL = "SELECT t.CONSTRAINT_NAME,t.COLUMN_NAME FROM information_schema.KEY_COLUMN_USAGE t LEFT JOIN information_schema.TABLE_CONSTRAINTS u ON  t.CONSTRAINT_NAME = u.CONSTRAINT_NAME  and t.TABLE_NAME = u.TABLE_NAME and t.TABLE_SCHEMA = u.TABLE_SCHEMA WHERE u.CONSTRAINT_TYPE IN ('PRIMARY KEY','UNIQUE') AND t.TABLE_NAME=? order by t.ORDINAL_POSITION ";


    /**
     * 表约束信息缓存
     */
    private static Map<String, List<DbPrimaryKeyData>> tableDbPrimaryKeyCache = new HashMap<String, List<DbPrimaryKeyData>>();


    @Override
    public List<DbPrimaryKeyData> getConstraint(Connection connection, String table) {

        //从缓存中取
        if (tableDbPrimaryKeyCache.containsKey(table)) {
            return tableDbPrimaryKeyCache.get(table);
        }

        try {
            PreparedStatement ps = null;
            ps = connection.prepareStatement(SQL);
            ps.setObject(1, table);
            ResultSet result = ps.executeQuery();
            return getDbPrimaryKeyData(result);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("获取约束信息异常", e);
        }

    }

}
