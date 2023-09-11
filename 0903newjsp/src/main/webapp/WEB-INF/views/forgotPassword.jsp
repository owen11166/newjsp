<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>忘記密碼</title>
</head>
<body>
<% if(request.getAttribute("errorMessage") != null) { %>
    <p style="color: red;"><%= request.getAttribute("errorMessage") %></p>
<% } %>
	<form action="/fp" method="post">
		<label for="username">用戶名:</label> <input type="text" id="username"
			name="username"> <br> <label for="email">電子郵件:</label>
		<input type="email" id="email" name="email"> <br> <input
			type="submit" value="提交">
	</form>
</body>
</html>
