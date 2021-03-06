package com.lianlianpay.ibatis.helper.config.domain;

import java.io.File;

/**
 * Created by chenrs on 2017/9/29.
 */
public class JavaModelGenerator {

    private String targetPackage;

    private String targetProject;

    public void mkdirs() {
        File file = new File(targetProject + "/src/main/java/" + targetPackage.replaceAll("\\.","/"));
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public String getPath(){
      return targetProject + "/src/main/java/" + targetPackage.replaceAll("\\.","/") +"/";
    }

    public String getTargetPackage() {
        return targetPackage;
    }

    public void setTargetPackage(String targetPackage) {
        this.targetPackage = targetPackage;
    }

    public String getTargetProject() {
        return targetProject;
    }

    public void setTargetProject(String targetProject) {
        this.targetProject = targetProject;
    }
}
