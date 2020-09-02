<%@ page import="java.util.ArrayList" %>
<%@ page import="db.Footballer" %><%--
  Created by IntelliJ IDEA.
  User: Zamanbek
  Date: 02.09.2020
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<h1>Main Page</h1>
    <form action="/get" method="GET">
        <input type="text"  name="id" placeholder="Search by Id) ">
        <button>Search</button>
        <a href="/add" type="button"><strong>Add</strong></a>
    </form>
    <% ArrayList<Footballer> footballers = (ArrayList<Footballer>)request.getAttribute("footballlers");
    if(footballers!=null){
        %>
        <h1>Total:  <%=footballers.size()%>  </h1>
        <%
            int index = footballers.size();
            for(Footballer f: footballers){
        %>


        <h2 style='color:#46A478; border:2px solid grey; border-radius:3px;'><strong><%=index%>)<%=f.getName()%>   <%=f.getSurname()%> </strong></h2>
        <h4> Club:<big style="color: darkblue;"> <%=f.getClub()%>  </big></h4>
        <h4> Salary:  <big style="color: darkblue;"> <strong> <%=f.getSalary()%> </strong> </big> M$</h4>
        <h4> Transfer Price: <big style="color: darkblue;"> <strong> <%=f.getTransferPrice()%> </strong> </big>M$</h4>
        <a href="/get?id=<%=index%>"><button>Перейти</button></a>
            <%
                        index--;
            }
        }
    %>
</body>
</html>
