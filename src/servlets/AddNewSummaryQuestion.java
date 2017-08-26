package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SummaryDAO;
import entity.Professor;
/**
 * Servlet implementation class AddNewSummaryQuestion
 */
@WebServlet("/AddNewSummaryQuestion")
public class AddNewSummaryQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewSummaryQuestion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String summaryQuestion = (String)session.getAttribute("summaryQuestion");
		Professor professor = (Professor)session.getAttribute("professor");
		int avatar_id = professor.getAvatar_id();
		
		SummaryDAO summaryDAO = new SummaryDAO();
		summaryDAO.addQuestion(summaryQuestion,avatar_id);
		//direct to jsp or forward the message;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
