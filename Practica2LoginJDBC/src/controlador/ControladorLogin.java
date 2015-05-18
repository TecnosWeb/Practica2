package controlador;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControladorLogin
 */
@WebServlet("/ControladorLogin")
public class ControladorLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
	ConexionDB c = new ConexionDB();
	
    public ControladorLogin() {
        super();
        c.crearConexion();
    	
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
		// TODO Auto-generated method stub
		String mensaje=null;
		String parametros[];
		parametros =new String[2];
		parametros[0]="usuario";
		parametros[1]="password";
		ResultSet r= c.select("usuarios", parametros);
		int i=c.buscarUsuario(r,request.getParameter("txtUsuario"),request.getParameter("txtPassword"));
		switch(i){
			case 1: 
				mensaje="Login correcto<br/><a href='bienvenido.html'>Continuar</a>";
				break;
			case -1:
				mensaje="Usuario inválido. Usuario nuevo?\n<a href='registro.html'>Registrarse</a>";
				break;
			case 0:
				mensaje="Contrasena incorrecta.<br/><a href='recuperar.html'>Olvidé mi contrasena</a>";
				break;
			default:
				mensaje+="Error. Usuario o contraseña desconocidos<br><a href='login.html'>Regresar</a>";
				break;
		}
			c.respuesta(response,mensaje);
		}
	

}
