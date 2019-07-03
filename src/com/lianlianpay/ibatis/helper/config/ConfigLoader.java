package com.lianlianpay.ibatis.helper.config;

import com.lianlianpay.ibatis.helper.config.constant.Constant;
import com.lianlianpay.ibatis.helper.config.domain.*;
import oracle.jdbc.driver.OracleConnection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UPDATE mysql.user SET authentication_string=PASSWORD(‘root') WHERE User='root';
 * <p>
 * Created by chenrs on 2017/9/29.
 */
public class ConfigLoader {


    public static Map<String, IbatorConfiguration> ibatorConfigurationMap = new HashMap<String, IbatorConfiguration>();

    //加载驱动类
    public static final List<String> driverClass = new ArrayList<String>();

    /**
     * 加载
     */
    public static void load() throws IOException, ParserConfigurationException, SAXException {

        InputStream is = Object.class.getResourceAsStream("/ibatorConfig.xml");
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
        Element element = doc.getDocumentElement();//
        NodeList childNodes = element.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);

            if (isDiverClass(node.getNodeName())) {
                driverClass.add(node.getAttributes().getNamedItem("location").getNodeValue());

            } else if (isIbatorContext(node.getNodeName())) {
                IbatorConfiguration configuration = new IbatorConfiguration(node.getAttributes().getNamedItem("id").getNodeValue());
                NodeList configurationNodeList = node.getChildNodes();
                for (int e = 0; e < configurationNodeList.getLength(); e++) {
                    Node confiurationNode = configurationNodeList.item(e);

                    if (isJdbcConnection(confiurationNode.getNodeName())) {
                        JdbcConnection jdbcConnection = resolveJdbcConnection(confiurationNode);
                        configuration.setJdbcConnection(jdbcConnection);
                    } else if (isJavaModelGenerator(confiurationNode.getNodeName())) {
                        JavaModelGenerator javaModelGenerator = resolveJavaModelGenerator(confiurationNode);
                        configuration.setJavaModelGenerator(javaModelGenerator);
                    } else if (isSqlMapGenerator(confiurationNode.getNodeName())) {
                        SqlMapGenerator sqlMapGenerator = resolveSqlMapGenerator(confiurationNode);
                        configuration.setSqlMapGenerator(sqlMapGenerator);
                    } else if (isDaoGenerator(confiurationNode.getNodeName())) {
                        DaoGenerator daoGenerator = resolveDaoGenerator(confiurationNode);
                        configuration.setDaoGenerator(daoGenerator);
                    } else if (isTable(confiurationNode.getNodeName())) {
                        TableConfiguration tableConfiguration = resolveTableConfiguration(confiurationNode);
                        configuration.getTableConfiguration().add(tableConfiguration);
                    } else if (isCreateTimeColumn(confiurationNode.getNodeName())) {
                        configuration.setCreateTimeColumn(confiurationNode.getAttributes().getNamedItem("column").getNodeValue());
                    } else if (isModifyTimeColumn(confiurationNode.getNodeName())) {
                        configuration.setModifyTimeColumn(confiurationNode.getAttributes().getNamedItem("column").getNodeValue());
                    } else if (isWhereDtoNodePath(confiurationNode.getNodeName())) {
                        WhereDtoGenerator whereDtoGenerator = resolveWhereDtoGenerator(confiurationNode);
                        configuration.setWhereDtoGenerator(whereDtoGenerator);
                    }
                }
                ibatorConfigurationMap.put(configuration.getId(), configuration);
            }


        }

    }

    private static WhereDtoGenerator resolveWhereDtoGenerator(Node confiurationNode) {
        WhereDtoGenerator whereDtoGenerator = new WhereDtoGenerator();
        whereDtoGenerator.setTargetProject(confiurationNode.getAttributes().getNamedItem("targetProject").getNodeValue());
        whereDtoGenerator.setTargetPackage(confiurationNode.getAttributes().getNamedItem("targetPackage").getNodeValue());
        return whereDtoGenerator;
    }

    private static boolean isWhereDtoNodePath(String nodeName) {
        return Constant.WHEREDTO_GENERATOR.equals(nodeName);
    }

    private static boolean isModifyTimeColumn(String nodeName) {
        return Constant.MODIFY_TIME_COLUMN.equals(nodeName);
    }

    private static boolean isCreateTimeColumn(String nodeName) {
        return Constant.CREATE_TIME_COLUMN.equals(nodeName);
    }

    private static TableConfiguration resolveTableConfiguration(Node confiurationNode) {
        TableConfiguration tableConfiguration = new TableConfiguration();
        tableConfiguration.setDomainObjectName(confiurationNode.getAttributes().getNamedItem("domainObjectName").getNodeValue());
        tableConfiguration.setSchema(confiurationNode.getAttributes().getNamedItem("schema").getNodeValue());
        tableConfiguration.setTableName(confiurationNode.getAttributes().getNamedItem("tableName").getNodeValue());
        return tableConfiguration;
    }

    private static DaoGenerator resolveDaoGenerator(Node confiurationNode) {
        DaoGenerator daoGenerator = new DaoGenerator();
        daoGenerator.setTargetProject(confiurationNode.getAttributes().getNamedItem("targetProject").getNodeValue());
        daoGenerator.setTargetPackage(confiurationNode.getAttributes().getNamedItem("targetPackage").getNodeValue());
        return daoGenerator;
    }

    private static SqlMapGenerator resolveSqlMapGenerator(Node confiurationNode) {
        SqlMapGenerator sqlMapGenerator = new SqlMapGenerator();
        sqlMapGenerator.setTargetProject(confiurationNode.getAttributes().getNamedItem("targetProject").getNodeValue());
        sqlMapGenerator.setTargetPackage(confiurationNode.getAttributes().getNamedItem("targetPackage").getNodeValue());
        return sqlMapGenerator;
    }

    private static JavaModelGenerator resolveJavaModelGenerator(Node confiurationNode) {
        JavaModelGenerator javaModelGenerator = new JavaModelGenerator();
        javaModelGenerator.setTargetProject(confiurationNode.getAttributes().getNamedItem("targetProject").getNodeValue());
        javaModelGenerator.setTargetPackage(confiurationNode.getAttributes().getNamedItem("targetPackage").getNodeValue());
        return javaModelGenerator;
    }

    private static JdbcConnection resolveJdbcConnection(Node confiurationNode) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        jdbcConnection.setConnectionURL(confiurationNode.getAttributes().getNamedItem("connectionURL").getNodeValue());
        jdbcConnection.setDriverClass(confiurationNode.getAttributes().getNamedItem("driverClass").getNodeValue());
        jdbcConnection.setPassword(confiurationNode.getAttributes().getNamedItem("password").getNodeValue());
        jdbcConnection.setUserId(confiurationNode.getAttributes().getNamedItem("userId").getNodeValue());
        return jdbcConnection;
    }

    private static boolean isTable(String nodeName) {
        return Constant.TABLE.equals(nodeName);
    }

    private static boolean isDaoGenerator(String nodeName) {
        return Constant.DAO_GENERATOR.equals(nodeName);
    }

    private static boolean isSqlMapGenerator(String nodeName) {
        return Constant.SQL_MAPGENERATOR.equals(nodeName);
    }

    private static boolean isJavaModelGenerator(String nodeName) {
        return Constant.JAVA_MODELGENERATOR.equals(nodeName);
    }

    private static boolean isJdbcConnection(String nodeName) {
        return Constant.JDBC_CONNECTION.equals(nodeName);
    }


    private static boolean isIbatorContext(String nodeName) {
        return Constant.IBATOR_CONTEXT.equals(nodeName);
    }

    private static boolean isDiverClass(String nodeName) {
        return Constant.CLASSPATH_ENTRY.equals(nodeName);
    }


    public static void initConnection() throws ClassNotFoundException, SQLException {



        for (String key : ConfigLoader.ibatorConfigurationMap.keySet()) {

            IbatorConfiguration ibatorConfiguration = ConfigLoader.ibatorConfigurationMap.get(key);

            JdbcConnection jdbcConnection = ibatorConfiguration.getJdbcConnection();
            //1.加载驱动程序
            Class.forName(jdbcConnection.getDriverClass());
            //2.获得数据库链接
            Connection conn = DriverManager.getConnection(jdbcConnection.getConnectionURL(), jdbcConnection.getUserId(), jdbcConnection.getPassword());

            if (conn instanceof OracleConnection) {//设置Oracle数据库的表注释可读
                ((OracleConnection) conn).setRemarksReporting(true);//设置连接属性,使得可获取到表的REMARK(备注)
            }

            ibatorConfiguration.setConnection(conn);
        }

    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
      ConfigLoader.load();
        System.out.println("hello");
    }

}
