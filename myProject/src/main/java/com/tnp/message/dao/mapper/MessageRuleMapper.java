package com.tnp.message.dao.mapper;


import com.tnp.message.dao.model.MessageRule;
import java.util.List;

public interface MessageRuleMapper {

     public int insert(MessageRule messageRule);

     public List<MessageRule> listByCondition(MessageRule messageRule);

     public List<MessageRule> pageByCondition(MessageRuleWhereDTO messageRuleWhereDTO);

     public int  countByCondition(MessageRuleWhereDTO messageRuleWhereDTO);

     public int updateByIdAndId(MessageRule messageRule);

     public MessageRule queryByIdAndId(MessageRule messageRule);

     public int deleteByIdAndId(MessageRule messageRule);

}