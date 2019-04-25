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
        System.out.println("方法:"+method);
        switch (method){
            case "checkMessage":{
                System.out.println("查看自己信息");
                checkMessage(request,response);
                break;
            }
            case "checkFriendMessage":{
                System.out.println("查看好友信息");
                checkFriendMessage(request, response);
                break;
            }
            case "addMesaage":{
                System.out.println("添加留言信息");
                addMessage(request, response);
                System.out.println("添加信息完毕");
                break;
            }
            case "deleteMesaage":{
                System.out.println("删除留言信息");
                deleteMesaage(request, response);
                break;
            }
            case "replyMessage":{
                System.out.println("回复留言");
                replyMessage(request, response);
                break;
            }
//            case "showReplyMessage":{
//                System.out.println("显示评论");
//                showReplyMessage(request, response);
//                break;
//            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    /**
     * 查看用户自己的信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void checkMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //本人信息
        User user = (User)request.getSession().getAttribute("user");
        request.getSession().removeAttribute("personinfo");
        System.out.println("本人："+user.toString());

            //本人查看自己的留言板
            List<Message> usermessage=messageService.checkAllUserMessage(request, response);
            request.setAttribute("allMessage", usermessage);
//            Map<Message,List<Message>> allReplyMap=messageService.checkSpecificMessage((int)(user.getUserId()));
            Map<Integer,List<Message>> allReplyMap=messageService.checkSpecificMessage((int)(user.getUserId()));
            request.setAttribute("allReply", allReplyMap);
//            System.out.println(allReplyMap.toString());
            request.getRequestDispatcher("leacots.jsp").forward(request, response);
    }

    /**
     * 查看好友信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void checkFriendMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //好友信息
        User friend=(User)request.getSession().getAttribute("personinfo");
        System.out.println("朋友:"+friend.toString());

            //查看别人的留言板
            List<Message> visitormessage=messageService.checkAllFriendMessage(request, response);
            request.setAttribute("allMessage", visitormessage);
//            Map<Message,List<Message>> allReplyMap=messageService.checkSpecificMessage((int)(friend.getUserId()));
            Map<Integer,List<Message>> allReplyMap=messageService.checkSpecificMessage((int)(friend.getUserId()));
            request.setAttribute("allReply", allReplyMap);
//            System.out.println(allReplyMap.toString());
            request.getRequestDispatcher("leacots.jsp").forward(request, response);
    }

    /**
     * 添加留言
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void addMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        messageService.addOneMessage(request, response);
        System.out.println("留言添加成功");
//        request.getRequestDispatcher("leacots.jsp").forward(request,response);

    }

    /**
     * 删除留言
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteMesaage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        messageService.deleteOneMessage(request, response);
        System.out.println("留言删除成功");
//        request.getRequestDispatcher("leacots.jsp").forward(request,response);

    }

    /**
     * 回复留言
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void replyMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        messageService.replyOneMessage(request, response);
        System.out.println("回复成功");

    }

    /**
     * 显示全部评论信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
//    protected void showReplyMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("显示全部评论信息了");
//        Map<Message,List<Message>> allReplyMap=messageService.checkSpecificMessage(request, response);
//        System.out.println(allReplyMap);
//        request.getSession().setAttribute("allReply", allReplyMap);
//        request.getRequestDispatcher("leacots.jsp").forward(request, response);
//
//    }


}
