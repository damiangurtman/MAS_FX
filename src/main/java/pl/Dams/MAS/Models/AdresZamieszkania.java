package pl.Dams.MAS.Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "ADRES_ZAMIESZKANIA")
public class AdresZamieszkania implements IModel {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private String ulica, miasto;

    // -- ATRYBUT KLASOWY
    private static final String domyslneMiasto = "Warszawa";

    @DatabaseField(canBeNull = false)
    private int numerMieszkania;

    private Klient klient;

    //==================================================================================================================

    public AdresZamieszkania() {
    }

    public AdresZamieszkania(String ulica, String miasto, int numerMieszkania) {
        setUlica(ulica);
        setMiasto(miasto);
        setNumerMieszkania(numerMieszkania);
    }

    public AdresZamieszkania(String ulica, int numerMieszkania) {
        setUlica(ulica);
        setMiasto();
        setNumerMieszkania(numerMieszkania);
    }

    //==================================================================================================================

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        if (ulica != null)
            this.ulica = ulica;
        else
            throw new RuntimeException("Podanie ulicy jest wymagane!");
    }

    //-- PRZECIAZENIE METOD
    public void setMiasto() {
        this.miasto = domyslneMiasto;
    }


    //-- METODA KLASOWA
    public static String getDomyslneMiasto() {
        return domyslneMiasto;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        if (miasto != null)
            this.miasto = miasto;
        else
            throw new RuntimeException("Podanie miasta jest wymagane!");
    }

    public int getNumerMieszkania() {
        return numerMieszkania;
    }

    public void setNumerMieszkania(int numerMieszkania) {
        if (numerMieszkania > 0)
            this.numerMieszkania = numerMieszkania;
        else
            throw new RuntimeException("Numer mieszkania nie moze byc ujemny!");
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        if (this.klient != klient) {
            if (klient != null) {
                this.klient = klient;
            }
        }
    }

}
