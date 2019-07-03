package com.lianlianpay.ibatis.helper.core.constraint;

import com.lianlianpay.ibatis.helper.core.domain.DbPrimaryKeyData;
import com.lianlianpay.ibatis.helper.utils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取oracle约束
 * Created by chenrs on 2017/11/16.
 */
public class OracleContraintServiceImpl extends BaseContraint implements ConstraintService {


    private final static String SQL = "  select u.CONSTRAINT_NAME, u.COLUMN_NAME from ALL_CONS_COLUMNS u left join user_constraints s   on u.TABLE_NAME = s.TABLE_NAME   and u.CONSTRAINT_NAME = s.CONSTRAINT_NAME  where s.CONSTRAINT_TYPE in ('P', 'U')   AND s.status = 'ENABLED'   and u.TABLE_NAME = ?  and u.OWNER = ? order by u.POSITION";

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
            ps.setObject(2, DbUtils.getSchema(connection));
            ResultSet result = ps.executeQuery();
            return getDbPrimaryKeyData(result);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("获取约束信息异常", e);
        }

    }


}
