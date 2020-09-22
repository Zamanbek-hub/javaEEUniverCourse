<%@ page import="classes.Student" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: Zamanbek
  Date: 16.09.2020
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HOME</title>
    <%@include file="./../templates/cdns.jsp" %>
    <%@include file="./../templates/navbarStyles.jsp" %>
</head>
<body>

<%@include file="./../templates/navbar.jsp" %>
<div class="container">
    <form action="/home" method="GET">


    <table class="table">
        <thead class="thead-light">
        <tr>
            <th scope="col">ID</th>
            <th scope="col">NAME</th>
            <th scope="col">SURNAME</th>
            <th scope="col">IIN</th>
            <th scope="col">BIRTHDATE</th>
            <th scope="col">IS GRANT</th>
            <th scope="col">Details</th>
        </tr>
        </thead>
        <tbody>
        <%
            ArrayList<Student> students = (ArrayList<Student> )request.getAttribute("students");

            if(students!=null){
                for(Student student: students){
        %>
        <tr>
            <th scope="row"><%=student.getId()%></th>
            <td><%=student.getName()%></td>
            <td><%=student.getSurname()%></td>
            <td><%=student.getIin()%></td>
            <td><%=student.getBirthdate()%></td>
            <td><%=student.isGrant()%></td>
            <td><a href="${pageContext.request.contextPath}/get?id=<%=student.getId()%>" ><button class="btn" style="background-color: darkviolet; color:white;">Details</button></a></td>
        </tr>
        <%
                }
            }
        %>


        </tbody>
    </table>

</div>


</body>
</html>
