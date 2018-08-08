package pl.Dams.MAS.Models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.List;

@DatabaseTable(tableName = "PRACOWNIK")
public class Pracownik extends Osoba implements IModel, IPracownik{

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private double pensja;

    @ForeignCollectionField
    private ForeignCollection<Specjalizacja> specjalizacje;

    //Atrybut powtarzalny
    private List<String> listSzkolenia = new ArrayList<>();

    // -- ATRYBUT OPCJONALNY
    @DatabaseField(canBeNull = true)
    private String adresMailowy;

    //==================================================================================================================

    // -- Wymagane do drop table
    public Pracownik() {
    }

    public Pracownik(String imie, String nazwisko, double pensja) {
        super(imie, nazwisko);
        setPensja(pensja);
    }

    public Pracownik(String imie, String nazwisko, String adresMailowy, double pensja) {
        super(imie, nazwisko);
        setAdresMailowy(adresMailowy);
        setPensja(pensja);
    }

    //==================================================================================================================


    //==================================================================================================================

    public double getPensja() {
        return pensja;
    }

    // -- OGRANICZENIE ATRYBUTU STATYCZNE I DYMANICZNE
    public void setPensja(double pensja) {

        if (pensja < 2000) {
            throw new RuntimeException("Pensja nie moze wynosic mniej niz 2000zl!");
        }

        if (pensja < this.pensja) {
            throw new RuntimeException("Pensja nie moze zmalec!");
        }

        this.pensja = pensja;
    }

    public String getAdresMailowy() {
        return adresMailowy;
    }

    public void setAdresMailowy(String adresMailowy) {
        this.adresMailowy = adresMailowy;
    }

    public void addSzkolenie(String szkolenie) {
        if (szkolenie == null)
            throw new RuntimeException("Szkolenie nie moze byc puste!");
        // -- OGRANICZENIE UNIQUE i ORDERED
        if (listSzkolenia != null)
            for (int i = 0; i < listSzkolenia.size(); i++)
                if (listSzkolenia.get(i).equals(szkolenie))
                    throw new RuntimeException("Takie szkolenie juz istnieje!");

        listSzkolenia.add(szkolenie);

    }

    public List<String> getSzkolenie() {
        return new ArrayList<>(listSzkolenia);
    }

    //==================================================================================================================

    // przesloniecie metod
    public String toString() {
        String info = "";

        if (adresMailowy == null) {
            info +=  imie + " " + nazwisko + " nie ma maila.";
        } else {
            info += imie + " " + nazwisko + " ma maila " + adresMailowy;
        }

        return info;
    }


}
