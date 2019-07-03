package com.tnp.message.dao.model;


import java.util.Date;
import com.tnp.share.common.utils.db.PaginationQueryParam;


public class MessageTemplateWhereDTO extends PaginationQueryParam {

    /**
    *主键
    */
    private  String id;

    /**
    *模板名称
    */
    private  String templateName;

    /**
    *使用状态 待使用：UNUSED 已使用：USED
    */
    private  String status;

    /**
    *消息类型：
            微信：WECHAT
            站内信：MAIL
            短信：SMS
            邮件：EMAIL
            电话：PHONE
    */
    private  String messageType;

    /**
    *消息正文
    */
    private  String messageContent;

    /**
    *消息标题
    */
    private  String messageTitle;

    /**
    *场景类型
    */
    private  String sceneType;

    /**
    *关联场景数
    */
    private  Integer relatedSceneNum;

    /**
    *创建时间
    */
    private  Date createTime;

    /**
    *更新时间
    */
    private  Date modifyTime;

    /**
    *Y/N
    */
    private  String canEdit;

    /**
    *0;未删除，ID：已删除
    */
    private  String deleteFlag;

    /**
    *开始时间
    */
    private  Date startDate;

    /**
    *结束时间
    */
    private  Date endDate;

    public  String getId() {
     return id;    
   }

    public void  setId(String id) {
     this.id=id;    
    }

    public  String getTemplateName() {
     return templateName;    
   }

    public void  setTemplateName(String templateName) {
     this.templateName=templateName;    
    }

    public  String getStatus() {
     return status;    
   }

    public void  setStatus(String status) {
     this.status=status;    
    }

    public  String getMessageType() {
     return messageType;    
   }

    public void  setMessageType(String messageType) {
     this.messageType=messageType;    
    }

    public  String getMessageContent() {
     return messageContent;    
   }

    public void  setMessageContent(String messageContent) {
     this.messageContent=messageContent;    
    }

    public  String getMessageTitle() {
     return messageTitle;    
   }

    public void  setMessageTitle(String messageTitle) {
     this.messageTitle=messageTitle;    
    }

    public  String getSceneType() {
     return sceneType;    
   }

    public void  setSceneType(String sceneType) {
     this.sceneType=sceneType;    
    }

    public  Integer getRelatedSceneNum() {
     return relatedSceneNum;    
   }

    public void  setRelatedSceneNum(Integer relatedSceneNum) {
     this.relatedSceneNum=relatedSceneNum;    
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

    public  String getCanEdit() {
     return canEdit;    
   }

    public void  setCanEdit(String canEdit) {
     this.canEdit=canEdit;    
    }

    public  String getDeleteFlag() {
     return deleteFlag;    
   }

    public void  setDeleteFlag(String deleteFlag) {
     this.deleteFlag=deleteFlag;    
    }

    public  Date getStartDate() {
     return startDate;    
   }

    public void  setStartDate(Date startDate) {
     this.startDate=startDate;    
    }

    public  Date getEndDate() {
     return endDate;    
   }

    public void  setEndDate(Date endDate) {
     this.endDate=endDate;    
    }

}