package com.lianlianpay.ibatis.helper.core.template.works.sqlmap;

import com.lianlianpay.ibatis.helper.config.domain.IbatorConfiguration;
import com.lianlianpay.ibatis.helper.config.domain.TableConfiguration;
import com.lianlianpay.ibatis.helper.core.domain.DbData;
import com.lianlianpay.ibatis.helper.utils.DbUtils;

import java.io.IOException;

/**
 * Created by chenrs on 2017/11/17.
 */
public class SqlBaseWorker {

    /**
     * 默认oracle
     */
    protected String databseType = "oracle";

    /**
     * 字段占位符
     *
     * @param dbData
     * @throws IOException
     */
    protected String placeholder(DbData dbData) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("#{");
        stringBuilder.append(DbUtils.formateJaveColName(dbData.getColName()));
        stringBuilder.append(",");
        stringBuilder.append("jdbcType=");
        stringBuilder.append(dbData.getDbType().equalsIgnoreCase("Date") ? "TIMESTAMP" : dbData.getDbType());
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    /**
     * java domain 路径
     *
     * @param ibatorConfiguration
     * @param tableConfiguration
     * @return
     */
    protected String getJavaDomainPath(IbatorConfiguration ibatorConfiguration, TableConfiguration tableConfiguration) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ibatorConfiguration.getJavaModelGenerator().getTargetPackage());
        stringBuilder.append(".");
        stringBuilder.append(tableConfiguration.getDomainObjectName());
        return stringBuilder.toString();
    }



    /**
     * java domain 路径
     *
     * @param ibatorConfiguration
     * @param tableConfiguration
     * @return
     */
    protected String getJavaWhereDtoPath(IbatorConfiguration ibatorConfiguration, TableConfiguration tableConfiguration) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ibatorConfiguration.getWhereDtoGenerator().getTargetPackage());
        stringBuilder.append(".");
        stringBuilder.append(tableConfiguration.getDomainObjectName());
        return stringBuilder.toString();
    }

    public String getDatabseType() {
        return databseType;
    }

    public void setDatabseType(String databseType) {
        this.databseType = databseType;
    }


    protected boolean isOracleConnection() {
        return databseType.equalsIgnoreCase("oracle");
    }

}
