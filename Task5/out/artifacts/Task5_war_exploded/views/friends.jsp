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
<style>
    button:hover{
        background-color: #433F73;
        color: white !important;
    }
</style>
<html>
<head>
    <title>Title</title>
    <script src="./js/tinymce/tinymce.min.js" referrerpolicy="origin"></script>
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
        <%@include file="templates/mainPage/profilePart.jsp" %>

        <div class="col-6 posts">
            <%@include file="templates/alerts.jsp" %>
<%--            <%--%>
<%--                ArrayList<Post> posts = (ArrayList<Post>) request.getAttribute("posts");--%>
<%--                if(posts != null){--%>
<%--                    for(Post post: posts){--%>
<%--            %>--%>
            <div class="card mb-2 d-flex justify-content-center align-self-center"  style="width: 600px; height: 80px;">
                <form class="form-inline my-2 my-lg-0 row justify-content-center no-gutters" style="width: 100%;">

                    <input class="form-control mr-sm-2 col-8" type="search" placeholder="Search Friends" aria-label="Search">
                    <div class="col-2">
                        <button class="btn btn-outline my-2 my-sm-0 float-right" style="border: #433F73 solid 2px; color: #433F73; font-weight: bolder;" type="submit"><i class="fas fa-search"></i>Search</button>
                    </div>
                </form>
            </div>

            <div class="card mb-2 d-flex justify-content-center align-self-center"  style="width: 600px; height: 200px;">
                <div class="row no-gutters" style="width:90%; margin-left: auto; margin-right: auto; ">
                    <div class="col-md-4">
                        <img src="https://picsum.photos/seed/picsum/200/300" style="height: 140px; width: 140px; border-radius: 50%;" class="card-img" alt="...">
                    </div>
                    <div class="col-md-7">
                        <div class="card-body">
                            <h5 class="card-title">Card title</h5>
                            <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
                            <button class="btn btn-outline" style="border: #433F73 solid 2px; color: #433F73; font-weight: bolder;"><i class="fa fa-plus" aria-hidden="true"></i>Add To Friends</button>
                        </div>
                    </div>
                </div>
            </div>
            <br/>
<%--            <%--%>
<%--                    }--%>
<%--                }--%>
<%--            %>--%>

            <br/>
            <br/>
        </div>

        <%@include file="templates/mainPage/notifyPart.jsp" %>


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
