package piekarnia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import model.Klient;
import model.Pracownik;
import model.Towar;
import model.TowarZamowienia;
import model.Zamowienia;

/**
 * Klasa służy utworzenia tabel piekarni
 * @author AdrianWilk
 */
public class Piekarnia {
    
    /**
     * 
     * metoda bez parametru tworzy tabele Pracowników, Towarów, Klientów, Zamówień
     * Tow-Zam za pomocą wywołania zmiennych w których przetrzymywane są polecenia SQL
     * i wywołania metody execute
     * @exception SQLException wyłapany wyjątek jeśli wystąpił błąd w tworzeniu tabeli
     */
    public boolean createTables()  {
        String createPracownicy =
                "CREATE TABLE IF NOT EXISTS Pracownicy(idPracownika INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "imie varchar(255) NOT NULL, "
                + "nazwisko varchar(255) NOT NULL,"
                + "pesel varchar(255) UNIQUE NOT NULL)";
        String createTowar = "CREATE TABLE IF NOT EXISTS Towar("
                + "idTowaru INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "idPracownika int NOT NULL, "
                + "nazwaTowaru varchar(255) NOT NULL,"
                + "FOREIGN KEY (idPracownika) REFERENCES Pracownicy(idPracownika) ON UPDATE CASCADE)";
         String createKlient = "CREATE TABLE IF NOT EXISTS Klient("
                + "idKlienta INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nazwaFirmy varchar(255) NOT NULL,"
                + "ulica varchar(255) UNIQUE NOT NULL, "
                + "kodPocztowy varchar(255) NOT NULL,"
                + "miasto varchar(255) NOT NULL,"
                + "telefon varchar(255) NOT NULL)";
           String createZamowienia = "CREATE TABLE IF NOT EXISTS Zamowienia("
                + "idZamowienia INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "idPracownika int NOT NULL,"
                + " idKlienta int NOT NULL,"
                + " idTowarZamowienia int NOT NULL,"
                + " FOREIGN KEY (idPracownika) REFERENCES Pracownicy(idPracownika) ON UPDATE CASCADE,"
                + " FOREIGN KEY (idKlienta) REFERENCES Klient(idKlienta) ON UPDATE CASCADE,"
                + " FOREIGN KEY (idTowarZamowienia) REFERENCES TowarZamowienia(idTowarZamowienia) ON UPDATE CASCADE)";
        String createTowarZamowienia = "CREATE TABLE IF NOT EXISTS TowarZamowienia("
                + "idTowarZamowienia INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " idTowaru int NOT NULL,"
                + " idZamowienia int NOT NULL,"
                + " ilosc int NOT NULL,"
                + " cena int NOT NULL,"                
                + " FOREIGN KEY (idTowaru) REFERENCES Towar(idTowaru) ON UPDATE CASCADE,"
                + " FOREIGN KEY (idZamowienia) REFERENCES Zamowienia(idZamowienia) ON UPDATE CASCADE)";

        try {
            stat.execute(createPracownicy);
            stat.execute(createTowar);
            stat.execute(createTowarZamowienia);
            stat.execute(createZamowienia);
            stat.execute(createKlient);
            
        } catch (SQLException e) {
            System.err.println("Blad przy tworzeniu tabeli");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /** 
     * Obiekt klasy Statement - pozwala on na wykonywanie zapytań na podstawie zdefiniowanych Stringów
     * Tworzymy połączenie z bazą danych jako argument metody przekazując String
     * załadowanie sterownika systemu
     * 
     */
    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:piekarnia.db";
 
    private Connection conn;
    private Statement stat; 
    public Piekarnia() {
        try {
            Class.forName(Piekarnia.DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Brak sterownika JDBC");
            e.printStackTrace();
        }
 
        try {
            conn = DriverManager.getConnection(DB_URL);
            stat = conn.createStatement();
        } catch (SQLException e) {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        }
 
        createTables();
    }
        /**
         * metoda która wstawia rekordy do tablicy Pracownicy
         * @exception SQLException błąd przy wstawianiu rekordów
         * @return false jeśli wyłapie wyjątek
         * @return true jeśli doda rekord
         * @param imie pobiera imie
         * @param nazwisko pobiera nazwisko
         * @param pesel pobiera pesel
         */

        public boolean insertPracownik(String imie, String nazwisko, String pesel) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "insert into Pracownicy values (NULL, ?, ?, ?);");
            prepStmt.setString(1, imie);
            prepStmt.setString(2, nazwisko);
            prepStmt.setString(3, pesel);
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy wstawianiu pracownika!");
            e.printStackTrace();
            return false;
        }
        return true;
    }
        /**
         * metoda która wstawia rekordy do tablicy Towary
         * @exception SQLException błąd przy wstawianiu rekordów
         * @return false jeśli wyłapie wyjątek
         * @return true jeśli doda rekord
         * @param idPracownika pobiera id pracownika
         * @param nazwaTowaru pobiera nazwę towaru
         */

    
        public boolean insertTowar(int idPracownika, String nazwaTowaru) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "insert into Towar values (null, ?, ?);");
            prepStmt.setInt(1, idPracownika); 
            prepStmt.setString(2, nazwaTowaru);        
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy wstawianiu towaru ");
            return false;
        }
        return true;
    }
        
        /**
         * metoda która wstawia rekordy do tablicy TowarZamowienia
         * @exception SQLException błąd przy wstawianiu rekordów
         * @return false jeśli wyłapie wyjątek
         * @return true jeśli doda rekord
         * @param idTowaru pobiera id towaru
         * @param ilosc pobiera ilosc porduktów
         * @param cena pobiera cene
         */

        public boolean insertTowarZamowienia(int idTowaru, int idZamowienia, int ilosc, int cena) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "insert into TowarZamowienia values (NULL, ?, ?, ?, ?);");
            prepStmt.setInt(1, idTowaru); 
            prepStmt.setInt(2, idZamowienia);    
            prepStmt.setInt(3, ilosc); 
            prepStmt.setInt(4, cena); 
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad wstawianiu towar zamówienia!");
            return false;
        }
        return true;
    }
        
        /**
         * metoda która wstawia rekordy do tablicy Zamowienia
         * @exception SQLException błąd przy wstawianiu rekordów
         * @return false jeśli wyłapie wyjątek
         * @return true jeśli doda rekord
         * @param idPracownika pobiera id pracownika
         * @param idKlienta pobiera id klienta
         * @param idTowarZamowienia pobiera id tow-zam
         */

        public boolean insertZamowienia(int idPracownika, int idKlienta, int idTowarZamowienia) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "insert into Zamowienia values (NULL, ?, ?, ?);");
            prepStmt.setInt(1, idPracownika); 
            prepStmt.setInt(2, idKlienta);    
            prepStmt.setInt(3, idTowarZamowienia); 
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy wstawianiu zamowienia!");
            return false;
        }
        return true;
    }
        /**
         * metoda która wstawia rekordy do tablicy Klient
         * wyłapanie błędu 
         * @exception SQLException błąd przy wstawianiu rekordów
         * @return false jeśli wyłapie wyjątek
         * @return true jeśli doda rekord
         * @param nazwaFirmy pobiera nazwe firmy
         * @param ulica pobiera ulicę
         * @param kodPocztowy pobiera kod pocztowy
         * @param miasto pobiera miasto
         * @param telefon pobiera telefon
         */

         public boolean insertKlient(String nazwaFirmy, String ulica, String kodPocztowy, String miasto, String telefon) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "insert into Klient values (NULL, ?, ?, ?, ?, ?);");
            prepStmt.setString(1, nazwaFirmy); 
            prepStmt.setString(2, ulica);    
            prepStmt.setString(3, kodPocztowy); 
            prepStmt.setString(4, miasto); 
            prepStmt.setString(5, telefon); 
            
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy wstawianiu klienta!");
            return false;
        }
        return true;
    }
        /**
         * Wyświetlenie zawartości tablicy Pracownicy
         * @exception SQLException błąd odczytu
         * @return null jeśli wyłapie wyjątek
         * @return pracownicy zwraca tabelę pracowników
         */
        public List<Pracownik> selectPracownicy() {
        List<Pracownik> pracownicy = new LinkedList<Pracownik>();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM Pracownicy");
            int id;
            String imie, nazwisko, pesel;
            while(result.next()) {
                id = result.getInt("idPracownika");
                imie = result.getString("imie");
                nazwisko = result.getString("nazwisko");
                pesel = result.getString("pesel");
                pracownicy.add(new Pracownik(id, imie, nazwisko, pesel));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return pracownicy;
    }
        /**
         * Wyświetlenie zawartości tablicy Towar
         * @exception SQLException błąd odczytu
         * @return null jeśli wyłapie wyjątek
         * @return pracownicy zwraca tabelę towarów
         */
        
        
        public List<Towar> selectTowar() {
        List<Towar> towar = new LinkedList<Towar>();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM Towar");
            int id, idPracownik;
            String nazwaTowaru;
            while(result.next()) {
                id = result.getInt("idTowaru");
                idPracownik = result.getInt("idPracownika");
                nazwaTowaru = result.getString("nazwaTowaru");               
                towar.add(new Towar(id, idPracownik, nazwaTowaru));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return towar;
    }
        
        /**
         * Wyświetlenie zawartości tablicy TowarZamowienia
         * @exception SQLException błąd odczytu
         * @return null jeśli wyłapie wyjątek
         * @return pracownicy zwraca tabelę TowarZamowienia
         */
         public List<TowarZamowienia> selectTowarZamowienia() {
        List<TowarZamowienia> towarZamowienia = new LinkedList<TowarZamowienia>();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM TowarZamowienia");
            int id, idTowar, idZamowienia, ilosc, cena;
            while(result.next()) {
                id = result.getInt("idTowarZamowienia");
                idTowar = result.getInt("idTowaru");
                idZamowienia = result.getInt("idZamowienia");        
                ilosc = result.getInt("ilosc"); 
                cena = result.getInt("cena"); 
                towarZamowienia.add(new TowarZamowienia(id, idTowar, idZamowienia,ilosc, cena));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return towarZamowienia;
    }
        /**
         * Wyświetlenie zawartości tablicy Zamowienia
         * @exception SQLException błąd odczytu
         * @return null jeśli wyłapie wyjątek
         * @return pracownicy zwraca tabelę zamówień
         */
        public List<Zamowienia> selectZamowienia() {
        List<Zamowienia> zamowienia = new LinkedList<Zamowienia>();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM Zamowienia");
            int id, idPracownik, idKlient, idTowarZamowienia;
            while(result.next()) {
                id = result.getInt("idTowarZamowienia");
                idPracownik = result.getInt("idPracownika");
                idKlient = result.getInt("idKlienta");        
                idTowarZamowienia = result.getInt("idTowarZamowienia"); 
                zamowienia.add(new Zamowienia(id, idPracownik, idKlient,idTowarZamowienia));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return zamowienia;
    }
        
        /**
         * Wyświetlenie zawartości tablicy Klient
         * @exception SQLException błąd odczytu
         * @return null jeśli wyłapie wyjątek
         * @return pracownicy zwraca tabelę klientów
         */
                public List<Klient> selectKlient() {
        List<Klient> klient = new LinkedList<Klient>();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM Klient");
            int id;
            String nazwaFirmy, ulica, kod, miasto, telefon;
            while(result.next()) {
                id = result.getInt("idKlienta");
                nazwaFirmy = result.getString("nazwaFirmy");
                ulica = result.getString("ulica"); 
                kod = result.getString("kodPocztowy"); 
                miasto = result.getString("miasto"); 
                telefon = result.getString("telefon"); 
                klient.add(new Klient(id, nazwaFirmy, ulica, kod, miasto, telefon));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return klient;
    }
          /**
           * Metoda zamykająca połaczenie z bazą danych
           * @exception SQLException jeśli będzie problem z zamknieciem polaczenia
           */
    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Problem z zamknieciem polaczenia");
            e.printStackTrace();
        }
    }


        
        
        
}
