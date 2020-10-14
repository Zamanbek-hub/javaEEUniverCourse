<%@ page import="java.util.concurrent.TimeUnit" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="db.classes.*" %>
<%@ page import="db.classes.helpers.UserFR" %>
<%@ page import="java.util.Calendar" %><%--
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


            <%


                ArrayList<Chat> chats = (ArrayList<Chat>)request.getAttribute("chats");
//                System.out.println("chats = " + chats);
                if(chats != null){
                    String frs = chats.toString();
                    for(Chat chat: chats){

            %>

<%--                            <p><%=frs%></p>--%>
            <a href = "/dialogue?friend=<%=chat.getOpponent_user().getId()%>&id=<%=chat.getId()%>" style="color: #000000; text-decoration: none;">
                <div class="card mb-2 d-flex justify-content-center align-self-center"  style="width: 600px; height: auto;">
                    <div class="row no-gutters" style="width:90%; margin-left: auto; margin-right: auto; ">
                        <div class="col-md-3">
<%--                            <a href="/user_profile?id=<%=chat.getOpponent_user().getId()%>">--%>
                                <img src="<%=chat.getOpponent_user().getPicture_url()%>" class="card-img" alt="..." style="height: 100px; width: 100px; margin-top: 10px; border-radius: 50%;" >
<%--                            </a>--%>
                        </div>
                        <div class="col-md-6">
                            <div class="card-body">
                                <h3 class="card-title"><%=chat.getOpponent_user().getFull_name()%></h3>

                                <%
                                    System.out.println("chats 1= " + chats);
                                if(chat.isRead_by_receiver()){
                                %>
                                <p><%=chat.getLatest_message_text()%></p>
                                <%
                                    } else {
                                %>
                                <p style="color:blue; font-weight: bold;"><%=chat.getLatest_message_text()%></p>
                                <%
                                    }
                                %>

                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="card-body">
                                <p style="font-weight: bold;">
                                    <%
                                        Date newDate =  new java.util.Date(chat.getLatest_message_time().getTime());
    //                                    Calendar now = Calendar.getInstance();
    //                                    System.out.println(now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE));
                                    %>
                                    <%=chat.getLatest_message_time()%>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </a>

            <%
                    }
                }
            %>


            <br/>
            <br/>
        </div>

        <%@include file="templates/mainPage/notifyPart.jsp" %>


    </div>
</div>


<%  } %>
<%@include file="templates/footer.jsp" %>





</body>
</html>
