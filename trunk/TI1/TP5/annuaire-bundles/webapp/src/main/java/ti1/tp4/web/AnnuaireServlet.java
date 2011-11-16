package ti1.tp4.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AnnuaireServlet
 */
public class AnnuaireServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnnuaireServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response); // quick & dirty
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String annuaireName = request.getParameter("annuaire");
		String action = request.getParameter("action");
		String url = request.getParameter("url");
		String description = request.getParameter("description");
		response.setContentType("text/html; charset=UTF-8");
		ServletOutputStream output = response.getOutputStream();
		output.println("<html>");
		output.println("<head><title>Servlet annuaire</title></head>");
		output.println("<body>");
		output.println("<h1>Servlet annuaire</h1>");
		output.println("<dl>");
		output.println("<dt>annuaire</dt>");
		output.println("<dd>"+annuaireName+"</dd>");
		output.println("<dt>action</dt>");
		output.println("<dd>"+action+"</dd>");
		output.println("<dt>url</dt>");
		output.println("<dd>"+url+"</dd>");
		output.println("<dt>description</dt>");
		output.println("<dd>"+description+"</dd>");
		output.println("</dl>");
		output.println("<p><a href='index.jsp'>Retour</a></p>");
		output.println("</body>");
		output.println("</html>");
	}

}
