package org.blog.dao;

import org.blog.entity.Message;

import java.util.List;
import java.util.Map;

public interface MessageDao {

    //查看用户的所有留言
    public List<Message> checkUserMessage(int message_user_id, int page, int count);

    //获得所有留言条数
    public int getAllCount(int message_user_id);

    //添加留言
    public Message AddMessage(Message message);

    //删除留言
    public void deleteMessage(int message_id);

    //回复留言(间接的添加留言)
    public void replyMessage(Message message);

    //查询所有的留言id（不包含评论）
//    public List<Message> checkUserMessage(int message_user_id);
    public List<Integer> checkUserMessage(int message_user_id);

    //根据留言id显示对应的评论信息
    public List<Message> showReply(int message_id);

}
