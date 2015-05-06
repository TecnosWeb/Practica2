package controlador;

import static org.junit.Assert.*;

import java.sql.ResultSet;

import junit.framework.Assert;

import org.junit.Test;

public class test1 {

	@Test
	public void testCrearConexion() {
		ConexionDB cbd=new ConexionDB();
		cbd.crearConexion();
		fail("Not yet implemented");
	}

	@Test
	public void testSelect() {
		//fail("Not yet implemented");
		
	}

	@Test
	public void testInsertarUsuario() {
		ConexionDB cbd=new ConexionDB();
		cbd.crearConexion();
		cbd.insertarUsuario("gerard", "Gerardo", "amo-a-fabiola", "tovar", "hermanos132");
		//fail("Not yet implemented");
	}
	@SuppressWarnings("deprecation")
	@Test
	public void testBuscarUsuario(){
		
		ConexionDB cbd=new ConexionDB();
		cbd.crearConexion();
		String parametros[];
		parametros =new String[2];
		parametros[0]="usuario";
		parametros[1]="password";
		ResultSet r=cbd.select("usuarios",parametros );
		Assert.assertEquals(1, cbd.buscarUsuario(r,"doom","123456"));
		
	}

}
