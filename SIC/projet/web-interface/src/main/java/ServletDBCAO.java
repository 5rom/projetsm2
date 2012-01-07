
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.univ_lyon1.master_info.m2ti.tiw5.services.CAODataBase;
import fr.univ_lyon1.master_info.m2ti.tiw5.services.CAODataBase_Service;



/**
 * Servlet implementation class ServletDBCAO
 */
public class ServletDBCAO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDBCAO() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String bouton=request.getParameter("bouton");
		Long pnum = Long.parseLong(request.getParameter("pnum"));
		String pnom = request.getParameter("pnom");
		CAODataBase_Service cAODBS= new CAODataBase_Service();
		CAODataBase cAODB = cAODBS.getCAODataBaseSOAP();
		cAODB.addProduit(pnum, pnom);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
