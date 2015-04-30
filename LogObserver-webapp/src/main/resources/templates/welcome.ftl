<!DOCTYPE html>

<html lang="en">
<#assign ages = {"Joe":23, "Fred":25} + {"Joe":30, "Julia":18}>
<#assign atime = ["a","b","c","d"]>
<body>
    <#if message ="Hello, Andy1">
    one message
    <#else>
    no message
    </#if>
<br>
<#list atime as age>
${age}
</#list>
${ages.Joe}
<br>
	Date: ${time?date}
	<br>
	Time: ${time?time}
	<br>
	Message: ${message}
</body>

</html>
