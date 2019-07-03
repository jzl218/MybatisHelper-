package com.tnp.message.dao.mapper;


import com.tnp.message.dao.model.MessageTemplate;
import java.util.List;

public interface MessageTemplateMapper {

     public int insert(MessageTemplate messageTemplate);

     public List<MessageTemplate> listByCondition(MessageTemplate messageTemplate);

     public List<MessageTemplate> pageByCondition(MessageTemplateWhereDTO messageTemplateWhereDTO);

     public int  countByCondition(MessageTemplateWhereDTO messageTemplateWhereDTO);

     public int updateByIdAndId(MessageTemplate messageTemplate);

     public MessageTemplate queryByIdAndId(MessageTemplate messageTemplate);

     public int deleteByIdAndId(MessageTemplate messageTemplate);

}