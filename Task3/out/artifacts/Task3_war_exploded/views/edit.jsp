<%@ page import="classes.Student" %><%--
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

<%
    Student student = (Student)request.getAttribute("student");
    if(student != null){
%>
    <form class="container mx-auto" action="/edit" method="POST" style="width: 700px">
        <h3><big>EDIT STUDENT</big></h3>
        <br>
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" class="form-control" id="name" name="name" value="<%=student.getName()%>" placeholder="Type Name">
        </div>
        <div class="form-group">
            <label for="surname">Surname:</label>
            <input type="text" class="form-control" id="surname" name="surname" value="<%=student.getSurname()%>" placeholder="Type Surname">
        </div>
        <div class="form-group">
            <label for="middle_name">Middle Name:</label>
            <input type="text" class="form-control" id="middle_name" name="middle_name" value="<%=student.getMiddleName()%>" placeholder="Type Middle name">
        </div>
        <div class="form-group">
            <label for="birthdate">Birthdate:</label>
            <input type="date" class="form-control" id="birthdate" name="birthdate" value="<%=student.getBirthdate()%>" placeholder="Type Birthdate">
        </div>
        <div class="form-group">
            <label for="iin">IIN:</label>
            <input type="number" class="form-control" id="iin" name="iin" value="<%=student.getIin()%>" placeholder="Type IIN" oninput="if(this.value.length>=12) { this.value = this.value.slice(0,12); }">
        </div>
        <div class="form-group">
            <label for="is_grant">Is Grant:</label>
            <select class="form-control" id="is_grant" name="is_grant">
                <% if (student.isGrant()) {%>
                <option selected value="True">yes</option>
                <option value="False">no</option>
                <% }else {%>
                <option selected value="False">no</option>
                <option value="True">yes</option>
                <% }%>
            </select>
        </div>
        <div class="form-group">
            <label for="specialty">Specialty:</label>
            <select class="form-control" id="specialty" name="specialty">
                <option selected><%=student.getSpecialty()%></option>
            </select>
        </div>

        <input type="hidden" name="edited_id" value="<%=student.getId()%>">

        <button type="submit" class="btn"  style="background-color: darkviolet; color: white">Edit Student</button>
    </form>
<% } %>
<script type="text/javascript">
    const specialties = ["CSSE", "IS", "SIB"]
    const select = document.getElementById("specialty");
    options = select.options;
    specialties.forEach(function (spec){
        console.log(spec);
        if(spec !== options[options.selectedIndex].value){
            select.innerHTML += "<option>" + spec + "</option>"
        }
    });
</script>
</body>
</html>
