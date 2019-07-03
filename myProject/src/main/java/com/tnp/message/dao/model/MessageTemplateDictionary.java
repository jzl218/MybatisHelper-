package com.tnp.message.dao.model;


import java.util.Date;


public class MessageTemplateDictionary {

    /**
    *主键
    */
    private  String id;

    /**
    *模板变量
    */
    private  String templateKey;

    /**
    *模板变量中文
    */
    private  String templateChineseKey;

    /**
    *备注
    */
    private  String memo;

    /**
    *创建时间
    */
    private  Date createTime;

    /**
    *更新时间
    */
    private  Date modifyTime;

    /**
    *场景类型
    */
    private  String sceneType;

    public  String getId() {
     return id;    
   }

    public void  setId(String id) {
     this.id=id;    
    }

    public  String getTemplateKey() {
     return templateKey;    
   }

    public void  setTemplateKey(String templateKey) {
     this.templateKey=templateKey;    
    }

    public  String getTemplateChineseKey() {
     return templateChineseKey;    
   }

    public void  setTemplateChineseKey(String templateChineseKey) {
     this.templateChineseKey=templateChineseKey;    
    }

    public  String getMemo() {
     return memo;    
   }

    public void  setMemo(String memo) {
     this.memo=memo;    
    }

    public  Date getCreateTime() {
     return createTime;    
   }

    public void  setCreateTime(Date createTime) {
     this.createTime=createTime;    
    }

    public  Date getModifyTime() {
     return modifyTime;    
   }

    public void  setModifyTime(Date modifyTime) {
     this.modifyTime=modifyTime;    
    }

    public  String getSceneType() {
     return sceneType;    
   }

    public void  setSceneType(String sceneType) {
     this.sceneType=sceneType;    
    }

}