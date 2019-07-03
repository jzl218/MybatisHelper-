package com.lianlianpay.ibatis.helper.core.domain;

/**
 * Created by chenrs on 2017/11/15.
 */
public class DbData {

    /**
     * 列名
     */
    private String colName;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 数据库字段类型
     */
    private String dbType;

    /**
     * java 类型
     */
    private String type;

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
