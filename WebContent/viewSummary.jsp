<%@include file="protect.jsp"%>
<%@ page
	import="java.io.*,java.util.*, java.util.concurrent.*, utility.*, entity.Professor, dao.SummaryDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>IS102 Web Platform</title>
<meta name="viewport" content="width=device-width, initial-scale=1">


<link rel="stylesheet" href="style/css/bootstrap.min.css">
<link rel="stylesheet" href="style/css/font-awesome.min.css">
<link rel="stylesheet" href="style/css/forumHomePageLayout.css">
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
				<li class="nav-item"><a class="nav-link" href="viewSummary.jsp">Post-class
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
	    Professor professor = (Professor)session.getAttribute("professor");
		int avatar_id = professor.getAvatar_id();
		SummaryDAO sdao = new SummaryDAO();
		HashMap<Integer, String> map = sdao.retrieveByAvatar(avatar_id);
	%>
	
	<div style="margin-top: 2%"></div>

	<div class="row justify-content-md-center">

		<div class="col-12 col-md-auto">

			<div class="row">

				<div class="col-12">
					<a style="width: 12rem; height: 2.4rem" href="#"><b>View All Post Class Summary</b></a>
					<div class="btn-group" role="group" aria-label="Basic example">
						<a class="btn btn-outline-primary" style="width: 13rem; height: 2.4rem"
							href="#"><b>Add New Question</b></a>	
					</div>
				</div>

			</div>
			<div class="scroll">

				<table class="table table-bordered">
					<thead class="thead-default">
						
						<tr>
							<th>No</th>
							<th>Question</th>
							<th colspan='3'>Actions</th>

						</tr>
						<%
							int no = 1;
							for (Integer key : map.keySet()) {
								 String question = map.get(key);
						%>
					</thead>
					<tbody>

						<tr>
							<th><%=no++%></th>
							<td><%=question%></td>
							<td ><a href="#">Edit</a></td>
							<td ><a href="deleteSummaryQuestion.jsp?q_id=<%=key%>">Delete</a></td>
						</tr>


					</tbody>




					<%
						} // end for
					%>


				</table>
			</div>
		</div>
		
	<div class="col-8"></div>


	<script
		src="//cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
	<script src="style/js/jquery-3.2.1.min.js"></script>
	<script src="style/js/bootstrap.min.js"></script>
	<%@ include file="footer.jsp"%>
</body>


</html>