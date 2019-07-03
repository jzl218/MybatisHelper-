package com.lianlianpay.ibatis.helper.core.constraint;

import com.lianlianpay.ibatis.helper.core.domain.DbPrimaryKeyData;

import java.sql.Connection;
import java.util.List;

/**
 * 获取约束信息
 * Created by chenrs on 2017/11/16.
 */
public interface ConstraintService {


    List<DbPrimaryKeyData> getConstraint(Connection connection, String table);
}
