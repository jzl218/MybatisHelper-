package com.tnp.message.dao.model;


import java.util.Date;


public class MessageScene {

    /**
    *主键
    */
    private  String id;

    /**
    *场景名称
    */
    private  String name;

    /**
    *场景类型
    */
    private  String sceneType;

    /**
    *场景条件
    */
    private  String sceneCondition;

    /**
    *场景条件值
    */
    private  String sceneConditionValue;

    /**
    *创建时间
    */
    private  Date createTime;

    /**
    *更新时间
    */
    private  Date modifyTime;

    /**
    *发送对象 
    */
    private  String sendTarget;

    /**
    *Y/N
    */
    private  String canEdit;

    /**
    *状态,启用enable，停用disable
    */
    private  String status;

    public  String getId() {
     return id;    
   }

    public void  setId(String id) {
     this.id=id;    
    }

    public  String getName() {
     return name;    
   }

    public void  setName(String name) {
     this.name=name;    
    }

    public  String getSceneType() {
     return sceneType;    
   }

    public void  setSceneType(String sceneType) {
     this.sceneType=sceneType;    
    }

    public  String getSceneCondition() {
     return sceneCondition;    
   }

    public void  setSceneCondition(String sceneCondition) {
     this.sceneCondition=sceneCondition;    
    }

    public  String getSceneConditionValue() {
     return sceneConditionValue;    
   }

    public void  setSceneConditionValue(String sceneConditionValue) {
     this.sceneConditionValue=sceneConditionValue;    
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

    public  String getSendTarget() {
     return sendTarget;    
   }

    public void  setSendTarget(String sendTarget) {
     this.sendTarget=sendTarget;    
    }

    public  String getCanEdit() {
     return canEdit;    
   }

    public void  setCanEdit(String canEdit) {
     this.canEdit=canEdit;    
    }

    public  String getStatus() {
     return status;    
   }

    public void  setStatus(String status) {
     this.status=status;    
    }

}