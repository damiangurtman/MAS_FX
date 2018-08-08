package pl.Dams.MAS.Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "KLIENT")
public abstract class Klient extends Osoba implements IModel{

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private AdresZamieszkania adresZamieszkania;

    //==================================================================================================================

    public Klient() {

    }

    public Klient(String imie, String nazwisko, AdresZamieszkania adresZamieszkania) {
        super(imie, nazwisko);
        setAdresZamieszkania(adresZamieszkania);
    }

    //==================================================================================================================


    public AdresZamieszkania getAdresZamieszkania() {
        return adresZamieszkania;
    }

    public void setAdresZamieszkania(AdresZamieszkania adresZamieszkania) {
        if (this.adresZamieszkania != adresZamieszkania) {
            if (adresZamieszkania != null) {
                this.adresZamieszkania = adresZamieszkania;
                this.adresZamieszkania.setKlient(this);
            } else {
                throw new RuntimeException("Adres zamieszkania nie moze byc pusty!");
            }
        } else {
            throw new RuntimeException("Nowy adres zamieszkania musi sie roznic od obecnego!");
        }
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return getImie() +" " + getNazwisko();
    }


}
