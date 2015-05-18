package controlador;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

	public class Gestionar{

		public boolean buscarUsuarioTxt (String nombreUsuario,String pass) throws FileNotFoundException{
			//boolean resultado=false; 			
			//System.out.println("entró a buscar");
			    try {
			    	BufferedReader br = new BufferedReader(new FileReader("datos.txt"),2);
			    	String line = br.readLine();
					String[] lineaSeparada;

					while (line != null){
						lineaSeparada = line.split("\\t");
						System.out.println(line);
						if (lineaSeparada[0].equals(nombreUsuario)&&lineaSeparada[1].equals(pass)) {
							br.close();
							return true;
			        		}
						line=br.readLine();
			        }
			        br.close();
			    } catch (IOException e) {
					e.printStackTrace();
					System.out.println("Error en la busqueda");
				}
			return false;
		}
		
		public Gestionar() {
			// TODO Auto-generated constructor stub
		}

		public static void main(String[] args) {
			// TODO Auto-generated method stub

		}
		public File crearDirectorio(){
			File f = new File("datos.txt");
			//System.out.println("Creacion de directorio correcta");
			return f;
		}
		
		public boolean registrarUsuarioTxt(File f,String user, String password, String nombre, String apellidos, String email) {	
			String data=user + "\t" + password + "\t" + nombre + "\t" + apellidos+ "\t" + email;
			   File aux=f; 
			   FileWriter wArchivo; 
			   try{ 
			       wArchivo = new FileWriter(aux,true); 
			       BufferedWriter bw = new BufferedWriter(wArchivo,2);
			       PrintWriter pw = new PrintWriter(bw); 
			       pw.println(data);
			       bw.close(); 
			       return true;
				}  
			    catch(Exception e) 
			    { 
			            e.printStackTrace(); 
			    } 
			   return false;
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
		/*private boolean tieneNombreYDominio(String Email) {
			String[] tokens = Email.split("@");
			return tokens.length == 2;
		}*/
		public boolean buscarUsuario(String nombreUsuario,String pass){
			return true;
		}
		public boolean registrarUsuario(File f,String user, String password, String nombre, String apellidos, String email){
			return true;
		}
		
}
