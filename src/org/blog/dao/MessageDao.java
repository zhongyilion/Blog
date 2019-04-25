package org.blog.dao;

import org.blog.entity.Message;

import java.util.List;
import java.util.Map;

public interface MessageDao {

    //�鿴�û�����������
    public List<Message> checkUserMessage(int message_user_id, int page, int count);

    //���������������
    public int getAllCount(int message_user_id);

    //�������
    public Message AddMessage(Message message);

    //ɾ������
    public void deleteMessage(int message_id);

    //�ظ�����(��ӵ��������)
    public void replyMessage(Message message);

    //��ѯ���е�����id�����������ۣ�
//    public List<Message> checkUserMessage(int message_user_id);
    public List<Integer> checkUserMessage(int message_user_id);

    //��������id��ʾ��Ӧ��������Ϣ
    public List<Message> showReply(int message_id);

}
