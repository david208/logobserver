<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${rc.contextPath}/css/bootstrap.min.css" />
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="${rc.contextPath}/js/jquery-1.11.1.min.js"></script>
    <script src="${rc.contextPath}/js/jquery.cookie.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="${rc.contextPath}/js/bootstrap.min.js"></script>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">


    <title>日志中心</title>
    <style type="text/css">
    .mt-center {
        margin-top:25%;
        text-align: center;
    }
    .mt-center img {
        display:inline-block;
    }
    .form-signin {
        max-width: 430px;
        padding: 15px;
        margin: 0 auto;
    }
    .login-panel {
        margin-top:5%;
    }
    </style>
    <script>
    $.cookie('Hm_lvt_4174b42be17806794feef4d1a53e30d5', '', { expires: -1,domain:'.jlfex.com'}); 
    </script>
</head>

<body>
    <div class="container">
        <div class="row">
            <div class="mt-center">

                <div class="login-panel form-signin">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                <span class="glyphicon glyphicon-log-in"></span>请登录</h3>
                        </div>
                        <div class="panel-body">
                            <#if (RequestParameters.error)??>
                                <div class="alert alert-danger" role="alert">
                                    <span class="glyphicon glyphicon-warning-sign"></span>用户名密码不正确</div>
                            </#if>
                            <form name="form" action="${rc.contextPath}/login" method="post">

                                <fieldset>
                                    <div class="form-group">
                                        <div class="input-group">
                                            <span class="input-group-addon">
                                                <span class="glyphicon glyphicon-user"></span>
                                            </span>
                                            <input type="text" class="form-control" placeholder="用户名" name="username" type="username" autofocus>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="input-group">
                                            <span class="input-group-addon">
                                                <span class="glyphicon glyphicon-lock"></span>
                                            </span>
                                            <input type="password" class="form-control" placeholder="密码" name="password" type="password" value="">
                                        </div>
                                    </div>

                                    <!-- Change this to a button or input when using this as a form -->
                                    <input type="submit" class="btn btn-warning btn-block" value="登录">
                                </fieldset>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


</body>

</html>
