<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="header">
    <div class="menu-btn">
        <div class="menu"></div>
    </div>
    <h1 class="logo">
        <a href="index.jsp">
            <span>MYBLOG</span>
            <img src="res/img/logo.png">
        </a>
    </h1>
    <div class="nav">

        <%
            String nowPage=request.getParameter("nowPage");
        if(nowPage!=null && nowPage.equals("index")){
        %>
            <a href="index.jsp" class="active">文章</a>
            <a href="whisper.jsp">微语</a>
            <a href="leacots.jsp">留言</a>
            <a href="album.jsp">相册</a>
        <a href="about.jsp">关于</a>


        <%
        }else  if(nowPage!=null && nowPage.equals("about")){
        %>
        <a href="index.jsp">文章</a>
        <a href="whisper.jsp">微语</a>
        <a href="leacots.jsp">留言</a>
        <a href="album.jsp">相册</a>
        <a href="about.jsp"  class="active">关于</a>
        <%
            }
            else  if(nowPage!=null && nowPage.equals("album")){
        %>
        <a href="index.jsp">文章</a>
        <a href="whisper.jsp">微语</a>
        <a href="leacots.jsp">留言</a>
        <a href="album.jsp"  class="active">相册</a>
        <a href="about.jsp" >关于</a>
        <%
            }

            else  if(nowPage!=null && nowPage.equals("leacots")){
        %>
        <a href="index.jsp">文章</a>
        <a href="whisper.jsp">微语</a>
        <a href="leacots.jsp" class="active">留言</a>
        <a href="album.jsp">相册</a>
        <a href="about.jsp"  >关于</a>
        <%
            }
            else  if(nowPage!=null && nowPage.equals("whisper")){
        %>
        <a href="index.jsp">文章</a>
        <a href="whisper.jsp" class="active">微语</a>
        <a href="leacots.jsp">留言</a>
        <a href="album.jsp">相册</a>
        <a href="about.jsp"  >关于</a>
        <%
            }
        %>


    </div>
    <ul class="layui-nav header-down-nav">
        <li class="layui-nav-item"><a href="index.jsp" class="active">文章</a></li>
        <li class="layui-nav-item"><a href="whisper.jsp">微语</a></li>
        <li class="layui-nav-item"><a href="leacots.jsp">留言</a></li>
        <li class="layui-nav-item"><a href="album.jsp">相册</a></li>
        <li class="layui-nav-item"><a href="about.jsp">关于</a></li>
    </ul>
    <p class="welcome-text">
        欢迎来到<span class="name">小明</span>的博客~
    </p>
</div>
