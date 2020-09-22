<br>
<%
    try{
        boolean success = (boolean)request.getAttribute("success");
        String message = (String)request.getAttribute("message");
        if(success) {
%>
    <div class="alert alert-success" role="alert">
        <%=message%>
    </div>
<%}else{%>
    <div class="alert alert-danger" role="alert">
        An error occurred
    </div>

<%
        }
    }catch (Exception e){

    }
%>

<br>