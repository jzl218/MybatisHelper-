package com.tnp.message.dao.mapper;


import com.tnp.message.dao.model.MessageSceneDetail;
import java.util.List;

public interface MessageSceneDetailMapper {

     public int insert(MessageSceneDetail messageSceneDetail);

     public List<MessageSceneDetail> listByCondition(MessageSceneDetail messageSceneDetail);

     public List<MessageSceneDetail> pageByCondition(MessageSceneDetailWhereDTO messageSceneDetailWhereDTO);

     public int  countByCondition(MessageSceneDetailWhereDTO messageSceneDetailWhereDTO);

     public int updateByIdAndId(MessageSceneDetail messageSceneDetail);

     public MessageSceneDetail queryByIdAndId(MessageSceneDetail messageSceneDetail);

     public int deleteByIdAndId(MessageSceneDetail messageSceneDetail);

}