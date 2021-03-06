package com.tnp.message.dao.model;


import java.util.Date;


public class MessageSceneDetail {

    /**
    *主键
    */
    private  String id;

    /**
    *场景ID
    */
    private  String sceneId;

    /**
    *消息模板ID
    */
    private  String messageTemplateId;

    /**
    *创建时间
    */
    private  Date createTime;

    /**
    *更新时间
    */
    private  Date modifyTime;

    /**
    *0;未删除，ID：已删除
    */
    private  String deleteFlag;

    public  String getId() {
     return id;    
   }

    public void  setId(String id) {
     this.id=id;    
    }

    public  String getSceneId() {
     return sceneId;    
   }

    public void  setSceneId(String sceneId) {
     this.sceneId=sceneId;    
    }

    public  String getMessageTemplateId() {
     return messageTemplateId;    
   }

    public void  setMessageTemplateId(String messageTemplateId) {
     this.messageTemplateId=messageTemplateId;    
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

    public  String getDeleteFlag() {
     return deleteFlag;    
   }

    public void  setDeleteFlag(String deleteFlag) {
     this.deleteFlag=deleteFlag;    
    }

}