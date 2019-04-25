package org.blog.service;

import org.blog.entity.Message;
import org.blog.entity.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface MessageService {

    //查看留言
    public List<Message> checkAllUserMessage(HttpServletRequest request, HttpServletResponse response);
    public List<Message> checkAllFriendMessage(HttpServletRequest request, HttpServletResponse response);


    //分页
    public Page messagePaging(int userid ,HttpServletRequest request, HttpServletResponse response);

    //获取当前时间
    public String getCurrentDate();

    //添加留言
    public void addOneMessage(HttpServletRequest request, HttpServletResponse response);
    
    //删除留言
    public void deleteOneMessage(HttpServletRequest request, HttpServletResponse response);

    //回复留言
    public void replyOneMessage(HttpServletRequest request, HttpServletResponse response);

    //查询所有留言id对应的评论
//    public Map<Message,List<Message>> checkSpecificMessage(int userid);
    public Map<Integer,List<Message>> checkSpecificMessage(int userid);


}

