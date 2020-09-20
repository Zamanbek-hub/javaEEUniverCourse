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
<div class="container">
    <form action="/home" method="GET">
        <div class="row">
            <div class="form-group col-3">
                <label for="name">Name:</label>
                <input type="text" class="form-control" id="name" name="name" placeholder="name">
            </div>

            <div class="form-group col-3">
                <label for="surname">Surname:</label>
                <input type="text" class="form-control" id="surname" name="surname" placeholder="surname">
            </div>

            <div class="form-group col-3">
                <label for="iin">IIN:</label>
                <input type="text" class="form-control" id="iin"  name="iin" placeholder="IIN">
            </div>


            <div class="form-group col-3">
                <label for="is_grant">Grant:</label>
                <select class="form-control" id="is_grant" name="is_grant">
<%--                    <option selected></option>--%>
                    <%
                        boolean is_grant = (boolean)request.getAttribute("is_grant");
                        if (is_grant){
                    %>
                    <option value="True" selected>yes</option>
                    <option value="False" >no</option>
                    <%
                        }else{
                    %>
                    <option value="True" >yes</option>
                    <option value="False" selected>no</option>
                    <%
                        }
                    %>
                </select>
            </div>
        </div>
        <button class="btn" type="submit" onclick="filterAjax()" style="background-color: darkviolet; color: white; float:right;">Filter</button>
    </form>

    <hr style="margin-top:50px">

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

    <nav aria-label="Page navigation example">
        <ul class="pagination">

            <%
                String currentURL = (String)request.getAttribute("currentURL");
                int pages = (int)request.getAttribute("pages");
                int active_page = (int)request.getAttribute("active_page");

                // to page won't be < 1
                if(active_page > 1){
            %>
            <li class="page-item">
                <a class="page-link" href="/home<%=currentURL%>&page=<%=active_page-1%>" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                    <span class="sr-only">Previous</span>
                </a>
            </li>
            <%
                }
                for(int i = 1; i<pages+1; i++){
                    if(i == active_page){
            %>
            <li class="page-item active"><a class="page-link" href="/home<%=currentURL%>&page=<%=i%>"><%=i%></a></li>
            <%
            }
            else{
            %>
            <li class="page-item"><a class="page-link" href="/home<%=currentURL%>&page=<%=i%>"><%=i%></a></li>
            <%
                    }
                }
                // to page won't be > pages
                if(active_page < pages){
            %>

            <li class="page-item">
                <a class="page-link" href="/home<%=currentURL%>&page=<%=active_page+1%>" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                    <span class="sr-only">Next</span>
                </a>
            </li>
            <%
                }

            %>
        </ul>
    </nav>
</div>


</body>
</html>
