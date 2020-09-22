<%@ page import="java.util.ArrayList" %>
<%@ page import="db.classes.League" %>
<%@ page import="db.classes.City" %><%--
  Created by IntelliJ IDEA.
  User: Zamanbek
  Date: 21.09.2020
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="./../templates/cdns.jsp" %>
    <%@include file="./../templates/style.jsp" %>
</head>
<body>
<%@include file="./../templates/navbar.jsp" %>
<div class="container">
    <%@include file="./../templates/messages.jsp" %>
    <br>

    <%
        String path = (String)request.getAttribute("path");
    %>
    <div class="card" >
        <div class="card-body" style="font-weight: bold">
            <%=path%>
        </div>
    </div>


    <br>
    <%
        ArrayList<City> cities = (ArrayList<City>)request.getAttribute("cities");
        System.out.println(cities);
        if(cities != null){
    %>

    <div class="card" style="background-color: white">

        <div class="card-header">
            <span style="font-weight: bolder">List of all Cities</span>
            <button class="btn btn-dark" data-toggle="modal" data-target="#addLeagueModal" style="float: right;">+Add new</button>
        </div>

        <div class="card-body">
            <table class="table table-light table-bordered table-striped table-hover">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">LEAGUE</th>
                    <th scope="col" class="text-right">OPERATIONS</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for(City city: cities){
                %>
                <tr>
                    <td><%=city.getName()%></td>
                    <td class="text-right">
                        <button class="btn btn-success" onclick="fillToUpdateLeagueModal(<%=city.getId()%>,'<%=city.getName()%>')" data-toggle="modal" data-target="#updateLeagueModal">Edit</button>
                        <a href="${pageContext.request.contextPath}/get_clubs?city_id=<%=city.getId()%>" >
                            <button class="btn btn-dark" >Details</button>
                        </a>
                    </td>
                </tr>

                <%
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
    <%
        }
    %>

    <div class="modal fade" id="addLeagueModal" tabindex="-1" role="dialog" aria-labelledby="addLeagueModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <form method="POST" action="/add_city">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addLeagueModalLabel">Modal title</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="add_name">Name:</label>
                            <input type="text" class="form-control" id="add_name" name="add_name" placeholder="Name of city...">
                        </div>
                        <input type="hidden" class="league_id"  name="league_id">
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-dark" >ADD</button>
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="modal fade" id="updateLeagueModal" tabindex="-1" role="dialog" aria-labelledby="updateLeagueModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <form method="POST" action="/home" id="update_form">
                    <div class="modal-header">
                        <h5 class="modal-title" id="updateLeagueModalLabel">Modal title</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" id="update_id" name="update_id">
                        <div class="form-group">
                            <label for="update_name">Name:</label>
                            <input type="text" class="form-control" id="update_name" name="update_name" placeholder="Name of city...">
                        </div>
                        <input type="hidden"  class="league_id" name="league_id">
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-dark"  onclick="changeAction('/update_city')">SAVE</button>
                        <button type="submit" class="btn btn-danger" onclick="changeAction('/delete_city')">DELETE</button>
                    </div>
                </form>
            </div>
        </div>
    </div>



</div>

<script type="text/javascript">
    const fillToUpdateLeagueModal = (id, name) =>{
        document.getElementById("update_id").value = id;
        document.getElementById("update_name").value = name;
    }

    const setLeagueID = (league_id) => {
        Array.from(document.getElementsByClassName("league_id")).forEach((input) => {
            input.value = league_id;
        });
    }

    <%

        League league = (League) request.getAttribute("league");

    %>
        setLeagueID('<%=league.getId()%>');
    <%
    %>

    const changeAction = (url) =>{
        document.getElementById("update_form").action = url;
    }
</script>
</body>
</html>
