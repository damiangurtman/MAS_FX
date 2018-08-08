package pl.Dams.MAS.Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.List;

@DatabaseTable(tableName = "KLIENT_PRACOWNIK")
public class KlientPracownik extends Klient implements IModel, IPracownik {

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Pracownik pracownik;

    @DatabaseField(canBeNull = false)
    private double dodatkowyRabat;

    public KlientPracownik() {

    }

    public KlientPracownik(String imie, String nazwisko, AdresZamieszkania adresZamieszkania, int pensja, double dodatkowyRabat) {
        super(imie, nazwisko, adresZamieszkania);
        pracownik = new Pracownik(imie, nazwisko, pensja);
        setDodatkowyRabat(dodatkowyRabat);
    }


    public void setDodatkowyRabat(double rabat) {
        if (rabat > 0.30)
            throw new RuntimeException("Dodatkowy rabat nie moze byc wiekszy nic 30%!");
        else if (rabat < 0)
            throw new RuntimeException("Dodatkowy rabat nie moze byc ujemny!");
        this.dodatkowyRabat = rabat;
    }

    @Override
    public double getPensja() {
       return pracownik.getPensja();
    }

    @Override
    public String getAdresMailowy() {
        return pracownik.getAdresMailowy();
    }

    @Override
    public List<String> getSzkolenie() {
        return new ArrayList<>(pracownik.getSzkolenie());
    }


    public String toString() {
        return getImie() + " " + getNazwisko()
                + " jest klientem, ktory jednoczesnie jest pracownikiem i posiada dodatkowy rabat w wysokosci: "
                + dodatkowyRabat;
    }

}
