<%@ page
	import="java.io.*,java.util.*, java.util.concurrent.*, utility.*, dao.SummaryDAO"%>
<%
String id = request.getParameter("q_id");
int question_id = Integer.parseInt(id);

SummaryDAO sdao = new SummaryDAO();
sdao.deleteQuestion(question_id);
response.sendRedirect("viewSummary.jsp");

%>