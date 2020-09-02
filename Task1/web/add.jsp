<%--
  Created by IntelliJ IDEA.
  User: Zamanbek
  Date: 02.09.2020
  Time: 10:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Footballer</title>
</head>
<body>
    <a href="/">List All FootBallers</a>
    <br><br><br>
    <form action="/add" style="border:2px solid grey" method="POST">
        <label>Name:</label>
        <input type="text" name="name">
        <label>Surname:</label>
        <input type="text" name="surname">
        <br>
        <br>
        <label>Club:</label>
        <input type="text" name="club">
        <br>
        <br>
        <label>Salary:</label>
        <input type="text" name="salary">
        <label>Transfer Price:</label>
        <input type="text" name="transfer_price">
        <br>
        <br>
        <button>Add Footballer</button>
    </form>
</body>
</html>
