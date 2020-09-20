<%--
  Created by IntelliJ IDEA.
  User: Zamanbek
  Date: 16.09.2020
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ADD</title>
    <%@include file="./../templates/cdns.jsp" %>
    <%@include file="./../templates/navbarStyles.jsp" %>
</head>
<body>
<%@include file="./../templates/navbar.jsp" %>

<%@include file="./../templates/messages.jsp" %>
<form class="container mx-auto" action="/add" method="POST" style="width: 700px">
    <h3><big>ADD STUDENT</big></h3>
    <br>
    <div class="form-group">
        <label for="name">Name:</label>
        <input type="text" class="form-control" id="name" name="name" placeholder="Type Name">
    </div>
    <div class="form-group">
        <label for="surname">Surname:</label>
        <input type="text" class="form-control" id="surname" name="surname" placeholder="Type Surname">
    </div>
    <div class="form-group">
        <label for="middle_name">Middle Name:</label>
        <input type="text" class="form-control" id="middle_name" name="middle_name" placeholder="Type Middle name">
    </div>
    <div class="form-group">
        <label for="birthdate">Birthdate:</label>
        <input type="date" class="form-control" id="birthdate" name="birthdate" placeholder="Type Birthdate">
    </div>
    <div class="form-group">
        <label for="iin">IIN:</label>
        <input type="number" class="form-control" id="iin" name="iin" placeholder="Type IIN" oninput="if(this.value.length>=12) { this.value = this.value.slice(0,12); }">
    </div>
    <div class="form-group">
        <label for="is_grant">Is Grant:</label>
        <select class="form-control" id="is_grant">
            <option value="True">yes</option>
            <option value="False">no</option>
        </select>
    </div>
    <div class="form-group">
        <label for="specialty">Specialty:</label>
        <select class="form-control" id="specialty" name="specialty">
            <option value="CSSE">CSSE</option>
            <option  value="SIB">SIB</option>
            <option value="IS">IS</option>
        </select>
    </div>

    <button type="submit" class="btn"  style="background-color: darkviolet; color: white">+Add Student</button>
</form>
</body>
</html>
