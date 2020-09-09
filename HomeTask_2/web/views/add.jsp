<%--
  Created by IntelliJ IDEA.
  User: Zamanbek
  Date: 09.09.2020
  Time: 22:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="templates/cdns.jsp" %>
    <%@include file="templates/navbarStyles.jsp" %>
</head>
<body>
<%@include file="templates/navbar.jsp" %>
<div class="container mx-auto" style="width: 500px">
    <div class="form-group">
        <label for="type">Choose Transport Type: </label>
        <select class="form-control" id="type">
            <option selected value="/add/truck">Truck</option>
            <option value="/add/bus">Bus</option>
            <option value="/add/car">Car</option>
        </select>
    </div>
    <a type="submit" class="btn btn-primary" id="add_router" href="/add/truck" role="button" style="background-color: <%=bgColor%>">Next ></a>
</div>

<script type="text/javascript">
    let types = document.getElementById("type");
    types.addEventListener("change", function (e) {
        options = types.options;
        document.getElementById("add_router").href = options[options.selectedIndex].value;
    });
</script>
</body>
</html>
