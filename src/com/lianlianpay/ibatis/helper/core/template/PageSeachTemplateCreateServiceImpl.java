package com.lianlianpay.ibatis.helper.core.template;

import com.lianlianpay.ibatis.helper.config.ConfigLoader;
import com.lianlianpay.ibatis.helper.config.domain.IbatorConfiguration;
import com.lianlianpay.ibatis.helper.config.domain.TableConfiguration;
import com.lianlianpay.ibatis.helper.core.BeanContainer;
import com.lianlianpay.ibatis.helper.core.column.ColumnQueryService;
import com.lianlianpay.ibatis.helper.core.domain.DbData;
import com.lianlianpay.ibatis.helper.utils.DbUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

/**
 * Created by chenrs on 2017/11/19.
 */
public class PageSeachTemplateCreateServiceImpl implements PageSeachTemplateCreateService {

    @Override
    public void createPageSeachObject() throws IOException {
        for (String key : ConfigLoader.ibatorConfigurationMap.keySet()) {

            IbatorConfiguration ibatorConfiguration = ConfigLoader.ibatorConfigurationMap.get(key);

            Connection con = ibatorConfiguration.getConnection();

            for (TableConfiguration tableConfiguration : ibatorConfiguration.getTableConfiguration()) {
                ColumnQueryService columnQueryService = BeanContainer.getBean(ColumnQueryService.class);
                List<DbData> list = columnQueryService.getTableInfo(con, tableConfiguration.getTableName());
                ibatorConfiguration.getJavaModelGenerator().mkdirs();
                FileOutputStream outputStream = new FileOutputStream(ibatorConfiguration.getJavaModelGenerator().getPath() + tableConfiguration.getDomainObjectName() + "WhereDTO.java");

                //package
                outputStream.write("package".getBytes());
                outputStream.write(" ".getBytes());
                outputStream.write(ibatorConfiguration.getJavaModelGenerator().getTargetPackage().getBytes());
                outputStream.write(";".getBytes());
                outputStream.write("\n".getBytes());
                outputStream.write("\n".getBytes());
                outputStream.write("\n".getBytes());

                //import
                outputStream.write("import ".getBytes());
                outputStream.write("java.util.Date;\n".getBytes());
                outputStream.write("import com.tnp.share.common.utils.db.PaginationQueryParam;\n".getBytes());

                if (containBigdecimal(list)) {
                    outputStream.write("import ".getBytes());
                    outputStream.write("java.math.BigDecimal;\n".getBytes());
                }
                outputStream.write("\n".getBytes());
                outputStream.write("\n".getBytes());

                //class body
                outputStream.write("public class ".getBytes());
                outputStream.write(tableConfiguration.getDomainObjectName().getBytes());
                outputStream.write("WhereDTO extends PaginationQueryParam {".getBytes());
                outputStream.write("\n".getBytes());
                outputStream.write("\n".getBytes());

                DbData startDate = new DbData();
                startDate.setType("Date");
                startDate.setRemarks("开始时间");
                startDate.setColName("START_DATE");

                DbData endDate = new DbData();
                endDate.setType("Date");
                endDate.setRemarks("结束时间");
                endDate.setColName("END_DATE");

                list.add(startDate);
                list.add(endDate);

                for (DbData dbData : list) {
                    outputStream.write("    /**\n".getBytes());
                    outputStream.write("    *".getBytes());
                    outputStream.write(dbData.getRemarks().getBytes());
                    outputStream.write("\n".getBytes());
                    outputStream.write("    */".getBytes());
                    outputStream.write("\n".getBytes());
                    outputStream.write("    private  ".getBytes());
                    outputStream.write(dbData.getType().getBytes());
                    outputStream.write(" ".getBytes());
                    outputStream.write(DbUtils.formateJaveColName(dbData.getColName()).getBytes());
                    outputStream.write(";".getBytes());
                    outputStream.write("\n".getBytes());
                    outputStream.write("\n".getBytes());
                }

                //get set
                for (DbData dbData : list) {

                    //get
                    outputStream.write("    public  ".getBytes());
                    outputStream.write(dbData.getType().getBytes());
                    outputStream.write(" ".getBytes());
                    outputStream.write("get".getBytes());
                    outputStream.write(DbUtils.toUpperFristChar(DbUtils.formateJaveColName(dbData.getColName())).getBytes());
                    outputStream.write("() {".getBytes());
                    outputStream.write("\n".getBytes());
                    outputStream.write("     return ".getBytes());
                    outputStream.write(DbUtils.formateJaveColName(dbData.getColName()).getBytes());
                    outputStream.write(";".getBytes());
                    outputStream.write("    \n   }\n\n".getBytes());

                    //set
                    outputStream.write("    public void ".getBytes());
                    outputStream.write(" ".getBytes());
                    outputStream.write("set".getBytes());
                    outputStream.write(DbUtils.toUpperFristChar(DbUtils.formateJaveColName(dbData.getColName())).getBytes());
                    outputStream.write("(".getBytes());
                    outputStream.write(dbData.getType().getBytes());
                    outputStream.write(" ".getBytes());
                    outputStream.write(DbUtils.formateJaveColName(dbData.getColName()).getBytes());
                    outputStream.write(") {".getBytes());
                    outputStream.write("\n".getBytes());
                    outputStream.write("     this.".getBytes());
                    outputStream.write(DbUtils.formateJaveColName(dbData.getColName()).getBytes());
                    outputStream.write("=".getBytes());
                    outputStream.write(DbUtils.formateJaveColName(dbData.getColName()).getBytes());
                    outputStream.write(";".getBytes());
                    outputStream.write("    \n    }\n\n".getBytes());
                }

                outputStream.write("}".getBytes());
            }

        }
    }


    private boolean containBigdecimal(List<DbData> list) {
        for (DbData dbData : list) {
            if (dbData.getType().equalsIgnoreCase("BigDecimal")) {
                return true;
            }
        }
        return false;
    }
}
