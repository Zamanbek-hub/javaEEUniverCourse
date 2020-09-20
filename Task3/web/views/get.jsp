<%@ page import="classes.Student" %><%--
  Created by IntelliJ IDEA.
  User: Zamanbek
  Date: 16.09.2020
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>GET</title>
    <%@include file="./../templates/cdns.jsp" %>
    <%@include file="./../templates/navbarStyles.jsp" %>
</head>
<body>

    <%@include file="./../templates/navbar.jsp" %>
    <%
        Student student = (Student)request.getAttribute("student");
        if(student != null){
    %>
    <div class="container mx-auto" style="width: 700px">
        <h3><big>STUDENT INFO</big></h3>
        <br>
        <div class="form-group">
            <label for="name">Name:</label>
            <p class="form-control" id="name"><%=student.getName()%></p>
        </div>
        <div class="form-group">
            <label for="surname">Surname:</label>
            <p class="form-control" id="surname"><%=student.getSurname()%></p>
        </div>
        <div class="form-group">
            <label for="middle_name">Middle Name:</label>
            <p class="form-control" id="middle_name"><%=student.getMiddleName()%></p>
        </div>
        <div class="form-group">
            <label for="birthdate">Birthdate:</label>
            <p class="form-control" id="birthdate"><%=student.getBirthdate()%></p>
        </div>
        <div class="form-group">
            <label for="iin">IIN:</label>
            <p class="form-control" id="iin"><%=student.getIin()%></p>
        </div>
        <div class="form-group">
            <label for="is_grant">Is Grant:</label>
            <p class="form-control" id="is_grant"><%=student.isGrant()%></p>
        </div>
        <div class="form-group">
            <label for="specialty">Specialty:</label>
            <p class="form-control" id="specialty"><%=student.getSpecialty()%></p>
        </div>

        <a href="/edit?id=<%=student.getId()%>" style="float:right;"><button type="submit" class="btn"  style="background-color: darkviolet; color: white">Edit</button></a>
        <form action="/delete" method="POST"  style="float:left">
            <input type="hidden" name="id" value="<%=student.getId()%>">
            <button type="submit" class="btn btn-danger" >Delete</button>
        </form>
    </div>
    <% } %>
</body>
</html>
