<%@ page import="db.classes.User" %>
<%@ page import="java.util.concurrent.TimeUnit" %><%--
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
                        <a><strong><%=user.getFull_name()%></strong></a>
                        <%
                            long diffInMillies = Math.abs(user.getBirth_date().getTime() - new java.sql.Date(System.currentTimeMillis()).getTime());
                            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) / 365;
                        %>
                        ,<%=diff%> years
                    </div>
                    <div class="profile_part">
                        <a style="color: dodgerblue;"><i class="fas fa-address-card"></i>My Profile</a>
                    </div>
                    <div class="profile_part">
                        <a style="color: dodgerblue;"><i class="fas fa-cogs"></i>Settings</a>
                    </div>
                    <div class="profile_part bound">
                        <a href="/logout" style="color: darkred;"><i class="fas fa-sign-out-alt"></i>Logout</a>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-6 posts">
            <%@include file="templates/messages.jsp" %>

            <form method="POST" action="/update_profile">
                <div class="title">
                    <h3>Edit Profile</h3>
                </div>
                <div class="form-group">
                    <label for="email">Email address</label>
                    <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp" value="<%=user.getEmail()%>">
                    <small  class="form-text text-muted">Change your email</small>
                </div>
                <div class="form-group">
                    <label for="full_name">Full name</label>
                    <input type="text" class="form-control" id="full_name" name="full_name" value="<%=user.getFull_name()   %>">
                    <small  class="form-text text-muted">Change your full name</small>
                </div>
                <div class="form-group">
                    <label for="birthdate">Birthdate</label>
                    <input type="date" class="form-control" id="birthdate" name="birthdate" value="<%=user.getBirth_date()%>">
                    <small  class="form-text text-muted">Change your birthdate</small>
                </div>
                <button type="submit" class="btn submit_button">Update Profile</button>
            </form>

            <hr/>

            <form method="POST" action="/update_picture_url">
                <div class="title">
                    <h3>Update Picture</h3>
                </div>
                <div class="form-group">
                    <label for="picture_url">URL</label>
                    <input type="text" class="form-control" id="picture_url" name="picture_url" value="<%=user.getPicture_url()%>">
                    <small class="form-text text-muted">Input your old password</small>
                </div>
                <button type="submit" class="btn submit_button">Update URL</button>
            </form>

            <hr/>

            <form method="POST" action="/update_password">
                <div class="title">
                    <h3>Update Password</h3>
                </div>
                <div class="form-group">
                    <label for="old_password">Password</label>
                    <input type="password" class="form-control" id="old_password" name="old_password">
                    <small class="form-text text-muted">Input your old password</small>
                </div>
                <div class="form-group">
                    <label for="new_password">New Password</label>
                    <input type="password" class="form-control" id="new_password" name="new_password">
                    <small class="form-text text-muted">Input your news password</small>
                </div>
                <div class="form-group">
                    <label for="re_new_password">Re-New Password</label>
                    <input type="password" class="form-control" id="re_new_password" name="re_new_password">
                    <small class="form-text text-muted">Re input your new password again</small>
                </div>
                <button type="submit" class="btn submit_button">Update Password</button>
            </form>

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
