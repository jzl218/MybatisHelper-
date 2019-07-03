package com.lianlianpay.ibatis.helper.core.template;

import com.lianlianpay.ibatis.helper.config.ConfigLoader;
import com.lianlianpay.ibatis.helper.config.domain.IbatorConfiguration;
import com.lianlianpay.ibatis.helper.config.domain.TableConfiguration;
import com.lianlianpay.ibatis.helper.core.template.works.sqlmap.SqlmapProducer;

import java.io.IOException;

/**
 * Created by chenrs on 2017/11/17.
 */
public class SqlmapTemplateCreateServiceImpl implements SqlmapTemplateCreateService {
    @Override
    public void createSqlMap() throws IOException {
        for (String key : ConfigLoader.ibatorConfigurationMap.keySet()) {
            IbatorConfiguration ibatorConfiguration = ConfigLoader.ibatorConfigurationMap.get(key);

            for (TableConfiguration tableConfiguration : ibatorConfiguration.getTableConfiguration()) {
                ibatorConfiguration.getSqlMapGenerator().mkdirs();
                SqlmapProducer sqlmapProducer = new SqlmapProducer(tableConfiguration, ibatorConfiguration);
                sqlmapProducer.doSqlmap();
            }
        }
    }

}
