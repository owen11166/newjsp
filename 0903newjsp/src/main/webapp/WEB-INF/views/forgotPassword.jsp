<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Forgot Password</title>
</head>
<body>
	<h2>Forgot Password</h2>
	<form:form action="${pageContext.request.contextPath}/forgotPassword"
		method="POST">
		<div>
			<label for="email">Email</label> <input type="email" id="email"
				name="email" required>
		</div>
		<button type="submit">Submit</button>
	</form:form>

	<c:if test="${not empty error}">
		<div class="error">${error}</div>
	</c:if>

	<c:if test="${not empty forgotSuccess}">
		<div class="success">A new password has been sent to your email.</div>
	</c:if>
</body>
</html>
