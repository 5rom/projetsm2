package ti1.tp5.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import tp5.serveur.Serveur;


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
		String res = new String("");
		String commande = request.getParameter("action");
		String url = request.getParameter("url");
		String description = request.getParameter("description");
		HashMap<String,String> parametres = new HashMap<String,String> ();
		parametres.put("desc", description);
		parametres.put("url", url);
		response.setContentType("text/html; charset=UTF-8");
		ServletOutputStream output = response.getOutputStream();
		BundleContext context = (BundleContext) getServletContext().getAttribute("osgi-bundlecontext");
		ServiceReference sr = context.getServiceReference(Serveur.class.getName());
		if (sr != null){
			Serveur si = (Serveur) context.getService(sr);
			output.println("init");
			res = si.traiteRequete(commande, parametres);
			output.println("started");
		}
		
		output.println("<html>");
		output.println("<head><title>Servlet annuaire</title></head>");
		output.println("<body>");
		output.println("<h1>Servlet annuaire</h1>");
		if (new String("initSites").equals(commande))
			output.println("</br>Sites r&eacute;actulis&eacute;s");
		else output.println("</br><b>r&eacute;sultat de la commande : "+commande+"</b>");
		output.println("</br></br>");
		output.println(res);
		if(new String("addSite").equals(commande) || new String("removeSite").equals(commande)){
			output.println("url : " + url);
			output.println("</br>description : " + description);
		}
		output.println("</br></br>");
		output.println("<p><a href='index.html'>Retour</a></p>");
		output.println("</body>");
		output.println("</html>");
	}

}
