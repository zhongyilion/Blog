package org.blog.controller;

import org.blog.entity.Message;
import org.blog.entity.User;
import org.blog.service.MessageService;
import org.blog.service.serviceimpl.MessageServicelmpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "MessageServlet",urlPatterns = "/MessageServlet")
public class MessageServlet extends HttpServlet {
    private MessageService messageService;

    @Override
    public void init() throws ServletException {
        messageService=new MessageServicelmpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method=request.getParameter("method");
        System.out.println("����:"+method);
        switch (method){
            case "checkMessage":{
                System.out.println("�鿴�Լ���Ϣ");
                checkMessage(request,response);
                break;
            }
            case "checkFriendMessage":{
                System.out.println("�鿴������Ϣ");
                checkFriendMessage(request, response);
                break;
            }
            case "addMesaage":{
                System.out.println("���������Ϣ");
                addMessage(request, response);
                System.out.println("�����Ϣ���");
                break;
            }
            case "deleteMesaage":{
                System.out.println("ɾ��������Ϣ");
                deleteMesaage(request, response);
                break;
            }
            case "replyMessage":{
                System.out.println("�ظ�����");
                replyMessage(request, response);
                break;
            }
//            case "showReplyMessage":{
//                System.out.println("��ʾ����");
//                showReplyMessage(request, response);
//                break;
//            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    /**
     * �鿴�û��Լ�����Ϣ
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void checkMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //������Ϣ
        User user = (User)request.getSession().getAttribute("user");
        request.getSession().removeAttribute("personinfo");
        System.out.println("���ˣ�"+user.toString());

            //���˲鿴�Լ������԰�
            List<Message> usermessage=messageService.checkAllUserMessage(request, response);
            request.setAttribute("allMessage", usermessage);
//            Map<Message,List<Message>> allReplyMap=messageService.checkSpecificMessage((int)(user.getUserId()));
            Map<Integer,List<Message>> allReplyMap=messageService.checkSpecificMessage((int)(user.getUserId()));
            request.setAttribute("allReply", allReplyMap);
//            System.out.println(allReplyMap.toString());
            request.getRequestDispatcher("leacots.jsp").forward(request, response);
    }

    /**
     * �鿴������Ϣ
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void checkFriendMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //������Ϣ
        User friend=(User)request.getSession().getAttribute("personinfo");
        System.out.println("����:"+friend.toString());

            //�鿴���˵����԰�
            List<Message> visitormessage=messageService.checkAllFriendMessage(request, response);
            request.setAttribute("allMessage", visitormessage);
//            Map<Message,List<Message>> allReplyMap=messageService.checkSpecificMessage((int)(friend.getUserId()));
            Map<Integer,List<Message>> allReplyMap=messageService.checkSpecificMessage((int)(friend.getUserId()));
            request.setAttribute("allReply", allReplyMap);
//            System.out.println(allReplyMap.toString());
            request.getRequestDispatcher("leacots.jsp").forward(request, response);
    }

    /**
     * �������
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void addMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        messageService.addOneMessage(request, response);
        System.out.println("������ӳɹ�");
//        request.getRequestDispatcher("leacots.jsp").forward(request,response);

    }

    /**
     * ɾ������
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteMesaage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        messageService.deleteOneMessage(request, response);
        System.out.println("����ɾ���ɹ�");
//        request.getRequestDispatcher("leacots.jsp").forward(request,response);

    }

    /**
     * �ظ�����
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void replyMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        messageService.replyOneMessage(request, response);
        System.out.println("�ظ��ɹ�");

    }

    /**
     * ��ʾȫ��������Ϣ
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
//    protected void showReplyMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("��ʾȫ��������Ϣ��");
//        Map<Message,List<Message>> allReplyMap=messageService.checkSpecificMessage(request, response);
//        System.out.println(allReplyMap);
//        request.getSession().setAttribute("allReply", allReplyMap);
//        request.getRequestDispatcher("leacots.jsp").forward(request, response);
//
//    }


}
