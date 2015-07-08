<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <#import "page.ftl" as page>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <!-- 新 Bootstrap 核心 CSS 文件 -->
        <link rel="stylesheet" href="/css/bootstrap.min.css" />


        <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
        <script src="/js/jquery-1.11.1.min.js"></script>

        <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
        <script src="/js/bootstrap.min.js"></script>

        <title>日志中心</title>
        <script type="text/javascript">
        $(function() {




            $("#systemCode").change(function() {
                $("#queryFrom").submit();
            });

            $("#search").click(function() {
                $("#queryFrom").submit();
            });


        })
        </script>
        <style>
        </style>
</head>

<body>
    <div class="container">

        <div class="rightbar fl table-responsive" style="overflow-x:hidden">
            
            <table class="table table-striped  table-hover ">

                <caption style="text-align: center">
                    <h1>文件记录</h1>
                    <form method="post" id="queryFrom" class="form-inline">
                        <fieldset>

                            <div class="row">

                                <div class="col-lg-6">

                                </div>
                                <div class="col-lg-6">

                                    <div class="input-group">
                                        <input type="text" class="form-control" placeholder="文件ID..." id="keyword" value='${keyword!""}' name="keyword">
                                        <span class="input-group-btn">
                                            <button id="search" class="btn btn-default" type="button">Go!</button>
                                        </span>
                                        </input>
                                    </div>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </caption>
                <thead>
                    <tr>
                        <th>系统号</th>
                        <th>用户名</th>
                        <th>ip</th>
                        <th>操作</th>
                        <th>记录时间</th>
                        <th>文件ID</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <#list resultVo.content as item>
                        <div>
                            <tr>
                                <td>${item.systemCode!''}</td>
                                <td>${item.username!'' }</td>
                                <td>${item.ip!'' }</td>
                                <td>${item.remark!''}</td>
                                <td>${item.logTime?string('yyyy-MM-dd HH:mm:ss')!''}
                                </td>
                                <td>${item.fileId!''}</td>
                                <td>
                                    <a class="btn btn-info" href="/file/${(item.fileId)!''}" role="button">下载文件</a>

                                </td>


                            </tr>

                        </div>
                    </#list>
                </tbody>
            </table>

            <div class="clearfix">
                <@page.greet page=resultVo queryForm="queryFrom" size=10/>


            </div>
        </div>
         <div align="center">
    <span class="glyphicon glyphicon-user"></span>${uname!""},您好
     <a href="/" class="btn">
            <span class="glyphicon glyphicon-step-backward"></span>返回</a>
        <a href="/logout" class="btn">
            <span class="glyphicon glyphicon-off "></span>退出</a>
    </div>
    </div>
</body>

</html>
