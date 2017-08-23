package servlets;

import java.io.*;
import dao.*;
import entity.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Authenticate
 */
@WebServlet("/ReplyToPost")
public class ReplyToPost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReplyToPost() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int avatar_id = 1;
		String tempPostID = (String)request.getParameter("postID");
		int post_id = Integer.parseInt(tempPostID);
		String tempPostTitle = request.getParameter("postTitle");
		String tempPostContent = request.getParameter("postContent");
		
        
		String errorMsg = "";
		PostDAO postDAO = new PostDAO();
		
		//System.out.println(post_id +" "+ tempPostTitle+" "+tempPostContent);
		
			
		if(tempPostContent.isEmpty()||tempPostContent == null){
			errorMsg = "Your reply content cannot be empty!";
			RequestDispatcher rd = request.getRequestDispatcher("replyToPost.jsp?post_id="+post_id);
			request.setAttribute("replyToPost", errorMsg);
			rd.forward(request, response);
			return;
		}else{
			postDAO.replyToPost(avatar_id, post_id, tempPostTitle, tempPostContent);
			RequestDispatcher rd = request.getRequestDispatcher("viewPost.jsp?post_id="+post_id);
			rd.forward(request, response);
			return;
		}

		

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
