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
    <form action="/get" method="POST">
        <input type="text" name="get_id">
        <button>Search</button>
        <a href="/add" type="button">Add</a>
    </form>
    <% ArrayList<Footballer> footballers = (ArrayList<Footballer>)request.getAttribute("footballlers");
    if(footballers!=null){
        %>
        <h1>Total:  <%=footballers.size()%>  </h1>
        <%
            int index = 0;
            for(Footballer f: footballers){
                index++;
        %>



        <h2 style='color:darkred; border:1px solid grey; border-radius:3px;'><strong><%=index%>)<%=f.getName()%>   <%=f.getSurname()%> </strong></h2>
        <h4> Club: <%=f.getClub()%>  </h4>
        <h4> Salary:  <%=f.getSalary()%> M$</h4>
        <h4> Transfer Price:  <%=f.getTransferPrice()%>  M$</h4>

            <%
                    }
        }
    %>
</body>
</html>
