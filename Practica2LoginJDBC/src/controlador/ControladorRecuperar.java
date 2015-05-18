package controlador;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControladorRecuperar
 */
@WebServlet("/ControladorRecuperar")
public class ControladorRecuperar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Recuperar rec=new Recuperar();
	ConexionDB conex =new ConexionDB();
	
	/**
     * @see HttpServlet#HttpServlet()
     */
	public void init(){
		
	}
    public ControladorRecuperar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		conex.crearConexion();
		String [] campo=new String[1];
		campo[0]="password";
		String email=request.getParameter("correo");
		ResultSet r=conex.select("usuarios",campo);
		if(conex.buscarEmail(r, email)){
			String pass;
			try {
				pass = r.getString("password");
				rec.mandar(email, pass);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}		
	}

}
