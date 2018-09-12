
package PiekarniaProjekt;

import java.util.List;
import model.Klient;
import model.Pracownik;
import model.Towar;
import model.TowarZamowienia;
import model.Zamowienia;
import piekarnia.Piekarnia;

/**
 * Klasa służy do tworzenia bazy danych
 * @author AdrianWilk
 */
public class TworzenieBazy {
    
/**
 * metoda bez parametrów która tworzy bazę danych
 * @author AdrianWilk
 */
    
    public static void baza(){
 /**
  * utworzenie obiektu piekarni
  */
      Piekarnia p = new Piekarnia();
      
      /**
       * Wywołanie metody insertPracownik, insertTowar, insertZamowienia
       * insertTowarZamowienia, insertKlient na obiekcie p
       * wstawienie wartości
       */
        p.insertPracownik("Karol", "Maciaszek", "123456789");
        p.insertPracownik("Piotr", "Wojtecki", "234567890");
        p.insertPracownik("Abdul", "Dabdul", "345678901");
 
        p.insertTowar(1, "bułka pszenna");
        p.insertTowar(2, "chleb pszenny");
        p.insertTowar(3, "chleb żytni");
        
        p.insertZamowienia(1,1,1);
        p.insertZamowienia(2,2,2);
        p.insertZamowienia(3,3,3);
 
        p.insertTowarZamowienia(1, 1, 1, 6);
        p.insertTowarZamowienia(2, 2, 2, 4);
        p.insertTowarZamowienia(3, 3, 3, 2);
        
        p.insertKlient("Biedronka", "Rzeszowska 125", "39-200", "Dębica", "123455678");
        p.insertKlient("Jubilatka", "Rzeszowska 2", "39-200", "Dębica", "5326721");
        p.insertKlient("Lewiatan", "Rzeszowska 1", "39-200", "Dębica", "9653211");
        
        
        /**
         * Utworzenie list pracownicy, towary, zamoowienia, towarZamowienia, 
         * klienci oraz wywołanie metody select, służy do wywołania rekordów
         */
        List<Pracownik> pracownicy = p.selectPracownicy();
        List<Towar> towary = p.selectTowar();
        List<Zamowienia> zamowienia = p.selectZamowienia();
        List<TowarZamowienia> towarZamowienia = p.selectTowarZamowienia();
        List<Klient> klienci = p.selectKlient();
 
        /**
         * Pzedstawienie wyników w konsoli za pomocą pętli for
         */
        System.out.println("Lista pracownikow: ");
        for(Pracownik pr: pracownicy)
            System.out.println(pr);
        
         System.out.println("Lista towarow: ");
        for(Towar t: towary)
            System.out.println(t);
        
         System.out.println("Lista zamowien: ");
        for(Zamowienia z: zamowienia)
            System.out.println(z);
        
        System.out.println("Lista Towarow&Zamowien: ");
        for(TowarZamowienia tz: towarZamowienia)
            System.out.println(tz);
        
        System.out.println("Lista klientow: ");
        for(Klient k: klienci)
            System.out.println(k);
 
        
        /**
         * Zamknięcie połączenia 
         */
        p.closeConnection();
    
    }
    
    
    
    
    
    
}
