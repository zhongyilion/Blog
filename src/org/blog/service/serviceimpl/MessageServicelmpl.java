package org.blog.service.serviceimpl;


import org.blog.dao.MessageDao;
import org.blog.dao.UserDao;
import org.blog.dao.daoimpl.MessageDaoImp;
import org.blog.dao.daoimpl.UserDaoImp;
import org.blog.entity.Message;
import org.blog.entity.Page;
import org.blog.entity.User;
import org.blog.service.MessageService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageServicelmpl implements MessageService {
    private MessageDao messageDao;
    private UserDao userDao;

    public MessageServicelmpl(){
        messageDao=new MessageDaoImp();
        userDao=new UserDaoImp();
    }

    /**
     * 用户本人的留言板信息
     * @param request
     * @param response
     * @return
     */
    @Override
    public List<Message> checkAllUserMessage(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("list查看本人的留言");
        User user = (User)request.getSession().getAttribute("user");
        Page p=messagePaging((int)(user.getUserId()),request,response);
        List<Message> message=messageDao.checkUserMessage((int) user.getUserId(),p.getNowpage(),p.getEveryPageCount());
        return message;
    }

    /**
     * 好友的留言板信息
     * @param request
     * @param response
     * @return
     */
    @Override
    public List<Message> checkAllFriendMessage(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("list查看好友的留言");
        User friend = (User)request.getSession().getAttribute("personinfo");
//        System.out.println(friend.toString());
        Page p=messagePaging((int)(friend.getUserId()),request,response);
        List<Message> message=messageDao.checkUserMessage((int) friend.getUserId(),p.getNowpage(),p.getEveryPageCount());
        return message;
    }

    /**
     * 分页
     * @param userid
     * @param request
     * @param response
     * @return
     */
    @Override
    public Page messagePaging(int userid,HttpServletRequest request, HttpServletResponse response) {
        String page=request.getParameter("page");
        String count=request.getParameter("count");
//        System.out.println("page:"+page+",count:"+count);
        //封装一个page分页
        Page p=new Page();
        p.setAllcount(messageDao.getAllCount(userid));
        p.setFirstpage(1);
        p.setEveryPageCount(Integer.parseInt(count));
        p.setNowpage(Integer.parseInt(page));
        int allpage=p.getAllcount()%Integer.parseInt(count)==0?p.getAllcount()/Integer.parseInt(count):p.getAllcount()/Integer.parseInt(count)+1;
        p.setLastpage(allpage);
        p.setPreviouspage(p.getNowpage()<=1?Integer.parseInt(page):Integer.parseInt(page)-1);
        p.setNextpage(p.getNowpage()>=p.getLastpage()?p.getNowpage():p.getNowpage()+1);
        request.setAttribute("page",p);
//        System.out.println("page"+p.toString());
        return p;
    }

    /**
     * 获得当前时间
     * @return
     */
    @Override
    public String getCurrentDate() {
        Date nowdate = new Date();//获取当前时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String message_time =format.format(nowdate);
        return message_time;
    }

    /**
     * 添加留言
     * @param request
     * @param response
     */
    @Override
    public void addOneMessage(HttpServletRequest request, HttpServletResponse response) {

        String message_time=getCurrentDate();
        String user_id=request.getParameter("userid");
        String message_user_id=request.getParameter("friendid");
        String message_contents=request.getParameter("messageContents");
        System.out.println("用户:"+message_user_id+",好友："+user_id+",内容："+message_contents+",时间："+message_time);

        Message message=new Message();
        message.setMessage_user_id(Integer.parseInt(message_user_id));

        User user=userDao.queryById((int)Integer.parseInt(user_id));
        user.setUserId(user.getUserId());
        user.setUserName(user.getUserName());
        user.setNickname(user.getNickname());
        user.setSex(user.getSex());
        user.setAge(user.getAge());
        user.setTelephone(user.getTelephone());
        user.setEmail(user.getEmail());
        user.setImage(user.getImage());

        message.setUser(user);
        message.setMessage_contents(message_contents);
        message.setMessage_time(message_time);
        System.out.println("封装："+message.toString());

        messageDao.AddMessage(message);

            JSONObject jsonObj=new JSONObject();
        try {
            jsonObj.put("message_id",message.getMessage_id());
            jsonObj.put("message_user_id",message.getMessage_user_id());
            jsonObj.put("message_contents",message.getMessage_contents());
            jsonObj.put("message_time",message.getMessage_time());
            jsonObj.put("have_read",message.getHave_read());
            jsonObj.put("reply_id",message.getReply_id());
            jsonObj.put("userId",message.getUser().getUserId());
            jsonObj.put("nickname",message.getUser().getNickname());
            jsonObj.put("userimage",message.getUser().getImage());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        response.setContentType("text/json;charset=utf-8");
        PrintWriter out= null;
        try {
            out = response.getWriter();
            out.write( jsonObj.toString());
            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 删除留言信息
     * @param request
     * @param response
     */
    @Override
    public void deleteOneMessage(HttpServletRequest request, HttpServletResponse response) {
        String messageid=request.getParameter("messageid");
        System.out.println("删除的留言id："+messageid);
        messageDao.deleteMessage(Integer.parseInt(messageid));
    }

    /**
     * 回复留言
     * @param request
     * @param response
     */
    @Override
    public void replyOneMessage(HttpServletRequest request, HttpServletResponse response) {
        String messageid=request.getParameter("messageid");
        String replyUserId=request.getParameter("replyUserId");
        String userid=request.getParameter("userid");
        String replyContents=request.getParameter("replyContents");
        String message_time=getCurrentDate();
        System.out.println("content:"+replyContents+",messageid:"+messageid+",replyUserid:"+replyUserId+",userid:"+userid+"messagetime:"+message_time);

        Message message=new Message();
        message.setMessage_user_id(Integer.parseInt(userid));
        User user=userDao.queryById(Integer.parseInt(replyUserId));
        user.setUserId(Long.parseLong(replyUserId));
        user.setUserName(user.getUserName());
        user.setNickname(user.getNickname());
        user.setSex(user.getSex());
        user.setAge(user.getAge());
        user.setTelephone(user.getTelephone());
        user.setEmail(user.getEmail());
        user.setImage(user.getImage());

        message.setUser(user);
        message.setMessage_contents(replyContents);
        message.setMessage_time(message_time);
        message.setReply_id(Integer.parseInt(messageid));
        System.out.println("留言："+message.toString());

        messageDao.replyMessage(message);


        JSONObject jsonObj1=new JSONObject();
        try {
//            jsonObj1.put("messageid",message.getMessage_id());
            jsonObj1.put("messageuserid",message.getMessage_user_id());//给你留言的用户成为了评论的主人
            jsonObj1.put("messagecontents",message.getMessage_contents());
            jsonObj1.put("messagetime",message.getMessage_time());
            jsonObj1.put("nicknames",message.getUser().getNickname());
            jsonObj1.toString();
            System.out.println("hahhhaahhahaha ");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        response.setContentType("text/json;charset=utf-8");
        PrintWriter out= null;
        try {
            out = response.getWriter();
            out.write( jsonObj1.toString());
            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有留言id对应的评论
     * @param userid
     * @return
     */
    @Override
//    public Map<Message,List<Message>> checkSpecificMessage(int userid) {
//        Map<Message,List<Message>> allreply=new HashMap<>();
//        System.out.println("Map集合");
//        List<Message> messageIds= messageDao.checkUserMessage(userid);
//        for (Message s:messageIds){
//            List<Message> replyMessage=messageDao.showReply(s.getMessage_id());
//            allreply.put(s,replyMessage);
//        }
//        System.out.println("-------------------------------------------------");
////        System.out.println(allreply);
//        return allreply;
//    }
    public Map<Integer,List<Message>> checkSpecificMessage(int userid) {
        Map<Integer,List<Message>> allreply=new HashMap<>();
        System.out.println("Map集合");
        List<Integer> messageIds= messageDao.checkUserMessage(userid);
        for (int s:messageIds){
            List<Message> replyMessage=messageDao.showReply(s);
            allreply.put(s,replyMessage);
        }
//        System.out.println("-------------------------------------------------");
//        System.out.println(allreply);
        System.out.println("-------------------------------------------------");
        return allreply;
    }
}
