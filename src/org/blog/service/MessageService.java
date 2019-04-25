package org.blog.service;

import org.blog.entity.Message;
import org.blog.entity.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface MessageService {

    //�鿴����
    public List<Message> checkAllUserMessage(HttpServletRequest request, HttpServletResponse response);
    public List<Message> checkAllFriendMessage(HttpServletRequest request, HttpServletResponse response);


    //��ҳ
    public Page messagePaging(int userid ,HttpServletRequest request, HttpServletResponse response);

    //��ȡ��ǰʱ��
    public String getCurrentDate();

    //�������
    public void addOneMessage(HttpServletRequest request, HttpServletResponse response);
    
    //ɾ������
    public void deleteOneMessage(HttpServletRequest request, HttpServletResponse response);

    //�ظ�����
    public void replyOneMessage(HttpServletRequest request, HttpServletResponse response);

    //��ѯ��������id��Ӧ������
//    public Map<Message,List<Message>> checkSpecificMessage(int userid);
    public Map<Integer,List<Message>> checkSpecificMessage(int userid);


}

