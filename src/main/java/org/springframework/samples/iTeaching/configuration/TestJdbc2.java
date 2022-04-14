package org.springframework.samples.iTeaching.configuration;


import java.sql.Connection;
import java.io.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.security.MessageDigest;
import javax.crypto.Cipher;
 
import org.jasypt.util.text.BasicTextEncryptor;
 
public class TestJdbc2 {
   public static void main(String[] args)
       throws ClassNotFoundException, SQLException,
              IOException
   {
 
       // Fethches the system property
       String path = System.getProperty("user.dir");
       System.out.println("Working Directory = " + path);
 
       // Creating a FileReader and specified the
       // name of the file to read from
       FileReader reader = new FileReader(
           path + "/src/config.properties");
 
       Properties p = new Properties();
 
       // Reads a property list from the input byte stream
       p.load(reader);
       details dt = new details();
       BasicTextEncryptor bte = new BasicTextEncryptor();
 
       // Getting key from details class object and
       // set the password for encryption and decryption
       bte.setPassword(dt.getKey());
 
       // Encrypt the message
       String encryptedid = bte.encrypt(dt.getUser());
 
       // Set the system property
       p.setProperty("username", encryptedid);
       BasicTextEncryptor bte1 = new BasicTextEncryptor();
 
       // Setting a password
       bte1.setPassword(dt.getKey2());
 
       // Encrypt the password
       String encryptedps = bte1.encrypt(dt.getPass());
       p.setProperty("password", encryptedps);
 
       // Writes the property list in the properties table
       // to the output character stream in a format
       // suitable for using load method
       p.store(
           new FileWriter(path + "/src/config.properties"),
           " Properties Data");
 
       // Load the driver class into the memory at the
       // runtime
       Class.forName("com.mysql.cj.jdbc.Driver");
 
       // Establishes the connection and decrypt the
       // encryptedid and encryptedps
       Connection conn = DriverManager.getConnection(
    	   "jdbc:mysql://localhost/iteaching",
           //"jdbc:mysql://localhost/iteaching?user=iteaching&password=iteaching&useUnicode=true&characterEncoding=UTF-8",
           bte.decrypt(encryptedid),
           bte1.decrypt(encryptedps));
       System.out.println("Connection successful!!!");
       System.out.println("Done");
   }
}