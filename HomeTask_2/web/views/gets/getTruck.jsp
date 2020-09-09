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
<%
    Truck transport = (Truck)request.getAttribute("transport");
    if(transport!=null){
%>
<div class="container">
    <div class="jumbotron" style="background-color: #E4E5E4">
        <h3 class="display-4" style="font-weight: 600"><%=transport.getTransportName()%></h3>
        <h3 class="display-6" style="font-weight: 500"> <%=transport.getTransportPrice()%> USD</h3>
        <hr class="my-4">
        <p>liftingCapacity: <%=transport.getLiftingCapacity()%> KG</p>
        <p>trailerPrice: <%=transport.getTrailerPrice()%> USD</p>

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
    document.getElementById("navbar").style.backgroundColor = "#6280B5";
</script>
</body>
</html>
