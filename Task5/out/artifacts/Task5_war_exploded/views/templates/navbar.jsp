<%

    boolean online = (boolean)request.getAttribute("online");
    System.out.println("Online = " + online);
    if(online){

%>

<nav class="navbar">
    <div class="container-fluid">
        <div class="order-1 order-md-0 dual-collapse2">
            <a class="navbar-brand" href="/"><i class="fab fa-asymmetrik"></i>Aralasu KZ</a>
        </div>
        <div class="order-3 dual-collapse2">
            <a href="/"><i class="fas fa-rss"></i>Feed</a>
            <a href="/"><i class="fas fa-user-friends"></i>My Friends</a>
            <a href="/"><i class="fa fa-users" aria-hidden="true"></i>Groups</a>
            <a href="/"><i class="fas fa-plus-circle"></i>My Posts</a>
            <a href="/"><i class="fab fa-telegram-plane"></i>Messages</a>
            <a href="/"><i class="far fa-images"></i>Pictures</a>
            <a href="/"><i class="fas fa-video"></i>Videos</a>
        </div>
    </div>
</nav>

<%      } else { %>

<nav class="navbar">
    <div class="container-fluid">
        <div class="order-1 order-md-0 dual-collapse2">
            <a class="navbar-brand" href="/"><i class="fab fa-asymmetrik"></i>Aralasu KZ</a>
        </div>
        <div class="order-3 dual-collapse2">
            <a href="/register"><i class="fa fa-user-plus" aria-hidden="true"></i>Register</a>
            <a href="/login"><i class="fa fa-sign-in" aria-hidden="true"></i> Login</a>
            <a href="/"><i class="fa fa-question-circle-o" aria-hidden="true"></i>FAQ</a>
            <a href="/"><i class="fab fa-asymmetrik"></i>About Aralasu</a>
        </div>
    </div>
</nav>

<%  }   %>