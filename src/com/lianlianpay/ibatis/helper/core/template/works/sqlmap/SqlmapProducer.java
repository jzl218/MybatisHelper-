package com.lianlianpay.ibatis.helper.core.template.works.sqlmap;

import com.lianlianpay.ibatis.helper.config.domain.IbatorConfiguration;
import com.lianlianpay.ibatis.helper.config.domain.TableConfiguration;
import com.lianlianpay.ibatis.helper.core.BeanContainer;
import com.lianlianpay.ibatis.helper.core.column.ColumnQueryService;
import com.lianlianpay.ibatis.helper.core.constraint.ConstraintService;
import com.lianlianpay.ibatis.helper.core.domain.DbData;
import com.lianlianpay.ibatis.helper.core.domain.DbPrimaryKeyData;
import com.lianlianpay.ibatis.helper.utils.DbUtils;
import com.mysql.jdbc.MySQLConnection;
import oracle.jdbc.OracleConnection;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by chenrs on 2017/11/17.
 */
public class SqlmapProducer extends SqlBaseWorker {

    private List<DbData> columns;

    private List<DbPrimaryKeyData> dbPrimaryKeyDatas;

    private FileOutputStream outputStream;

    private TableConfiguration tableConfiguration;

    private IbatorConfiguration ibatorConfiguration;

    public SqlmapProducer(TableConfiguration tableConfiguration, IbatorConfiguration ibatorConfiguration) throws FileNotFoundException {
        init(tableConfiguration, ibatorConfiguration);
        this.tableConfiguration = tableConfiguration;
        this.ibatorConfiguration = ibatorConfiguration;
    }

    private void init(TableConfiguration tableConfiguration, IbatorConfiguration ibatorConfiguration) throws FileNotFoundException {
        outputStream = new FileOutputStream(ibatorConfiguration.getSqlMapGenerator().getPath() + tableConfiguration.getTableName() + ".xml");
        ColumnQueryService columnQueryService = BeanContainer.getBean(ColumnQueryService.class);
        columns = columnQueryService.getTableInfo(ibatorConfiguration.getConnection(), tableConfiguration.getTableName());
        ConstraintService constraintService = BeanContainer.getBean(ibatorConfiguration.getJdbcConnection().getDriverClass(), ConstraintService.class);
        dbPrimaryKeyDatas = constraintService.getConstraint(ibatorConfiguration.getConnection(), tableConfiguration.getTableName());

        if (ibatorConfiguration.getConnection() instanceof MySQLConnection) {
            databseType="mysql";
        } else if (ibatorConfiguration.getConnection() instanceof OracleConnection) {
            databseType = "oracle";
        }
    }

    public void doSqlmap() throws IOException {

        sqlHead();

        sqlNameSpace();

//        sqlTypeAlias();

        sqlResult();

        sqlBaseColumns();

        sqlInsert();

        sqlSelect();

        sqlPageByCondition();

        sqlCountByConditon();

        sqlContrainUpdate();

        sqlContrainDelete();

        sqlContrainSelect();

        sqlTail();
    }

    private void sqlCountByConditon() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        //查询
        stringBuilder.append("  <select id=\"countByCondition");
        stringBuilder.append("\" resultType=\"int\" parameterType=\"");
        stringBuilder.append(getJavaWhereDtoPath(ibatorConfiguration, tableConfiguration));
        stringBuilder.append("WhereDTO\">");
        stringBuilder.append("\n");
        stringBuilder.append("         select count(1)");
        stringBuilder.append("  from ");
        stringBuilder.append(tableConfiguration.getTableName());
        stringBuilder.append(" where 1=1");
        stringBuilder.append("\n");

        if (null != ibatorConfiguration.getCreateTimeColumn() && !"".equals(ibatorConfiguration.getCreateTimeColumn().trim())) {

            if (containCreateTimeColumn()) {
                stringBuilder.append("      <if test=\"startDate != null\" >\n");
                stringBuilder.append("   and   <![CDATA[");
                stringBuilder.append(ibatorConfiguration.getCreateTimeColumn());
                stringBuilder.append(" >= #{startDate,jdbcType=TIMESTAMP} ]]>\n");
                stringBuilder.append("      </if>\n");

                stringBuilder.append("      <if test=\"endDate != null\" >\n");
                stringBuilder.append("  and    <![CDATA[");
                stringBuilder.append(ibatorConfiguration.getCreateTimeColumn());
                if (ibatorConfiguration.getConnection() instanceof  OracleConnection) {
                    stringBuilder.append(" <= #{endDate,jdbcType=TIMESTAMP} + 1 ]]>\n");
                } else {
                    stringBuilder.append(" < date_add(#{endDate,jdbcType=TIMESTAMP}, interval 1 day)  ]]>\n");
                }
                stringBuilder.append("      </if>\n");
            }
        }

        for (DbData dbData : columns) {
            if(dbData.getColName().equals(ibatorConfiguration.getCreateTimeColumn())) {
                continue;
            }
            stringBuilder.append("     <if test=\"");
            stringBuilder.append(DbUtils.formateJaveColName(dbData.getColName()));
            stringBuilder.append(" != null\">\n   and  ");
            stringBuilder.append(dbData.getColName());
            stringBuilder.append(" = ");
            stringBuilder.append(placeholder(dbData));
            stringBuilder.append("\n");
            stringBuilder.append("      </if>\n");
        }
        stringBuilder.append("  </select>\n");
        stringBuilder.append("\n");
        outputStream.write(stringBuilder.toString().getBytes());

    }

    private boolean containCreateTimeColumn() {
        for (DbData dbData : columns) {
            if (dbData.getColName().equalsIgnoreCase(ibatorConfiguration.getCreateTimeColumn())) {
                return true;
            }
        }
        return false;
    }

    private void sqlPageByCondition() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        //查询
        stringBuilder.append("  <select id=\"pageByCondition");
        stringBuilder.append("\" resultMap=\"BaseResultMap\" parameterType=\"");
        stringBuilder.append(getJavaWhereDtoPath(ibatorConfiguration, tableConfiguration));
        stringBuilder.append("WhereDTO\">");
        stringBuilder.append("\n");
        if (ibatorConfiguration.getConnection() instanceof  OracleConnection) {
            stringBuilder.append("       select * from ( select a.*, rownum rn from ( ");
        }
        stringBuilder.append("  select <include refid=\"baseColumns\"/> \n ");
        stringBuilder.append("  from ");
        stringBuilder.append(tableConfiguration.getTableName());
        stringBuilder.append(" where 1=1");
        stringBuilder.append("\n");

        if (null != ibatorConfiguration.getCreateTimeColumn() && !"".equals(ibatorConfiguration.getCreateTimeColumn().trim())) {
            if (containCreateTimeColumn()) {
                stringBuilder.append("      <if test=\"startDate != null\" >\n");
                stringBuilder.append("     and <![CDATA[");
                stringBuilder.append(ibatorConfiguration.getCreateTimeColumn());
                stringBuilder.append(" >= #{startDate,jdbcType=TIMESTAMP} ]]>\n");
                stringBuilder.append("      </if>\n");

                stringBuilder.append("      <if test=\"endDate != null\" >\n");
                stringBuilder.append("    and  <![CDATA[");
                stringBuilder.append(ibatorConfiguration.getCreateTimeColumn());
                if (ibatorConfiguration.getConnection() instanceof  OracleConnection) {
                    stringBuilder.append(" <= #{endDate,jdbcType=TIMESTAMP} + 1 ]]>\n");
                } else {
                    stringBuilder.append(" < date_add(#{endDate,jdbcType=TIMESTAMP}, interval 1 day)  ]]>\n");
                }
                stringBuilder.append("      </if>\n");
            }
        }

        for (DbData dbData : columns) {
            if(dbData.getColName().equals(ibatorConfiguration.getCreateTimeColumn())) {
                continue;
            }
            stringBuilder.append("      <if test=\"");
            stringBuilder.append(DbUtils.formateJaveColName(dbData.getColName()));
            stringBuilder.append("!= null\">\n  and   ");
            stringBuilder.append(dbData.getColName());
            stringBuilder.append(" = ");
            stringBuilder.append(placeholder(dbData));
            stringBuilder.append("\n");
            stringBuilder.append("       </if>\n");
        }
        if (ibatorConfiguration.getConnection() instanceof  OracleConnection) {
            stringBuilder.append("       <![CDATA[ ) a where rownum <= #endRowNum# ) where rn >= #startRowNum#]]>\n");
        } else {
            stringBuilder.append("   limit #{offset}, #{limit} \n");
        }
        stringBuilder.append("  </select>\n");
        stringBuilder.append("\n");
        outputStream.write(stringBuilder.toString().getBytes());
    }

    private void sqlSelect() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        //查询
        stringBuilder.append("  <select id=\"listByCondition");
        stringBuilder.append("\" resultMap=\"BaseResultMap\" parameterType=\"");
        stringBuilder.append(getJavaDomainPath(ibatorConfiguration, tableConfiguration));
        stringBuilder.append("\">");
        stringBuilder.append("\n");
        stringBuilder.append("      select ");
        stringBuilder.append("  <include refid=\"baseColumns\"/> \n ");
        stringBuilder.append("  from ");
        stringBuilder.append(tableConfiguration.getTableName());
        stringBuilder.append(" where 1=1");
        stringBuilder.append("\n");
        for (DbData dbData : columns) {
            stringBuilder.append("      <if test=\"");
            stringBuilder.append(DbUtils.formateJaveColName(dbData.getColName()));
            stringBuilder.append(" != null\">\n  and   ");
            stringBuilder.append(dbData.getColName());
            stringBuilder.append(" = ");
            stringBuilder.append(placeholder(dbData));
            stringBuilder.append("\n");
            stringBuilder.append("      </if>\n");
        }
        stringBuilder.append("  </select>\n");
        stringBuilder.append("\n");
        outputStream.write(stringBuilder.toString().getBytes());
    }


    private void sqlHead() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
        stringBuilder.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n");
        outputStream.write(stringBuilder.toString().getBytes());
    }

    private void sqlNameSpace() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<mapper namespace=\"");
        stringBuilder.append(ibatorConfiguration.getDaoGenerator().getTargetPackage());
        stringBuilder.append(".");
        stringBuilder.append(tableConfiguration.getDomainObjectName());
        stringBuilder.append("Mapper");
        stringBuilder.append("\">");
        stringBuilder.append("\n");
        outputStream.write(stringBuilder.toString().getBytes());
    }

    private void sqlTypeAlias() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("  <typeAliases>\n");
        stringBuilder.append("  <typeAlias alias=\"");
        stringBuilder.append(getJavaDomainPath(ibatorConfiguration, tableConfiguration));
        stringBuilder.append("\"");
        stringBuilder.append(" type=\"");
        stringBuilder.append(getJavaDomainPath(ibatorConfiguration, tableConfiguration));
        stringBuilder.append("\"/>\n");

        stringBuilder.append("  <typeAlias alias=\"");
        stringBuilder.append(getJavaWhereDtoPath(ibatorConfiguration, tableConfiguration));
        stringBuilder.append("WhereDTO\"");
        stringBuilder.append(" type=\"");
        stringBuilder.append(getJavaWhereDtoPath(ibatorConfiguration, tableConfiguration));
        stringBuilder.append("WhereDTO\"/>\n");
        stringBuilder.append("</typeAliases>\n");
        outputStream.write(stringBuilder.toString().getBytes());
    }

    private void sqlResult() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("  <resultMap id=\"BaseResultMap\" type=\"");
        stringBuilder.append(getJavaDomainPath(ibatorConfiguration, tableConfiguration));
        stringBuilder.append("\">\n");

        for (DbData dbData : columns) {
            stringBuilder.append("      <result column=\"");
            stringBuilder.append(dbData.getColName());
            stringBuilder.append("\"  ");
            stringBuilder.append("property=\"");
            stringBuilder.append(DbUtils.formateJaveColName(dbData.getColName()));
            stringBuilder.append("\"  ");
            stringBuilder.append("jdbcType=\"");
            stringBuilder.append(dbData.getDbType().equalsIgnoreCase("Date")?"TIMESTAMP":dbData.getDbType());
            stringBuilder.append("\"  ");
            stringBuilder.append("/>");
            stringBuilder.append("\n");
        }
        stringBuilder.append("  </resultMap>");
        stringBuilder.append("\n");
        stringBuilder.append("\n");
        outputStream.write(stringBuilder.toString().getBytes());

    }

    private void sqlBaseColumns() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("  <sql id=\"baseColumns\">");
        stringBuilder.append("\n");
        for (int i = 0; i < columns.size(); i++) {
            stringBuilder.append("      ");
            stringBuilder.append(columns.get(i).getColName());
            if (i != columns.size() - 1) {
                stringBuilder.append(",");
            }
            stringBuilder.append("\n");
        }
        stringBuilder.append("  </sql>");
        stringBuilder.append("\n");
        stringBuilder.append("\n");
        outputStream.write(stringBuilder.toString().getBytes());

    }

    private void sqlInsert() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("  <insert id=\"insert\" parameterType=\"");
        stringBuilder.append(getJavaDomainPath(ibatorConfiguration, tableConfiguration));
        stringBuilder.append("\">");
        stringBuilder.append("\n");
        stringBuilder.append("      insert into ");
        stringBuilder.append(tableConfiguration.getTableName());
        stringBuilder.append("  (<include refid=\"baseColumns\"/>) ");
        stringBuilder.append("values");
        stringBuilder.append("\n");
        stringBuilder.append("(");
        for (int i = 0; i < columns.size(); i++) {

            if (columns.get(i).getColName().equalsIgnoreCase(ibatorConfiguration.getCreateTimeColumn())
                    || columns.get(i).getColName().equalsIgnoreCase(ibatorConfiguration.getModifyTimeColumn())) {
                if (ibatorConfiguration.getConnection() instanceof OracleConnection) {
                    stringBuilder.append("sysdate");
                } else if (ibatorConfiguration.getConnection() instanceof MySQLConnection) {
                    stringBuilder.append("sysdate()");
                } else {
                    stringBuilder.append(placeholder(columns.get(i)));
                }
            } else {
                stringBuilder.append(placeholder(columns.get(i)));
            }
            if (i != columns.size() - 1) {
                stringBuilder.append(",");
            }
        }
        stringBuilder.append(")");
        stringBuilder.append("\n");
        stringBuilder.append("  </insert>");
        stringBuilder.append("\n");
        stringBuilder.append("\n");
        outputStream.write(stringBuilder.toString().getBytes());
    }

    private void sqlContrainUpdate() throws IOException {

        boolean notDynamicModify = containModifyTimeColumn() &&
                (ibatorConfiguration.getConnection() instanceof OracleConnection
                        || ibatorConfiguration.getConnection() instanceof MySQLConnection);
        //约束操作
        for (DbPrimaryKeyData dbPrimaryKeyData : dbPrimaryKeyDatas) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("  <update id=\"updateBy");
            stringBuilder.append(DbUtils.methodName(dbPrimaryKeyData.getColName()));
            stringBuilder.append("\" parameterType=\"");
            stringBuilder.append(getJavaDomainPath(ibatorConfiguration, tableConfiguration));
            stringBuilder.append("\">");
            stringBuilder.append("\n");
            stringBuilder.append("      update ");
            stringBuilder.append(tableConfiguration.getTableName());


            //存在更新字段 且为支持的oracle和mysql
                stringBuilder.append("  set \n");
                if (ibatorConfiguration.getConnection() instanceof OracleConnection) {
                    stringBuilder.append("      ");
                    stringBuilder.append(ibatorConfiguration.getModifyTimeColumn());
                    stringBuilder.append(" = ");
                    stringBuilder.append("sysdate");
                    stringBuilder.append("\n");
                } else if (ibatorConfiguration.getConnection() instanceof MySQLConnection) {
                    stringBuilder.append("      ");
                    stringBuilder.append(ibatorConfiguration.getModifyTimeColumn());
                    stringBuilder.append(" = ");
                    stringBuilder.append("sysdate()");
                    stringBuilder.append("\n");
                }


            for (DbData dbData : columns) {

                if (dbData.getColName().equalsIgnoreCase(ibatorConfiguration.getModifyTimeColumn())) {
                    continue;
                }
                stringBuilder.append("     <if test=\"");
                stringBuilder.append(DbUtils.formateJaveColName(dbData.getColName()));
                stringBuilder.append(" != null\">\n         ,");
                stringBuilder.append(dbData.getColName());
                stringBuilder.append(" = ");
                stringBuilder.append(placeholder(dbData));
                stringBuilder.append("\n");
                stringBuilder.append("      </if>\n");
            }

            stringBuilder.append("      where");
            for (int i = 0; i < dbPrimaryKeyData.getColName().size(); i++) {
                stringBuilder.append(" ");
                if (i != 0) {
                    stringBuilder.append(" and ");
                }
                stringBuilder.append(dbPrimaryKeyData.getColName().get(i));
                stringBuilder.append(" = ");
                stringBuilder.append(placeholder(getDbDataByColName(dbPrimaryKeyData.getColName().get(i))));
            }
            stringBuilder.append("\n    </update>");
            stringBuilder.append("\n");
            stringBuilder.append("\n");
            outputStream.write(stringBuilder.toString().getBytes());
        }
    }

    private DbData getDbDataByColName(String colname) {
        for (DbData dbData : columns) {
            if (dbData.getColName().equals(colname)) {
                return dbData;
            }
        }
        return null;
    }

    private boolean containModifyTimeColumn() {
        for (DbData dbData : columns) {
            if (dbData.getColName().equalsIgnoreCase(ibatorConfiguration.getModifyTimeColumn())) {
                return true;
            }
        }
        return false;
    }

    private void sqlContrainSelect() throws IOException {
        //约束操作
        for (DbPrimaryKeyData dbPrimaryKeyData : dbPrimaryKeyDatas) {
            StringBuilder stringBuilder = new StringBuilder();
            //查询
            stringBuilder.append("  <select id=\"queryBy");
            stringBuilder.append(DbUtils.methodName(dbPrimaryKeyData.getColName()));
            stringBuilder.append("\" resultMap=\"BaseResultMap\" parameterType=\"");
            stringBuilder.append(getJavaDomainPath(ibatorConfiguration, tableConfiguration));
            stringBuilder.append("\">");
            stringBuilder.append("\n");
            stringBuilder.append("      select ");
            stringBuilder.append("  <include refid=\"baseColumns\"/> from\n     ");
            stringBuilder.append(tableConfiguration.getTableName());
            stringBuilder.append("\n");
            stringBuilder.append("      where");
            for (int i = 0; i < dbPrimaryKeyData.getColName().size(); i++) {
                stringBuilder.append(" ");
                if (i != 0) {
                    stringBuilder.append(" and ");
                }
                stringBuilder.append(dbPrimaryKeyData.getColName().get(i));
                stringBuilder.append(" = ");
                stringBuilder.append(placeholder(getDbDataByColName(dbPrimaryKeyData.getColName().get(i))));
            }
            stringBuilder.append("  \n  </select>");
            stringBuilder.append("\n");
            stringBuilder.append("\n");
            outputStream.write(stringBuilder.toString().getBytes());

        }
    }

    public void sqlContrainDelete() throws IOException {
        //约束操作
        for (DbPrimaryKeyData dbPrimaryKeyData : dbPrimaryKeyDatas) {
            StringBuilder stringBuilder = new StringBuilder();
            //删除
            stringBuilder.append("  <delete id=\"deleteBy");
            stringBuilder.append(DbUtils.methodName(dbPrimaryKeyData.getColName()));
            stringBuilder.append("\" parameterType=\"");
            stringBuilder.append(getJavaDomainPath(ibatorConfiguration, tableConfiguration));
            stringBuilder.append("\">");
            stringBuilder.append("\n");
            stringBuilder.append("      delete from ");
            stringBuilder.append(tableConfiguration.getTableName());
            stringBuilder.append("\n");
            stringBuilder.append("      where");
            for (int i = 0; i < dbPrimaryKeyData.getColName().size(); i++) {
                stringBuilder.append(" ");
                if (i != 0) {
                    stringBuilder.append(" and ");
                }
                stringBuilder.append(dbPrimaryKeyData.getColName().get(i));
                stringBuilder.append(" = ");
                stringBuilder.append(placeholder(getDbDataByColName(dbPrimaryKeyData.getColName().get(i))));
            }
            stringBuilder.append("  \n  </delete>\n");
            stringBuilder.append("\n");
            outputStream.write(stringBuilder.toString().getBytes());
        }


    }

    private void sqlTail() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("</mapper>");
        outputStream.write(stringBuilder.toString().getBytes());
    }
}
