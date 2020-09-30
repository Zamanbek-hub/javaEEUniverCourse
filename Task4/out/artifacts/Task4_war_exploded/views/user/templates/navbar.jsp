<%@ page import="db.classes.Publication" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="db.classes.Language" %>


<div class="row top_menu">
    <div class="col-3 text-center top_menu_langs">
    <%

        ArrayList<Language> languages = (ArrayList<Language> )request.getAttribute("languages");

        if(languages!=null){
            for(Language lan: languages){


    %>
    <a class="" href="/set_language_cookie?language_id=<%=lan.getId()%>"><span class="top_menu_langs_text"><%=lan.getCode()%></span></a>
    <%
            }
        }
    %>
    </div>
    <div class="col-6 text-center top_menu_title">
        <span class="top_menu_title_text">World News Portal</span>
    </div>

        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Styles
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                <a class="dropdown-item" href="#">Action</a>
                <a class="dropdown-item" href="#">Another action</a>
                <a class="dropdown-item" href="#">Something else here</a>
            </div>
        </li>


</div>
<nav class="navbar navbar-expand-lg" style="background-color: #2B9E74;">
    <a class="navbar-brand" href="/">World News Portal</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
            <%

                ArrayList<Publication> publications = (ArrayList<Publication> )request.getAttribute("publications");

                if(publications!=null){
                    for(Publication pub: publications){


            %>
            <a class="nav-item nav-link active" href="/?publication_id=<%=pub.getId()%>"><%=pub.getName()%> <span class="sr-only">(current)</span></a>
            <%
                    }
                }
            %>
        </div>
    </div>
</nav>