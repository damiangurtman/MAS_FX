package pl.Dams.MAS.Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.List;

@DatabaseTable(tableName = "PRODUKT")
public class Produkt implements IModel{

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private String nazwa;

    @DatabaseField(canBeNull = false)
    private double wartoscNetto;

    private List<ZamowienieHurt> zamowieniaHurt;

    //==================================================================================================================

    private Produkt() {

    }

    public Produkt(String nazwa, double wartoscNetto) {
        setNazwa(nazwa);
        setWartoscNetto(wartoscNetto);
    }

    //==================================================================================================================
    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        if (nazwa != null)
            this.nazwa = nazwa;
        else
            throw new RuntimeException("Nazwa produktu jest wymagana!");
    }

    public double getWartoscNetto() {
        return wartoscNetto;
    }

    public void setWartoscNetto(double wartoscNetto) {
        if (wartoscNetto < 0)
            throw new RuntimeException("Wartosc produktu nie moze byc ujemna!");
        this.wartoscNetto = wartoscNetto;
    }

    public void addZamowienieHurt(ZamowienieHurt zamowienieHurt) {
        if (zamowieniaHurt == null)
            zamowieniaHurt = new ArrayList<>();

        if (zamowienieHurt == null)
            throw new RuntimeException("Zamowienie hurt jest puste!");

        if (!zamowieniaHurt.contains(zamowienieHurt)) {
            zamowieniaHurt.add(zamowienieHurt);
            zamowienieHurt.addProdukt(this);
        }
    }

    public void removeZamowienieHurt(ZamowienieHurt zamowienieHurt) {
        if (zamowieniaHurt.contains(zamowienieHurt))
            zamowieniaHurt.remove(zamowienieHurt);
        zamowienieHurt.removeProdukt(this);
        zamowienieHurt = null;
    }

    public List<ZamowienieHurt> getZamowieniaHurt() {
        return new ArrayList<>(zamowieniaHurt);
    }

    //==================================================================================================================

    public String toString() {
        return getNazwa() + " Cena: " + getWartoscNetto();
    }

}
