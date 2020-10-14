<%@ page import="java.util.concurrent.TimeUnit" %>
<div class="col-3">
    <div style="float: right;">
        <img src="<%=user.getPicture_url()%>" alt="" style="width: 250px; height: 250px;">
        <div class="profile_menu">
            <div class="profile_part bound">
                <a>
                    <strong><%=user.getFull_name()%>,
                        <%
                            long diffInMillies = Math.abs(user.getBirth_date().getTime() - new java.sql.Date(System.currentTimeMillis()).getTime());
                            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) / 365;
                        %>
                        <%=diff%> years
                    </strong>

                </a>
            </div>
            <div class="profile_part">
                <%
                    if(caseT.equals("friend")) {
                %>
                <form class="form-inline" method="POST" action="/remove_friend" style="width:100%; margin-top: 0px;">
                    <input type="hidden" value="<%=user.getId()%>" name="friend_id">
                    <button class="btn" style="color: dodgerblue;">
                        <span style="font-weight: bold;">
                            <i class="fas fa-minus-circle"></i>
                            Remove From Friends
                        </span>
                    </button>
                </form>
                <%
                    }
                %>

                <%
                    if(caseT.equals("request_send")) {
                %>
                <form class="form-inline" method="POST" action="/delete_friend_request" style="width:100%; margin-top: 0px;">
                    <input type="hidden" value="<%=user.getId()%>" name="friend_id">
                    <button class="btn" type="submit" style="color: dodgerblue;">
                        <span style="font-weight: bold;">
                            <i class="fas fa-check"></i>
                        Request Sent
                        </span>
                    </button>
                </form>
                <%
                    }
                %>

                <%
                    if(caseT.equals("request_get")) {
                %>
                <form class="form-inline" id="confirm_search_form" method="POST" action="/delete_friend_request" style="width:100%; margin-top: 0px;">
                    <input type="hidden" value="<%=user.getId()%>" name="friend_id">
                    <button class="btn btn-outline" id="confirm-button" type="submit" onclick="changeURL('/confirm_friend_request')" style="color: dodgerblue;">
                        <span style="font-weight: bold;">
                            <i class="fas fa-plus-circle"></i>
                        Confirm
                        </span>
                    </button>
                    <button class="btn btn-outline" id="reject-button" type="submit" onclick="changeURL('/delete_friend_request')" style="color: dodgerblue;">
                        <span style="font-weight: bold;">
                            <i class="fas fa-trash-alt"></i>
                            Reject
                        </span>
                    </button>
                </form>
                <%
                    }
                %>


                <%
                    if(caseT.equals("nothing")) {
                %>
                <form class="form-inline" method="POST" action="/send_friend_request" style="width:100%; margin-top: 0px;">
                    <input type="hidden" value="<%=user.getId()%>" name="friend_id">
                    <button class="btn btn-outline" type="submit" style="color: dodgerblue;">
                        <span style="font-weight: bold;">
                            <i class="fa fa-plus" aria-hidden="true"></i>
                            Add To Friends
                        </span>
                    </button>
                </form>
                <%
                    }
                %>


            </div>
            <div class="profile_part">
                <a href="/" style="color: dodgerblue;"><i class="fab fa-telegram-plane"></i> Send Message</a>
            </div>
            <div class="profile_part bound">
                <a href="/logout" style="color: darkred;"><i class="fas fa-sign-out-alt"></i> Logout</a>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">
    const changeURL = (url) => {
        document.getElementById("confirm_search_form").action = url;
    }
</script>