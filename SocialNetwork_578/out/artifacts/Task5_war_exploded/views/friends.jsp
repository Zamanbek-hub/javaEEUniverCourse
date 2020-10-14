<%@ page import="java.util.concurrent.TimeUnit" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="db.classes.*" %>
<%@ page import="db.classes.helpers.UserFR" %><%--
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
                ArrayList<UserFR> search_users = (ArrayList<UserFR>)request.getAttribute("search_users");
                ArrayList<UserFR> friends = (ArrayList<UserFR>)request.getAttribute("friends");

                if(search_users != null){
            %>


                <div class="card mb-2 d-flex justify-content-center align-self-center"  style="width: 600px; height: 80px;">
                    <h3 style="margin-left: auto; margin-right: auto;">Search Results for: "<%=search_value%>"</h3>
                </div>
                <%
                    for (UserFR userFR: search_users)
                    {
                        if(! user.getId().equals(userFR.getUser().getId())){
                %>

                <div class="card mb-2 d-flex justify-content-center align-self-center"  style="width: 600px; height: auto;">
                    <div class="row no-gutters" style="width:90%; margin-left: auto; margin-right: auto; ">
                        <div class="col-md-4">
                            <a href="/user_profile?id=<%=userFR.getUser().getId()%>&type=1">
                                <img src="<%=userFR.getUser().getPicture_url()%>" style="height: 150px; width: 150px; margin-top: 30px; border-radius: 50%;" class="card-img" alt="...">
                            </a>
                        </div>
                        <div class="col-md-7">
                            <div class="card-body">
                                <h3 class="card-title"><%=userFR.getUser().getFull_name()%></h3>
                                <%
                                    diffInMillies = Math.abs(userFR.getUser().getBirth_date().getTime() - new java.sql.Date(System.currentTimeMillis()).getTime());
                                    diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) / 365;
                                %>
                                <p class="card-text" style="font-size:25px;"><small class="text-muted"><%=diff%> years old</small></p>
                                <p><%=userFR.getUser().getEmail()%></p>
                                <%

                                    if(! userFR.isFriend()){
                                        if(userFR.getFriendRequest().getType().equals("add")) {
                                %>
                                <form class="form-inline" method="POST" action="/send_friend_request" style="width:100%; margin-top: 0px;">
                                    <input type="hidden" value="<%=userFR.getUser().getId()%>" name="friend_id">
                                    <input type="hidden" value="<%=search_value%>" name="search_user">
                                    <button class="btn btn-outline" type="submit" style="border: #433F73 solid 2px; color: #433F73; font-weight: bolder;"><i class="fa fa-plus" aria-hidden="true"></i>Add To Friends</button>
                                </form>

                                <%
                                        } else if(userFR.getFriendRequest().getType().equals("sent")) {
                                %>
                                <form class="form-inline" method="POST" action="/delete_friend_request" style="width:100%; margin-top: 0px;">
                                    <input type="hidden" value="<%=userFR.getUser().getId()%>" name="friend_id">
                                    <input type="hidden" value="<%=search_value%>" name="search_user">
                                    <button class="btn btn-outline" type="submit" style="border: #433F73 solid 2px; color: #433F73; font-weight: bolder;"><i class="fas fa-check"></i>Request Sent</button>
                                </form>
                                <%
                                        } else if(userFR.getFriendRequest().getType().equals("get")) {
                                %>

                                <form class="form-inline" id="confirm_search_after_search_<%=userFR.getUser().getId()%>" method="POST" action="/delete_friend_request" style="width:100%; margin-top: 0px;">
                                    <input type="hidden" value="<%=userFR.getUser().getId()%>" name="friend_id">
                                    <input type="hidden" value="<%=search_value%>" name="search_user">
                                    <button class="btn btn-outline" type="submit" onclick="changeURL('/confirm_friend_request', 'confirm_search_after_search_<%=userFR.getUser().getId()%>')" style="border: #433F73 solid 2px; color: #433F73; font-weight: bolder; margin-right: 10px;"><i class="fas fa-plus-circle"></i>Confirm</button>
                                    <button class="btn btn-outline"  type="submit" onclick="changeURL('/delete_friend_request', 'confirm_search_after_search_<%=userFR.getUser().getId()%>')" style="border: #433F73 solid 2px; color: #433F73; font-weight: bolder;"><i class="fas fa-trash-alt"></i>Reject</button>
                                </form>
                                <%
                                        }
                                    } else {
                                        if(userFR.isChat()){
                                %>

                                    <a href = "/dialogue?friend=<%=userFR.getUser().getId()%>&id=<%=userFR.getChat().getId()%>">
                                        <button class="btn btn-outline" type="submit"  style="border: #433F73 solid 2px; color: #433F73; font-weight: bolder; margin-right: 10px;"><i class="fab fa-telegram-plane"></i>Send message</button>
                                    </a>


                                <%
                                        } else {

                                %>

                                <form class="form-inline" method="POST" action="/create_chat" style="width:100%; margin-top: 0px;">
                                    <input type="hidden" value="<%=userFR.getUser().getId()%>" name="opponent_user_id">
                                    <input type="hidden" value="<%=search_value%>" name="search_user">
                                    <button class="btn btn-outline" type="submit"  style="border: #433F73 solid 2px; color: #433F73; font-weight: bolder; margin-right: 10px;"><i class="fab fa-telegram-plane"></i>Send message</button>

                                </form>


                                <%
                                        }
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

                                <form class="form-inline" id="confirm_search_form_<%=fr.getId()%>" method="POST" action="/delete_friend_request" style="width:100%; margin-top: 0px;">
                                    <input type="hidden" value="<%=fr.getId()%>" name="friend_id">
                                    <input type="hidden" value="<%=search_value%>" name="search_user">
                                    <button class="btn btn-outline" type="submit" onclick="changeURL('/confirm_friend_request', 'confirm_search_form_<%=fr.getId()%>')" style="border: #433F73 solid 2px; color: #433F73; font-weight: bolder; margin-right: 10px;"><i class="fas fa-plus-circle"></i>Confirm</button>
                                    <button class="btn btn-outline"  type="submit" onclick="changeURL('/delete_friend_request', 'confirm_search_form_<%=fr.getId()%>')" style="border: #433F73 solid 2px; color: #433F73; font-weight: bolder;"><i class="fas fa-trash-alt"></i>Reject</button>
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

                    System.out.println("friends = " + friends);
                    if(friends != null){
                        String frs = friends.toString();
                        for(UserFR fr: friends){

                %>

<%--                <p><%=frs%></p>--%>

                <div class="card mb-2 d-flex justify-content-center align-self-center"  style="width: 600px; height: auto;">
                    <div class="row no-gutters" style="width:90%; margin-left: auto; margin-right: auto; ">
                        <div class="col-md-4">
                            <a href="/user_profile?id=<%=fr.getUser().getId()%>">
                                <img src="<%=fr.getUser().getPicture_url()%>" class="card-img" alt="..." style="height: 150px; width: 150px; margin-top: 30px; border-radius: 50%;" >
                            </a>
                        </div>
                        <div class="col-md-7">
                            <div class="card-body">
                                <h3 class="card-title"><%=fr.getUser().getFull_name()%></h3>
                                <%
                                    diffInMillies = Math.abs(fr.getUser().getBirth_date().getTime() - new java.sql.Date(System.currentTimeMillis()).getTime());
                                    diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) / 365;
                                %>
                                <p class="card-text" style="font-size:25px;"><small class="text-muted"><%=diff%> years old</small></p>
                                <p><%=fr.getUser().getEmail()%></p>

                                <%
                                    if(fr.isChat()) {
                                %>

                                <a href = "/dialogue?friend=<%=fr.getUser().getId()%>&id=<%=fr.getChat().getId()%>">
                                    <button class="btn btn-outline" type="submit"  style="border: #433F73 solid 2px; color: #433F73; font-weight: bolder; margin-right: 10px;"><i class="fab fa-telegram-plane"></i>Send message</button>
                                </a>

                                <%
                                    } else {
                                %>
                                <form class="form-inline"  method="POST" action="/create_chat" style="width:100%; margin-top: 0px;">
                                    <input type="hidden" value="<%=fr.getUser().getId()%>" name="opponent_user_id">
                                    <input type="hidden" value="<%=search_value%>" name="search_user">
                                    <button class="btn btn-outline"  type="submit"  style="border: #433F73 solid 2px; color: #433F73; font-weight: bolder; margin-right: 10px;"><i class="fab fa-telegram-plane"></i>Send message</button>

                                </form>

                                <%
                                    }
                                %>



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


    const changeURL = (url, form) => {
        document.getElementById(form).action = url;
    }

</script>

</body>
</html>
