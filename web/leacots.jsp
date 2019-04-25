<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <title>留言</title>
  <link rel="stylesheet" type="text/css" href="res/layui/css/layui.css">
  <link rel="stylesheet" type="text/css" href="res/css/main.css">
  <!--加载meta IE兼容文件-->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
  <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
  <script src="js">
  <![endif]-->
</head>
<body>
<jsp:include page="top.jsp">
  <jsp:param name="nowPage" value="leacots"></jsp:param>
</jsp:include>


<div class="content whisper-content leacots-content">
  <div class="cont w1000">
    <div class="whisper-list">
      <div class="item-box">
        <div class="review-version">
          <div class="form-box">
            <img class="banner-img" src="res/img/liuyan.jpg">
            <div class="form">
              <%--<form class="layui-form" action="">--%>
                <div class="layui-form-item layui-form-text">
                  <div class="layui-input-block">
                      <textarea name="messageContent" placeholder="既然来了，就说几句" class="layui-textarea"></textarea>
                  </div>
                </div>
                <div class="layui-form-item">
                  <div class="layui-input-block" style="text-align: right;">
                    <button class="addMessageButton" style="height: 40px;width: 80px;background-color: #eb7350;cursor: pointer">确定</button>
                  </div>
                </div>
              <%--</form>--%>
              <c:choose>
                <c:when test="${empty sessionScope.personinfo}">
                  <img src="${sessionScope.user.image}" style="width: 100px;height: 100px;border-radius: 5px;position: relative;top:-10px;border: 1px solid red">
                </c:when>
                <c:otherwise>
                  <img src="${sessionScope.personinfo.image}" style="width: 100px;height: 100px;border-radius: 5px;position: relative;top:-10px;border: 1px solid red">
                </c:otherwise>
              </c:choose>
            </div>
          </div>

          <div >
            <c:choose>
              <c:when test="${ empty sessionScope.personinfo}">
                <div style="position:relative;right: -50px;top: -40px;">${sessionScope.user.nickname}的留言板</div>
              </c:when>
              <c:otherwise>
                <div style="position:relative;right: -50px;top: -40px;">${sessionScope.personinfo.nickname}的留言板</div>
              </c:otherwise>
            </c:choose>
           <div  style="position:relative;right: -50px;top: -20px;">全部留言 <span style="color: orangered">${requestScope.page.allcount}</span></div>
          </div>

            <div class="list-cont">

                <c:forEach var="msg" items="${requestScope.allMessage}" varStatus="s">
                    <div class="cont">

                        <div class="img">
                            <a class="visitResource"><img src="${msg.user.image}" alt="" style="width: 50px;height: 50px;border-radius: 5px;"></a>
                            <span style="display: none">${msg.user.userId}</span>
                        </div>

                        <div class="text">
                            <p class="tit"><span class="name">${msg.user.nickname}</span><span class="data">${msg.message_time}</span></p>
                            <p class="ct">${msg.message_contents}</p>
                            <div class="operationMessage" style="position: relative;left: 820px;top: -25px;display: none;">
                                <span style="display: none">${msg.message_id}</span>
                                <span style="display: none">${msg.message_user_id}</span>
                                <span style="display: none">${sessionScope.user.userId}</span>
                                <span style="display: none">${sessionScope.personinfo.nickname}</span>
                                <c:if test="${msg.message_user_id eq sessionScope.user.userId}">
                                    <button class="reply">回复</button>
                                    <button class="delete">删除</button>
                                </c:if>
                                    <%--<c:if test="${msg.message_user_id eq sessionScope.user.userId or msg.user.user_id eq sessionScope.user.userId }">--%>
                                    <%--<button class="reply">回复</button>--%>
                                    <%--<button class="delete">删除</button>--%>
                                    <%--</c:if>--%>
                            </div>
                        </div>

                        <div class="messagedetail">
                            <div class="replyMessage" style="position:relative;left: 70px; display: none;">
                                <input type="text" placeholder="评论" style="width: 400px;">
                                <input type="button" value="发送">
                            </div>
                            <div class="replys">
                                <c:forEach var="re" items="${requestScope.allReply}" varStatus="r">
                                    <c:if test="${re.key eq msg.message_id}">
                                        <c:forEach items="${re.value}" var="v">
                                            <div style="position: relative;left:100px;">
                                                <span style="color:#3f2469;">
                                                        <c:choose>
                                                            <c:when test="${v.message_user_id eq sessionScope.user.userId}">我</c:when>
                                                            <c:otherwise>${sessionScope.personinfo.nickname}</c:otherwise>
                                                        </c:choose></span>回复
                                                <span style="color:#3f2469;">${v.user.nickname}
                                                </span>:${v.message_contents}
                                                <span style="margin-left: 50px;font-size: 10px;color: #9F9F9F;">----${v.message_time}</span>
                                            </div>
                                        </c:forEach>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>

                    </div>
                </c:forEach>

            </div>
          <table cellspacing="0" cellpadding="0" class="page">
            <tr>
              <td colspan="2">当前第${requestScope.page.nowpage}页/总共${requestScope.page.lastpage}页</td>
              <td><button><a href="/MessageServlet?method=checkMessage&page=1&count=10">首页</a></button></td>
              <td><button><a href="/MessageServlet?method=checkMessage&page=${requestScope.page.previouspage}&count=10">上一页</a></button></td>
              <td><button><a href="/MessageServlet?method=checkMessage&page=${requestScope.page.nextpage}&count=10">下一页</a></button></td>
              <td><button><a href="/MessageServlet?method=checkMessage&page=${requestScope.page.lastpage}&count=10">尾页</a></button></td>
            </tr>
          </table>
        </div>
      </div>
    </div>
  </div>
</div>

<%--<div id="demo1"></div>--%>
<script type="text/html" id="laytplCont">
  <div class="cont">
    <div class="img">
      {{#  if(d.avatar){ }}
      <img src="{{d.avatar}}" alt="">
      {{#  } else { }}
      <img src="res/img/header.png" alt="">
      {{# } }}
    </div>
    <div class="text">
      <p class="tit"><span class="name">{{d.name}}</span><span class="data">2018/06/06</span></p>
      <p class="ct">{{d.cont}}</p>
    </div>
  </div>
</script>

<%@include file="footer.jsp"%>
<script type="text/javascript" src="res/layui/layui.js"></script>
<script type="text/javascript">
    layui.config({
        base: '../res/js/util/'
    }).use(['element','laypage','form','menu','jquery'],function(){
        element = layui.element,laypage = layui.laypage,form = layui.form,menu = layui.menu,$=layui.jquery;
        laypage.render({
            elem: 'demo'
            ,count: 70 //数据总数，从服务端得到
        });
        menu.init();
        menu.submit()
    });

    //点击好友头像就能查看好友相关信息
  $(document).on("click"," .list-cont .cont .img .visitResource",function () {
          var index=$(this).parent().parent().index();
          var id=$(".list-cont .cont:eq("+index+")").find(".img").find("span").text();
          $.get(
            "/user/showpersoninfo",
            {"userId":id},
            function (result) {
              location.href = "/personinformation.jsp";
            }
          )
  });

    //传递参数添加信息
  $(document).on("click",".layui-input-block .addMessageButton",function () {
  //     $(".layui-input-block .addMessageButton").click(function () {
     var messageContents=$(".layui-input-block textarea").val();//留言内容
      console.log(messageContents);
      if (messageContents=="") {
          alert("请输入内容");
      }else{
          if(${empty sessionScope.personinfo}){
              $.post(
                  "/MessageServlet",
                  {
                      "method":"addMesaage",
                      "messageContents":messageContents,
                      "userid":"${sessionScope.user.userId}",
                      "friendid":"${sessionScope.user.userId}"
                  },
                  function(data) {
                      $(".layui-input-block textarea").val("");
                      $(".list-cont .cont:first").before("<div class='cont'><div class='img'><a class='visitResource'><img src='"+data.userimage+"' alt='' style='width: 50px;height: 50px;border-radius: 5px;'></a><span style='display: none'>"+data.userId+"</span></div><div class='text'><p class='tit'><span class='name'>"+data.nickname+"</span><span class='data'>"+data.message_time+"</span></p><p class='ct'>"+data.message_contents+"</p><div class='operationMessage' style='position: relative;left: 820px;top: -25px;display: none;'><span style='display: none'>"+data.message_id+"</span><span style='display: none'>"+data.message_user_id+"</span><button class='reply'>回复</button><button class='delete'>删除</button></div></div><div class='messagedetail'><div class='replyMessage' style='position:relative;left: 70px; display: none;'><input type='text' placeholder='评论' style='width: 400px;'><input type='button' value='发送'></div><div class='replys'></div></div></div>");
                      var messageUser=$(".list-cont .cont:eq(3)").find(".text").find(".operationMessage").find("span:eq(1)").text();//留言板用户
                      var loginUser=$(".list-cont .cont:eq(3)").find(".text").find(".operationMessage").find("span:eq(2)").text();//登录的用户
                        if (messageUser==loginUser){

                        } else {
                            $(".reply").hide();
                            $(".delete").hide();
                        }
                  }
              );
          }else{
              $.post(
                  "/MessageServlet",
                  {
                      "method":"addMesaage",
                      "messageContents":messageContents,
                      "userid":"${sessionScope.user.userId}",
                      "friendid":"${sessionScope.personinfo.userId}"
                  },
                  function (data) {
                      $(".layui-input-block textarea").val("");
                      $(".list-cont .cont:first").before("<div class='cont'><div class='img'><a class='visitResource'><img src='"+data.userimage+"' alt='' style='width: 50px;height: 50px;border-radius: 5px;'></a><span style='display: none'>"+data.userId+"</span></div><div class='text'><p class='tit'><span class='name'>"+data.nickname+"</span><span class='data'>"+data.message_time+"</span></p><p class='ct'>"+data.message_contents+"</p><div class='operationMessage' style='position: relative;left: 820px;top: -25px;display: none;'><span style='display: none'>"+data.message_id+"</span><span style='display: none'>"+data.message_user_id+"</span><button class='reply'>回复</button><button class='delete'>删除</button></div></div><div class='messagedetail'><div class='replyMessage' style='position:relative;left: 70px; display: none;'><input type='text' placeholder='评论' style='width: 400px;'><input type='button' value='发送'></div><div class='replys'></div></div></div>");
                      var messageUser=$(".list-cont .cont:eq(3)").find(".text").find(".operationMessage").find("span:eq(1)").text();//留言板用户
                      var loginUser=$(".list-cont .cont:eq(3)").find(".text").find(".operationMessage").find("span:eq(2)").text();//登录的用户
                      if (messageUser==loginUser){

                      } else {
                          $(".reply").hide();
                          $(".delete").hide();
                      }                  }
              );
          }
      }

  });

  // 删除好友的留言
    $(document).on("click"," .list-cont .cont .text .operationMessage .delete",function () {
        var index=$(this).parent().parent().parent().index();
        var id=$(".list-cont .cont:eq("+index+")").find(".text").find(".operationMessage").find("span:first").text();
        console.log("hhhhhh:"+id);
        if (window.confirm("您确定要删除该条留言吗？")) {
            if(${empty sessionScope.personinfo}){
                $.post(
                    "/MessageServlet",
                    {
                        "method":"deleteMesaage",
                        "messageid":id
                    },
                    function (data) {
                        myrefresh();
                    }
                );
            }

        }

    });

    //只能有一个评论input出来，
    $(document).on("click"," .list-cont .cont .text .operationMessage .reply",function () {
        var index=$(this).parent().parent().parent().index();
        $(".messagedetail:not("+index+") .replyMessage").hide();
        $(".messagedetail:eq("+index+") .replyMessage").show();
    });

    // 回复好友的留言
    $(document).on("click"," .list-cont .cont .text .operationMessage .reply",function () {
            var index=$(this).parent().parent().parent().index();
            $(".messagedetail:eq("+index+") .replyMessage").show();
            var messageid=$(".list-cont .cont:eq("+index+")").find(".text").find(".operationMessage").find("span:first").text();//留言的id
            var replyUserId=$(".list-cont .cont:eq("+index+")").find(".text").find(".operationMessage").find("span:eq(1)").text();//回复人的id（留言板主人）
            var userid=$(".list-cont .cont:eq("+index+")").find(".img").find("span").text();//给留言板用户留言的用户
            // $(".messagedetail:eq("+index0+") .replyMessage input[type=text]").text("");
            $(document).on("click",".messagedetail:eq("+index+") .replyMessage input[type=button]",function () {
                var content=$(".messagedetail:eq("+index+") .replyMessage input[type=text]").val();
                console.log("content:"+content+",messageid:"+messageid+",replyUserid:"+replyUserId+",userid:"+userid);
                $.post(
                    "/MessageServlet",
                    {
                        "method":"replyMessage",
                        "replyContents":content,
                        "messageid":messageid,
                        "userid":userid,
                        "replyUserId":replyUserId
                    },
                    function (data) {
                        $(".messagedetail:eq("+index+") .replyMessage").hide();
                        $(".list-cont .cont:eq("+index+") .replys").append("<div style='position: relative;left:100px;'><span style='color:#3f2469;'>我</span>回复<span style='color:#3f2469;'>"+data.nicknames+"</span>"+data.messagecontents+"<span style='margin-left: 50px;font-size: 10px;color: #9F9F9F;'>----"+data.messagetime+"</span></div>");
                        // if (data.messageuserid !=replyUserId){
                        //     var friendUser=$(".list-cont .cont:eq(3)").find(".text").find(".operationMessage").find("span:eq(3)").text();//好友
                        //     $(".replys:eq("+index+") span:first ").html(friendUser);
                        // }


                    }
                );
            });
        });

  // 鼠标移入移出事件
    $(document).on("mouseover"," .list-cont .cont .text",function () {
        var index=$(this).parent().index();
        $(".operationMessage:eq("+index+")").show();
    });
    $(document).on("mouseout"," .list-cont .cont .text",function () {
        var index=$(this).parent().index();
        $(".operationMessage:eq("+index+")").hide();
    });

    //实现网页刷新
    function myrefresh() {
        window.location.reload();
    }

</script>

</body>
</html>