package controlador;

	import java.util.Properties;
	import javax.mail.Message;
	import javax.mail.MessagingException;
	import javax.mail.PasswordAuthentication;
	import javax.mail.Session;
	import javax.mail.Transport;
	import javax.mail.internet.InternetAddress;
	import javax.mail.internet.MimeMessage;

	public class Recuperar {
	   public void mandar(String email,String pass) {
	      String to = email;
	      String from = "dr.acula.belowme@gmail.com";
	      final String username = "didier";
	      final String password = "uzu-rendan";
	      String host = "smtp.gmail.com";
	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");

	      Session session = Session.getInstance(props,
	      new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	         }
	      });

	      try {
	         Message message = new MimeMessage(session);
	         message.setFrom(new InternetAddress(from));
	         message.setRecipients(Message.RecipientType.TO,
	         InternetAddress.parse(to));
	         message.setSubject("Testing Subject");
	         message.setText("Contraseña de recuperación"+pass);

	         Transport.send(message);
	         System.out.println("Mensaje enviado");
	      } catch (MessagingException e) {
	            throw new RuntimeException(e);
	      }
	   }
/*	   public static void main(String args[]){
		   Recuperar r= new Recuperar();
		   r.mandar("epw3d@hotmail.com","patito");
	   }*/
	}
