package model;
/**
 * Klasa służy do tworzenia obiektu Zamowienia
 * @author AdrianWilk
 */

public class Zamowienia {
    /**
     * Pola klasy Zamowienia
     */
    private int id;
    private int idPracownik;
    private int idKlient;
    private int idTowarZamowienia;
/**
 * Metoda bez parametrów
 * zwracajaca id
 * @return id
 */

    public int getId() {
        return id;
    }
/**
 * Metoda bez parametrów
 * zwracajaca idPracownik
 * @return idPracownik
 */

    public int getIdPracownik() {
        return idPracownik;
    }
/**
 * Metoda bez parametrów
 * zwracajaca idKlient
 * @return idKlient
 */
    public int getIdKlient() {
        return idKlient;
    }
/**
 * Metoda bez parametrów
 * zwracajaca idTowarZamowienia
 * @return idTowarZamowienia
 */
    public int getIdTowarZamowienia() {
        return idTowarZamowienia;
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
 * Pozwala modyfikować wartość pola idKlient
 * @param idKlient
 * 
 */
    public void setIdKlient(int idKlient) {
        this.idKlient = idKlient;
    }
/**
 * Metoda z parametrem
 * Pozwala modyfikować wartość pola idTowarZamowienia
 * @param idTowarZamowienia 
 * 
 */
    public void setIdTowarZamowienia(int idTowarZamowienia) {
        this.idTowarZamowienia = idTowarZamowienia;
    }
/**
 * konstruktor bez parametrów
 */

    public Zamowienia() {
    }
/**
 * Konstruktor z parametrami 
 * @param id  
 * @param idPracownik
 * @param idKlient
 * @param idTowarZamowienia
 */
    public Zamowienia(int id, int idPracownik, int idKlient, int idTowarZamowienia) {
        this.id = id;
        this.idPracownik = idPracownik;
        this.idKlient = idKlient;
        this.idTowarZamowienia = idTowarZamowienia;
    }
/**
 * Metoda bez parametrów
 * Zwraca tekstowy opis obiektu
 */

    @Override
    public String toString() {
        return "Zamowienia{" + "id=" + id + ", idPracownik=" + idPracownik + ", idKlient=" + idKlient + ", idTowarZamowienia=" + idTowarZamowienia + '}';
    }
    
    
}
