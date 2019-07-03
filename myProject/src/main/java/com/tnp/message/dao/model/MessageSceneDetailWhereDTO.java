package com.tnp.message.dao.model;


import java.util.Date;
import com.tnp.share.common.utils.db.PaginationQueryParam;


public class MessageSceneDetailWhereDTO extends PaginationQueryParam {

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