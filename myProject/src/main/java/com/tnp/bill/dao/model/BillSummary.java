package com.tnp.bill.dao.model;


import java.util.Date;


public class BillSummary {

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

}