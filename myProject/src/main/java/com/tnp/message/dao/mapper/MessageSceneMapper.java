package com.tnp.message.dao.mapper;


import com.tnp.message.dao.model.MessageScene;
import java.util.List;

public interface MessageSceneMapper {

     public int insert(MessageScene messageScene);

     public List<MessageScene> listByCondition(MessageScene messageScene);

     public List<MessageScene> pageByCondition(MessageSceneWhereDTO messageSceneWhereDTO);

     public int  countByCondition(MessageSceneWhereDTO messageSceneWhereDTO);

     public int updateByIdAndId(MessageScene messageScene);

     public MessageScene queryByIdAndId(MessageScene messageScene);

     public int deleteByIdAndId(MessageScene messageScene);

}