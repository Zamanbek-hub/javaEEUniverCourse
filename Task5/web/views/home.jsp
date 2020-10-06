<%@ page import="db.classes.User" %>
<%@ page import="java.util.concurrent.TimeUnit" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: zaman
  Date: 10/6/2020
  Time: 3:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="templates/cdns.jsp" %>
    <%@include file="./templates/styles.jsp" %>
</head>
<body>
<%@include file="templates/navbar.jsp" %>
<%
    User user  = (User)request.getSession().getAttribute("current_user");
    if(user != null){
%>
<div class="home_page" style="width: 1200px; display: block; margin-left: auto; margin-right: auto;">
    <div class="row" style="height: 500px;">
        <div class="col-3">
            <div style="float: right;">
                <img src="<%=user.getPicture_url()%>" alt="" style="width: 250px; height: 250px;">
                <div class="profile_menu">
                    <div class="profile_part bound">
                        <a>
                            <strong><%=user.getFull_name()%> </strong>
                            <%
                                long diffInMillies = Math.abs(user.getBirth_date().getTime() - new java.sql.Date(System.currentTimeMillis()).getTime());
                                long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) / 365;
                            %>
                            ,<%=diff%> years

                        </a>
                    </div>
                    <div class="profile_part">
                        <a href="/profile" style="color: dodgerblue;"><i class="fas fa-address-card"></i>My Profile</a>
                    </div>
                    <div class="profile_part">
                        <a href="/" style="color: dodgerblue;"><i class="fas fa-cogs"></i>Settings</a>
                    </div>
                    <div class="profile_part bound">
                        <a href="/logout" style="color: darkred;"><i class="fas fa-sign-out-alt"></i>Logout</a>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-6 posts">
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title">First Java EE</h4>
                    <p class="card-text">I created</p>
                    <a href="" class="btn btn-outline-primary">Read More</a>
                </div>
                <div class="card-footer">
                    <p>Posted on septempber by <a href="/"><strong><%=user.getFull_name()%></strong></a></p>
                </div>
            </div>

            <br/>
            <br/>
        </div>

        <div class="col-s3">
            <div style="margin-left: 30px;">
                <div class="card" style="background-color: #57BD9E; width: 15rem;">
                    <div class="card-header" >
                        Latest BirthDays
                    </div>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">Cras justo odio</li>
                        <li class="list-group-item">Dapibus ac facilisis in</li>
                        <li class="list-group-item">Vestibulum at eros</li>
                    </ul>
                </div>

                <br/>

                <div class="card" style="background-color: #57BD9E; width: 15rem;">
                    <div class="card-header">
                        My Games
                    </div>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">Cras justo odio</li>
                        <li class="list-group-item">Dapibus ac facilisis in</li>
                        <li class="list-group-item">Vestibulum at eros</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<%  } %>
<%@include file="templates/footer.jsp" %>

</body>
</html>
