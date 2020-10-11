<%--
  Created by IntelliJ IDEA.
  User: zaman
  Date: 10/6/2020
  Time: 3:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="templates/cdns.jsp" %>
    <%@include file="./templates/styles.jsp" %>
</head>
<body>
<%@include file="templates/navbar.jsp" %>
<%@include file="templates/alerts.jsp" %>
<div class="container d-flex justify-content-center">
    <form method="POST" action="/login">
        <div class="title">
            <h3>Sign In</h3>
        </div>
        <div class="form-group">
            <label for="email">Email address</label>
            <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp">
            <small  class="form-text text-muted">Input your email</small>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" name="password">
            <small class="form-text text-muted">Input your password</small>
        </div>
        <div class="form-check">
            <input type="checkbox" class="form-check-input" value="true" id="remember_me" name="remember_me">
            <label class="form-check-label" for="remember_me">Remember me</label>
        </div>
        <button type="submit" class="btn submit_button">Submit</button>
    </form>
</div>
<%@include file="templates/footer.jsp" %>
</body>
</html>
