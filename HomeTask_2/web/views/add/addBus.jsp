<%@ page import="interfaces.Transports" %>
<%@ page import="db.classes.Bus" %><%--
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
<form class="container mx-auto"  action="/add/bus" method="POST" style="width: 500px">
    <%@include file="addDefaultFields.jsp" %>
    <div class="form-group">
        <label for="passengerPlaces">passengerPlaces</label>
        <input type="number" class="form-control" id="passengerPlaces" name="passengerPlaces" placeholder="Type passengerPlaces">
    </div>
    <button type="submit" class="btn btn-primary" id="submit">+Add New Bus</button>
</form>


<script type="text/javascript">
    document.getElementById("navbar").style.backgroundColor = "#9A1313";
    document.getElementById("submit").style.backgroundColor = "#9A1313";

</script>
</body>
</html>
