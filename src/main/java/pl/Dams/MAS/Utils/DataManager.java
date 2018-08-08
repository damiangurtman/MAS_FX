package pl.Dams.MAS.Utils;

import pl.Dams.MAS.DAO.*;
import pl.Dams.MAS.Models.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    public static void initData() throws SQLException {


        //==================================================================================================================

        AdresZamieszkania adresZamieszkania = new AdresZamieszkania("Norwida", "Mstow", 7);
        AdresZamieszkania adresZamieszkania2 = new AdresZamieszkania("Zlota", "Warszawa", 60);
        AdresZamieszkania adresZamieszkaniaKLASOWY = new AdresZamieszkania("Zelazna", 12);
        AdresZamieszkaniaDao adresZamieszkaniaDao = new AdresZamieszkaniaDao();
        adresZamieszkaniaDao.createOrUpdate(adresZamieszkania);
        adresZamieszkaniaDao.createOrUpdate(adresZamieszkania2);
        adresZamieszkaniaDao.createOrUpdate(adresZamieszkaniaKLASOWY);

        Klient klient = new KlientNormalny("Tomasz", "Gurtman", adresZamieszkania);
        Klient klient2 = new KlientNormalny("Adam", "Malysz", adresZamieszkania2);

        KlientNormalnyDao klientNormalnyDao = new KlientNormalnyDao();
        klientNormalnyDao.createOrUpdate(klient);
        klientNormalnyDao.createOrUpdate(klient2);
        klientNormalnyDao.delete(klient);

        klient = new KlientNieaktywny(klient, new MyDate(2018, 2, 21));

        KlientStalyDao klientStalyDao = new KlientStalyDao();
        klientStalyDao.createOrUpdate(klient);

        //==================================================================================================================

        Produkt macbook_pro = new Produkt("Macbook Pro", 5300);
        Produkt macbook_air = new Produkt("Macbook Air", 3700);
        ProduktDao produktDao = new ProduktDao();
        produktDao.createOrUpdate(macbook_pro);
        produktDao.createOrUpdate(macbook_air);

        KlientNormalny klientNormalny1 = new KlientNormalny("Przemyslaw", "Saletra", adresZamieszkania);
        KlientNormalny klientNormalny2 = new KlientNormalny("Marcin", "Najman", adresZamieszkania2);

        Zamowienie zamowienieDetalKrajowe= new ZamowienieDetal(macbook_pro, 30, 10, klientNormalny1);
        Zamowienie zamowienieDetalZagraniczne= new ZamowienieDetal(macbook_pro, "Japonia", 200, klientNormalny2);

        ZamowienieDetalDao zamowienieDetalDao = new ZamowienieDetalDao();
        zamowienieDetalDao.createOrUpdate(zamowienieDetalKrajowe);
        zamowienieDetalDao.createOrUpdate(zamowienieDetalZagraniczne);


        //==================================================================================================================

        //-- WIELODZIEDZICZENIE
//        KlientPracownik klientPracownik = new KlientPracownik("Jan", "Jankowski", adresZamieszkania, 3000, 0.20);
//        KlientPracownikDao klientPracownikDao = new KlientPracownikDao();
//        klientPracownikDao.createOrUpdate(klientPracownik);

        //==================================================================================================================

        System.out.println(zamowienieDetalZagraniczne.toString());



        //==================================================================================================================
        //-- ASOCJACJA KWALIFIKOWANA

        System.out.println("//==================================================================================================================");
        ZamowienieDetal zamowienieDetal = zamowienieDetalDao.queryForId(ZamowienieDetal.class, 1);

        List<ZamowienieDetal> zamowienieDetal2 = zamowienieDetalDao.queryForAll(ZamowienieDetal.class);
        System.out.println(zamowienieDetal2);
        System.out.println(zamowienieDetal);
        System.out.println("//==================================================================================================================");


        //==================================================================================================================

        //-- KOMPOZYCJA
        List<Produkt> lista = new ArrayList<>();
        lista.add(macbook_air);
        lista.add(macbook_pro);
        System.out.println("//==================================================================================================================");
        ZamowienieHurt test = new ZamowienieHurt(lista, 15, 3);

        zamowienieDetalDao.createOrUpdate(test);
//
        System.out.println(test);









    }
}
