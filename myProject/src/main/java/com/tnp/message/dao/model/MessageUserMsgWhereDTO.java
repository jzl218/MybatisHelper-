package com.tnp.message.dao.model;


import java.util.Date;
import com.tnp.share.common.utils.db.PaginationQueryParam;


public class MessageUserMsgWhereDTO extends PaginationQueryParam {

    /**
    *主键
    */
    private  String id;

    /**
    *用户类型 管理员:MANAGER,操作员:OPERATOR，手机：PHONE
    */
    private  String userType;

    /**
    *用户ID
    */
    private  String userId;

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
    *消息标题
    */
    private  String messageTitle;

    /**
    *消息内容
    */
    private  String messageContent;

    /**
    *是否已读：Y/N/NA
    */
    private  String isRead;

    /**
    *INIT/SUCCESS/FAIL/CANCEL
    */
    private  String deliveryResult;

    /**
    *发送时间
    */
    private  Date sendTime;

    /**
    *发送方式：
            实时发送：REALTIME
            定时发送：FIXED_TIME
    */
    private  String sendType;

    /**
    *发送条件
    */
    private  String sendCondition;

    /**
    *卖家：SELLER,服务商：FACILITATOR
    */
    private  String smsPlatform;

    /**
    *创建时间
    */
    private  Date createTime;

    /**
    *更新时间
    */
    private  Date modifyTime;

    /**
    *渠道标识
    */
    private  String channelId;

    /**
    *渠道失败原因
    */
    private  String failureReason;

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

    public  String getUserType() {
     return userType;    
   }

    public void  setUserType(String userType) {
     this.userType=userType;    
    }

    public  String getUserId() {
     return userId;    
   }

    public void  setUserId(String userId) {
     this.userId=userId;    
    }

    public  String getMessageType() {
     return messageType;    
   }

    public void  setMessageType(String messageType) {
     this.messageType=messageType;    
    }

    public  String getMessageTitle() {
     return messageTitle;    
   }

    public void  setMessageTitle(String messageTitle) {
     this.messageTitle=messageTitle;    
    }

    public  String getMessageContent() {
     return messageContent;    
   }

    public void  setMessageContent(String messageContent) {
     this.messageContent=messageContent;    
    }

    public  String getIsRead() {
     return isRead;    
   }

    public void  setIsRead(String isRead) {
     this.isRead=isRead;    
    }

    public  String getDeliveryResult() {
     return deliveryResult;    
   }

    public void  setDeliveryResult(String deliveryResult) {
     this.deliveryResult=deliveryResult;    
    }

    public  Date getSendTime() {
     return sendTime;    
   }

    public void  setSendTime(Date sendTime) {
     this.sendTime=sendTime;    
    }

    public  String getSendType() {
     return sendType;    
   }

    public void  setSendType(String sendType) {
     this.sendType=sendType;    
    }

    public  String getSendCondition() {
     return sendCondition;    
   }

    public void  setSendCondition(String sendCondition) {
     this.sendCondition=sendCondition;    
    }

    public  String getSmsPlatform() {
     return smsPlatform;    
   }

    public void  setSmsPlatform(String smsPlatform) {
     this.smsPlatform=smsPlatform;    
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

    public  String getChannelId() {
     return channelId;    
   }

    public void  setChannelId(String channelId) {
     this.channelId=channelId;    
    }

    public  String getFailureReason() {
     return failureReason;    
   }

    public void  setFailureReason(String failureReason) {
     this.failureReason=failureReason;    
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