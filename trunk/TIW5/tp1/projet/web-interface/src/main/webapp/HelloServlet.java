import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tiw5.modele.Album;

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
		
		PrintWriter out = response.getWriter();

		
		/*if (qui.equalsIgnoreCase("xml")){
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
			
		}*/
		String format="";
		if (request.getParameter("format").equals("1"))
			format="xml";
		else if (request.getParameter("format").equals("2"))
			format="xhtml";
		out.println("<HTML>\n<BODY>\n" +
				"<H1>Recapitulatif des informations</H1>\n" +
				"<UL>\n" +			   
		"  <LI>Numéro de l'album: "
				+ request.getParameter("noAlbum") + "\n" +
				"  <LI>Choix: "
				+ request.getParameter("format") + "\n" +
				"</UL>\n" +				
				
		"<FORM Method=\"POST\" Action=\"index.jsp\">"+
		"<INPUT type=submit value=Retour>"+
		"</FORM>"+				
				
		"</BODY></HTML>"); 
		
		Album a;

		/**
		 * Ici :
		 * -Faire ce qui est fait dans testMapping pour recuperer la liste des albums de la base.
		 * -Parcourir la liste et chercher l'album d'id noAlbum.
		 * -Si format = xml faire ce qui est fait dans testModSemiStruct pour marshalliser l'album choisi et afficher le xml transformé avec xslt
		 * -Si format = xhtml, construire simplement l'affichage en html (out.println("...")).
		 * -Mettre un bouton retour pour revenir à l'index.jsp. 
		 */
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

