
package PiekarniaProjekt;

import java.sql.*;
import java.awt.*;
import javax.swing.*;

/**
 * Klasa służąca do połączenia z bazą
 */
public class PolaczenieBaza {
    

    
    Connection conn= null;
    
    
    /**
     * metoda nawiązująca połaczenie z bazą
     * pobiera nazwę sterownika oraz ścieżkę bazy danych
     * jeśli połączymy się z bazą wyskoczy informacja w przeciwnym wypadku wyłapie błąd
     * @exception Exception błąd w nawiązaniu połączenia
     * jeśli się połączymy to metoda zwróci
     * @return conn połączenie w przeciwnym wypadku
     * @return null oraz wyskoczy komunikat z błędem
     */
    
    public static Connection ConnectDB(){
        try{
            Class.forName("org.sqlite.JDBC");
             Connection conn = DriverManager.getConnection("jdbc:sqlite:./dane/piekarnia.db");
              Input.showMessage("Połączono z bazą danych!");
            return conn;
            
        }catch (Exception e){
            
            Input.showMessage("Wystąpił błąd w próbie nawiązania połączenia z bazą daych!");
            return null;
        }
    }

}
