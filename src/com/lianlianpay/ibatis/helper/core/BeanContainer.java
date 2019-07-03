package com.lianlianpay.ibatis.helper.core;

import com.lianlianpay.ibatis.helper.core.column.ColumnQueryService;
import com.lianlianpay.ibatis.helper.core.constraint.ConstraintService;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenrs on 2017/11/16.
 */
public class BeanContainer {

    public final static Map<String, Map<String, Object>> beanMap = new HashMap<String, Map<String, Object>>();

    public static void init() throws IOException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        File configPath = new File(Object.class.getResource("/config/").getFile());
        File[] interfaceList = configPath.listFiles();
        for (File temp : interfaceList) {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(temp)));
            String line = null;
            Map<String, Object> interfaceMap = new HashMap<String, Object>();
            while (null != (line = br.readLine())) {
                String[] data = line.split("=");
                Class classType = Class.forName(data[1]);
                Object obj = classType.newInstance();
                interfaceMap.put(data[0], obj);
            }
            beanMap.put(temp.getName(), interfaceMap);
        }
    }

    public static <T> T getBean(String key, Class T) {
        return (T) beanMap.get(T.getName()).get(key);
    }

    public static <T> T getBean( Class T) {
        return (T) beanMap.get(T.getName()).get("default");
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        BeanContainer.init();
        ColumnQueryService columnQueryService = BeanContainer.getBean("default", ColumnQueryService.class);
        System.out.println(columnQueryService);

    }
}
