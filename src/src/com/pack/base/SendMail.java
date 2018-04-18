package src.com.pack.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.IOUtils;
import org.testng.ISuite;

import com.util.ExtentTestNGIReporterListener;

public class SendMail {

  

public static void execute( String reportFileName1, String reportFileName, String Env) throws Exception {
	final String username = "espireautomation@gmail.com";
	final String password = "Espire@123";
 
  Properties props = new Properties();
  props.put("mail.smtp.starttls.enable", "true");
  props.put("mail.smtp.auth", "true");
  props.put("mail.smtp.host", "smtp.gmail.com");
  props.put("mail.smtp.port", "587");
   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
     protected PasswordAuthentication getPasswordAuthentication() {
      return new PasswordAuthentication(username, password);   }  });
   try {
	   System.out.println("Sending...");
	   MimeMessage message = new MimeMessage(session);
	   message.setFrom(new InternetAddress("espireautomation@gmail.com"));
	 message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("Walter.Hoff@brandsdalgroup.com, magne@brandsdalgroup.com"));
	 message.setRecipients(Message.RecipientType.CC, InternetAddress.parse("rajeev.singh@espire.com, venkata.rao@espire.com"));
	//message.setRecipients(Message.RecipientType.CC, InternetAddress.parse("rajeev.singh@espire.com"));
	   
	   DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy :: HH:mm:ss");
	   Date date = new Date();
	   //return dateFormat.format(date);
	   String EmailSubject= Env+ " env automation test results" ;
	   System.out.println("Email Subject -"+EmailSubject);
	   message.setSubject(EmailSubject);
	   Thread.sleep(5000);
	   String basePath = new File("").getAbsolutePath();
	   System.out.println("base path- "+basePath);
	   String path = new File("test-output\\").getAbsolutePath();
	   System.out.println("absolute path"+path);
	   String file = path;
	   String fileName = reportFileName1;
	   String finalpath =file + fileName;
	   StringWriter writer = new StringWriter();
	   Multipart multipart = new MimeMultipart();
	   //Code for HTML BODY Summary Report
	   BodyPart htmlBodyPart = new MimeBodyPart();
	   IOUtils.copy(new FileInputStream(new File(finalpath)), writer);
	   htmlBodyPart.setContent(writer.toString(), "text/html");
	   multipart.addBodyPart(htmlBodyPart);
	   
	   // Part two is attachment of Detailed Report
	
	
		String filename = reportFileName;
      /* String basePath1 = new File("").getAbsolutePath();
	   System.out.println(basePath1);
	   String path1 = new File("test-output\\").getAbsolutePath();
	   System.out.println(path1);
	   String file1 = path1;*/
	   addAttachment(multipart, filename);
	   
	   System.out.println("Extent-"+ExtentTestNGIReporterListener.extentfilename);
	   Thread.sleep(1000);
	   //Rajeev - Uncomment below line to include extent report in email
	  // addAttachment(multipart, "\\"+ ExtentTestNGIReporterListener.extentfilename);
	   
	  /* DataSource source = new FileDataSource(file1+filename);
       messageBodyPart.setDataHandler(new DataHandler(source));
       messageBodyPart.setFileName(filename);
       multipart.addBodyPart(messageBodyPart);*/
      
       message.setContent(multipart); 
       Transport.send(message);
	   System.out.println("Done");
   		} catch (MessagingException e) {
   			throw new RuntimeException(e);
   		}
 }
	private static void addAttachment(Multipart multipart, String filename) throws Exception
	{
		 String basePath1 = new File("").getAbsolutePath();
		   System.out.println(basePath1);
		   String path1 = new File("test-output\\").getAbsolutePath();
		   System.out.println(path1);
		   String file1 = path1;
		//   System.out.println(filename);
	    DataSource source = new FileDataSource(file1+filename);
	    BodyPart messageBodyPart = new MimeBodyPart();        
	    messageBodyPart.setDataHandler(new DataHandler(source));
	    messageBodyPart.setFileName(filename);
	    multipart.addBodyPart(messageBodyPart);
	}
  
}
