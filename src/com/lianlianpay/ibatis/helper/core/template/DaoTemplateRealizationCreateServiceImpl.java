package com.lianlianpay.ibatis.helper.core.template;

import com.lianlianpay.ibatis.helper.config.ConfigLoader;
import com.lianlianpay.ibatis.helper.config.domain.IbatorConfiguration;
import com.lianlianpay.ibatis.helper.config.domain.TableConfiguration;
import com.lianlianpay.ibatis.helper.core.BeanContainer;
import com.lianlianpay.ibatis.helper.core.constraint.ConstraintService;
import com.lianlianpay.ibatis.helper.core.domain.DbPrimaryKeyData;
import com.lianlianpay.ibatis.helper.utils.DbUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

/**
 * Created by chenrs on 2017/11/17.
 */
public class DaoTemplateRealizationCreateServiceImpl implements  DaoTemplateRealizationCreateService {
    @Override
    public void createDaoInterfaceRealization() throws IOException {
        for (String key : ConfigLoader.ibatorConfigurationMap.keySet()) {

            IbatorConfiguration ibatorConfiguration = ConfigLoader.ibatorConfigurationMap.get(key);

            Connection con = ibatorConfiguration.getConnection();

            for (TableConfiguration tableConfiguration : ibatorConfiguration.getTableConfiguration()) {
                ibatorConfiguration.getDaoGenerator().mkdirs();
                FileOutputStream outputStream = new FileOutputStream(ibatorConfiguration.getDaoGenerator().getPath() + DbUtils.toUpperFristChar(tableConfiguration.getDomainObjectName()) + "DaoImpl.java");

                outputStream.write("package".getBytes());
                outputStream.write(" ".getBytes());
                outputStream.write(ibatorConfiguration.getDaoGenerator().getTargetPackage().getBytes());
                outputStream.write(";".getBytes());
                outputStream.write("\n".getBytes());
                outputStream.write("\n".getBytes());
                outputStream.write("\n".getBytes());

                outputStream.write("import ".getBytes());
                outputStream.write(ibatorConfiguration.getJavaModelGenerator().getTargetPackage().getBytes());
                outputStream.write(".".getBytes());
                outputStream.write(tableConfiguration.getDomainObjectName().getBytes());
                outputStream.write(";\n".getBytes());
                outputStream.write("import org.springframework.orm.ibatis.SqlMapClientTemplate;\n".getBytes());
                outputStream.write("import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;\n".getBytes());
                outputStream.write("import java.util.List;".getBytes());
                outputStream.write("\n".getBytes());
                outputStream.write("\n".getBytes());
                //class body
                outputStream.write("public class ".getBytes());
                outputStream.write( DbUtils.toUpperFristChar(tableConfiguration.getDomainObjectName()).getBytes());
                outputStream.write( "DaoImpl ".getBytes());
                outputStream.write(" extends SqlMapClientDaoSupport implements ".getBytes());
                outputStream.write( DbUtils.toUpperFristChar(tableConfiguration.getDomainObjectName()).getBytes());
                outputStream.write( "Dao {".getBytes());
                outputStream.write("\n".getBytes());
                outputStream.write("\n".getBytes());

                outputStream.write("   private static final String SQLMAP_SPACE = ".getBytes());
                outputStream.write("\"".getBytes());
                outputStream.write(tableConfiguration.getTableName().getBytes());
                outputStream.write(".\";".getBytes());
                outputStream.write("\n".getBytes());
                outputStream.write("\n".getBytes());



                //插入
                outputStream.write("     @Override\n".getBytes());
                outputStream.write("     public void insert(".getBytes());
                outputStream.write(tableConfiguration.getDomainObjectName().getBytes());
                outputStream.write(" ".getBytes());
                outputStream.write(DbUtils.toLowFristChar(tableConfiguration.getDomainObjectName()).getBytes());
                outputStream.write(") {\n".getBytes());
                outputStream.write("      getSqlMapClientTemplate().insert(SQLMAP_SPACE + \"insert\", ".getBytes());
                outputStream.write(DbUtils.toLowFristChar(tableConfiguration.getDomainObjectName()).getBytes());
                outputStream.write("); \n".getBytes());
                outputStream.write("     }".getBytes());
                outputStream.write("\n".getBytes());
                outputStream.write("\n".getBytes());

                //查询
                outputStream.write("     @Override\n".getBytes());
                outputStream.write("     public List<".getBytes());
                outputStream.write(tableConfiguration.getDomainObjectName().getBytes());
                outputStream.write(">".getBytes());
                outputStream.write(" listByCondition(".getBytes());
                outputStream.write(tableConfiguration.getDomainObjectName().getBytes());
                outputStream.write(" ".getBytes());
                outputStream.write(DbUtils.toLowFristChar(tableConfiguration.getDomainObjectName()).getBytes());
                outputStream.write(") {\n".getBytes());
                outputStream.write("       return getSqlMapClientTemplate().queryForList(SQLMAP_SPACE + \"listByCondition\", ".getBytes());
                outputStream.write(DbUtils.toLowFristChar(tableConfiguration.getDomainObjectName()).getBytes());
                outputStream.write("); \n".getBytes());
                outputStream.write("     }".getBytes());
                outputStream.write("\n".getBytes());
                outputStream.write("\n".getBytes());


                //分页
                outputStream.write("     @Override\n".getBytes());
                outputStream.write("     public List<".getBytes());
                outputStream.write(tableConfiguration.getDomainObjectName().getBytes());
                outputStream.write(">".getBytes());
                outputStream.write(" pageByCondition(".getBytes());
                outputStream.write(tableConfiguration.getDomainObjectName().getBytes());
                outputStream.write("WhereDTO ".getBytes());
                outputStream.write(DbUtils.toLowFristChar(tableConfiguration.getDomainObjectName()).getBytes());
                outputStream.write("WhereDTO".getBytes());
                outputStream.write(") {\n".getBytes());
                outputStream.write("       return getSqlMapClientTemplate().queryForList(SQLMAP_SPACE + \"pageByCondition\", ".getBytes());
                outputStream.write(DbUtils.toLowFristChar(tableConfiguration.getDomainObjectName()).getBytes());
                outputStream.write("WhereDTO); \n".getBytes());
                outputStream.write("     }".getBytes());
                outputStream.write("\n".getBytes());
                outputStream.write("\n".getBytes());

                outputStream.write("     @Override\n".getBytes());
                outputStream.write("     public int ".getBytes());
                outputStream.write(" countByCondition(".getBytes());
                outputStream.write(tableConfiguration.getDomainObjectName().getBytes());
                outputStream.write("WhereDTO ".getBytes());
                outputStream.write(DbUtils.toLowFristChar(tableConfiguration.getDomainObjectName()).getBytes());
                outputStream.write("WhereDTO".getBytes());
                outputStream.write(") {\n".getBytes());
                outputStream.write("       return (Integer)getSqlMapClientTemplate().queryForObject(SQLMAP_SPACE + \"countByCondition\", ".getBytes());
                outputStream.write(DbUtils.toLowFristChar(tableConfiguration.getDomainObjectName()).getBytes());
                outputStream.write("WhereDTO); \n".getBytes());
                outputStream.write("     }".getBytes());
                outputStream.write("\n".getBytes());
                outputStream.write("\n".getBytes());


                ConstraintService constraintService = BeanContainer.getBean(ibatorConfiguration.getJdbcConnection().getDriverClass(),ConstraintService.class);
                List<DbPrimaryKeyData> datas = constraintService.getConstraint(con, tableConfiguration.getTableName());

                for (DbPrimaryKeyData dbPrimaryKeyData : datas) {
                    outputStream.write("     @Override\n".getBytes());
                    outputStream.write("     public int updateBy".getBytes());
                    outputStream.write(DbUtils.methodName(dbPrimaryKeyData.getColName()).getBytes());
                    outputStream.write("(".getBytes());
                    outputStream.write(tableConfiguration.getDomainObjectName().getBytes());
                    outputStream.write(" ".getBytes());
                    outputStream.write(DbUtils.toLowFristChar(tableConfiguration.getDomainObjectName()).getBytes());
                    outputStream.write(") {".getBytes());
                    outputStream.write("\n".getBytes());
                    outputStream.write("        return getSqlMapClientTemplate().update(SQLMAP_SPACE + ".getBytes());
                    outputStream.write("\"".getBytes());
                    outputStream.write("updateBy".getBytes());
                    outputStream.write(   DbUtils.methodName(dbPrimaryKeyData.getColName()).getBytes());
                    outputStream.write("\",".getBytes());
                    outputStream.write(DbUtils.toLowFristChar(tableConfiguration.getDomainObjectName()).getBytes());
                    outputStream.write("); \n".getBytes());
                    outputStream.write("     }".getBytes());
                    outputStream.write("\n".getBytes());
                    outputStream.write("\n".getBytes());

                    outputStream.write("     @Override\n".getBytes());
                    outputStream.write("     public ".getBytes());
                    outputStream.write(tableConfiguration.getDomainObjectName().getBytes());
                    outputStream.write(" queryBy".getBytes());
                    outputStream.write(DbUtils.methodName(dbPrimaryKeyData.getColName()).getBytes());
                    outputStream.write("(".getBytes());
                    outputStream.write(tableConfiguration.getDomainObjectName().getBytes());
                    outputStream.write(" ".getBytes());
                    outputStream.write(DbUtils.toLowFristChar(tableConfiguration.getDomainObjectName()).getBytes());
                    outputStream.write(") {".getBytes());
                    outputStream.write("\n".getBytes());
                    outputStream.write("        return (".getBytes());
                    outputStream.write(tableConfiguration.getDomainObjectName().getBytes());
                    outputStream.write(")getSqlMapClientTemplate().queryForObject(SQLMAP_SPACE +  ".getBytes());
                    outputStream.write("\"".getBytes());
                    outputStream.write("queryBy".getBytes());
                    outputStream.write(   DbUtils.methodName(dbPrimaryKeyData.getColName()).getBytes());
                    outputStream.write("\",".getBytes());
                    outputStream.write(DbUtils.toLowFristChar(tableConfiguration.getDomainObjectName()).getBytes());
                    outputStream.write("); \n".getBytes());
                    outputStream.write("     }".getBytes());
                    outputStream.write("\n".getBytes());
                    outputStream.write("\n".getBytes());

                    outputStream.write("     @Override\n".getBytes());
                    outputStream.write("     public int deleteBy".getBytes());
                    outputStream.write(DbUtils.methodName(dbPrimaryKeyData.getColName()).getBytes());
                    outputStream.write("(".getBytes());
                    outputStream.write(tableConfiguration.getDomainObjectName().getBytes());
                    outputStream.write(" ".getBytes());
                    outputStream.write(DbUtils.toLowFristChar(tableConfiguration.getDomainObjectName()).getBytes());
                    outputStream.write(") {".getBytes());
                    outputStream.write("\n".getBytes());
                    outputStream.write("        return getSqlMapClientTemplate().delete(SQLMAP_SPACE + ".getBytes());
                    outputStream.write("\"".getBytes());
                    outputStream.write("deleteBy".getBytes());
                    outputStream.write(   DbUtils.methodName(dbPrimaryKeyData.getColName()).getBytes());
                    outputStream.write("\",".getBytes());
                    outputStream.write(DbUtils.toLowFristChar(tableConfiguration.getDomainObjectName()).getBytes());
                    outputStream.write("); \n".getBytes());
                    outputStream.write("     }".getBytes());
                    outputStream.write("\n".getBytes());
                    outputStream.write("\n".getBytes());
                }
                outputStream.write("}".getBytes());

            }
        }
    }
}
