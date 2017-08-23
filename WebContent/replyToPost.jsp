<!--<%@include file="protect.jsp"%>-->
<%@ page
	import="java.io.*,java.util.*, java.util.concurrent.*, utility.*, entity.Post, dao.PostDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>IS102 Web Platform</title>
<meta name="viewport" content="width=device-width, initial-scale=1">


<link rel="stylesheet" href="style/css/bootstrap.min.css">
<link rel="stylesheet" href="style/css/font-awesome.min.css">

</head>
<body>
	<nav class="navbar navbar-toggleable-md navbar-light bg-faded">
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarTogglerDemo01"
			aria-controls="navbarTogglerDemo01" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarTogglerDemo01">
			<a class="navbar-brand" href="home.jsp">IS102</a>
			<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
				<li class="nav-item"><a class="nav-link" href="attendance.jsp">Attendance</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Post-class
						Summary</a></li>
				<li class="nav-item"><a class="nav-link"
					href="consultation.jsp">Consultation</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Dashboard</a></li>
				<li class="nav-item"><a class="nav-link" href="forumHome.jsp">Forum</a></li>
				<li class="nav-item"><a class="nav-link" href="logout.jsp">Logout</a></li>
			</ul>
		</div>
	</nav>


	<%
	   
	 String tempID = request.getParameter("parent_id");
     int parentID = Integer.parseInt(tempID);

	 PostDAO pd = new PostDAO();
 	 Post parentPost = pd.retrieveParentPost(parentID);
		
 	 int lastPostID = pd.lastPostID(parentID);
	
	%>
	<div style="margin-top: 2%"></div>
	<div class="container text-center">
		<header>
			<h2>CAT Forum</h2>
			<hr>
		</header>
		
		<form name="replyForm" method="post" action="ReplyToPost" >
                </br>
                <table cellspacing="2" cellpadding="0">
                           <tr>
                        <td>
                        <input type ="text" name="parentID" value="<%=parentID%>" hidden/>
                            <input type ="text" name="postID" value="<%=lastPostID+1%>" hidden/>
                        </td>
                    </tr>
                    <tr>
                    <th>
                            <label>Post Title</label>
                        </th>
                        <td>
                   
                            <input type ="text" name="postTitle" value="<%=parentPost.getPost_title()%>" readonly/>
                        </td>
                    </tr>
                    <tr>
                    
                        <th>
                            <label>Post Content</label>
                        </th>
                        <td><textarea rows="4" cols="50" name="postContent" placeholder="Enter your reply here...">
</textarea>
                            </tr>
                  <tr>
                        <td colspan="2"><input type="submit" value="Submit"></td>
                    </tr>
    
                    </table>
                    </form>


		
	</div>

	<script
		src="//cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
	<script src="style/js/jquery-3.2.1.min.js"></script>
	<script src="style/js/bootstrap.min.js"></script>
	<%@ include file="footer.jsp"%>
</body>


</html>