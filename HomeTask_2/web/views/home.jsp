<%@ page import="interfaces.Transports" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Zamanbek
  Date: 09.09.2020
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="templates/cdns.jsp" %>
    <%@include file="templates/navbarStyles.jsp" %>
</head>
<body>
<%@include file="templates/navbar.jsp" %>

<div class="container">
    <%
        ArrayList<Transports> transports = (ArrayList<Transports>)request.getAttribute("transports");
        int total = (int)request.getAttribute("total");
        if(transports!=null){
    %>
    <h1>Total:  <%=total%>  </h1>
    <%

        for(Transports transport: transports){
    %>
    <div class="jumbotron" style="background-color: #C2F7D2">
        <h3 class="display-4" style="font-weight: 600"><%=transport.getTransportName()%></h3>
        <h3 class="display-6" style="font-weight: 500"> <%=transport.getTransportPrice()%> USD</h3>
        <hr class="my-4">
        <p><%=transport.getTransportDescription()%></p>
        <a class="btn btn-primary btn-lg" style="background-color: #2D8347" href="${pageContext.request.contextPath}/get?id=<%=transport.getId()%>" role="button">Read More</a>
    </div>
    <%
            }
        }
    %>



    <nav aria-label="Page navigation example">
        <ul class="pagination">

            <%
                int pages = (int)request.getAttribute("pages");
                int active_page = (int)request.getAttribute("active_page");

                // to page won't be < 1
                if(active_page > 1){
            %>
            <li class="page-item"><a class="page-link" href="/home?page=<%=active_page-1%>">Previous</a></li>
            <%
                }
                for(int i = 1; i<pages+1; i++){
                    if(i == active_page){
            %>
            <li class="page-item active"><a class="page-link" href="/home?page=<%=i%>"><%=i%></a></li>
            <%
            }
            else{
            %>
            <li class="page-item"><a class="page-link" href="/home?page=<%=i%>"><%=i%></a></li>
            <%
                    }
                }
                // to page won't be > pages
                if(active_page < pages){
            %>

            <li class="page-item"><a class="page-link" href="/home?page=<%=active_page+1%>">Next</a></li>
            <%
                }

            %>
        </ul>
    </nav>


</div>


</body>
</html>
