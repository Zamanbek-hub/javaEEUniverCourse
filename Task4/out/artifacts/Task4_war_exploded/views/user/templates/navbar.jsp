<%@ page import="db.classes.Publication" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="db.classes.Language" %>


<div class="row top_menu">
    <div class="col-3 text-center top_menu_langs">
    <%

        ArrayList<Language> languages = (ArrayList<Language> )request.getAttribute("languages");
        Language language = (Language )request.getAttribute("language");

        if(languages!=null){
            for(Language lan: languages){
                if(lan.getId().equals(language.getId())){

    %>
    <a class="active" style="font-weight: bold; text-decoration-style: dashed" href="/set_language_cookie?language_id=<%=lan.getId()%>"><span class="top_menu_langs_text"><%=lan.getCode()%></span><span class="sr-only">(current)</span></a>
    <%
                } else {
                        %>
        <a class="" href="/set_language_cookie?language_id=<%=lan.getId()%>"><span class="top_menu_langs_text"><%=lan.getCode()%></span></a>
    <%
                    }
            }
        }
    %>
    </div>
    <div class="col-6 text-center top_menu_title">
        <span class="top_menu_title_text">World News Portal</span>
    </div>

    <div class="top_menu_styles col-1">
        <li class="nav-item dropdown form-control">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Styles
            </a>

            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                <%
                    String [] styles = {"Default", "Light", "Dark", "Monochrome", "Monochrome Inverse", "Large", "Small", "Facebook", "Instagram"};


                    for(int i = 0; i < styles.length; i++){
                        int j = i + 1;
                        if(j == Integer.parseInt(style)){

                %>
                <a class="dropdown-item active" href="/set_style?style=<%=j%>"><%=styles[i]%></a>
                <%}else{%>
                <a class="dropdown-item" href="/set_style?style=<%=j%>"><%=styles[i]%></a>
                <%
                        }

                    }

                %>
            </div>
        </li>
    </div>

    <div class="top_menu_sign_in col-1">
        <a class="btn btn-outline-secondary" href="/admin/home">Sign In</a>
    </div>


</div>
<nav class="navbar navbar-expand-lg">
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
            <a class="nav-item nav-link active pubs" href="/?publication_id=<%=pub.getId()%>" style="margin-left:40px;"><%=pub.getName()%> <span class="sr-only">(current)</span></a>
            <%
                    }
                }
            %>
        </div>
    </div>
</nav>