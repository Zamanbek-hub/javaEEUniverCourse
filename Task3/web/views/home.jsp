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

<form class="container-fluid " action="/" method="GET">
    <div class="row">
        <div class="form-group col-3">
            <label for="name">Name:</label>
            <input type="text" class="form-control" id="name" name="name"  placeholder="Type Name">
        </div>
        <div class="form-group col-3">
            <label for="surname">Surname:</label>
            <input type="text" class="form-control" id="surname" name="surname" placeholder="Type Surname">
        </div>
        <div class="form-group col-3">
            <label for="iin">IIN:</label>
            <input type="number" class="form-control" id="iin" name="iin"  placeholder="Type IIN" oninput="if(this.value.length>=12) { this.value = this.value.slice(0,12); }">
        </div>
        <div class="form-group col-3">
            <label for="is_grant">Is Grant:</label>
            <select class="form-control" id="is_grant" name="is_grant">
                <%
                    boolean is_grant = (boolean) request.getAttribute("is_grant");
                    if (is_grant) {%>
                <option selected value="True">yes</option>
                <option value="False">no</option>
                <% }else {%>
                <option selected value="False">no</option>
                <option value="True">yes</option>
                <% }%>
            </select>
        </div>
    </div>
    <button type="submit" class="btn"  style="background-color: #AA3FFC; float:right; color:white;">Filter</button>
</form>

<br>
<hr>

<div class="container">



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
