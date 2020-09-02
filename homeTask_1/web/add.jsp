<%--
  Created by IntelliJ IDEA.
  User: Zamanbek
  Date: 02.09.2020
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/">List All Cars</a>
<br><br><br>
<form action="/add" style="border:2px solid grey" method="POST">
    <label>Model:</label>
    <input type="text" name="model" placeholder="Type">
    <label>Name:</label>
    <input type="text" name="name" placeholder="Type">
    <br>
    <br>
    <label>Year:</label>
    <input type="text" name="year" placeholder="numbers">
    <label>Price:</label>
    <input type="text" name="price" placeholder="numbers">
    <br>
    <br>
    <button>Add Car</button>
</form>
</body>
</html>
