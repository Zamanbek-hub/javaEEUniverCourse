<%@ page import="db.classes.Message" %>
<%
    try{
        Message message = (Message)request.getAttribute("message");
        if(message.isHidden()){
            if(message.isSuccess()) {
%>
<br>
<div class="alert alert-success" role="alert">
    <%=message.getMessage()%>
</div>
<%          }else{  %>
<br>
<div class="alert alert-danger" role="alert">
    An error occurred
</div>

<%
            }
        }
    }catch (Exception e){

    }
%>

<br>