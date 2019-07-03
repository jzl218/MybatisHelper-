package com.tnp.bill.dao.model;


import java.util.Date;
import com.tnp.share.common.utils.db.PaginationQueryParam;


public class BillSummaryWhereDTO extends PaginationQueryParam {

    /**
    *主键
    */
    private  String id;

    /**
    *用户id
    */
    private  String userId;

    /**
    *结算日期
    */
    private  String balanceDate;

    /**
    *收入
    */
    private  Long income;

    /**
    *支出
    */
    private  String expense;

    /**
    *余额
    */
    private  Long balance;

    /**
    *创建时间
    */
    private  Date createTime;

    /**
    *更新时间
    */
    private  Date modifyTime;

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

    public  String getUserId() {
     return userId;    
   }

    public void  setUserId(String userId) {
     this.userId=userId;    
    }

    public  String getBalanceDate() {
     return balanceDate;    
   }

    public void  setBalanceDate(String balanceDate) {
     this.balanceDate=balanceDate;    
    }

    public  Long getIncome() {
     return income;    
   }

    public void  setIncome(Long income) {
     this.income=income;    
    }

    public  String getExpense() {
     return expense;    
   }

    public void  setExpense(String expense) {
     this.expense=expense;    
    }

    public  Long getBalance() {
     return balance;    
   }

    public void  setBalance(Long balance) {
     this.balance=balance;    
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