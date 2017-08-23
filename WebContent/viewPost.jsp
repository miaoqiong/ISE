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
		// retrieve all posts that have same parent ID
		HashMap<Integer, Post> map = pd.retrieveAPost(parentID);

		Post parentPost = pd.retrieveParentPost(parentID);
	%>
	<div style="margin-top: 2%"></div>
	<div class="container text-center">
		<header>
			<h2>
				View Post:
				<%=parentPost.getPost_title()%></h2>
			<hr>
			<h3>
				Post Title:
				<%=parentPost.getPost_id()%> &nbsp;
				<%=parentPost.getPost_title()%></h3>
			<h3>
				Post Content:
				<%=parentPost.getPost_content()%>
			</h3>
		</header>

		<a href="#"><b>Reply</b></a></br> <br>

		<table cellspacing="2" cellpadding="0" class="table_">

			<tr>
				<th>Avatar Name</th>
				<th>Post Content</th>
				<th>Datetime</th>
				<th>Editing Options</th>

			</tr>
			<%
				for (Integer key : map.keySet()) {
					Post post = map.get(key);

					if (post.getPost_id() != parentID) {
			%>
			<tr>
				<td><%=post.getAvatar_id()%></td>
				<td><%=post.getPost_id()%>&nbsp; <%=post.getPost_content()%></td>
				<td><%=post.getTimestamp()%></td>
				<td>tbc</td>


			</tr>
			<%
				}
				} // end for
			%>


		</table>



	</div>

	<script
		src="//cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
	<script src="style/js/jquery-3.2.1.min.js"></script>
	<script src="style/js/bootstrap.min.js"></script>
	<%@ include file="footer.jsp"%>
</body>


</html>