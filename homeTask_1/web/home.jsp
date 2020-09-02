<%@ page import="db.classes.Car" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Zamanbek
  Date: 02.09.2020
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Main Page</h1>
<form action="/get" method="GET">
    Search by <select name="attr" id="attribute" style="width:100px;">
        <option value="none2" selected>id)</option>
        <option value="name">name</option>
        <option value="model">model</option>
        <option value="year">year</option>
        <option value="price">price</option>
    </select>
    <input type="text" name="search" placeholder="Type ">
    <button>Search</button>
    <a href="/add" type="button"><strong>Add</strong></a>
</form>
<% ArrayList<Car> cars = (ArrayList<Car>)request.getAttribute("footballlers");
    if(cars!=null){
%>
<h1>Total:  <%=cars.size()%>  </h1>
<%
    int index = 0;
    for(Car car: cars){
%>


<h2 style='color:#46A478; border:2px solid grey; border-radius:3px;'><strong><%=index+1%>)<%=car.getModel()%>  <%=car.getName()%> </strong></h2>
<h4> Price:<big style="color: darkblue;"> <%=car.getPrice()%> </big> KZT</h4>
<h4> Year:  <big style="color: darkblue;"> <strong> <%=car.getYear()%> </strong> </big> </h4>
<a href="${pageContext.request.contextPath}/get?search=<%=index%>&attr=none"><button>Перейти</button></a>
<%
            index++;
        }
    }
%>
</body>
</html>
