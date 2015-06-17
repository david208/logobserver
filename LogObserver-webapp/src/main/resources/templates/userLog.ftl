<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <#import "page.ftl" as page>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <!-- 新 Bootstrap 核心 CSS 文件 -->
        <link rel="stylesheet" href="/css/bootstrap.min.css" />

        <link type="text/css" rel="stylesheet" href="/style/bootstrap-switch.min.css" />
        <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
        <script src="/js/jquery-1.11.1.min.js"></script>

        <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
        <script src="/js/bootstrap.min.js"></script>
        <script src="/js/bootstrap-switch.min.js" type="text/javascript"></script>

        <title>日志中心</title>
        <script type="text/javascript">
        var refreshTime;
        $(function() {
            if ("${refreshTime?c}" == "")
                refreshTime = 10000;
            else
                refreshTime = parseInt("${refreshTime?c}");
            $("input[value=" + refreshTime + "]").attr("checked", "checked");
            if ("${type}" == "" || "${type}" == "true")
                var type = true;
            else
                var type = false;
            /*  $("[name='my-checkbox']").bootstrapSwitch(); */
            $('input[name="my-checkbox"]').bootstrapSwitch('state', type);
            $("#type").val(type);
            if (type == true)
                refreash();
            $('input[name="my-checkbox"]').on(
                'switchChange.bootstrapSwitch',
                function(event, state) {
                    type = $('input[name="my-checkbox"]').bootstrapSwitch(
                        'state');
                    $("#type").val(type);
                    if (type == true)
                        refreash();
                    else
                        close();

                });

            $("#systemCode").change(function() {
                $("#queryFrom").submit();
            });

            $("#search").click(function() {
                $("#queryFrom").submit();
            });

            $("input[name=refreshTime]").click(function() {
                $("#queryFrom").submit();
            });

            $(".moreBtn").click(function() {
                $(this).parent().parent().next().toggleClass("hidden");
                $(this).parent().parent().next().next().toggleClass("hidden");
                $('input[name="my-checkbox"]').bootstrapSwitch('state', false, false);
            });

        })
         var timer;

        function myrefresh() {
            window.location.reload();
            //window.location.replace(location);
        }

        function refreash() {
            timer = setInterval('myrefresh()', refreshTime);
        } //指定n秒刷新一次

        function close() {
            clearInterval(timer);
        }
        </script>
        <style>
        </style>
</head>

<body>
    <div class="container">

        <div class="rightbar fl table-responsive" style="overflow-x:hidden">
            <div align="right">
                
                    <span class="glyphicon glyphicon-user"></span>${uname!""},您好
                <a href="/logout" class="btn">
                    <span class="glyphicon glyphicon-off "></span>退出</a>

            </div>
            <table class="table table-striped  table-hover ">

                <caption style="text-align: center">
                    <h1>用户操作记录</h1>
                    <form method="post" id="queryFrom" class="form-inline">
                        <fieldset>

                            <div class="row">
                                <div class="col-lg-2">
                                    <input id="switch-state" type="checkbox" checked="" name="my-checkbox" />
                                    <input name="type" id="type" type="hidden"></input>
                                </div>
                                <div class="col-lg-4">
                                    <label class="radio-inline">
                                        <input type="radio" name="refreshTime" id="r10000" value="10000" />10s</label>
                                    <label class="radio-inline">
                                        <input type="radio" name="refreshTime" id="r30000" value="30000" />30s</label>
                                    <label class="radio-inline">
                                        <input type="radio" name="refreshTime" id="r60000" value="60000" />60s</label>
                                </div>
                                <div class="col-lg-6">
                                    <select class="form-control" name="systemCode" id="systemCode">
                                        <option value="">全部</option>
                                        <#list systemCodeList as item>

                                            <#if systemCode?? && systemCode==item>
                                                <option value="${item}">${item}</option>
                                                <option selected value="${item}">${item}</option>
                                                <#else>
                                                    <option value="${item}">${item}</option>
                                            </#if>
                                        </#list>
                                    </select>
                                    <div class="input-group">
                                        <input type="text" class="form-control" placeholder="关键字..." id="keyword" value='${keyword!""}' name="keyword">
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
                                <td><a class="btn btn-success moreBtn" href="javascript:void(0)" role="button">更多</a>
                                    <#if (item.fileInfo.id)??>
                                        <a class="btn btn-info" href="/file/${(item.fileInfo.id)!''}" role="button">下载文件</a>

                                    </#if>
                                </td>


                            </tr>



                            <tr class="hidden success">
                                <td>输入</td>
                                <td style="word-break : break-all;" colspan=5>${item.arg!''}</td>
                            </tr>

                            <tr class="hidden success">
                                <td>输出</td>
                                <td style="word-break : break-all;" colspan=5>${item.result!''}</td>
                            </tr>


                        </div>
                    </#list>
                </tbody>
            </table>

            <div class="clearfix">
                <@page.greet page=resultVo queryForm="queryFrom" size=10/>


            </div>
        </div>
    </div>
</body>

</html>
