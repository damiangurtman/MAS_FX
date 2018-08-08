package pl.Dams.MAS.Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "OSOBA")
public abstract class Osoba implements IModel{

    @DatabaseField(canBeNull = false)
    protected String imie, nazwisko;

    // -- Wymagany do drop table
    public Osoba() {

    }

    public Osoba(String imie, String nazwisko) {
        setImie(imie);
        setNazwisko(nazwisko);
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        if (imie != null)
            this.imie = imie;
        else
            throw new IllegalArgumentException("Imie nie moze byc puste!");
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        if (nazwisko != null)
            this.nazwisko = nazwisko;
        else
            throw new IllegalArgumentException("Nazwisko nie moze byc puste!");
    }

}
