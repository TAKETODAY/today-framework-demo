<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>登录</title>
</head>
<body>

<form action="login" method="POST">
    userId:<input type="text" name="email" value="${email}"><br>
    passwd:<input type="password" name="password"><br>
    <input type="submit" value="登录">
</form>

<p color="red" style="color: red;">${msg}</p>

</body>
</html>
