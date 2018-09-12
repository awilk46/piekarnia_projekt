package model;
/**
 * Klasa służy do tworzenia obiektu TowarZamowienia
 * @author AdrianWilk
 */

public class TowarZamowienia {
    /**
     * Pola klasy Towar
     */
    
    private int id;
    private int idTowar;
    private int idZamowienia;
    private int ilosc;
    private int cena;
/**
 * Metoda z parametrem
 * Pozwala modyfikować wartość pola cena
 * @param cena  
 * 
 */
    public void setCena(int cena) {
        this.cena = cena;
    }
/**
 * Metoda bez parametru
 * zwracajaca cena
 * @return cena
 */

    public int getCena() {
        return cena;
    }
/**
 * Metoda bez parametru
 * zwracajaca id
 * @return id
 */
    public int getId() {
        return id;
    }
/**
 * Metoda bez parametru
 * zwracajaca idTowar
 * @return idTowar
 */
    public int getIdTowar() {
        return idTowar;
    }
/**
 * Metoda bez parametru
 * zwracajaca idZamowienia
 * @return idZamowienia
 */
    public int getIdZamowienia() {
        return idZamowienia;
    }
/**
 * Metoda bez parametru
 * zwracajaca ilosc
 * @return ilosc
 */
    public int getIlosc() {
        return ilosc;
    }
/**
 * Metoda z parametrem
 * Pozwala modyfikować wartość pola id
 * @param id  
 * 
 */
    public void setId(int id) {
        this.id = id;
    }
/**
 * Metoda z parametrem
 * Pozwala modyfikować wartość pola idTowar
 * @param idTowar  
 * 
 */
    public void setIdTowar(int idTowar) {
        this.idTowar = idTowar;
    }
/**
 * Metoda z parametrema
 * Pozwala modyfikować wartość pola idZamowienia
 * @param idZamowienia  
 * 
 */
    public void setIdZamowienia(int idZamowienia) {
        this.idZamowienia = idZamowienia;
    }
/**
 * Metoda z parametrem
 * Pozwala modyfikować wartość pola ilosc
 * @param ilosc  
 * 
 */
    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }
/**
 * konstruktor bez parametrów
 */
    public TowarZamowienia() {
    }
/**
 * Konstruktor z parametrami 
 * @param id  
 * @param idTowar
 * @param idZamowienia
 * @param ilosc
 * @param cena
 */
    public TowarZamowienia(int id, int idTowar, int idZamowienia, int ilosc, int cena) {
        this.id = id;
        this.idTowar = idTowar;
        this.idZamowienia = idZamowienia;
        this.ilosc = ilosc;
        this.cena = cena;
    }

/**
 * Metoda bez parametrów
 * Zwraca tekstowy opis obiektu
 */

    @Override
    public String toString() {
        return "TowarZamowienia{" + "id=" + id + ", idTowar=" + idTowar + ", idZamowienia=" + idZamowienia + ", ilosc=" + ilosc + ", cena=" + cena + '}';
    }

   
    
    
    
    
    
}
