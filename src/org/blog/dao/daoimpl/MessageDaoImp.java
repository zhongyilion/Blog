package org.blog.dao.daoimpl;

import org.blog.DButil.BaseDB;
import org.blog.dao.MessageDao;
import org.blog.dao.UserDao;
import org.blog.entity.Message;
import org.blog.entity.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MessageDaoImp extends BaseDB implements MessageDao {
     UserDao userDao=new UserDaoImp();
    JdbcTemplate jdbcTemplate = jdbcTemplate();

    /**
     * 查看留言
     * @param message_user_id
     * @param page
     * @param count
     * @return
     */
    @Override
    public List<Message> checkUserMessage(int message_user_id, int page, int count) {
        System.out.println("page:"+page);
        String sql ="select * from message  where  message_user_id=? and reply_id=0 order by message_time desc limit "+(page-1)*count+","+count;
        String s="select user_id  from message where message_id = ?";
        List<Message> messages=null;
        try {
            messages=jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Message.class),message_user_id);
            for(Message m:messages){
                Integer integer = jdbcTemplate.queryForObject(s, Integer.class, m.getMessage_id());
                m.setUser(userDao.queryById(integer.intValue()));
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
//        System.out.println(messages);
        return messages;
}

    /**
     *  获得所有留言条数
     * @param message_user_id
     * @return
     */
    @Override
    public int getAllCount(int message_user_id) {
        System.out.println("id:"+message_user_id);
        int result=0;
        String sql="select count(message_user_id) from message where message_user_id="+message_user_id+"  and reply_id=0";
        result=jdbcTemplate.queryForObject(sql,Integer.class);
//        System.out.println("共有记录："+result);
        return result;
    }

    /**
     * 添加留言
     * @return
     */
    @Override
    public Message AddMessage(Message message) {
        System.out.println("mess:"+message.toString());
        String sql = "insert into message values(null,?,?,?,?,0,0);";
        jdbcTemplate.update(sql,message.getMessage_user_id(),message.getUser().getUserId(),message.getMessage_contents(),message.getMessage_time());
        return message;

    }

    /**
     * 删除留言
     * @param message_id
     * @return
     */
    @Override
    public void deleteMessage(int message_id) {
        String sql="delete from message where message_id=?";
        jdbcTemplate.update(sql,message_id);
    }

    /**
     * 回复留言
     * @param message
     */
    @Override
    public void replyMessage(Message message) {
        String sql="insert into message values(null,?,?,?,?,0,?);";
        jdbcTemplate.update(sql,message.getMessage_user_id(),message.getUser().getUserId(),message.getMessage_contents(),message.getMessage_time(),message.getReply_id());

    }

    /**
     * 查询所有的留言id（不包含评论）
     * @return
     */
    @Override
//    public List<Message> checkUserMessage(int message_user_id) {
//        String sql ="select * from message  where  message_user_id=? and reply_id=0";
//        String s="select user_id  from message where message_id = ?";
//        List<Message> messages=null;
//        try {
//            messages=jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Message.class),message_user_id);
//            for(Message m:messages){
//                Integer integer = jdbcTemplate.queryForObject(s, Integer.class, m.getMessage_id());
//                m.setUser(userDao.queryById(integer.intValue()));
//            }
//        } catch (DataAccessException e) {
//            e.printStackTrace();
//        }
////        System.out.println(messages);
//        return messages;
//    }

    public List<Integer> checkUserMessage(int message_user_id) {
        String sql ="select message_id from message  where  message_user_id=? and reply_id=0";
        List<Integer> messagesId=null;
        try {
            messagesId=jdbcTemplate.queryForList(sql,Integer.class,message_user_id);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
//        System.out.println(messages);
        return messagesId;
    }

    /**
     * 根据留言id显示对应的评论信息
     * @param message_id
     * @return
     */
    @Override
    public List<Message> showReply(int message_id) {
        String sql = "select * from message where reply_id = ? order by message_time";
        String s="select user_id  from message where message_id = ?";
        List<Message> message=null;
        try {
            message = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Message.class), message_id);
            for(Message m:message){
                Integer integer = jdbcTemplate.queryForObject(s, Integer.class, m.getMessage_id());
                m.setUser(userDao.queryById(integer.intValue()));
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
//        System.out.println("该留言id:"+message_id+"的评论为："+message);
        return message;
    }


}
