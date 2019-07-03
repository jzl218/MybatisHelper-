package com.lianlianpay.ibatis.helper.core.domain;

import java.util.List;

/**
 * 唯一约束信息
 * Created by chenrs on 2017/11/15.
 */
public class DbPrimaryKeyData {

    /**
     * 约束对应的字段集合
     */
    private List<String> colName;

    /**
     * 唯一约束名称
     */
    private String primaryKeyName;

    public List<String> getColName() {
        return colName;
    }

    public void setColName(List<String> colName) {
        this.colName = colName;
    }

    public String getPrimaryKeyName() {
        return primaryKeyName;
    }

    public void setPrimaryKeyName(String primaryKeyName) {
        this.primaryKeyName = primaryKeyName;
    }

}
