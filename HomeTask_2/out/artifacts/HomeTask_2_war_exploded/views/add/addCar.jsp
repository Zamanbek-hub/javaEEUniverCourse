<%@ page import="interfaces.Transports" %>
<%@ page import="db.classes.Car" %><%--
  Created by IntelliJ IDEA.
  User: Zamanbek
  Date: 09.09.2020
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="../templates/cdns.jsp" %>
    <%@include file="../templates/navbarStyles.jsp" %>
</head>
<body>
<%@include file="../templates/navbar.jsp" %>

<form class="container mx-auto" action="/add/car" method="POST" style="width: 500px">
    <%@include file="addDefaultFields.jsp" %>
    <div class="form-group">
        <label for="carcaseType">carcaseType</label>
        <input type="text" class="form-control" id="carcaseType" name="carcaseType" placeholder="Type carcaseType">
    </div>
    <div class="form-group">
        <label for="maxSpeed">maxSpeed</label>
        <input type="number" class="form-control" id="maxSpeed" name="maxSpeed" placeholder="Type maxSpeed">
    </div>
    <div class="form-group">
        <label for="engineVolume">engineVolume</label>
        <input type="number" step="0.01" class="form-control" id="engineVolume" name="engineVolume" placeholder="Type engineVolume">
    </div>
    <div class="form-group">
        <label for="year">Year</label>
        <input type="number" class="form-control" id="year" name="year" placeholder="Type year">
    </div>
    <button type="submit" class="btn btn-primary" id="submit">+Add New Car</button>
</form>

<script type="text/javascript">
    document.getElementById("navbar").style.backgroundColor = "#909545";
    document.getElementById("submit").style.backgroundColor = "#909545";
</script>
</body>
</html>
