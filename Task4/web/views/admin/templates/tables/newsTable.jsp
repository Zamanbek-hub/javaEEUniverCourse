<%@ page import="db.classes.Publication" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="db.classes.News" %>

<div class="row">
    <div class="col-2" style="background-color: #F4F4F4">
        <br/>
        <p><big>ADMIN PANEL</big></p>
        <hr>
        <ul>
            <li><a href="/admin/languages">Languages</a></li>
            <li><a href="/admin/publications">Publications</a></li>
            <li style="color:blue; font-weight: bold;"><a href="/admin/news">News</a></li>
        </ul>

    </div>
    <div class="col-10" style="background-color: white; height: auto;">
        <%@include file="./../messages.jsp" %>
        <div class="card-body">
            <span style="font-weight: bolder; font-size:24px;">News</span>
            <button class="btn btn-success" data-toggle="modal" data-target="#addModal" style="float: right;">+Add new</button>
        </div>

        <hr>

        <div class="card-body">
            <table class="table table-light table-bordered table-striped table-hover">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Title</th>
                    <th scope="col">Language</th>
                    <th scope="col">Added date</th>
                    <th scope="col">Publication</th>
                    <th scope="col">DETAILS</th>
                    <th scope="col">OPERATIONS</th>
                </tr>
                </thead>
                <tbody>


                <%
                    int count = 0;
                    ArrayList<News> news = (ArrayList<News> )request.getAttribute("news");

                    if(news!=null){
                        for(News n: news){
                            count++;

                %>
                <tr>
                    <th scope="row"><%=count%></th>
                    <td><%=n.getTitle()%></td>
                    <td><%=n.getLanguage().getName()%></td>
                    <td><%=n.getPost_date()%></td>
                    <td><%=n.getPublication().getName()%></td>
                    <td>
                        <a href="news_details">
                            <button class="btn btn-primary float-right" style="margin-right: 10px;" >Details</button>
                        </a>
                    </td>
                    <td>
                        <form class="float-right" action="/delete_news" method="POST" style="height: 15px;">
                            <input type="hidden" name="delete_id" value="<%=n.getId()%>">
                            <button class="btn btn-danger ">Delete</button>
                        </form>
                        <button class="btn btn-primary float-right" style="margin-right: 10px;"
                                onclick="fillToUpdateModal(<%=n.getId()%>,'<%=n.getTitle()%>',
                                        '<%=n.getShort_content()%>', '<%=n.getContent()%>', '<%=n.getPicture_url()%>',
                                        <%=n.getLanguage().getId()%>, <%=n.getPublication().getId()%>)"
                                data-toggle="modal" data-target="#updateModal">Edit</button>
                    </td>
                </tr>
                <%
                        }
                    }
                %>


                </tbody>
            </table>
        </div>
    </div>

</div>


<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="addModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form method="POST" action="/add_news">
                <div class="modal-header">
                    <h5 class="modal-title" id="addLeagueModalLabel">Modal title</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="add_title">Title:</label>
                        <input type="text" class="form-control" id="add_title" name="add_title" placeholder="Name of title...">
                    </div>
                    <div class="form-group">
                        <label for="add_short_content">Short Content:</label>
                        <textarea type="text" class="form-control" id="add_short_content" name="add_short_content"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="add_content">Content:</label>
                        <textarea type="text" class="form-control" id="add_content" name="add_content"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="add_language">Language:</label>
                        <select class="form-control" id="add_language" name="add_language">
                            <%
                                ArrayList<Language> languages = (ArrayList<Language> )request.getAttribute("languages");

                                if(languages!=null){
                                    for(Language lan: languages){

                            %>
                            <option value="<%=lan.getId()%>" selected> <%=lan.getName()%></option>
                            <%
                                    }
                                }

                            %>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="add_publication">Publication:</label>
                        <select class="form-control" id="add_publication" name="add_publication">
                            <%
                                ArrayList<Publication> publications = (ArrayList<Publication> )request.getAttribute("publications");

                                if(publications!=null){
                                    for(Publication pub: publications){

                            %>
                            <option value="<%=pub.getId()%>" selected> <%=pub.getName()%></option>
                            <%
                                    }
                                }

                            %>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="add_picture_url">Picture URL:</label>
                        <input type="text" class="form-control" id="add_picture_url" name="add_picture_url" placeholder="URL...">
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-dark" >ADD</button>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="updateModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form method="POST" action="/update_news" id="update_form">
                <div class="modal-header">
                    <h5 class="modal-title" id="updateLeagueModalLabel">Modal title</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <input type="hidden" id="update_id" name="update_id">
                    <div class="form-group">
                        <label for="update_title">Title:</label>
                        <input type="text" class="form-control" id="update_title" name="update_title" placeholder="Name of title...">
                    </div>
                    <div class="form-group">
                        <label for="update_short_content">Short Content:</label>
                        <textarea type="text" class="form-control" id="update_short_content" name="update_short_content"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="update_content">Content:</label>
                        <textarea type="text" class="form-control" id="update_content" name="update_content"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="update_picture_url">Picture URL:</label>
                        <input type="text" class="form-control" id="update_picture_url" name="update_picture_url" placeholder="URL...">
                    </div>
                    <input type="hidden" id="update_language_id" name="update_language_id">
                    <input type="hidden" id="update_publication_id" name="update_publication_id">
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-dark" >SAVE</button>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
    const fillToUpdateModal = (id, title, short_content, content, url, lan_id, pub_id) =>{
        document.getElementById("update_id").value = id;
        document.getElementById("update_title").value = title;
        document.getElementById("update_short_content").value = short_content;
        document.getElementById("update_content").value = content;
        document.getElementById("update_picture_url").value = url;
        document.getElementById("update_language_id").value = lan_id;
        document.getElementById("update_publication_id").value = pub_id;
    }

</script>