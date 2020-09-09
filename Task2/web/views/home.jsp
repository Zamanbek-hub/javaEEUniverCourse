<%@ page import="db.clasess.Ticket" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Zamanbek
  Date: 09.09.2020
  Time: 9:22
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
<div class="container">
    <%
        ArrayList<Ticket> tickets = (ArrayList<Ticket>)request.getAttribute("tickets");
        int total = (int)request.getAttribute("total");
        if(tickets!=null){
    %>
    <h1>Total:  <%=total%>  </h1>
    <%

        for(Ticket ticket: tickets){
    %>
    <div class="jumbotron">
        <h1 class="display-4"><%=ticket.getFromCity()%> - <%=ticket.getToCity()%></h1>
        <p class="lead">Price: <%=ticket.getPrice()%> KZT</p>
<%--        <hr class="my-4">--%>
        <p>Duration: <%=ticket.getPrice()%> Hours</p>
        <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/get?id=<%=ticket.getId()%>" role="button">Details</a>
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
