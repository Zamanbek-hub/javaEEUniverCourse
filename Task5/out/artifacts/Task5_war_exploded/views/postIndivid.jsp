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

<div class="home_page" style="width: 1200px; display: block; margin-left: auto; margin-right: auto;">
    <div class="row" style="height: 500px;">
        <div class="col-9 posts">
            <%
                Post post = (Post) request.getAttribute("post");
                if(post != null){

            %>
            <div class="card">
                <div class="card-body">
                    <h3 class="card-title"><%=post.getTitle()%></h3>
                    <h5 class="card-text"><%=post.getShort_content()%></h5>
                    <p class="card-text"><%=post.getContent()%></p>
                    <div class="row">
                        <form class="col-2" method="POST"  action="/post_delete" style="margin-top: 0px;">
                            <input type="hidden" value="<%=post.getId()%>" id="post_id" name="post_id">
                            <button class="btn btn-danger" type="submit">Delete</button>
                        </form>
                        <div class="col-2">
                            <button class="btn btn-success" data-toggle="modal" data-target="#updateModal">Update</button>
                        </div>
                    </div>
                </div>
                <div class="card-footer">
                    <p>Posted on <%=post.getPost_date()%> by <a href="/"><strong><%=post.getAuthor().getFull_name()%></strong></a></p>
                </div>
            </div>
            <br/>

            <div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="updateModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form method="POST" action="/post_update">
                            <div class="modal-header">
                                <h5 class="modal-title" id="updateModalLabel">Update Post</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <label for="update_title">Title:</label>
                                    <input type="text" class="form-control" id="update_title" name="update_title" value="<%=post.getTitle()%>" placeholder="Name of title...">
                                </div>
                                <div class="form-group">
                                    <label for="update_short_content">Short Content:</label>
                                    <textarea type="text" class="form-control" id="update_short_content" name="update_short_content" ><%=post.getShort_content()%></textarea>
                                </div>
                                <div class="form-group">
                                    <label for="update_content">Content:</label>
                                    <textarea type="text" class="form-control" id="update_content" name="update_content" ><%=post.getContent()%></textarea>
                                </div>

                                <input type="hidden" value="<%=post.getId()%>" id="post_id" name="post_id">
                                <input type="hidden" value="<%=post.getAuthor().getId()%>" id="user_id" name="user_id">

                            </div>
                            <div class="modal-footer">
                                <button type="submit" class="btn btn-success" >Update</button>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <%

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
                                if(birthday.getId() != post.getAuthor().getId()){
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


<%@include file="templates/footer.jsp" %>





</body>
</html>
