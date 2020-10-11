<div class="col-3">
    <div style="float: right;">
        <img src="<%=user.getPicture_url()%>" alt="" style="width: 250px; height: 250px;">
        <div class="profile_menu">
            <div class="profile_part bound">
                <a>
                    <strong><%=user.getFull_name()%> </strong>
                    <%
                        long diffInMillies = Math.abs(user.getBirth_date().getTime() - new java.sql.Date(System.currentTimeMillis()).getTime());
                        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) / 365;
                    %>
                    ,<%=diff%> years

                </a>
            </div>
            <div class="profile_part">
                <a href="/profile" style="color: dodgerblue;"><i class="fas fa-address-card"></i> My Profile</a>
            </div>
            <div class="profile_part">
                <a href="/" style="color: dodgerblue;"><i class="fas fa-cogs"></i> Settings</a>
            </div>
            <div class="profile_part bound">
                <a href="/logout" style="color: darkred;"><i class="fas fa-sign-out-alt"></i> Logout</a>
            </div>
        </div>
    </div>
</div>