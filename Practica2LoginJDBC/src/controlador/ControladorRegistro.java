package controlador;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControladorRegistro
 */
@WebServlet("/ControladorRegistro")
public class ControladorRegistro extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
	ConexionDB conex =new ConexionDB();
	
    public ControladorRegistro() {
        super();
        conex.crearConexion();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("txtUsuario");
		String password = request.getParameter("txtPassword");
		String eMail = request.getParameter("txtEMail");
		String nombre = request.getParameter("txtNombre");
		String apellidos = request.getParameter("txtApellidos");
		String mensaje="";
		if (user.isEmpty() || password.equals(null) || eMail.equals(null) || nombre.equals(null)||apellidos.equals(null)) 
			mensaje = "---------------------<br>Error. Campo(s) nulo(s) o e-mail invalido<br>-----------------------";
		else{
			try {
				String parametros[];
				parametros =new String[2];
				parametros[0]="usuario";
				parametros[1]="password";
				ResultSet r = conex.select("usuarios", parametros);
				if(conex.buscarUsuario(r,user, password)==1)
					mensaje= "Este usuario ya ha sido registrado, introduzca otro. ";
				if(conex.insertarUsuario(user, password, nombre, apellidos, eMail))
					mensaje = "-------------------------------<br>Registro exitoso!<br>-----------------------------------";
				}
			 catch (Exception e) {
				System.out.println("Error al buscar usuario.");
				e.printStackTrace();
			}
	}
		mensaje += "<br><a href='login.html'>Regresar</a>";
		conex.respuesta(response, mensaje);
	}

}
