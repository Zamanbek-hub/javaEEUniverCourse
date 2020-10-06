<%
    String mainBGColor = "navy";
%>
<style>

    nav{
        background-color: <%=mainBGColor%>;
    }

    nav a{
        color: white;
        padding-left: 30px;
    }

    nav a i{
        padding-right: 5px;
    }


    form{
        margin-top: 50px;
        width: 500px;
    }

    form .title{
        text-align: center;
    }

    form .submit_button{
        margin-top: 20px;
        background-color: <%=mainBGColor%>;
        color: white;
    }

    form .submit_button:hover{
        color: white;
        font-weight: bold;
    }

    footer{
        background-color: #262626;
        color: white;
        text-align: center;
        height: 50px;

        position: fixed;
        bottom: 0;
        width: 100%;
    }


/*    Only Home styles */
    .home_page {
        margin-top: 30px;
    }

    .profile_menu{
        margin-top: 20px;
    }

    .profile_menu a{
        margin-left: 20px;
    }

    .profile_part{
        border:  1px solid slategrey;
    }

    .bound{
        border:  1px solid slategray;
        border-top-left-radius: 3px;
        border-top-right-radius: 3px;

    }

    .posts .card{
        width: 90%;
        height: 200px;
        display: block;
        margin-left: auto;
        margin-right: auto;
    }



</style>