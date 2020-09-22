<%@ page import="db.classes.Club" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="db.classes.League" %><%--
  Created by IntelliJ IDEA.
  User: Zamanbek
  Date: 21.09.2020
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="./templates/cdns.jsp" %>
    <%@include file="./templates/style.jsp" %>
</head>
<body>
<%@include file="./templates/navbar.jsp" %>
<div class="container">
    <br>
    <br>

    <%
       String path = (String)request.getAttribute("path");
    %>
    <div class="card" >
        <div class="card-body" style="font-weight: bold">
            <%=path%>
        </div>
    </div>


    <br>
    <div class="card" style="background-color: white">
        <%
            League league = (League)request.getAttribute("league");
        %>

        <div class="card-header text-center">
            <span style="font-weight: bolder"><%=league.getName()%></span>
        </div>

        <div class="card-body">
            <table class="table table-light table-bordered table-striped table-hover">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">CLUB</th>
                    <th scope="col">CITY</th>
                    <th scope="col">FOUNDED YEAR</th>
                    <th scope="col">DETAILS</th>
                </tr>
                </thead>
                <tbody>
                <%
                    ArrayList<Club> clubs = (ArrayList<Club> )request.getAttribute("clubsByLeague");

                    if(clubs!=null){
                        for(Club club: clubs){
                %>
                <tr>
        <%--            <th scope=""><%=club.getId()%></th>--%>
                    <td><%=club.getName()%></td>
                    <td><%=club.getCity().getName()%></td>
                    <td><%=club.getFoundedYear()%></td>
                    <td><a href="${pageContext.request.contextPath}/get_club?club_id=<%=club.getId()%>" ><button class="btn" style="background-color: darkviolet; color:#ffffff;">Details</button></a></td>
                </tr>
                <%
                        }
                    }
                %>


                </tbody>
            </table>
        </div>
    </div>
</div>



</body>
</html>
