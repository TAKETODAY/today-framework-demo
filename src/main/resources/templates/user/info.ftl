<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>用户信息</title>
</head>
<body>

<h1>${msg}</h1>
<img src="">
姓名：${USER_INFO.name}<br/>
bio：${USER_INFO.description}<br/>
email：${USER_INFO.email} <br/>
blog：<a href="${USER_INFO.site}">
    ${USER_INFO.site}
</a>
<br/>
background: ${USER_INFO.background}

</body>
</html>
