<%@ page import="db.classes.Car" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Zamanbek
  Date: 02.09.2020
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/">List All Cars</a>
<% Map<Integer, Car> cars = (Map<Integer, Car>)request.getAttribute("cars");
    String currentAttr = (String) request.getAttribute("attr");
    String currentSearch = (String) request.getAttribute("search");
    if(cars!=null){
%>
<h1>Total:  <%=cars.size()%>  </h1>
<%
    int index = 0;
    for(Map.Entry entry:cars.entrySet()){
        Car car = (Car) entry.getValue();
%>


<h2 style='color:#46A478; border:2px solid grey; border-radius:3px;'><strong><%=index+1%>)<%=car.getModel()%>  <%=car.getName()%> </strong></h2>
<h4> Price:<big style="color: darkblue;"> <%=car.getPrice()%> </big> KZT</h4>
<h4> Year:  <big style="color: darkblue;"> <strong> <%=car.getYear()%> </strong> </big> </h4>
<form action="/delete" method="POST">
        <input type="hidden" name="attr" value="<%=currentAttr%>">
    <input type="hidden" name="search" value="<%=currentSearch%>">
    <input type="hidden" name="delete_id" value="<%=entry.getKey()%>">
    <button>Delete</button>
</form>
<%
            index++;
        }
    }
%>
</body>
</html>
