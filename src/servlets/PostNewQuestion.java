package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PostDAO;
import entity.Student;

/**
 * Servlet implementation class PostNewQuestion
 */
@WebServlet("/PostNewQuestion")
public class PostNewQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostNewQuestion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpSession session = request.getSession();
		//Student student = (Student)session.getAttribute("user");
		//int avatar_id = student.getAvatar_id();
		int avatar_id = 1;
		String post_title =  request.getParameter("postTitle");
		String post_content =  request.getParameter("postContent");
		PostDAO postDAO = new PostDAO();
		
		String errorMsg = "";
		if(post_title.isEmpty()||post_title == null){
			errorMsg = "The post title cannot be empty!";
			RequestDispatcher rd = request.getRequestDispatcher("newPost.jsp");
			request.setAttribute("newPostMsg", errorMsg);
			rd.forward(request, response);
			return;
		}else if(post_content.isEmpty()||post_content == null){
			errorMsg = "The post content cannot be empty!";
			RequestDispatcher rd = request.getRequestDispatcher("newPost.jsp");
			request.setAttribute("newPostMsg", errorMsg);
			rd.forward(request, response);
			return;
		}else{
			postDAO.addNewPost(avatar_id, post_title, post_content);
			RequestDispatcher rd = request.getRequestDispatcher("forumHome.jsp");
			rd.forward(request, response);
			return;
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
