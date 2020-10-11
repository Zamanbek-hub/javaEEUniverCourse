<%@ page import="db.classes.Alert" %>
<%@ page import="db.classes.Alert" %>
<div class="container">
<%
    try{
        Alert alert = (Alert)request.getAttribute("alert");
        System.out.println("alert = " + alert);
        if(! alert.isHidden()){
            if(alert.isSuccess()) {
%>
<br>
<div class="alert alert-success" role="alert">
    <%=alert.getMessage()%>
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
</div>
<%          }else{  %>
<br>
<div class="alert alert-danger" role="alert">
    <%=alert.getMessage()%>
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