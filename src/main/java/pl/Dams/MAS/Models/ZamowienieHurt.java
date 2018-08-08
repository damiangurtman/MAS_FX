package pl.Dams.MAS.Models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.List;

@DatabaseTable(tableName = "ZAMOWIENIE_HURT")
public class ZamowienieHurt extends Zamowienie implements IModel{

    @DatabaseField(canBeNull = false)
    private int maxLiczbaPrzedmiotow;

    @ForeignCollectionField
    private ForeignCollection<SzczegolZamowienia> szczegolyZamowienia;

    private List<Produkt> produkty;

//    private List<SzczegolZamowienia> szczegolyZamowienia;

    // -- zamowienie hurtowe, krajowe

    public ZamowienieHurt() {

    }

    public ZamowienieHurt(List<Produkt> produkty, int maxWaga, int maxLiczbaPrzedmiotow) {
        super(maxWaga);
        setProdukty(produkty);
        setMaxLiczbaPrzedmiotow(maxLiczbaPrzedmiotow);
//        szczegolyZamowienia = (ForeignCollection<SzczegolZamowienia>) new ArrayList<SzczegolZamowienia>();
    }

    // -- zamowienie hurtowe, zagraniczne

    public ZamowienieHurt(List<Produkt> produkty, String krajPochodzenia, int maxLiczbaPrzedmiotow) {
        super(krajPochodzenia);
        setProdukty(produkty);
        setMaxLiczbaPrzedmiotow(maxLiczbaPrzedmiotow);
//        szczegolyZamowienia = (ForeignCollection<SzczegolZamowienia>) new ArrayList<SzczegolZamowienia>();
    }

    //==================================================================================================================

    // POLIMORFIZM METOD
    @Override
    public double obliczCeneKoncowa() {
        return 0;
    };

    //==================================================================================================================

    public int getMaxLiczbaPrzedmiotow() {
        return maxLiczbaPrzedmiotow;
    }

    public void setMaxLiczbaPrzedmiotow(int maxLiczbaPrzedmiotow) {
        if (maxLiczbaPrzedmiotow > 0)
            this.maxLiczbaPrzedmiotow = maxLiczbaPrzedmiotow;
        else
            throw new RuntimeException("Maksymalna liczba przedmiotow nie moze byc ujemna!");
    }

    public List<Produkt> getProdukty() {
        return new ArrayList<Produkt>(produkty);
    }

    private void setProdukty(List<Produkt> produkty) {
        if (produkty == null)
            throw new RuntimeException("Lista produktow jest pusta!");

        if (produkty.size() > 0)
            this.produkty = produkty;
        else
            throw new RuntimeException("Lista produktow jest pusta!");
    }

    public void addProdukt(Produkt produkt) {
        if (produkt == null)
            throw new RuntimeException("Produkt nie istnieje!");

        if (!produkty.contains(produkt)) {
            produkty.add(produkt);
            produkt.addZamowienieHurt(this);
        }
    }

    public void removeProdukt(Produkt produkt) {
        if (produkty.contains(produkt)) {
            produkty.remove(produkt);
            produkt.removeZamowienieHurt(this);
            produkt = null;
        }
    }


//    public List<SzczegolZamowienia> getSzczegolyZamowienia() {
//        return new ArrayList<>(szczegolyZamowienia);
//    }

    public String pokazProdukty() {
        String txt = "";
        for(Produkt p : produkty)
            txt += p.getNazwa() + "\n";
        return txt;
    }



    public String toString() {
        String str = "";

        if (this.getMaxWaga() == 0) {
            str += " Zamowienie hurtowe i zagraniczne, pochodzi z "
                    + this.getKrajPochodzenia() + "\nZamowione produkty to: \n" + this.pokazProdukty();
                    //+ "\nSzczegoly zamowienia: " + this.getSzczegolyZamowienia().toString();
        } else {
            str += "Zamowienie hurtowe i krajowe, jego maksymalna waga wynosi "
                    + this.getMaxWaga() + "kg." + "\nZamowione produkty to: \n" + this.pokazProdukty();
                    //+ "\nSzczegoly zamowienia:  " + this.getSzczegolyZamowienia().toString();
        }
        return str;
    }

    //-- KOMPOZYCJA

    @DatabaseTable(tableName = "SZCZEGOL_ZAMOWIENIA")
    public static class SzczegolZamowienia implements IModel {

        @DatabaseField(generatedId = true)
        private int id;

        @DatabaseField(canBeNull = false)
        private String qrCode;

        @DatabaseField(canBeNull = false, foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
        private ZamowienieHurt zamowienie;

        public SzczegolZamowienia() {

        }

        private SzczegolZamowienia(String qrCode) {
            setQrCode(qrCode);
        }


        public String getQrCode() {
            return qrCode;
        }

        public void setQrCode(String qrCode) {
            if (qrCode == null)
                throw new RuntimeException("QRCode nie moze byc pusty!");
            this.qrCode = qrCode;
        }

        public String toString() {
            return this.qrCode;
        }


    }

    public SzczegolZamowienia createSzczegolZamowienia(String qrCode) {
        SzczegolZamowienia szczegolZamowienia =  new SzczegolZamowienia(qrCode);
        szczegolyZamowienia.add(szczegolZamowienia);
        return szczegolZamowienia;
    }


}
