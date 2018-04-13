package src.com.pack.database;

import  java.sql.Connection;		
import  java.sql.Statement;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



import  java.sql.ResultSet;		
import  java.sql.DriverManager;		
import  java.sql.SQLException;



public class database {
	protected WebDriver driver;
	public String dbUrl;
	
	
	public database(WebDriver driver) {
		this.driver = driver;
		
	}
 
public String customeremail(String env, String URL,String site,String uemail) throws ClassNotFoundException, SQLException {

                
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                System.out.println("Envioranme Value-"+env);
                if(env.contains("releasetest")){
                	 dbUrl = "jdbc:sqlserver://krs01db13\\releasetest";
                	 System.out.println(dbUrl);
    			}else{
    				 dbUrl = "jdbc:sqlserver://krs01db13\\test";
    				 System.out.println(dbUrl);
    			} 
                
                //String dbUrl = "jdbc:sqlserver://KRS01DB09"; //test
                //String dbUrl = "jdbc:sqlserver://KRS01DB10"; //relasetest
               // String dbUrl = database;
              
                String sms1=null;
                String ToEmail= null;
                String ToName= null;
                String Body= null;
                String username = "tespire";   
                String password = "Espire123";    
                
                //update nhcustomersales..customer
               // set emailaddress ='raj3422eev@netthandelen.no' 
               // where emailaddress ='rajeev@netthandelen.no' 
                //String query = "SELECT TOP 1  * FROM [NHCustomersales].[dbo].[customer] order by customerid desc;";  
                
                String query = "select top 1 ToEmail, ToName, Body from [NHCustomersales].[dbo].[Emailmessage] where fromname like '%"+site+ "%' and toemail like '%" +uemail+"%' order by Emailmessageid desc;";
                //String query ="select  ToEmail, ToName, Body from [NHCustomersales].[dbo].[Emailmessage] where Emailmessageid=39650456;";
                Connection con = DriverManager.getConnection(dbUrl,username,password);  
                System.out.println(con);
                Statement stmt = con.createStatement();
                System.out.println(stmt);
                 ResultSet rs = stmt.executeQuery(query);
                 System.out.println(rs);
                 //sms1 = rs.getString("EmailAddress");
              
                  while (rs.next()) {
                	  ToEmail = rs.getString(1);
                	  ToName =rs.getString(2);
                	  Body = rs.getString(3);
                         //sms1 =  sms.substring(1, 10);
                        System.out.println(ToEmail);
                        System.out.println(ToName);
                        //System.out.println(Body);
                        Pattern p = Pattern.compile("(/Activate/.*/.*[A-Z0-9]\")");
                       // String tt = "/Activate/.*/.*[1-9]";
                        java.util.regex.Matcher m = p.matcher(Body);
                        if (m.find())
                        {
                          // we're only looking for one group, so get it
                          String theGroup = m.group(1);
                          System.out.println("in loop");
                          // print the group out for verification
                          //System.out.format("'%s'\n", theGroup);
                          System.out.println(theGroup);
                          String theGroups = theGroup.replaceAll("\"", "");
                          System.out.println(theGroups);
                          String activation =URL+theGroups;
                          System.out.println(activation);
                          driver.navigate().to(activation);
                        
                        		 
                        }
                        //String activation =Body.replaceAll(tt," "); 
                        //System.out.println(activation);
                        
                         } 

                       rs.close();
                       stmt.close();
                       
                       return sms1;
                                
     
      }

public String smsmessage(String env, String URL) throws ClassNotFoundException, SQLException {

    
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    
    if(env.contains("releasetest")){
    	 dbUrl = "jdbc:sqlserver://krs01db13\\releasetest";
    	 System.out.println(dbUrl);
	}else{
		 dbUrl = "jdbc:sqlserver://krs01db13\\test";
		 System.out.println(dbUrl);
	} 
    
    //String dbUrl = "jdbc:sqlserver://KRS01DB09"; //test
    //String dbUrl = "jdbc:sqlserver://KRS01DB10"; //relasetest
   // String dbUrl = database;
  
    String sms1=null;
    String Recipient= null;
    String Body= null;
    String username = "tespire";   
    String password = "Espire123";    
    
    //update nhcustomersales..customer
   // set emailaddress ='raj3422eev@netthandelen.no' 
   // where emailaddress ='rajeev@netthandelen.no' 
    //String query = "SELECT TOP 1  * FROM [NHCustomersales].[dbo].[customer] order by customerid desc;";  
    
    String query = "select top 1 Recipient, Body from [NHCustomersales].[dbo].[SmsMessage] order by SmsMessageid desc;";
    //String query ="select  ToEmail, ToName, Body from [NHCustomersales].[dbo].[Emailmessage] where Emailmessageid=39650456;";
    Connection con = DriverManager.getConnection(dbUrl,username,password);    
    Statement stmt = con.createStatement();
     ResultSet rs = stmt.executeQuery(query);
     //sms1 = rs.getString("EmailAddress");
  
      while (rs.next()) {
    	  
    	  Recipient =rs.getString(1);
    	  Body = rs.getString(2);
             //sms1 =  sms.substring(1, 10);
            System.out.println(Recipient);
            System.out.println(Body);
            //System.out.println(Body);
            Pattern p = Pattern.compile("([0-9A-Za-z]{5})");
           // String tt = "/Activate/.*/.*[1-9]";
            java.util.regex.Matcher m = p.matcher(Body);
            if (m.find())
            {
              // we're only looking for one group, so get it
              String smscode = m.group(1);
              //System.out.println("in loop");
              // print the group out for verification
              //System.out.format("'%s'\n", theGroup);
              System.out.println(smscode);
             //String theGroups = theGroup.replaceAll("\"", "");
             //System.out.println(theGroups);
             // String activation =URL+theGroups;
             // System.out.println(activation);
              //driver.navigate().to(activation);
              
            driver.findElement(By.id("txt-code")).sendKeys(smscode);
            
            driver.findElement(By.id("btn-validate")).click();
            
            		 
            }
            //String activation =Body.replaceAll(tt," "); 
            //System.out.println(activation);
            
             } 

           rs.close();
           stmt.close();
           
           return sms1;
                    

}


}

