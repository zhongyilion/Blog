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
    <%--<ul class="layui-nav header-down-nav">--%>
        <%--<li class="layui-nav-item"><a href="index.jsp" class="active">文章</a></li>--%>
        <%--<li class="layui-nav-item"><a href="whisper.jsp">微语</a></li>--%>
        <%--<li class="layui-nav-item"><a href="leacots.jsp">留言</a></li>--%>
        <%--<li class="layui-nav-item"><a href="album.jsp">相册</a></li>--%>
        <%--<li class="layui-nav-item"><a href="about.jsp">nihoa</a></li>--%>
        <%--<li class="layui-nav-item"><a href="about.jsp">登陆</a></li>--%>
    <%--</ul>--%>
    <ul class="layui-nav layui-layout-right" style="background-color: white">
        <li class="layui-nav-item" style="cursor: pointer"> <a id="login" style="color: black">登陆</a></li>
        <li class="layui-nav-item" style="cursor: pointer"><a id="register" style="color: black">注册</a></li>
    </ul>
</div>
<%--登陆框--%>
<form class="layui-form" id="demo1" style="display: none" action="#">
    <div class="layui-form-item" style="margin-top: 20px">
        <label class="layui-form-label">用户名:</label>
        <div class="layui-input-block" style="width: 200px">
            <input type="text" name="username" lay-verify="required" autocomplete="off" placeholder="请输入用户名" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密码:</label>
        <div class="layui-input-block" style="width: 200px">
            <input type="password" name="password" lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
    <label class="layui-form-label">验证码:</label>
    <div class="layui-input-block" style="width: 200px">
        <input type="text" name="testcode" lay-verify="test" placeholder="请输入验证码" autocomplete="off" class="layui-input"><br>
        <img src="/imageServlet" id="safecode">&nbsp;&nbsp;&nbsp;<a href="javascript:reloadCode();">看不清楚</a>
    </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="demo1" id="sub">登陆</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>


<%--注册框--%>
<form class="layui-form" id="demo2" style="display: none" action="/user/register">
    <div class="layui-form-item" style="margin-top: 20px">
        <label class="layui-form-label">用户名:</label>
        <div class="layui-input-block" style="width: 200px">
            <input type="text" name="username" lay-verify="required|username" autocomplete="off" id="rname" placeholder="请输入用户名" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密码:</label>
        <div class="layui-input-block" style="width: 200px">
            <input type="password" name="password" lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">确认密码:</label>
        <div class="layui-input-block" style="width: 200px">
            <input type="password" name="newpassword" lay-verify="required|newpassword" placeholder="确认密码" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">昵称:</label>
        <div class="layui-input-block" style="width: 200px">
            <input type="text" name="nickname" lay-verify="required" placeholder="请输入昵称" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">电话号码:</label>
        <div class="layui-input-block" style="width: 200px">
            <input type="text" name="phone" lay-verify="required|phone" placeholder="请输入电话号码" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">邮箱:</label>
        <div class="layui-input-block" style="width: 200px">
            <input type="text" name="email" lay-verify="required|email" placeholder="请输入邮箱" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="demo2">注册</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script src="res/layui/layui.js"></script>
<script src="res/js/util/jquery-3.3.1.js"></script>
<script>

    $(function () {
        if(sessionStorage.getItem("user") != null){
            // alert(sessionStorage.getItem("user").nickname)

            showuser(JSON.parse(sessionStorage.getItem("user")))
        }

        layui.use(['element','layer','jquery','form'],function () {
            var element = layui.element;
            var layer = layui.layer,form = layui.form,$ = layui.jquery;
            /*
               为登陆添加点击事件（弹窗）
            */
            $("#login").click(function () {
                layer.open({
                    type:1,
                    title:'登陆',
                    skin: 'layui-layer-rim', //加上边框
                    area: '360px', //宽高
                    content: $("#demo1"),
                    end : function() {
                        $(".layui-layer-shade").css("display","none")}
                })
            })
            /*
                点击注册后弹窗
            */
            $("#register").click(function () {
                layer.open({
                    type:1,
                    title:'<h3>注册</h3>',
                    skin: 'layui-layer-rim', //加上边框
                    area: '360px', //宽
                    content: $("#demo2")
                })
            })
            /*
            * 为验证码输入提供异步的验证
            *
            */
            form.verify({
                test:function (value) {
                    value = value.toUpperCase()
                    var meg=0;
                    //这里改为同步刷新，不然异步的话，最后才会执行ajax
                    $.ajaxSettings.async = false;
                    $.post(
                        "/TestPiccodeServlet",
                        {"value":value},
                        function (result) {
                            if(result == "true"){
                                meg = 1;
                            }
                        }
                    )
                    if(meg == 0){
                        reloadCode();
                        return "验证码错误!"
                    }
                },
                newpassword:function (value) {
                    if(value != $("#demo2").find("[name=password]:input").val()){
                        layer.msg("新密码与旧密码不一致!")
                    }
                }
            })

            /*
            *登陆表单提交方法
            *
            */
            form.on('submit(demo1)', function(data){
                var meg = 0;
                var user = null;
                $.ajaxSettings.async = false;
                $.post(
                    "/user/login",
                    data.field,
                    function (result) {
                        if(result == null){
                            meg = 0;
                        }else{
                            meg = 1;
                            user = result;
                            sessionStorage.setItem("user",JSON.stringify(user));
                        }
                    }
                )
                /*
                * meg=1代表登陆成功
                * */
                if(meg == 1){
                    showuser(JSON.parse(sessionStorage.getItem("user")))
                    layer.closeAll()
                    element.init()
                    return false;
                }else {
                    layer.msg("账号或密码错误!");
                    //清空表单的同时刷新验证码
                    $("#demo1").find('input[type=text],input[type=password]').each(function() {
                        $(this).val('');
                    });
                    reloadCode();
                    return false;
                }
            });

            form.on('submit(demo2)',function () {
                var password = $("#demo2").find("[name=password]:input").val()
                var newpassword = $("#demo2").find("[name=newpassword]:input").val()
                if(password != newpassword){
                    // layer.msg("新密码与旧密码不一致!")
                    return false
                }else {
                    return true
                }
            })
            /*
                验证框方法
            */

            //注册时验证用户名是否存在
            //不能用children()因为这个只能取到子元素
            $("#demo2").find("[name='username']:input").blur(function () {
                var meg = 0;
                //这种用外部的变量来判断最好用同步的方式
                $.ajaxSettings.async = false;
                $.post(
                    "/user/queryByName",
                    {"username":$(this).val()},
                    function (result) {
                        // alert(result)
                        if(result == "true"){
                            meg = 1;
                        }
                    }
                )
                if(meg == 1){
                    $(this).val('')
                    layer.msg("该用户名已存在!")
                }
            })
        })
    })

    /**
     * 如果登陆过则显示用户的信息
     * @param user
     */
    function showuser(user) {
        $("ul").empty()
        var lis = '<li class="layui-nav-item"><a style="color: black">欢迎您</a></li>'
        var li = '<li class="layui-nav-item">\n' +
            '        <a style="color: black" id="nickname"></a>\n' +
            '        <dl class="layui-nav-child">\n' +
            '        <dd><a href="javascript:;">修改信息</a></dd>\n' +
            '        <dd><a href="javascript:;">安全管理</a></dd>\n' +
            '        <dd><a id="exit" style="color: black">退了</a></dd>\n' +
            '        </dl>\n' +
            '    </li>'
        lis +=li
        $("ul").html(lis)
        $("#nickname").html('<img src="'+user.headerimg+'" class="layui-nav-img">&nbsp;&nbsp;'+user.nickname+'')
        $("#exit").click(function () {
            exit()
        })
        layui.use(['element'],function () {
            var element = layui.element;
            element.init()
        })
    }

    /*
        这个方法貌似没啥作用
    */
    function login(user) {
        showuser(user)
        layui.use(['element','layer','jquery','form'],function () {
            var element = layui.element;
            var layer = layui.layer,form = layui.form,$ = layui.jquery;
            // 重新渲染页面
            element.init()
            // layer.open({
            //     type:1,
            //     skin: 'layui-layer-rim', //加上边框
            //     area: '360px', //宽高
            //     content: $("#demo1")
            // })
        })
    }
    /**
     * 重新加载验证码
     * @param user
     */
    function reloadCode(){
        var time = new Date();
        // 给URL传递参数可以清空浏览器的缓存，让浏览器认为这是一个新的请求
        document.getElementById('safecode').src = '/imageServlet?d=' + time;
        //前端获取不到后端改变的session值 就是不能即时获取
        <%--i++--%>
        <%--code = "<%=session.getAttribute("piccode")%>";--%>
        <%--alert(code+"----"+i)--%>
    }
    /*
    * 退出方法
    * */
    function exit() {
        // alert("123")
        sessionStorage.removeItem("user")
        location.reload()
    }
</script>
