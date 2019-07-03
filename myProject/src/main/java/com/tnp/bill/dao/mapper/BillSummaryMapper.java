package com.tnp.bill.dao.mapper;


import com.tnp.bill.dao.model.BillSummary;
import java.util.List;

public interface BillSummaryMapper {

     public int insert(BillSummary billSummary);

     public List<BillSummary> listByCondition(BillSummary billSummary);

     public List<BillSummary> pageByCondition(BillSummaryWhereDTO billSummaryWhereDTO);

     public int  countByCondition(BillSummaryWhereDTO billSummaryWhereDTO);

     public int updateByUserIdAndBalanceDate(BillSummary billSummary);

     public BillSummary queryByUserIdAndBalanceDate(BillSummary billSummary);

     public int deleteByUserIdAndBalanceDate(BillSummary billSummary);

     public int updateById(BillSummary billSummary);

     public BillSummary queryById(BillSummary billSummary);

     public int deleteById(BillSummary billSummary);

}