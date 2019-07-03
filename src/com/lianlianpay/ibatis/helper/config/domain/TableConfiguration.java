package com.lianlianpay.ibatis.helper.config.domain;

/**
 * Created by chenrs on 2017/9/29.
 */
public class TableConfiguration {


    private String schema;

    private String tableName;

    private String domainObjectName;

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDomainObjectName() {
        return domainObjectName;
    }

    public void setDomainObjectName(String domainObjectName) {
        this.domainObjectName = domainObjectName;
    }
}
