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
    User current_user  = (User)request.getSession().getAttribute("current_user");
    String search_value = (String)request.getAttribute("search_value");
    User user = (User)request.getAttribute("user");
    String caseT  = (String) request.getAttribute("case");
    if(current_user != null){
%>
<div class="home_page" style="width: 1200px; display: block; margin-left: auto; margin-right: auto;">
    <div class="row" style="height: 500px;">
        <%@include file="templates/mainPage/otherUserProfilePart.jsp" %>

        <div class="col-9 posts">
            <%@include file="templates/alerts.jsp" %>

            <%
                Chat chat = (Chat)request.getAttribute("chat");
                if(chat != null){
            %>
            <div class="card mb-2 d-flex justify-content-center align-self-center"  style="width: 800px; height: auto;">
                <div class="row no-gutters" style="width:90%; margin-left: auto; margin-right: auto; ">
                    <div class="col-md-12">
                        <div style="margin-top: 20px; margin-bottom: 20px;">
            <%


                ArrayList<Message> messages = (ArrayList<Message>)request.getAttribute("messages");
                System.out.println("messages = " + messages);
                if(messages != null){
                    String frs = messages.toString();
                    for(Message message: messages){
                        if(! message.getUser().getId().equals(current_user.getId())){

            %>

            <%--                            <p><%=frs%></p>--%>
                        <div style="width: 100%;">
                            <div class="card-body" style="display: inline-block; background-color: white; width:auto; height: auto; min-height:50px; border: 1px solid #E7E7E7; border-radius: 10px; margin-bottom: 8px;">
                                    <div style="width: 100%;">
                                        <span class="time-left" style="font-size: 12px; min-width:80px;margin-bottom: 7px;"><%=message.getSent_date()%></span>
                                    </div>
                                    <p class="card-title"><%=message.getMessage_text()%></p>

                                    <%--                            <p><%=chat.getLatest_message_text()%></p>--%>

                            </div>
                        </div>

                        <%
                                } else {

                        %>
                            <div style="width: 100%; float:right;">
                                <div class="card-body float-right" style="display: inline-block; min-width:80px; background-color: #efefef; width:auto; height: auto; min-height:50px;  border: 10px;border-radius: 10px; margin-bottom: 8px;">
                                    <div style="width: 100%;">
                                        <span class="time-left float-right" style="font-size: 12px;"><%=message.getSent_date()%></span>
                                    </div>
                                        <p class="card-title float-right"><%=message.getMessage_text()%></p>

                                    <%--                            <p><%=chat.getLatest_message_text()%></p>--%>
                                </div>
                            </div>

            <%
                        }
                    }
                }
            %>
                        </div>
                    </div>

                </div>
            </div>


            <br/>

            <div class="card mb-2 d-flex justify-content-center align-self-center"  style="width: 800px; height: 80px;">
                <form class="form-inline my-2 my-lg-0 row justify-content-center no-gutters" action="/add_message" method="POST" style="width: 100%;">
                    <input class="form-control mr-sm-2 col-9" name="message_text" type="text" placeholder="Send Message" aria-label="Search">
                    <input type="hidden" value="<%=chat.getId()%>" name="chat_id">
                    <%
                        if(chat.getUser().getId().equals(current_user.getId())){
                    %>
                    <input type="hidden" value="<%=chat.getOpponent_user().getId()%>" name="user_id">
                    <input type="hidden" value="<%=chat.getUser().getId()%>" name="sender_id">
                    <%
                        } else {
                    %>
                    <input type="hidden" value="<%=chat.getUser().getId()%>" name="user_id">
                    <input type="hidden" value="<%=chat.getOpponent_user().getId()%>" name="sender_id">
                    <%
                        }
                    %>
                    <div class="col-2">
                        <button class="btn btn-outline my-2 my-sm-0 float-right" style="border: #433F73 solid 2px; color: #433F73; font-weight: bolder;"     type="submit">
                            <i class="fab fa-telegram-plane"></i>
                            Send
                        </button>
                    </div>
                </form>
            </div>

            <br/>
            <br/>
        </div>
        <%
            } else {
        %>
        <h3>First Create Chat</h3>
        <%
            }
        %>



    </div>
</div>


<%  } %>
<%@include file="templates/footer.jsp" %>





</body>
</html>
