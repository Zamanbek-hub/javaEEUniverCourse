<%
    try{
        boolean success = (boolean)request.getAttribute("success");
        String type = (String)request.getAttribute("type");
        String message = (String)request.getAttribute("message");
        if(success) {
%>
<div class="alert alert-<%=type%>" role="alert">
    <%=message%>
</div>
<br>
<%
        }
    } catch(Exception e){

    }
%>