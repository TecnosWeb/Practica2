package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionDB {
	Connection con;
	public static final int USUARIO_NO_VALIDO=-1;
	public static final int CONTRASENA_INVALIDA=0;
	public static final int USUARIO_ACEPTADO=1;
	public ConexionDB() {
		// TODO Auto-generated constructor stub
	}

	public void crearConexion(){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url="jdbc:mysql://localhost:3306/usuarios_web";
			String password="uzu-rendan";
			String user="root";
			con= DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /*finally {
			cerrar(con);
		}*/
		
	}
	public ResultSet select(String tabla,String campos[]){
		String query="SELECT ";
		for(int i=0;i<campos.length;i++){
			query+=campos[i];
			if(i<campos.length-1) query+=",";
		}
		query+=" FROM "+tabla;
		try{
		Statement consulta = con.createStatement();
		ResultSet resultados = consulta.executeQuery(query);
		return resultados;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println("Error en la sentencia SELECT");
		e.printStackTrace();
	}
		return null;
	}
	public int buscarUsuario(ResultSet resultados,String usuario,String password){
		int mensaje=USUARIO_NO_VALIDO;
		
		try {
			while(resultados.next()){
				if(resultados.getString("usuario").equals(usuario)){
					mensaje=CONTRASENA_INVALIDA;
					if(resultados.getString("password").equals(password)){
						mensaje=USUARIO_ACEPTADO;
						return mensaje;
					}			
				}
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void insertarUsuario(String usuario,String nombre, String password, String apellido,String email ){
		String query="INSERT INTO usuarios(nombre,correo,password,apellidos,usuario) values('"+nombre+"','"+email+"','"+password+"','"+apellido+"','"+usuario+"');";
		try {
			Statement consulta=con.createStatement();
			//consulta.executeQuery(query);
			consulta.execute(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error insertando\n"+e.getMessage());
			e.printStackTrace();
		}
		
	}
	
}
