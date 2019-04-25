<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>博客后台管理系统</title>
    <link rel="stylesheet" href="../../res/layui/css/layui.css">
    <link rel="stylesheet" href="../../res/editormd/editormd.min.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">博客后台管理</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="">控制台</a></li>
            <li class="layui-nav-item"><a href="">商品管理</a></li>
            <li class="layui-nav-item"><a href="">用户</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">其它系统</a>
                <dl class="layui-nav-child">
                    <dd><a href="">邮件管理</a></dd>
                    <dd><a href="">消息管理</a></dd>
                    <dd><a href="">授权管理</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;" id="user">

                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="/about.jsp">退了</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">管理列表</a>
                    <dl class="layui-nav-child">
                        <dd><a href="myarticle.html">文章管理</a></dd>
                        <dd><a href="javascript:;">我的关注</a></dd>
                        <dd><a href="myfans.html">我的粉丝</a></dd>
                        <!--<dd><a href="">超链接</a></dd>-->
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">解决方案</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">列表一</a></dd>
                        <dd><a href="javascript:;">列表二</a></dd>
                        <dd><a href="">超链接</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="">云市场</a></li>
                <li class="layui-nav-item"><a href="">发布商品</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body layui-container" style="margin-left: 100px" id="content">
        <h2>文章修改</h2>
        <hr>
        <!-- 编辑器的表单 -->
        <div class="layui-form">
            <form action="#" method="post">
                <div class="layui-inline" style="margin-left: -25px;padding-left: 0;">
                    <label class="layui-form-label" style="padding-left: 0;"><strong>文章标题</strong></label>
                    <div class="layui-input-inline" style="margin-left: -6px;width: 275px;">
                        <input type="text" name="r_summary" id="r_summary" placeholder="请用简短的文字介绍一下你的文章吧！" class="layui-input" value="${sessionScope.article.title}"/>
                    </div>
                </div>
                <br/>
                <br/>
                <label><strong>文章内容</strong></label>
                <!-- 添加Markdown的容器 -->
                <div id="editormd" style="margin-top: 20px">
                    <textarea id="demo" style="display: none;">${sessionScope.article.content}</textarea>
                    <!--<textarea class="editormd-markdown-textarea" name="editormd-markdown-doc"></textarea>-->
                    <!--<textarea class="editormd-html-textarea" name="editormd-html-code"></textarea>-->
                </div>
                <div class="layui-inline" style="margin-top: 20px;">
                    <button type="button" id="publishBtn" class="layui-btn">修改</button>
                    <button type="button" id="cleanBtn" class="layui-btn">清空</button>
                </div>
            </form>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>
<script src="../../res/js/util/jquery-3.3.1.js"></script>
<script src="../../res/layui/layui.js"></script>
<script src="../../res/editormd/editormd.min.js" type="text/javascript" charset="utf-8"></script>
<script>
    //JavaScript代码区域
    layui.use(['element','layer'], function(){
        var element = layui.element,layer = layui.layer;

    });
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        laydate.render({
            elem: '#r_date'
        });
    });
    layui.use(['layedit','layer'], function(){
        var layedit = layui.layedit,layer = layui.layer;
        layedit.build('demo'); //建立编辑器
        var index = layedit.build('demo')
        var user = JSON.parse(sessionStorage.getItem("user"))
        $("#publishBtn").click(function () {
            var title = $("#r_summary").val()
            var content = layedit.getContent(index)
            var userId = user.userId
            $.post(
                "/article/editArticle",
                {"title":title,"content":content,"userId":userId,"articleId":"${sessionScope.article.articleId}"},
                function () {
                    $("#content").html("<h1>修改成功!</h1>")
                }
            )
        })
        $("#cleanBtn").click(function () {
            layer.confirm('确认清空这篇文章吗？',function (mm) {
                layer.close(mm)
                layedit.setContent(index, "", false);
                // layer.msg($("#demo").val())

            })
        })
    });
</script>
<script>
    var user = JSON.parse(sessionStorage.getItem("user"))
    $("#user").html('<img src="'+user.image+'" class="layui-nav-img" id="test1" style="cursor: pointer"> '+user.nickname+'')
    // $("img").attr("src",user.image)
</script>
<script>


</script>
</body>
</html>