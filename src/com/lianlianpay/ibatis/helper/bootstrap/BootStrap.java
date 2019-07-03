package com.lianlianpay.ibatis.helper.bootstrap;

import com.lianlianpay.ibatis.helper.config.ConfigLoader;
import com.lianlianpay.ibatis.helper.config.domain.IbatorConfiguration;
import com.lianlianpay.ibatis.helper.core.BeanContainer;
import com.lianlianpay.ibatis.helper.core.template.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 启动类
 * Created by chenrs on 2017/11/14.
 */
public class BootStrap {


    public static void main(String[] args) throws SQLException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException {
        try {

            //初始化bean
            BeanContainer.init();
            //加载配置
            ConfigLoader.load();
            //创建连接
            ConfigLoader.initConnection();
            //创建domain
            createDataObject();
            //创建分页查询Dto
            createPageSearchDtoObject();
            //创建dao接口
            createDaoInterface();
            //创建sql实现
            createSqlMap();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            distory();
        }

    }

    private static void createPageSearchDtoObject() throws IOException {
        PageSeachTemplateCreateService pageSeachTemplateCreateService = BeanContainer.getBean(PageSeachTemplateCreateService.class);
        pageSeachTemplateCreateService.createPageSeachObject();
    }

    private static void createSqlMap() throws IOException {
        SqlmapTemplateCreateService sqlmapTemplateCreateService = BeanContainer.getBean(SqlmapTemplateCreateService.class);
        sqlmapTemplateCreateService.createSqlMap();
    }

    private static void createDao() throws IOException {
        DaoTemplateRealizationCreateService daoTemplateRealizationCreateService = BeanContainer.getBean(DaoTemplateRealizationCreateService.class);
        daoTemplateRealizationCreateService.createDaoInterfaceRealization();
    }

    private static void createDaoInterface() throws IOException {
        DaoTemplateCreateService daoTemplateCreateService = BeanContainer.getBean(DaoTemplateCreateService.class);
        daoTemplateCreateService.createDaoInterface();
    }

    private static void createDataObject() throws IOException {
        DomainTemplateCreateService domainTemplateCreateService = BeanContainer.getBean(DomainTemplateCreateService.class);
        domainTemplateCreateService.createDataObject();
    }

    private static void distory() throws SQLException {
        for (IbatorConfiguration IbatorConfiguration : ConfigLoader.ibatorConfigurationMap.values()) {
            IbatorConfiguration.getConnection().close();
        }

    }


}
