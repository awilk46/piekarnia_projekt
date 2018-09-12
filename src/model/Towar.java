
package model;

/**
 * Klasa służy do tworzenia obiektu Towar
 * @author AdrianWilk
 */
public class Towar {
    /**
     * Pola klasy Towar
     */
    
    private int id;
    private int idPracownik;
    private String nazwaTowaru;

    
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
 * zwracajaca idPracownik
 * @return idPracownik
 */
    public int getIdPracownik() {
        return idPracownik;
    }
/**
 * Bezparametrowa metoda 
 * zwracajaca nazwaTowaru
 * @return nazwaTowaru
 */
    public String getNazwaTowaru() {
        return nazwaTowaru;
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
 * Pozwala modyfikować wartość pola idPracownik
 * @param idPracownik 
 * 
 */
    public void setIdPracownik(int idPracownik) {
        this.idPracownik = idPracownik;
    }
/**
 * Metoda z parametrem
 * Pozwala modyfikować wartość pola nazwaTowaru
 * @param nazwaTowaru
 * 
 */
    public void setNazwaTowaru(String nazwaTowaru) {
        this.nazwaTowaru = nazwaTowaru;
    }
/**
 * konstruktor bez parametrów
 */

    public Towar() {
    }
/**
 * Konstruktor z parametrami 
 * @param id  
 * @param idPracownik
 * @param nazwaTowaru 
 */
    public Towar(int id, int idPracownik, String nazwaTowaru) {
        this.id = id;
        this.idPracownik = idPracownik;
        this.nazwaTowaru = nazwaTowaru;
    }
/**
 * Metoda bez parametrów
 * Zwraca tekstowy opis obiektu
 */

    @Override
    public String toString() {
        return "Towar{" + "id=" + id + ", idPracownik=" + idPracownik + ", nazwaTowaru=" + nazwaTowaru + '}';
    }
    
    
}
