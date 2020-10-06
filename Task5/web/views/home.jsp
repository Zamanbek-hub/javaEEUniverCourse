<%@ page import="db.classes.User" %>
<%@ page import="java.util.concurrent.TimeUnit" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="db.classes.Post" %><%--
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
    <script src="https://cdn.tiny.cloud/1/no-api-key/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>
    <script>tinymce.init({selector:'textarea'});</script>
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
                        <a href="/profile" style="color: dodgerblue;"><i class="fas fa-address-card"></i> My Profile</a>
                    </div>
                    <div class="profile_part">
                        <a href="/" style="color: dodgerblue;"><i class="fas fa-cogs"></i> Settings</a>
                    </div>
                    <div class="profile_part bound">
                        <a href="/logout" style="color: darkred;"><i class="fas fa-sign-out-alt"></i> Logout</a>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-6 posts">
            <%@include file="templates/messages.jsp" %>
            <div style="width: 90%; display: block; margin-left: auto; margin-right: auto;">
                <button class="btn" style="background-color: <%=mainBGColor%>; color: white;" data-toggle="modal" data-target="#addModal">
                    <i class="fas fa-plus-square"></i>
                    Add New
                </button>
            </div>
            <br/>
            <%
                ArrayList<Post> posts = (ArrayList<Post>) request.getAttribute("posts");
                if(posts != null){
                    for(Post post: posts){
            %>
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title"><%=post.getTitle()%></h4>
                    <p class="card-text"><%=post.getShort_content()%></p>
                    <a href="/post_details?post_id=<%=post.getId()%>" class="btn btn-outline-primary">Read More</a>
                </div>
                <div class="card-footer">
                    <p>Posted on <%=post.getPost_date()%> by <a href="/"><strong><%=post.getAuthor().getFull_name()%></strong></a></p>
                </div>
            </div>
            <br/>
            <%
                    }
                }
            %>

            <br/>
            <br/>
        </div>

        <div class="col-s3">
            <div style="margin-left: 30px;">
                <%
                    ArrayList<User> latest_birthdays = (ArrayList<User>) request.getAttribute("latest_birthdays");
                    if(latest_birthdays != null){
                %>
                <div class="card" style="background-color: #57BD9E; width: 15rem;">
                    <div class="card-header" style="color: white; font-weight: bold; font-size: 15px;">
                        Latest BirthDays
                    </div>
                    <ul class="list-group list-group-flush">
                        <%
                            String [] months = new String[]{"January", "February", "March", "April", "May", "June" +
                                                            "Jule", "August", "September", "October", "November", "December"};
                            for(User birthday: latest_birthdays){
                                if(birthday.getId() != user.getId()){
                        %>
                        <li class="list-group-item"><%=birthday.getFull_name()%>, <%=birthday.getBirth_date().getDay()%> <%=months[birthday.getBirth_date().getMonth() - 1]%></li>
                        <%
                                }
                            }
                        %>
                    </ul>
                </div>
                <%
                        }
                %>

                <br/>

                <div class="card" style="background-color: #57BD9E; width: 15rem;">
                    <div class="card-header" style="color: white; font-weight: bold; font-size: 15px;">
                        My Games
                    </div>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item" style="color:blue;"><i class="fas fa-futbol"></i> <strong>FOOTBALL ONLINE</strong>  </li>
                        <li class="list-group-item" style="color:blue;"><i class="fas fa-table-tennis"></i>  <strong>PING PONG ONLINE</strong> </li>
                        <li class="list-group-item" style="color:blue;"><i class="fas fa-chess"></i> <strong>CHESS MASTERS</strong></li>
                        <li class="list-group-item" style="color:blue;"><i class="fas fa-car"></i> <strong>RACES ONLINE</strong></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="addModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form method="POST" action="/add_post">
                <div class="modal-header">
                    <h5 class="modal-title" id="addLeagueModalLabel">Add New Post</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="add_title">Title:</label>
                        <input type="text" class="form-control" id="add_title" name="add_title" placeholder="Name of title...">
                    </div>
                    <div class="form-group">
                        <label for="add_short_content">Short Content:</label>
                        <textarea type="text" class="form-control" id="add_short_content" name="add_short_content"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="add_content">Content:</label>
                        <textarea type="text" class="form-control" id="add_content" name="add_content"></textarea>
                    </div>

                    <input type="hidden" value="<%=user.getId()%>" id="user_id" name="user_id">

                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success" >ADD</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>
<%  } %>
<%@include file="templates/footer.jsp" %>





</body>
</html>
