<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>Document</title>
    <link rel="stylesheet" type="text/css" href="res/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="res/css/main.css">
    <!--加载meta IE兼容文件-->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<div class="about-content">
    <div class="w1000">
        <div class="item info">
            <div class="title">
                <h3>我的介绍</h3>
            </div>
            <div class="cont">
                <img src="res/img/xc_img1.jpg">
                <div class="per-info">
                    <p>
                        <span class="name">${requestScope.personinfo.nickname}</span><br />
                        <span class="age">${requestScope.personinfo.age}岁</span><br />
                        <span class="sex">${requestScope.personinfo.sex}</span><br />
                        <span class="message"><a href="">留言板</a></span><br />
                        <span class="message"><a href="">${requestScope.personinfo.nickname}的文章</a></span><br />
                        <%--<span><a href="views/backstage/welcome.html">后台管理</a></span>--%>
                        <%--<span class="interest">爱好旅游,打游戏</span>--%>
                    </p>
                </div>
            </div>
        </div>
        <div class="item tool">
            <div class="title">
                <h3>我的技能</h3>
            </div>
            <div class="layui-fluid">
                <div class="layui-row">
                    <div class="layui-col-xs6 layui-col-sm3 layui-col-md3">
                        <div class="cont-box">
                            <img src="res/img/gr_img2.jpg">
                            <p>80%</p>
                        </div>
                    </div>
                    <div class="layui-col-xs6 layui-col-sm3 layui-col-md3">
                        <div class="cont-box">
                            <img src="res/img/gr_img3.jpg">
                            <p>80%</p>
                        </div>
                    </div>
                    <div class="layui-col-xs6 layui-col-sm3 layui-col-md3">
                        <div class="cont-box">
                            <img src="res/img/gr_img4.jpg">
                            <p>80%</p>
                        </div>
                    </div>
                    <div class="layui-col-xs6 layui-col-sm3 layui-col-md3">
                        <div class="cont-box">
                            <img src="res/img/gr_img5.jpg">
                            <p>80%</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="item contact">
            <div class="title">
                <h3>联系方式</h3>
            </div>
            <div class="cont">
                <img src="res/img/erweima.jpg">
                <div class="text">
                    <%--<p class="WeChat">微信：<span>1234567890</span></p>--%>
                    <p>Email：<span class="email">${requestScope.personinfo.email}</span></p>
                    <p>电话：<span  class="iphone">${requestScope.personinfo.telephone}</span></p>
                </div>
            </div>
        </div>
    </div>
</div>



<script type="text/javascript" src="res/layui/layui.js"></script>
<script type="text/javascript">
    layui.config({
        base: '../res/js/util/'
    }).use(['element','laypage','form','layer','menu','upload'],function(){
        element = layui.element,laypage = layui.laypage,form = layui.form,layer = layui.layer,menu = layui.menu,upload=layui.upload;
        menu.init();
    })
</script>
</body>
</html>