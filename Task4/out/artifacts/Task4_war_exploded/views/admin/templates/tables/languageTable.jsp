<div class="row">
    <div class="col-2" style="background-color: #F4F4F4">
        <br/>
        <p><big>ADMIN PANEL</big></p>
        <hr>
        <ul>
            <li style="color:blue; font-weight: bold;"><a href="/admin/languages">Languages</a></li>
            <li><a href="/admin/publications">Publications</a></li>
            <li><a href="/admin/news">News</a></li>
        </ul>

    </div>

    <div class="col-10" style="background-color: white; height: auto; min-height: 800px;">
        <%@include file="./../messages.jsp" %>
        <div class="card-body">
            <span style="font-weight: bolder; font-size:24px;">Languages</span>
            <button class="btn btn-success" data-toggle="modal" data-target="#addModal" style="float: right;">+Add new</button>
        </div>

        <hr>

        <div class="card-body">
            <table class="table table-light table-bordered table-striped table-hover">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Name</th>
                    <th scope="col">Code</th>
                    <th scope="col">OPERATIONS</th>
                </tr>
                </thead>
                <tbody>


                <%
                    int count = 0;
                    ArrayList<Language> languages = (ArrayList<Language> )request.getAttribute("languages");

                    if(languages!=null){
                        for(Language lan: languages){
                            count++;

                %>
                <tr>
                    <th scope="row"><%=count%></th>
                    <td><%=lan.getName()%></td>
                    <td><%=lan.getCode()%></td>
                    <td>
                        <form class="float-right" action="/delete_language" method="POST" style="height: 15px;">
                            <input type="hidden" name="delete_id" value="<%=lan.getId()%>">
                            <button class="btn btn-danger ">Delete</button>
                        </form>
                        <button class="btn btn-primary float-right" style="margin-right: 10px;" onclick="fillToUpdateModal(<%=lan.getId()%>, '<%=lan.getName()%>', '<%=lan.getCode()%>')" data-toggle="modal" data-target="#updateModal">Edit</button>
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
            <form method="POST" action="/add_language">
                <div class="modal-header">
                    <h5 class="modal-title" id="addLeagueModalLabel">Modal title</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="add_name">Name:</label>
                        <input type="text" class="form-control" id="add_name" name="add_name" placeholder="Name of league...">
                    </div>
                    <div class="form-group">
                        <label for="add_code">Description:</label>
                        <input type="text" class="form-control" id="add_code" name="add_code" style="text-transform: uppercase;"
                               placeholder="###" oninput="if(this.value.length>=3) { this.value = this.value.slice(0,3); }">
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
            <form method="POST" action="/update_language" id="update_form">
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
                        <label for="update_code">Code:</label>
                        <input type="text" class="form-control" id="update_code" name="update_code" style="text-transform: uppercase;"
                               placeholder="###" oninput="if(this.value.length>=3) { this.value = this.value.slice(0,3); }">
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
    const fillToUpdateModal = (id, name, code,year) =>{
        document.getElementById("update_id").value = id;
        document.getElementById("update_name").value = name;
        document.getElementById("update_code").value = code;
    }

</script>