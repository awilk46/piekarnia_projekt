package model;

/**
 * Klasa służy do tworzenia obiektu Pracownik
 * @author AdrianWilk
 */
public class Pracownik {
     /**
     * Pola klasy Zamowienia
     */
private int id;
private String imie;
private String nazwisko;
private String pesel;    


/**
 * Bezparametrowa metoda 
 * zwracajaca id
 * @return id
 */
    public int getId() {
        return id;
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
 * zwracajaca imie
 * @return imie
 */
    public String getImie() {
        return imie;
    }
/**
 * Metoda z parametrem
 * Pozwala modyfikować wartość pola imie
 * @param imie 
 * 
 */
    public void setImie(String imie) {
        this.imie = imie;
    }
    
/**
 * Metoda bez parametru
 * zwracajaca nazwisko
 * @return nazwisko
 */
    public String getNazwisko() {
        return nazwisko;
    }
/**
 *  Metoda z parametrem
 * Pozwala modyfikować wartość pola nazwisko
 * @param nazwisko 
 * 
 */
    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }
/**
 * Metoda bez parametru
 * zwracajaca pesel
 * @return pesel
 */
    public String getPesel() {
        return pesel;
    }
    
/**
 *  Metoda z parametrem
 * Pozwala modyfikować wartość pola pesel
 * @param pesel 
 * 
 */
    public void setPesel(String pesel) {
        this.pesel = pesel;
    }
    
/**
 * konstruktor bez parametrów
 */
    public Pracownik(){
    }
/**
 * Konstruktor z parametrami 
 * @param id  
 * @param imie
 * @param nazwisko
 * @param pesel
 */
    public Pracownik(int id, String imie, String nazwisko, String pesel) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
    }
/**
 * Metoda bez parametru
 * Zwraca tekstowy opis obiektu
 */
    @Override
    public String toString() {
        return "Pracownik{" + "id=" + id + ", imie=" + imie + ", nazwisko=" + nazwisko + ", pesel=" + pesel + '}';
    }
 






}
