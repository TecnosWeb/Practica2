package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletResponse;

public class ConexionDB {
	Connection con;
	static final int USUARIO_NO_VALIDO=-1;
	static final int CONTRASENA_INVALIDA=0;
	static final int USUARIO_ACEPTADO=1;
	public ConexionDB() {
		}
	public void crearConexion(){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("llegamos a instanciar");
			String url="jdbc:mysql://localhost:3306/usuarios_web";
			String password="uzu-rendan";
			String user="root";
			con= DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("creando conexion "+e.getMessage());
		} 
	}
	public ResultSet select(String tabla,String campos[]){
		String query="SELECT ";
		for(int i=0;i<campos.length;i++){
			query+=campos[i];
			if(i<campos.length-1) query+=",";
		}
		query+=" FROM "+tabla+";";
		System.out.println(query);
		try{
		Statement consulta = con.createStatement();
		System.out.println("consulta creada");
		ResultSet resultados = consulta.executeQuery(query);
		return resultados;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println("Error en la sentencia SELECT");
		e.printStackTrace();
	}
		System.out.println("no se pudo ejecutar");
		return null;
	}
	public int buscarUsuario(ResultSet resultados,String usuario,String password){
		int mensaje=0;
		try {
			while(resultados.next()){
					if(resultados.getString("usuario").equals(usuario)&&resultados.getString("password").equals(password)){
					return 1;
				}
			}
			return mensaje;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return mensaje;
		
		}
	public boolean buscarEmail(ResultSet resultados,String email){
		boolean mensaje=false;
		try {
			while(resultados.next()){
					if(resultados.getString("email").equals(email)){
					return true;
				}
			}
			return mensaje;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return mensaje;
		
		}
	
	
	public Object parser(Object o){
		if(o instanceof String ) return (String)o;
		if(o instanceof Integer ) return (Integer)o;
		return null;
	}
	public void cerrar(Connection c){
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public boolean insertarUsuario(String usuario, String password,String nombre, String apellido,String email ){
		String query="INSERT INTO usuarios(nombre,correo,password,apellidos,usuario) values('"+nombre+"','"+email+"','"+password+"','"+apellido+"','"+usuario+"');";
		try {
			Statement consulta=con.createStatement();
			consulta.execute(query);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error insertando\n"+e.getMessage());
			e.printStackTrace();
			return false;
		}
		
	}
	public void respuesta(HttpServletResponse resp, String msg) throws IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<t1>" + msg + "</t1>");
		out.println("</body>");
		out.println("</html>");
	}
}
