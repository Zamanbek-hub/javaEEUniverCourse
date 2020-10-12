<%@ page import="java.util.concurrent.TimeUnit" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="db.classes.*" %><%--
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
    String search_value = (String)request.getAttribute("search_value");
    if(user != null){
%>
<div class="home_page" style="width: 1200px; display: block; margin-left: auto; margin-right: auto;">
    <div class="row" style="height: 500px;">
        <%@include file="templates/mainPage/profilePart.jsp" %>

        <div class="col-6 posts">
            <%@include file="templates/alerts.jsp" %>

            <div class="card mb-2 d-flex justify-content-center align-self-center"  style="width: 600px; height: 80px;">
                <form class="form-inline my-2 my-lg-0 row justify-content-center no-gutters" style="width: 100%;">

                    <input class="form-control mr-sm-2 col-8" name="search_user" type="search" placeholder="Search Friends" aria-label="Search">
                    <div class="col-2">
                        <button class="btn btn-outline my-2 my-sm-0 float-right" style="border: #433F73 solid 2px; color: #433F73; font-weight: bolder;"     type="submit"><i class="fas fa-search"></i>Search</button>
                    </div>
                </form>
            </div>

            <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
                <li class="nav-item">
                    <button class="btn btn-primary" id="search-tab" onclick="searchPanelActive()">Search Result</button>
                </li>
                <li class="nav-item">
                    <button class="btn" id="requests-tab" onclick="requestPanelActive()">Requests</button>
                </li>
                <li class="nav-item">
                    <button class="btn" id="friends-tab" onclick="friendsPanelActive()">Friends</button>
                </li>
            </ul>

            <div id="search-panel">
            <%
//                ArrayList<User> search_users = (ArrayList<User>)request.getAttribute("search_users");
                Map<User, FriendRequest> search_users = (Map<User, FriendRequest>)request.getAttribute("search_users");

                if(search_users != null){
            %>


                <div class="card mb-2 d-flex justify-content-center align-self-center"  style="width: 600px; height: 80px;">
                    <h3 style="margin-left: auto; margin-right: auto;">Search Results for: "<%=search_value%>"</h3>
                </div>
                <%
                    for (Map.Entry<User, FriendRequest> entry : search_users.entrySet())
                    {
                        User res = entry.getKey();
                        FriendRequest fr = entry.getValue();
                %>

                <div class="card mb-2 d-flex justify-content-center align-self-center"  style="width: 600px; height: auto;">
                    <div class="row no-gutters" style="width:90%; margin-left: auto; margin-right: auto; ">
                        <div class="col-md-4">
                            <a href="/user_profile?id=<%=res.getId()%>&type=1">
                                <img src="<%=res.getPicture_url()%>" style="height: 150px; width: 150px; margin-top: 30px; border-radius: 50%;" class="card-img" alt="...">
                            </a>
                        </div>
                        <div class="col-md-7">
                            <div class="card-body">
                                <h3 class="card-title"><%=res.getFull_name()%></h3>
                                <%
                                    diffInMillies = Math.abs(res.getBirth_date().getTime() - new java.sql.Date(System.currentTimeMillis()).getTime());
                                    diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) / 365;
                                %>
                                <p class="card-text" style="font-size:25px;"><small class="text-muted"><%=diff%> years old</small></p>
                                <p><%=res.getEmail()%></p>
                                <%
                                if(fr.getId() != -1) {
                                %>
                                <form class="form-inline" method="POST" action="/delete_friend_request" style="width:100%; margin-top: 0px;">
                                    <input type="hidden" value="<%=res.getId()%>" name="friend_id">
                                    <input type="hidden" value="<%=search_value%>" name="search_user">
                                    <button class="btn btn-outline" type="submit" style="border: #433F73 solid 2px; color: #433F73; font-weight: bolder;"><i class="fas fa-check"></i>Request Sent</button>
                                </form>
                                <%
                                    } else {
                                %>
                                <form class="form-inline" method="POST" action="/send_friend_request" style="width:100%; margin-top: 0px;">
                                    <input type="hidden" value="<%=res.getId()%>" name="friend_id">
                                    <input type="hidden" value="<%=search_value%>" name="search_user">
                                    <button class="btn btn-outline" type="submit" style="border: #433F73 solid 2px; color: #433F73; font-weight: bolder;"><i class="fa fa-plus" aria-hidden="true"></i>Add To Friends</button>
                                </form>
                                <%
                                    }
                                %>

                            </div>
                        </div>
                    </div>
                </div>
                <br/>
                <%
                        }
                    }
                %>
            </div>

            <div id="requests-panel" style="display: none;">
                <%
                    ArrayList<User> friend_requests = (ArrayList<User>)request.getAttribute("friend_requests");
                    System.out.println("friend_requests = " + friend_requests);
                    if(friend_requests != null){
                        for(User fr: friend_requests){

                %>


                <div class="card mb-2 d-flex justify-content-center align-self-center"  style="width: 600px; height: auto;">
                    <div class="row no-gutters" style="width:90%; margin-left: auto; margin-right: auto; ">
                        <div class="col-md-4">
                            <a href="/user_profile?id=<%=fr.getId()%>">
                                <img src="<%=fr.getPicture_url()%>" class="card-img" alt="..." style="height: 150px; width: 150px; margin-top: 30px; border-radius: 50%;" >
                            </a>
                        </div>
                        <div class="col-md-7">
                            <div class="card-body">
                                <h3 class="card-title"><%=fr.getFull_name()%></h3>
                                <%
                                    diffInMillies = Math.abs(fr.getBirth_date().getTime() - new java.sql.Date(System.currentTimeMillis()).getTime());
                                    diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) / 365;
                                %>
                                <p class="card-text" style="font-size:25px;"><small class="text-muted"><%=diff%> years old</small></p>
                                <p><%=fr.getEmail()%></p>

                                <form class="form-inline" id="confirm_search_form" method="POST" action="/delete_friend_request" style="width:100%; margin-top: 0px;">
                                    <input type="hidden" value="<%=fr.getId()%>" name="friend_id">
                                    <input type="hidden" value="<%=search_value%>" name="search_user">
                                    <button class="btn btn-outline" id="confirm-button" type="submit" onclick="changeURL('/confirm_friend_request')" style="border: #433F73 solid 2px; color: #433F73; font-weight: bolder; margin-right: 10px;"><i class="fas fa-plus-circle"></i>Confirm</button>
                                    <button class="btn btn-outline" id="reject-button" type="submit" onclick="changeURL('/delete_friend_request')" style="border: #433F73 solid 2px; color: #433F73; font-weight: bolder;"><i class="fas fa-trash-alt"></i>Reject</button>
                                </form>


                            </div>
                        </div>
                    </div>
                </div>

                <%
                        }
                    }
                %>
            </div>

            <div id="friends-panel" style="display: none;">
                <%
                    ArrayList<User> friends = (ArrayList<User>)request.getAttribute("friends");
                    System.out.println("friends = " + friends);
                    if(friends != null){
                        for(User fr: friends){

                %>


                <div class="card mb-2 d-flex justify-content-center align-self-center"  style="width: 600px; height: auto;">
                    <div class="row no-gutters" style="width:90%; margin-left: auto; margin-right: auto; ">
                        <div class="col-md-4">
                            <a href="/user_profile?id=<%=fr.getId()%>">
                                <img src="<%=fr.getPicture_url()%>" class="card-img" alt="..." style="height: 150px; width: 150px; margin-top: 30px; border-radius: 50%;" >
                            </a>
                        </div>
                        <div class="col-md-7">
                            <div class="card-body">
                                <h3 class="card-title"><%=fr.getFull_name()%></h3>
                                <%
                                    diffInMillies = Math.abs(fr.getBirth_date().getTime() - new java.sql.Date(System.currentTimeMillis()).getTime());
                                    diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) / 365;
                                %>
                                <p class="card-text" style="font-size:25px;"><small class="text-muted"><%=diff%> years old</small></p>
                                <p><%=fr.getEmail()%></p>

                                <form class="form-inline" id="confrim_search_form" method="POST" action="/delete_friend_request" style="width:100%; margin-top: 0px;">
                                    <input type="hidden" value="<%=fr.getId()%>" name="friend_id">
                                    <input type="hidden" value="<%=search_value%>" name="search_user">
                                    <button class="btn btn-outline" id="send-message-button" type="submit"  style="border: #433F73 solid 2px; color: #433F73; font-weight: bolder; margin-right: 10px;"><i class="fab fa-telegram-plane"></i>Send message</button>

                                </form>


                            </div>
                        </div>
                    </div>
                </div>

                <%
                        }
                    }
                %>
            </div>

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



<script type="text/javascript">

    const searchPanelActive = () =>{
        console.log("search-tab");
        document.getElementById("search-panel").style.display = "block";
        document.getElementById("search-tab").className = "btn btn-primary";
        document.getElementById("friends-panel").style.display = "none";
        document.getElementById("friends-tab").className = "btn";
        document.getElementById("requests-panel").style.display = "none";
        document.getElementById("requests-tab").className = "btn";
    }

    const requestPanelActive = () =>{
        console.log("search-tab");
        document.getElementById("search-panel").style.display = "none";
        document.getElementById("requests-panel").style.display = "block";
        document.getElementById("friends-panel").style.display = "none";
        document.getElementById("search-tab").className = "btn";
        document.getElementById("friends-tab").className = "btn";
        document.getElementById("requests-tab").className = "btn btn-primary";
    }

    const friendsPanelActive = () =>{
        console.log("friends-tab");
        if( typeof(document.getElementById("search-panel")) != "undefined")
        document.getElementById("search-panel").style.display = "none";
        document.getElementById("requests-panel").style.display = "none";
        document.getElementById("friends-panel").style.display = "block";
        document.getElementById("friends-tab").className = "btn btn-primary";
        document.getElementById("search-tab").className = "btn";
        document.getElementById("requests-tab").className = "btn";
    }


    const changeURL = (url) => {
        document.getElementById("confirm_search_form").action = url;
    }

</script>

</body>
</html>
