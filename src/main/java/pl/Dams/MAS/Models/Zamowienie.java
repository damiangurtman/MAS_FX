package pl.Dams.MAS.Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

enum StanZamowienia {zlozone, przetwarzane, rozpoczete, dostarczone, zakonczone, anulowane};

@DatabaseTable(tableName = "ZAMOWIENIE")
public abstract class Zamowienie implements IModel{

    //wieloaspektowe, wczesniej bylo jeszcze dziedziczenie ze wzgledu na to czy zamowienie jest zagraniczne czy nie
    //zagraniczne posiada atrybut kraj pochodzenia (dyskryminator) a krajowe maja maksymalna wage zamowienia

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private Date date;

    @DatabaseField(canBeNull = true)
    private String krajPochodzenia;

    @DatabaseField(canBeNull = true)
    private int maxWaga;

    @DatabaseField(canBeNull = false)
    private StanZamowienia stan;

    //==================================================================================================================

    public Zamowienie() {

    }

    // -- zamowienie krajowe

    public Zamowienie(int maxWaga) {
        setMaxWaga(maxWaga);
        setStan(StanZamowienia.zlozone);
        date = new MyDate();
    }

    // -- zamowienie zagraniczone

    public Zamowienie(String krajPochodzenia) {
        setKrajPochodzenia(krajPochodzenia);
        setStan(StanZamowienia.zlozone);
        date = new MyDate();
    }


    //==================================================================================================================

    public abstract double obliczCeneKoncowa();

    //==================================================================================================================

    public String getKrajPochodzenia() {
        return krajPochodzenia;
    }

    public void setKrajPochodzenia(String krajPochodzenia) {
        if (krajPochodzenia != null)
            this.krajPochodzenia = krajPochodzenia;
        else
            throw new RuntimeException("Kraj pochodzenia jest wymagany!");
    }

    public int getMaxWaga() {
        return maxWaga;
    }

    public void setMaxWaga(int maxWaga) {
        if (maxWaga > 0)
            this.maxWaga = maxWaga;
        else
            throw new RuntimeException("Waga zamowienia nie moze byc ujemna!");
    }

    public StanZamowienia getStan() {
        return stan;
    }

    public void setStan(StanZamowienia stan) {
        if (stan == null)
            throw new RuntimeException("Stan zamowienia nie moze byc pusty!");
        this.stan = stan;
    }

    public Date getDate() {
        return date;
    }

    public void anulujZamowienie() {
        setStan(StanZamowienia.anulowane);
    }

    public void przetworzZamowienie() {
        setStan(StanZamowienia.przetwarzane);
    }

    public void rozpocznijZamowienie() {
        setStan(StanZamowienia.rozpoczete);
    }

    public void dostarczZamowienie() {
        setStan(StanZamowienia.dostarczone);
    }

    public void zakonczZamowienie() {
        setStan(StanZamowienia.zakonczone);
    }

    //==================================================================================================================

}
