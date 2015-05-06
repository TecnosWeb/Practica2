package controlador;

import java.io.File;
import java.io.IOException;

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
    Gestionar g=new Gestionar();   
    File f=g.crearDirectorio();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorRegistro() {
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
		String user = request.getParameter("txtUsuario");
		String password = request.getParameter("txtPassword");
		String eMail = request.getParameter("txtEMail");
		String nombre = request.getParameter("txtNombre");
		String apellidos = request.getParameter("txtApellidos");
		String mensaje = "---------------------<br>Error. Campo(s) nulo(s) o e-mail invalido<br>-----------------------";
		if (!(user.isEmpty() || password.isEmpty() || eMail.isEmpty() || nombre.isEmpty()||
				apellidos.isEmpty())) {
			try {
				if(g.buscarUsuario(user, password))
					mensaje= "Este usuario ya ha sido registrado, introduzca otro. ";
				
				else{
					if(g.registrarUsuario(f,user, password, nombre, apellidos, eMail))
						mensaje = "-------------------------------<br>Registro exitoso!<br>-----------------------------------";
				}
			} catch (Exception e) {
				System.out.println("Error al buscar usuario.");
				e.printStackTrace();
			}
		}
		mensaje += "<br><a href='login.html'>Regresar</a>";
		g.respuesta(response, mensaje);
	}

}
