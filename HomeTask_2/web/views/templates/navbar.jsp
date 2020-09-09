<%
    String bgColor = "#168016";
    String color = "#ffffff";
%>

<nav class="navbar navbar-expand-lg navbar-light" id="navbar" style="background-color:<%=bgColor%>; color:<%=color%>; border-radius: 2px">
    <a class="navbar-brand" href="/" style="color:white; font-weight: bold">dongelekter.kz</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/" style="color:white"> All Transports</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/add" style="color:white">Add Transport</a>
            </li>

        </ul>

    </div>
</nav>

<br>
<br>
<br>