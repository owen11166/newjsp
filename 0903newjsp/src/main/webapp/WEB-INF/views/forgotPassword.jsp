<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>忘記密碼</title>
</head>
<body>
    <form action="/process-forgot-password" method="post">
        <label for="username">用戶名:</label>
        <input type="text" id="username" name="username" >
        <br>
        <label for="email">電子郵件:</label>
        <input type="email" id="email" name="email" >
        <br>
        <input type="submit" value="提交">
    </form>
</body>
</html>
