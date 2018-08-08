package pl.Dams.MAS.Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "ZAMOWIENIE_DETAL")
public class ZamowienieDetal extends Zamowienie implements IModel{

    @DatabaseField(canBeNull = false)
    private double kosztPrzesylki;

    @DatabaseField(canBeNull = false)
    private double cena;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private KlientNormalny klient;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Produkt produkt;

    //==================================================================================================================

    public ZamowienieDetal() {

    }

    // -- zamowienie detaliczne, krajowe

    public ZamowienieDetal(Produkt produkt, int maxWaga, double kosztPrzesylki, KlientNormalny klient) {
        super(maxWaga);
        setProdukt(produkt);
        setKosztPrzesylki(kosztPrzesylki);
        setKlient(klient);
        cena = produkt.getWartoscNetto();
    }

    // -- zamowienie detaliczne, zagraniczne

    public ZamowienieDetal(Produkt produkt, String krajPochodzenia, double kosztPrzesylki, KlientNormalny klient) {
        super(krajPochodzenia);
        setProdukt(produkt);
        setKosztPrzesylki(kosztPrzesylki);
        setKlient(klient);
        cena = produkt.getWartoscNetto();
    }

    //==================================================================================================================

    // POLIMORFIZM METOD
    @Override
    public double obliczCeneKoncowa() {
        return produkt.getWartoscNetto() * 1.23;
    }

    //==================================================================================================================


    public Klient getKlient() {
        return klient;
    }

    public void setKlient(KlientNormalny klient) {
        if (klient == null)
            throw new RuntimeException("Klient jest wymagany!");
        this.klient = klient;
    }

    public double getKosztPrzesylki() {
        return kosztPrzesylki;
    }

    public void setKosztPrzesylki(double kosztPrzesylki) {
        if (kosztPrzesylki > 0)
            this.kosztPrzesylki = kosztPrzesylki;
        else
            throw new RuntimeException("Koszt przesylki nie moze byc ujemny!");
    }

    public Produkt getProdukt() {
        return produkt;
    }

    public double getCena() {
        return produkt.getWartoscNetto();
    }

    public void setProdukt(Produkt produkt) {
        if (produkt == null)
            throw new RuntimeException("Podanie produktu jest wymagane!");
        this.produkt = produkt;
    }

    public String toString() {
        String str = "";

        if (this.getMaxWaga() == 0) {
            str += "\n Zamowienie detaliczne i zagraniczne, pochodzi z "
                    + this.getKrajPochodzenia() + ". \n" + " Zamowiony produkt: " + this.getProdukt().getNazwa() + ". \n" + " Stan zamowienia: "
                    + this.getStan() + ". \n Klient to: " + this.getKlient().getImie()
                    + " " + this.getKlient().getNazwisko() + "\n Data utworzenia: " + this.getDate()
                    + ". \n Cena netto: " + this.getProdukt().getWartoscNetto() + ".\n Koszt przesylki: " + this.getKosztPrzesylki();
        } else {
            str += "\n Zamowienie detaliczne i krajowe, jego maksymalna waga wynosi "
                    + this.getMaxWaga() + "kg. \n" + " " + "Zamowiony produkt: " + this.getProdukt().getNazwa() + ". \n" + " Stan zamowienia: "
                    + this.getStan() + ". \n Klient to: " + this.getKlient().getImie()
                    + " " + this.getKlient().getNazwisko() + "\n Data utworzenia: " + this.getDate()
                    + ". \n Cena netto: " + this.getProdukt().getWartoscNetto() + ".\n Koszt przesylki: " + this.getKosztPrzesylki();
        }
        return str;
    }

}
