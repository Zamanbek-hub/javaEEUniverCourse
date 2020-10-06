<%@ page import="db.classes.Message" %>
<div class="container">
<%
    try{
        Message message = (Message)request.getAttribute("message");
        if(! message.isHidden()){
            if(message.isSuccess()) {
%>
<br>
<div class="alert alert-success" role="alert">
    <%=message.getMessage()%>
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
</div>
<%          }else{  %>
<br>
<div class="alert alert-danger" role="alert">
    <%=message.getMessage()%>
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
</div>

<%
            }
        }
    }catch (Exception e){

    }
%>

<br>
</div>