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
<%@include file="./templates/messages.jsp" %>
<div class="container d-flex justify-content-center">

    <form method="POST" action="/register">
        <div class="title">
            <h3>Create New Account</h3>
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
        <div class="form-group">
            <label for="re_password">Re-Password</label>
            <input type="password" class="form-control" id="re_password" name="re_password">
            <small class="form-text text-muted">Re input your password again</small>
        </div>
        <div class="form-group">
            <label for="full_name">Full name</label>
            <input type="text" class="form-control" id="full_name" name="full_name">
            <small  class="form-text text-muted">Input your full name</small>
        </div>
        <div class="form-group">
            <label for="birthdate">Birthdate</label>
            <input type="date" class="form-control" id="birthdate" name="birthdate">
            <small  class="form-text text-muted">Input your birthdate</small>
        </div>
        <div class="form-check">
            <input type="checkbox" class="form-check-input" id="remember_me" name="remember_me">
            <label class="form-check-label" for="remember_me">Remember me</label>
        </div>
        <button type="submit" class="btn submit_button">Submit</button>
    </form>
</div>
<%@include file="templates/footer.jsp" %>
</body>
</html>
