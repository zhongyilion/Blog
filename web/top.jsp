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
    <ul class="layui-nav layui-layout-right" style="background-color: white" id="lis">
        <li class="layui-nav-item" style="cursor: pointer;display: none;" id="one"><a id="login" style="color: black">登陆</a></li>
        <li class="layui-nav-item" style="cursor: pointer;display: none" id="two"><a id="register" style="color: black">注册</a></li>
        <script src="res/js/util/jquery-3.3.1.js"></script>
        <script>
            if(sessionStorage.getItem("user") == null){
                // $("#lis").children().eq(0).css("display","none")
                // $("#lis").eq(1).attr("display","block")
                $("#one").css("display","")
                $("#two").css("display","")
            }else {
                $("#one").css("display","none")
                $("#two").css("display","none")
            }
        </script>
        <li class="layui-nav-item" style="display: none" id="three"><a style="color: black">欢迎您</a></li>
        <li class="layui-nav-item" lay-unselect="" style="display: none" id="four">
            <a id="nickname" style="color: black"></a>
            <dl class="layui-nav-child">
                <dd><a href="javascript:;" id="edit">修改信息</a></dd>
                <dd><a href="javascript:;">安全管理</a></dd>
                <dd><a href="javascript:;" id="exit">退了</a></dd>
            </dl>
        </li>
        <script>
            if(sessionStorage.getItem("user") != null){
                $("#three").css("display","")
                $("#four").css("display","")
                var user = JSON.parse(sessionStorage.getItem("user"))
                // $("#nickname").html('<img src="'+user.image+'" class="layui-nav-img"> '+user.nickname+'')

                // $("#nickname").html('<img src="'+user.image+'" class="layui-nav-img">'+user.nickname)
                // alert(user.nickname)
                // $("#nn").html(user.nickname)
            }
        </script>
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
        <label class="layui-form-label">性别</label>
        <div class="layui-input-block">
            <input type="radio" name="sex" value="男" title="男" checked="">
            <input type="radio" name="sex" value="女" title="女">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">年龄:</label>
        <div class="layui-input-block" style="width: 200px">
            <input type="text" name="age" lay-verify="required|age" placeholder="请输入年龄" autocomplete="off" class="layui-input">
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

<%--信息编辑框--%>
<form class="layui-form" id="demo3" style="display: none" action="#">
    <div class="layui-form-item" style="margin-top: 20px">
        <label class="layui-form-label">用户名:</label>
        <div class="layui-input-block" style="width: 200px">
            <input type="text" name="username" lay-verify="required|username" autocomplete="off" placeholder="请输入用户名" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">昵称:</label>
        <div class="layui-input-block" style="width: 200px">
            <input type="text" name="nickname" lay-verify="required" placeholder="请输入昵称" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-block">
            <input type="radio" name="sex" value="男" title="男" checked="">
            <input type="radio" name="sex" value="女" title="女">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">年龄:</label>
        <div class="layui-input-block" style="width: 200px">
            <input type="text" name="age" lay-verify="required|age" placeholder="请输入年龄" autocomplete="off" class="layui-input">
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
            <button class="layui-btn" lay-submit="" lay-filter="demo3">编辑</button>
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

        layui.use(['element','layer','jquery','form','upload'],function () {
            var element = layui.element;
            var layer = layui.layer,form = layui.form,$ = layui.jquery,upload=layui.upload;
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
                    $("#one").css("display","none")
                    $("#two").css("display","none")
                    $("#three").css("display","")
                    $("#four").css("display","")

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
            * 为编辑框提供异步刷新
            * */


            form.on('submit(demo3)',function (data) {
                // alert("？？？")
                $.ajaxSettings.async = false;
                $.post('/user/editUserMessage',data.field,function (user) {
                    sessionStorage.setItem("user",JSON.stringify(user))
                })
                // layui.closeAll()
                location.reload()
                return false;
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
     *
     */

    function showuser(user) {

        $("#exit").click(function () {
            exit()
        })
        /*
        * 为编辑信息添加弹窗
        * */
        $("#edit").click(function () {
            layer.open({
                type:1,
                title:'<h3>编辑</h3>',
                skin: 'layui-layer-rim', //加上边框
                area: '360px', //宽
                content: $("#demo3"),
                end : function() {
                    $(".layui-layer-shade").css("display","none")}
            })

            var message = JSON.parse(sessionStorage.getItem("user"));
            $("#demo3").find("[name='username']:input").val(message.userName)
            // 设置为只读
            $("#demo3").find("[name='username']:input").attr("readonly","readonly")
            $("#demo3").find("[name='nickname']:input").val(message.nickname)
            $("#demo3").find("[name='age']:input").val(message.age)
            $("#demo3").find("[value='女']:input").attr("checked",'checked')
            $("#demo3").find("[name='phone']:input").val(message.telephone)
            $("#demo3").find("[name='email']:input").val(message.email)

        })
        $("#nickname").html('<img src="'+user.image+'" class="layui-nav-img" id="test1" style="cursor: pointer"> '+user.nickname+'')

        layui.use(['element','form','upload'],function () {
            var element = layui.element,form = layui.form,upload=layui.upload;
            var uploadInst = upload.render({
                elem: '#test1'
                ,url: '/user/upload'
                // ,feild: 'mf'
                ,before: function(obj){
                    //预读本地文件示例，不支持ie8
                    obj.preview(function(index, file, result){
                        $('#demo1').attr('src', result); //图片链接（base64）
                    });
                }
                ,done: function(res){
                    //如果上传失败
                    if(res.code > 0){
                        return layer.msg('上传失败');
                    }else if (res.code == 0){
                        sessionStorage.setItem("user",JSON.stringify(res.user))
                        // location.reload()
                        $("#test1").attr("src",res.user.image)
                        return layer.msg('修改成功!')
                    }

                }
                ,error: function(){
                    //演示失败状态，并实现重传
                    var demoText = $('#demoText');
                    demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                    demoText.find('.demo-reload').on('click', function(){
                        uploadInst.upload();
                    });
                }
            });
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

        $.post(
            "/user/exit",
            function () {
                sessionStorage.removeItem("user")
                location.reload()
            }
        )
    }
</script>
