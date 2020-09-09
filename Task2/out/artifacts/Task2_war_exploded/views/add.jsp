<%--
  Created by IntelliJ IDEA.
  User: Zamanbek
  Date: 09.09.2020
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="templates/cdns.jsp" %>
</head>
<body>
<%@include file="templates/navbar.jsp" %>
<form action="/add" method="POST" class="container">
    <div class="form-group">
        <label for="from">From city</label>
        <input type="text" class="form-control" id="from" name="from" placeholder="Enter Start">
<%--        <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>--%>
    </div>
    <div class="form-group">
        <label for="to">To city</label>
        <input type="text" class="form-control" id="to" name="to" placeholder="Enter Finish">
    </div>
    <div class="form-group">
        <label for="price">Price</label>
        <input type="number" class="form-control" id="price" name="price" placeholder="Enter Price">
    </div>
    <div class="form-group">
        <label for="duration">Duration</label>
        <input type="number" class="form-control" id="duration" name="duration" placeholder="Enter Duration">
    </div>
    <button type="submit" class="btn btn-primary">Add Ticket</button>
</form>
</body>
</html>
