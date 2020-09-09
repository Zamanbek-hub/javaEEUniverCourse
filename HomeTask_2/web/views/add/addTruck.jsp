<%@ page import="interfaces.Transports" %>
<%@ page import="db.classes.Truck" %><%--
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

<form class="container mx-auto" action="/add/truck" method="POST" style="width: 500px">
    <%@include file="addDefaultFields.jsp" %>
    <div class="form-group">
        <label for="liftingCapacity">liftingCapacity</label>
        <input type="number" step="0.01" class="form-control" id="liftingCapacity" name="liftingCapacity" placeholder="Type liftingCapacity">
    </div>
    <div class="form-group">
        <label for="trailerPrice">trailerPrice</label>
        <input type="number" class="form-control" id="trailerPrice" name="trailerPrice" placeholder="Type trailerPrice">
    </div>
    <button type="submit" class="btn btn-primary" id="submit">+Add New Truck</button>
</form>

<script type="text/javascript">
    document.getElementById("navbar").style.backgroundColor = "#6280B5";
    document.getElementById("submit").style.backgroundColor = "#6280B5";

</script>
</body>
</html>
