<%@ page import="db.classes.News" %><%--
  Created by IntelliJ IDEA.
  User: zaman
  Date: 9/30/2020
  Time: 3:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="./templates/cdns.jsp" %>
    <%@include file="./templates/styles.jsp" %>

</head>
<body>
<%@include file="./templates/navbar.jsp" %>
<div class="container">
<div class="row">
    <%

        News n = (News)request.getAttribute("news");

        if(n!=null){



    %>
    <div class="col-8">
        <div class="card">
            <h4 class="card-title"><%=n.getTitle()%></h4>
            <h5 class="card-title"><%=n.getPost_date()%></h5>
            <img class="card-img-top" src="<%=n.getPicture_url()%>" alt="Card image cap">
            <div class="card-body">
                <p class="card-text"><%=n.getShort_content()%></p>
                <p class="card-text"><%=n.getContent()%></p>
                <a href="/" class="btn btn-primary">Read More</a>
            </div>
        </div>
    </div>



    <div class="col-4">
        <div class="about_pub">
            <div class="about_pub_body">
                <h4 class="title">About <%=n.getPublication().getName()%></h4>
                <p class="card-text"><%=n.getPublication().getDescription()%></p>
                <p class="card-text">Rating:<%=n.getPublication().getRating()%></p>
                <a href="/" class="">Read More</a>
            </div>
        </div>
    </div>
    <%


        }


    %>

</div>
</div>
</body>
</html>
