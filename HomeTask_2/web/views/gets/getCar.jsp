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

<%
    Car transport = (Car)request.getAttribute("transport");
    if(transport!=null){
%>
<div class="container">
    <div class="jumbotron" style="background-color: #E4E5E4">
        <h3 class="display-4" style="font-weight: 600"><%=transport.getTransportName()%></h3>
        <h3 class="display-6" style="font-weight: 500"> <%=transport.getTransportPrice()%> USD</h3>
        <hr class="my-4">
        <p>carcaseType: <%=transport.getCarcaseType()%></p>
        <p>maxSpeed: <%=transport.getMaxSpeed()%> km/h</p>
        <p>engineVolume: <%=transport.getEngineVolume()%> liters</p>
        <p>year: <%=transport.getYear()%></p>
        <form action="/delete" method="POST">
            <input type="hidden" name="id" value="<%=transport.getId()%>">
            <button class="btn btn-danger">Delete</button>
        </form>
    </div>
</div>
<%
    }
%>
<script type="text/javascript">
    document.getElementById("navbar").style.backgroundColor = "#909545";
</script>
</body>
</html>
