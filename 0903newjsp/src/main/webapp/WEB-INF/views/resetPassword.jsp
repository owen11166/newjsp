<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reset Password</title>
</head>
<body>
<h2>Reset Password</h2>
<form action="resetPassword" method="post">
	<label for="email">Email:</label>
	<input type="email" id="email" name="email" required>
	<br><br>
	<input type="submit" value="Submit">
</form>
</body>
</html>
