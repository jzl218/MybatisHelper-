package com.lianlianpay.ibatis.helper.config.domain;

import com.mysql.jdbc.StringUtils;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenrs on 2017/9/29.
 */
public class IbatorConfiguration {

    private String id;

    private JdbcConnection jdbcConnection = new JdbcConnection();

    private DaoGenerator daoGenerator = new DaoGenerator();

    private JavaModelGenerator javaModelGenerator = new JavaModelGenerator();

    private SqlMapGenerator sqlMapGenerator = new SqlMapGenerator();

    private WhereDtoGenerator whereDtoGenerator = new WhereDtoGenerator();

    private List<TableConfiguration> tableConfiguration = new ArrayList<TableConfiguration>();

    private String createTimeColumn;

    private String modifyTimeColumn;

    private Connection connection;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public JdbcConnection getJdbcConnection() {
        return jdbcConnection;
    }

    public void setJdbcConnection(JdbcConnection jdbcConnection) {
        this.jdbcConnection = jdbcConnection;
    }

    public DaoGenerator getDaoGenerator() {
        return daoGenerator;
    }

    public void setDaoGenerator(DaoGenerator daoGenerator) {
        this.daoGenerator = daoGenerator;
    }

    public JavaModelGenerator getJavaModelGenerator() {
        return javaModelGenerator;
    }

    public void setJavaModelGenerator(JavaModelGenerator javaModelGenerator) {
        this.javaModelGenerator = javaModelGenerator;
    }

    public SqlMapGenerator getSqlMapGenerator() {
        return sqlMapGenerator;
    }

    public void setSqlMapGenerator(SqlMapGenerator sqlMapGenerator) {
        this.sqlMapGenerator = sqlMapGenerator;
    }

    public List<TableConfiguration> getTableConfiguration() {
        return tableConfiguration;
    }

    public void setTableConfiguration(List<TableConfiguration> tableConfiguration) {
        this.tableConfiguration = tableConfiguration;
    }

    public IbatorConfiguration(String id) {
        this.id = id;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getCreateTimeColumn() {
        if (StringUtils.isNullOrEmpty(createTimeColumn)) {
            return createTimeColumn;
        } else {
            return createTimeColumn.toLowerCase();
        }
    }

    public void setCreateTimeColumn(String createTimeColumn) {
        this.createTimeColumn = createTimeColumn;
    }

    public String getModifyTimeColumn() {
        if (StringUtils.isNullOrEmpty(modifyTimeColumn)) {
            return modifyTimeColumn;
        } else {
            return modifyTimeColumn.toLowerCase();
        }
    }

    public void setModifyTimeColumn(String modifyTimeColumn) {
        this.modifyTimeColumn = modifyTimeColumn;
    }

    public WhereDtoGenerator getWhereDtoGenerator() {
        return whereDtoGenerator;
    }

    public void setWhereDtoGenerator(WhereDtoGenerator whereDtoGenerator) {
        this.whereDtoGenerator = whereDtoGenerator;
    }
}
