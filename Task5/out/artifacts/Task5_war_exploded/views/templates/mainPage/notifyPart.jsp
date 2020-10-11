<div class="col-3">
    <div style="margin-left: 30px;">
        <%
            ArrayList<User> latest_birthdays = (ArrayList<User>) request.getAttribute("latest_birthdays");
            if(latest_birthdays != null){
        %>
        <div class="card" style="background-color: #57BD9E; width: 15rem;">
            <div class="card-header" style="color: white; font-weight: bold; font-size: 15px;">
                Latest BirthDays
            </div>
            <ul class="list-group list-group-flush">
                <%
                    String [] months = new String[]{"January", "February", "March", "April", "May", "June" +
                            "Jule", "August", "September", "October", "November", "December"};
                    for(User birthday: latest_birthdays){
                        if(!birthday.getId().equals(user.getId())){
                %>
                <li class="list-group-item"><%=birthday.getFull_name()%>, <%=birthday.getBirth_date().getDay()%> <%=months[birthday.getBirth_date().getMonth() - 1]%></li>
                <%
                        }
                    }
                %>
            </ul>
        </div>
        <%
            }
        %>

        <br/>

        <div class="card" style="background-color: #57BD9E; width: 15rem;">
            <div class="card-header" style="color: white; font-weight: bold; font-size: 15px;">
                My Games
            </div>
            <ul class="list-group list-group-flush">
                <li class="list-group-item" style="color:blue;"><i class="fas fa-futbol"></i> <strong>FOOTBALL ONLINE</strong>  </li>
                <li class="list-group-item" style="color:blue;"><i class="fas fa-table-tennis"></i>  <strong>PING PONG ONLINE</strong> </li>
                <li class="list-group-item" style="color:blue;"><i class="fas fa-chess"></i> <strong>CHESS MASTERS</strong></li>
                <li class="list-group-item" style="color:blue;"><i class="fas fa-car"></i> <strong>RACES ONLINE</strong></li>
            </ul>
        </div>
    </div>
</div>