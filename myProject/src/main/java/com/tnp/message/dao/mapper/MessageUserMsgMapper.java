package com.tnp.message.dao.mapper;


import com.tnp.message.dao.model.MessageUserMsg;
import java.util.List;

public interface MessageUserMsgMapper {

     public int insert(MessageUserMsg messageUserMsg);

     public List<MessageUserMsg> listByCondition(MessageUserMsg messageUserMsg);

     public List<MessageUserMsg> pageByCondition(MessageUserMsgWhereDTO messageUserMsgWhereDTO);

     public int  countByCondition(MessageUserMsgWhereDTO messageUserMsgWhereDTO);

     public int updateByIdAndId(MessageUserMsg messageUserMsg);

     public MessageUserMsg queryByIdAndId(MessageUserMsg messageUserMsg);

     public int deleteByIdAndId(MessageUserMsg messageUserMsg);

}