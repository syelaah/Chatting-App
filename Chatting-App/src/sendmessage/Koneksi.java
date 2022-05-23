package sendmessage;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author sye
 */
public class Koneksi {
     private static java.sql.Connection connect;
     
     public static java.sql.Connection getConnection(){ //method ini berguna membuat koneksi ke MYSQL
         if (connect == null) {
             try {
                 String url ="jdbc:mysql://localhost:3306/db_chatting";
                 String username ="root";
                 String password ="";
                 
                 DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                 connect = DriverManager.getConnection(url,username,password);
                 System.out.println("Koneksi berhasil");
             } catch (SQLException e) {
                 System.out.println("Gagal terhubung dengan database");
             }
         }
         return connect;
     }
}