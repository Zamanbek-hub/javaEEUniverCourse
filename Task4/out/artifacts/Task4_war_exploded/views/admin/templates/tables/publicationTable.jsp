<%@ page import="db.classes.Publication" %>
<%@ page import="java.util.ArrayList" %>

<div class="row">
    <div class="col-2" style="background-color: #F4F4F4">
        <br/>
        <p><big>ADMIN PANEL</big></p>
        <hr>
        <ul>
            <li><a href="/admin/languages">Languages</a></li>
            <li style="color:blue; font-weight: bold;"><a href="/admin/publications">Publications</a></li>
            <li><a href="/admin/news">News</a></li>
        </ul>

    </div>
    <div class="col-10" style="background-color: white; height: auto;">
        <%@include file="./../messages.jsp" %>
        <div class="card-body">
            <span style="font-weight: bolder; font-size:24px;">Publications</span>
            <button class="btn btn-success" data-toggle="modal" data-target="#addModal" style="float: right;">+Add new</button>
        </div>

        <hr>

        <div class="card-body">
            <table class="table table-light table-bordered table-striped table-hover">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Name</th>
                    <th scope="col">Description</th>
                    <th scope="col">Rating</th>
                    <th scope="col">OPERATIONS</th>
                </tr>
                </thead>
                <tbody>


                <%
                    int count = 0;
                    ArrayList<Publication> publications = (ArrayList<Publication> )request.getAttribute("publications");

                    if(publications!=null){
                        for(Publication pub: publications){
                            count++;

                %>
                <tr>
                    <th scope="row"><%=count%></th>
                    <td><%=pub.getName()%></td>
                    <td><%=pub.getDescription()%></td>
                    <td><span style="max-width: 300px;"><%=pub.getRating()%></span></td>
                    <td>
                        <form class="float-right" action="/delete_publication" method="POST" style="height: 15px;">
                            <input type="hidden" name="delete_id" value="<%=pub.getId()%>">
                            <button class="btn btn-danger ">Delete</button>
                        </form>
                        <button class="btn btn-primary float-right" style="margin-right: 10px;" onclick="fillToUpdateModal(<%=pub.getId()%>, '<%=pub.getName()%>', '<%=pub.getDescription()%>', <%=pub.getRating()%>)" data-toggle="modal" data-target="#updateModal">Edit</button>
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
            <form method="POST" action="/add_publication">
                <div class="modal-header">
                    <h5 class="modal-title" id="addLeagueModalLabel">Modal title</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="add_name">Name:</label>
                        <input type="text" class="form-control" id="add_name" name="add_name" placeholder="Name of publication...">
                    </div>
                    <div class="form-group">
                        <label for="add_description">Description:</label>
                        <textarea type="text" class="form-control" id="add_description" name="add_description" style="text-transform: uppercase;" placeholder="Description..."></textarea>
                    </div>
                    <div class="form-group">
                        <label for="add_rating">Rating:</label>
                        <input type="number" class="form-control" id="add_rating" name="add_rating" style="text-transform: uppercase;"
                               placeholder="##" oninput="if(this.value.length>=2) { this.value = this.value.slice(0,2); }">
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
            <form method="POST" action="/update_publication" id="update_form">
                <div class="modal-header">
                    <h5 class="modal-title" id="updateLeagueModalLabel">Modal title</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <input type="hidden" id="update_id" name="update_id">
                    <div class="form-group">
                        <label for="update_name">Name:</label>
                        <input type="text" class="form-control" id="update_name" name="update_name" placeholder="Name of league...">
                    </div>
                    <div class="form-group">
                        <label for="update_description">Description:</label>
                        <textarea type="text" class="form-control" id="update_description" name="update_description" placeholder="Description..."></textarea>
                    </div>
                    <div class="form-group">
                        <label for="update_rating">Rating:</label>
                        <input type="text" class="form-control" id="update_rating" name="update_rating"
                               placeholder="##" oninput="if(this.value.length>=2) { this.value = this.value.slice(0,2); }">
                    </div>
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
    const fillToUpdateModal = (id, name, description,rating) =>{
        document.getElementById("update_id").value = id;
        document.getElementById("update_name").value = name;
        document.getElementById("update_description").value = description;
        document.getElementById("update_rating").value = rating;
    }

</script>