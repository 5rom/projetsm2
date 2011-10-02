import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String qui=request.getParameter("qui");
		PrintWriter out = response.getWriter();

		
		if (qui.equalsIgnoreCase("xml")){
			response.setContentType("text/xml");
			out.println("<album id=\"0\">");
			out.println("<titre>Mon album</titre>");
			out.println("</album>");
		} else if (qui.equalsIgnoreCase("xhtml") || qui.equalsIgnoreCase("html")){
			response.setContentType("text/html");
			out.println("<head><title>Mon album</title></head>");
			out.println("<body> ");
			out.println("Album 1");
			out.println("Titre : Mon album");
			out.println("</body>");
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
