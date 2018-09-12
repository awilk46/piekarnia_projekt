
package model;
/**
 * Klasa służy do tworzenia obiektu Klient
 * @author Adrian Wilk
 */


public class Klient {
    
    /**
     * Pola klasy klient
     */
    

    private int id;
    private String nazwaFirmy;
    private String ulica;
    private String kod;
    private String miasto;
    private String telefon;

/**
 * Bezparametrowa metoda 
 * zwracajaca id
 * @return id
 */

    public int getId() {
        return id;
    }
/**
 * Bezparametrowa metoda
 * zwracajaca nazwę firmy
 * @return nazwaFirmy
 */

    public String getNazwa() {
        return nazwaFirmy;
    }

/**
 * Bezparametrowa metoda
 * zwracajaca ulice
 * @return ulica
 */
    public String getUlica() {
        return ulica;
    }
    
/**
 * Bezparametrowa metoda
 * zwracajaca kod pocztowy
 * @return kod
 */

    public String getKod() {
        return kod;
    }

/**
 * Bezparametrowa metoda
 * zwracajaca miasto
 * @return id miasto
 */
    public String getMiasto() {
        return miasto;
    }

/**
 * Metoda z parametrem
 * zwracajaca numer telefonu
 * @return telefon
 */
    public String getTelefon() {
        return telefon;
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
 * Pozwala modyfikować wartość pola nazwaFirmy
 * @param setNazwa  
 * 
 */
    public void setNazwa(String nazwaFirmy) {
        this.nazwaFirmy = nazwaFirmy;
    }
/**
 * Metoda z parametrem
 * Pozwala modyfikować wartość pola ulica
 * @param ulica  
 * 
 */
    public void setUlica(String ulica) {
        this.ulica = ulica;
    }
/**
 * Metoda z parametrem
 * Pozwala modyfikować wartość pola kod
 * @param kod  
 * 
 */
    public void setKod(String kod) {
        this.kod = kod;
    }
/**
 * Metoda z parametrem
 * Pozwala modyfikować wartość pola miasto
 * @param miasto  
 * 
 */
    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }
/**
 * Metoda z parametrem
 * Pozwala modyfikować wartość pola telefon
 * @param telefon  
 * 
 */
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
/**
 * konstruktor bezparametrowy
 */
    public Klient() {}
/**
 * Konstruktor z parametrami 
 * @param id  
 * @param nazwaFirmy
 * @param ulica
 * @param kod
 * @param miasto
 * @param telefon
 * 
 */
    public Klient(int id, String nazwaFirmy, String ulica, String kod, String miasto, String telefon) {
        this.id = id;
        this.nazwaFirmy = nazwaFirmy;
        this.ulica = ulica;
        this.kod = kod;
        this.miasto = miasto;
        this.telefon = telefon;
    }
/**
 * Metoda bez parametrów
 * Zwraca tekstowy opis obiektu
 */
    @Override
    public String toString() {
        return "Klient{" + "id=" + id + ", nazwa=" + nazwaFirmy + ", ulica=" + ulica + ", kod=" + kod + ", miasto=" + miasto + ", telefon=" + telefon + '}';
    }
    
    
    
}
