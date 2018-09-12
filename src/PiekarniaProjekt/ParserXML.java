package PiekarniaProjekt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import model.Klient;
import model.Pracownik;
import model.Towar;
import model.TowarZamowienia;
import model.Zamowienia;


/**
 * Klasa służy do parsowania pliku XML
 * @author AdrianWilk
 */

public class ParserXML {


/**
 * Metoda główna która wywołuje metodę prasowanieXML(xmlFile)
 * 
 * @throws FileNotFoundException wyrzucenie wyjątku- nie odnaleziono pliku
 * @throws XMLStreamException wyrzucenie wyjątku- błąd z połączeniem
 */
    public static void main(String[] args) throws FileNotFoundException, XMLStreamException
    {
//        jdbc("SELECT * FROM Pracownicy;");
//        System.out.println("\n");
//        jdbc("INSERT INTO Pracownicy VALUES (5,'Adrian','Wilk','163456789');");
//        System.out.println("\n");
//        jdbc("SELECT * FROM Klient;");
//        jdbc("INSERT INTO Klient VALUES (5,'Kaufland','Cmentarna 23','39-200','Debica','22778899');");
//        System.out.println("\n");
        
        
        /**
         * Tworzymy obiekt File o nazwie xmlFile który pobiera wartość zmiennej path
         * wywołujemy metodę prasowanieXML(xmlFile) która parsuje nam wybrany plik .xml
         */
       String path = "./dane/danePiekarnia.xml";
       File xmlFile = new File(path);
       parsowanieXML(xmlFile);


    }
    
//    public static void jdbc(String query)
//    {
//        Connection connection;
//        Statement statement = null;
//        
//        try {
//            Class.forName("org.sqlite.JDBC");
//        } catch (ClassNotFoundException ex) {
//            System.out.println("! Nie odnaleziono sterownika JDBC!");
//        }
//        
//        try {
//            connection=DriverManager.getConnection("jdbc:sqlite:C:/Users/adria/Documents/NetBEansProjects/piekarnia_projekt/piekarnia.db");
//            statement = connection.createStatement();
//            System.out.println("Po��czono z baz� danych!");
//        } 
//        catch (SQLException ex) 
//        {
//            System.out.println("Wystąpił błąd podczas łączenia z bazą danych!!!");
//        }
//        
//        
//        StringTokenizer st = new StringTokenizer(query, " ");
//        String firstWord = st.nextToken().toUpperCase();
//        if(firstWord.equals("SELECT"))
//        {
//            // wykonywanie zapytan SELECT do bazy
//            try {
//                ResultSet resultSet = statement.executeQuery(query);
//                ResultSetMetaData rsmd = resultSet.getMetaData();
//                int liczbaKol = rsmd.getColumnCount();
//                while (resultSet.next())
//                {
//                    String linia = new String();
//                    for (int i=1; i<=liczbaKol; i++)
//                    {
//                        if (i>1) linia = linia + " ";
//                            linia = linia + resultSet.getString(i);
//                    }
//                    System.out.println(linia);
//                }
//            } catch (SQLException ex) {
//                System.out.println("Wystąpił błąd podczas próby zadania zapytania do bazy!");
//            }
//        }
//        else if(firstWord.equals("INSERT") || firstWord.equals("DELETE") || firstWord.equals("UPDATE"))
//        {
//            // wykonywanie zapytan INSERT/DELETE/UPDATE do bazy
//            try {
//                int wynikUpdate = statement.executeUpdate(query);
//                if(wynikUpdate > 0)
//                    System.out.println("Liczba zmodyfikowanych rekordow: " + wynikUpdate);
//                else
//                    System.out.println("Nie zmodyfikowano zadnego rekordu");
//            } catch (SQLException ex) {
//                System.out.println("! Wystapil blad podczas proby aktualizacji bazy danych!!!");
//            }
//        }
//        
//        // zamkniecie polaczenia
//        try {
//            statement.close();
//            System.out.println("Pomy�lnie zamkni�to po��czenie z baz� danych!");
//        } catch (SQLException ex) {
//            System.out.println("! Wyst�pi� b��d podczas pr�by zamkni�cia po��czenia z baz� danych!!!");
//        }
////        
//    }
    
/**
 * metoda z parametrem parsowanieXML, która wyrzuca wyjątki oraz łączy się 
 * z bazą, służy do prasowania pliku XML
 * @param filePath odwołanie do ścieżki pliku
 * @throws FileNotFoundException wyrzucenie wyjątku- nie odnaleziono pliku
 * @throws XMLStreamException wyrzucenie wyjątku- błąd z połączeniem
 */
    public static void parsowanieXML(File filePath) throws FileNotFoundException, XMLStreamException
    {
        
        
        /**
         * połączenie z bazą danych, wyłapanie wyjątków
         * @exception ClassNotFoundException nie odnaleziono sterownika
         * @exception SQLException błąd podczas łączenia z bazą
         */
        Connection connection;
        Statement statement = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            System.out.println("! Nie odnaleziono sterownika JDBC!");
        }
        
        try {
            connection=DriverManager.getConnection("jdbc:sqlite:./dane/piekarnia.db");
            statement = connection.createStatement();
            System.out.println("Połączono z bazą danych!");
        } 
        catch (SQLException ex) 
        {
            System.out.println("Wystąpił błąd podczas łączenia z bazą danych!");
        }
        
        
        
        /**
         *Tworzymy array listy listaKlientow, listaPracownikow,
         * listaTowarow, listaTowZam, listaZamowien
         */
        
        ArrayList<model.Klient> listaKlientow=new ArrayList<model.Klient>();
        ArrayList<model.Pracownik> listaPracownikow=new ArrayList<model.Pracownik>();
        ArrayList<model.Towar> listaTowarow=new ArrayList<model.Towar>();
        ArrayList<model.TowarZamowienia> listaTowZam=new ArrayList<model.TowarZamowienia>();
        ArrayList<model.Zamowienia> listaZamowien=new ArrayList<model.Zamowienia>();

        /**
         * przypisanie pamięci
         */
        Klient klient;
        Pracownik pracownik;
        Towar towar;
        TowarZamowienia towZam;
        Zamowienia zamowienia;
        

        XMLInputFactory iFactory = XMLInputFactory.newInstance();
        InputStream xmlFile = new FileInputStream(filePath);
        XMLStreamReader parser = iFactory.createXMLStreamReader(xmlFile);

        /**
         * Utworzenie obiektów
         */

        klient= new Klient();
        pracownik= new Pracownik();
        towar= new Towar();
        towZam= new TowarZamowienia();
        zamowienia= new Zamowienia();
        
             /**
              * główna pętla metody, wniej następuje parsowanie pliku
              * 
              */    
        
        // dopoki masz nastepny element ...
        while (parser.hasNext()) 
        {
            // jesli jest to ... , wowczas ...
            switch (parser.next()) 
            {
// START ELEMENT //
                case XMLStreamConstants.START_ELEMENT:
                    
                    //pracownik
                    /**
                     * jeśli zawiera w nazwie słowo "Pracownik" to tworzy obiekt
                     * o nazwie pracownik
                     * 
                     */
                    if (parser.getLocalName().equals("Pracownik")) {
                        pracownik= new Pracownik();
                    }
                    if (parser.getLocalName().equals("idPracownik")) {
                        pracownik.setId(Integer.parseInt(parser.getElementText()));
                    }
                    if (parser.getLocalName().equals("imie")) {
                        pracownik.setImie(""+parser.getElementText()+"");
                    }
                    if (parser.getLocalName().equals("nazwisko")) {
                        pracownik.setNazwisko(""+parser.getElementText()+"");
                    }
                    if (parser.getLocalName().equals("pesel")) {
                        pracownik.setPesel(""+parser.getElementText()+"");
                    }
                             

                    
                    //klient
                    /**
                     * jeśli zawiera w nazwie słowo "Klient" to tworzy obiekt
                     * o nazwie klient
                     * 
                     */
                    
                    if (parser.getLocalName().equals("Klient")) {
                        klient = new Klient();
                    }
                    if (parser.getLocalName().equals("idKlient")) {
                        klient.setId(Integer.parseInt(parser.getElementText()));
                    }
                    if (parser.getLocalName().equals("nazwa")) {
                        klient.setNazwa(""+parser.getElementText()+"");
                    }
                    if (parser.getLocalName().equals("ulica")) {
                        klient.setUlica(""+parser.getElementText()+"");
                    }
                    if (parser.getLocalName().equals("kod")) {
                        klient.setKod(""+parser.getElementText()+"");
                    }
                    if (parser.getLocalName().equals("miasto")) {
                        klient.setMiasto(""+parser.getElementText()+"");
                    }
                    if (parser.getLocalName().equals("telefon")) {
                        klient.setTelefon(""+parser.getElementText()+"");
                    }
                            

                    //towar
                    /**
                     * jeśli zawiera w nazwie słowo "Towar" to tworzy obiekt
                     * o nazwie towar
                     * 
                     */
                    if (parser.getLocalName().equals("Towar")) {
                        towar = new Towar();
                    }
                    if (parser.getLocalName().equals("idTowar")) {
                        towar.setId(Integer.parseInt(parser.getElementText()));
                    }
                    if (parser.getLocalName().equals("idPracownikTowar")) {
                        towar.setIdPracownik(Integer.parseInt(parser.getElementText()));
                    }
                    if (parser.getLocalName().equals("nazwaTowaru")) {
                        towar.setNazwaTowaru(""+parser.getElementText()+"");
                    }
                 

                     //towar-zamowienia
                    
                    /**
                     * jeśli zawiera w nazwie słowo "TowarZamowienia" to tworzy obiekt
                     * o nazwie towZam
                     * 
                     */
                    
                    if (parser.getLocalName().equals("TowarZamowienia")) {
                        towZam = new TowarZamowienia();
                    }
                    if (parser.getLocalName().equals("idTowarZamowienia")) {
                        towZam.setId(Integer.parseInt(parser.getElementText()));
                    }
                    if (parser.getLocalName().equals("idTowarTowarZamowienia")) {
                        towZam.setIdTowar(Integer.parseInt(parser.getElementText()));
                    }
                    if (parser.getLocalName().equals("idZamowienia")) {
                        towZam.setIdZamowienia(Integer.parseInt(parser.getElementText()));
                    }
                    if (parser.getLocalName().equals("ilosc")) {
                        towZam.setIlosc(Integer.parseInt(parser.getElementText()));
                    }
                    if (parser.getLocalName().equals("cena")) {
                        towZam.setCena(Integer.parseInt(parser.getElementText()));
                    }
                       

                    //zamowienia
                    
                    /**
                     * jeśli zawiera w nazwie słowo "Zamowienia" to tworzy obiekt
                     * o nazwie zamowienia
                     * 
                     */
   
                    if (parser.getLocalName().equals("Zamowienia")) {
                        zamowienia= new Zamowienia();
                    }  
                     if (parser.getLocalName().equals("idZamowieniaZamowienia")) {
                        zamowienia.setId(Integer.parseInt(parser.getElementText()));
                    }
                    if (parser.getLocalName().equals("idPracownikZamowienia")) {
                        zamowienia.setIdPracownik(Integer.parseInt(parser.getElementText()));
                    }
                    if (parser.getLocalName().equals("idKlientZamowienia")) {
                        zamowienia.setIdKlient(Integer.parseInt(parser.getElementText()));
                    }
                    if (parser.getLocalName().equals("idTowarZamowieniaZamowienia")) {
                        zamowienia.setIdTowarZamowienia(Integer.parseInt(parser.getElementText()));
                    }
                                            

                    break;
// END ELEMENT //
                case XMLStreamConstants.END_ELEMENT:
                    
                    /**
                     * jeśli zawiera w nazwie słowo "Pracownik" dodaje do listaPracownikow
                     * obiekt pracownik
                     */
                         if (parser.getLocalName().equals("Pracownik")) {
                            listaPracownikow.add(pracownik);
                         }
                    /**
                     * jeśli zawiera w nazwie słowo "Klient" dodaje do listaKlientow
                     * obiekt klient
                     */
           
                        if (parser.getLocalName().equals("Klient")) {
                            listaKlientow.add(klient);
                        }
                    /**
                     * jeśli zawiera w nazwie słowo "Towar" dodaje do listaTowarow
                     * obiekt towar
                     */

                        if (parser.getLocalName().equals("Towar")) {
                            listaTowarow.add(towar);
                        }
                        
                    /**
                     * jeśli zawiera w nazwie słowo "TowarZamowienia" dodaje do listaTowZam
                     * obiekt towZam
                     */
                        if (parser.getLocalName().equals("TowarZamowienia")) {
                            listaTowZam.add(towZam);
                        }
                        
                   /**
                     * jeśli zawiera w nazwie słowo "Zamowienia" dodaje do listaZamowien
                     * obiekt zamowienia
                     */
                        if (parser.getLocalName().equals("Zamowienia")) {
                            listaZamowien.add(zamowienia);
                        }  
                        
                        
                   /**
                     * jeśli zawiera w nazwie słowo "Pracownik" wstawia w tabelę Pracownicy
                     * pracownika i jego pobrane wartości z obiektu pracownik
                     * wyłapuje wyjątek SQLException
                     * @exception SQLException - błąd w aktualizacji
                     */
                        if (parser.getLocalName().equals("Pracownik"))

                          {
                            String zapytanie = "INSERT INTO ";
                            zapytanie += "Pracownicy(idPracownika, imie, nazwisko, pesel) VALUES (";
                            zapytanie += pracownik.getId()+", '"+pracownik.getImie()+"', '"+pracownik.getNazwisko()+"', '"+ pracownik.getPesel()+"');";
                            System.out.println(zapytanie);
                            
                        try {
                                int wynikUpdate = statement.executeUpdate(zapytanie);
                                if(wynikUpdate > 0)
                                     Input.showMessage("Liczba zmodyfikowanych rekordow w Pracownikach: " + wynikUpdate);

                            } catch (SQLException ex) {
//                               JOptionPane.showMessageDialog(null,ex);
                            } 
                          }
                              
                        
                    /**
                     * jeśli zawiera w nazwie słowo "Klient" wstawia w tabelę Klient
                     * klienta i jego pobrane wartości z obiektu klient
                     * wyłapuje wyjątek SQLException
                     * @exception SQLException - błąd w aktualizacji
                     */
                              
                        if(parser.getLocalName().equals("Klient")){
                            String zapytanie = "INSERT INTO ";
                            zapytanie += "Klient (idKlienta, nazwaFirmy, ulica, kodPocztowy,miasto,telefon) VALUES (";
                            zapytanie +=  klient.getId()+", '"+klient.getNazwa()+"', '"+klient.getUlica()+"', '"+klient.getKod() +"', '"+klient.getMiasto()+"', '"+klient.getTelefon()+"');";
                            System.out.println(zapytanie);
                        try {
                                int wynikUpdate = statement.executeUpdate(zapytanie);
                                if(wynikUpdate > 0)
                                    Input.showMessage("Liczba zmodyfikowanych rekordow w Klientach: " + wynikUpdate);
 
                            } catch (SQLException ex) {
//                                JOptionPane.showMessageDialog(null,ex);
                            }
                        }
                        
                    /**
                     * jeśli zawiera w nazwie słowo "Towar" wstawia w tabelę Towar
                     * towar i jego pobrane wartości z obiektu towar
                     * wyłapuje wyjątek SQLException
                     * @exception SQLException - błąd w aktualizacji
                     */

                        if(parser.getLocalName().equals("Towar")){
                            String zapytanie = "INSERT INTO ";
                            zapytanie += "Towar (idTowaru, idPracownika, nazwaTowaru) VALUES (";
                            zapytanie += towar.getId()+", "+towar.getIdPracownik()+", '"+towar.getNazwaTowaru()+"');";
                            System.out.println(zapytanie);
                        try {
                                int wynikUpdate = statement.executeUpdate(zapytanie);
                                if(wynikUpdate > 0)
                                    Input.showMessage("Liczba zmodyfikowanych rekordow w Towarach: " + wynikUpdate);

                            } catch (SQLException ex) {
//                                 JOptionPane.showMessageDialog(null,ex);
                            }
                        }
                    /**
                     * jeśli zawiera w nazwie słowo "TowarZamowienia" wstawia w tabelę TowarZamowienia
                     * Tow-Zam i jego pobrane wartości z obiektu towZam
                     * wyłapuje wyjątek SQLException
                     * @exception SQLException - błąd w aktualizacji
                     */
                        
                        
                        if(parser.getLocalName().equals("TowarZamowienia")){
                            String zapytanie = "INSERT INTO ";
                            zapytanie += "TowarZamowienia (idTowarZamowienia, idTowaru, idZamowienia, ilosc, cena) VALUES (";
                            zapytanie += towZam.getId()+", "+towZam.getIdTowar()+", "+towZam.getIdZamowienia()+", "+towZam.getIlosc()+", "+towZam.getCena()+");";
                            System.out.println(zapytanie);
                            try {
                                int wynikUpdate = statement.executeUpdate(zapytanie);
                                if(wynikUpdate > 0)
                                     Input.showMessage("Liczba zmodyfikowanych rekordow w TowarZamowienia: " + wynikUpdate);

                            } catch (SQLException ex) {
//                                 JOptionPane.showMessageDialog(null,ex);
                            }
                        }
                       
                    /**
                     * jeśli zawiera w nazwie słowo "Zamowienia" wstawia w tabelę Zamowienia
                     * zamowienia i jego pobrane wartości z obiektu zamowienia
                     * wyłapuje wyjątek SQLException
                     * @exception SQLException - błąd w aktualizacji
                     */
                        if(parser.getLocalName().equals("Zamowienia")){
                            String zapytanie = "INSERT INTO ";
                            zapytanie += "Zamowienia (idZamowienia, idPracownika, idKlienta, idTowarZamowienia) VALUES (";
                            zapytanie += zamowienia.getId()+", "+zamowienia.getIdPracownik()+", "+zamowienia.getIdKlient()+", "+zamowienia.getIdTowarZamowienia()+");";
                            System.out.println(zapytanie);
                           try {
                                int wynikUpdate = statement.executeUpdate(zapytanie);
                                if(wynikUpdate > 0)
                                     Input.showMessage("Liczba zmodyfikowanych rekordow w Zamowieniach: " + wynikUpdate);
                          
                            } catch (SQLException ex) {
//                                JOptionPane.showMessageDialog(null,ex);
                            }
                        }
                        
                 
   
                        break;    
                      
                    case XMLStreamConstants.END_DOCUMENT:
              
                        

//                        for(model.Klient k: listaKlientow) System.out.println(k);
//                        for(model.Pracownik p: listaPracownikow) System.out.println(p);
//                        for(model.Towar t: listaTowarow) System.out.println(t);
//                        for(model.TowarZamowienia tz: listaTowZam) System.out.println(tz);
//                        for(Zamowienia z: listaZamowien) System.out.println(z);

                        
//                        
//                      Input.showMessage("Zmodyfikowano "+ listaKlientow.size() +" Klientów.");
//                      Input.showMessage("Zmodyfikowano "+ listaPracownikow.size() +" Pracowników.");
//                      Input.showMessage("Zmodyfikowano "+ listaTowZam.size() +" Towar-Zamówienia.");
//                      Input.showMessage("Zmodyfikowano "+ listaZamowien.size() +" Zamówień.");
                    break;   
                        
            }
            
        }
    }

  
    
}
