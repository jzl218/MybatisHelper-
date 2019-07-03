package com.lianlianpay.ibatis.helper.core.column;

import com.lianlianpay.ibatis.helper.core.domain.DbData;

import java.sql.Connection;
import java.util.List;

/**
 * Created by chenrs on 2017/11/16.
 */
public interface ColumnQueryService {

    List<DbData> getTableInfo(Connection conn, String table) ;
}
