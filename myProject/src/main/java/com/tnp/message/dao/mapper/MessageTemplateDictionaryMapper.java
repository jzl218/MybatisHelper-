package com.tnp.message.dao.mapper;


import com.tnp.message.dao.model.MessageTemplateDictionary;
import java.util.List;

public interface MessageTemplateDictionaryMapper {

     public int insert(MessageTemplateDictionary messageTemplateDictionary);

     public List<MessageTemplateDictionary> listByCondition(MessageTemplateDictionary messageTemplateDictionary);

     public List<MessageTemplateDictionary> pageByCondition(MessageTemplateDictionaryWhereDTO messageTemplateDictionaryWhereDTO);

     public int  countByCondition(MessageTemplateDictionaryWhereDTO messageTemplateDictionaryWhereDTO);

     public int updateByIdAndId(MessageTemplateDictionary messageTemplateDictionary);

     public MessageTemplateDictionary queryByIdAndId(MessageTemplateDictionary messageTemplateDictionary);

     public int deleteByIdAndId(MessageTemplateDictionary messageTemplateDictionary);

}