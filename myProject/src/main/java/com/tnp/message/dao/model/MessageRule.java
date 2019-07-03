package com.tnp.message.dao.model;


import java.util.Date;


public class MessageRule {

    /**
    *主键
    */
    private  String id;

    /**
    *消息发送条件
    */
    private  String msgSendConditon;

    /**
    *条件值配置项
    */
    private  String conditionValueConfig;

    /**
    *场景类型
    */
    private  String sceneType;

    /**
    *专属消息模板
            卖家：SELLER,服务商：FACILITATOR 通用:COMMON
    */
    private  String msgType;

    /**
    *备注
    */
    private  String memo;

    /**
    *短信发送对象
            卖家管理员:seller
            服务商管理员:facilitator
            发起用户(不分角色):initiate_user
    */
    private  String smsSendTarget;

    /**
    *站内信发送对象卖家管理员:seller
            服务商管理员:facilitator
            发起平台(不分角色):initiate_platform
    */
    private  String mailSendTarget;

    /**
    *站内信发送角色
            管理员:manager
            操作员:operator
            管理员和操作员:manager_operator
            发起用户:initiate_user
    */
    private  String mailSendRole;

    /**
    *按|分隔 SMS|MAIL
    */
    private  String channel;

    /**
    *创建时间
    */
    private  Date createTime;

    /**
    *更新时间
    */
    private  Date modifyTime;

    /**
    *外部确认接口
    */
    private  String confirmUrl;

    public  String getId() {
     return id;    
   }

    public void  setId(String id) {
     this.id=id;    
    }

    public  String getMsgSendConditon() {
     return msgSendConditon;    
   }

    public void  setMsgSendConditon(String msgSendConditon) {
     this.msgSendConditon=msgSendConditon;    
    }

    public  String getConditionValueConfig() {
     return conditionValueConfig;    
   }

    public void  setConditionValueConfig(String conditionValueConfig) {
     this.conditionValueConfig=conditionValueConfig;    
    }

    public  String getSceneType() {
     return sceneType;    
   }

    public void  setSceneType(String sceneType) {
     this.sceneType=sceneType;    
    }

    public  String getMsgType() {
     return msgType;    
   }

    public void  setMsgType(String msgType) {
     this.msgType=msgType;    
    }

    public  String getMemo() {
     return memo;    
   }

    public void  setMemo(String memo) {
     this.memo=memo;    
    }

    public  String getSmsSendTarget() {
     return smsSendTarget;    
   }

    public void  setSmsSendTarget(String smsSendTarget) {
     this.smsSendTarget=smsSendTarget;    
    }

    public  String getMailSendTarget() {
     return mailSendTarget;    
   }

    public void  setMailSendTarget(String mailSendTarget) {
     this.mailSendTarget=mailSendTarget;    
    }

    public  String getMailSendRole() {
     return mailSendRole;    
   }

    public void  setMailSendRole(String mailSendRole) {
     this.mailSendRole=mailSendRole;    
    }

    public  String getChannel() {
     return channel;    
   }

    public void  setChannel(String channel) {
     this.channel=channel;    
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

    public  String getConfirmUrl() {
     return confirmUrl;    
   }

    public void  setConfirmUrl(String confirmUrl) {
     this.confirmUrl=confirmUrl;    
    }

}