package pl.Dams.MAS;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.Dams.MAS.DAO.*;
import pl.Dams.MAS.Models.*;
import pl.Dams.MAS.UI.MainView;
import pl.Dams.MAS.Utils.DataManager;
import pl.Dams.MAS.Utils.DbManager;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application{


    @Override
    public void start(Stage primaryStage) throws Exception {
        MainView mainView = new MainView();
        primaryStage.setTitle("Z-kom");
        primaryStage.setScene(new Scene(mainView.getMainPane(), 400, 200));
        primaryStage.show();
    }


    public static void main(String[] args) throws SQLException, IOException {
//        DbManager.initDatabase();
//        DataManager.initData();
//        DbManager.closeConnectionSource();
        launch(args);
    }

    // INFORMACJE
    // ATRYBUT ZLOZONY, ATRYBUT KLASOWY, METODA KLASOWA, PRZECIAZANIE METOD - AdresZamieszkania
    //ATRYBUT OPCJONALNY, ATRYBUT POWTARZALNY - Pracownik, adresMailowy, szkolenia
    //ATRYBUT POCHODNY - Zamowienie, getCena()
    //PRZESLONIECIE - wszystkie toString()

    //ASOCJACJE
    //KWALIFIKOWANA - QueryForId
    //KOMPOZYCJA - ZamowienieHurt i SzczegolZamowienia
    //BINARNA - KlientNormalny i AdresZamieszkania

    //KLASA ABSTRAKCYJNA - Osoba/Zamowienie
    //POLIMORFICZNE WYWOLYWANIE METOD - Zamowienie, obliczCeneKoncowa()
    //WIELODZIEDZICZENIE - KlientPracownik
    //DZIEDZICZENIE WIELOASPEKTOWE - Zamowienie krajowe/zagraniczne, zamowienie detal/hurt
    //DZIEDZICZENIE DYNAMICZNE - KlientNieaktywny

    //OGRANICZEINA
    //ATRYBUTU DYNAMICZNE I STATYCZNE - Pracownik, pensja
    //UNIQUE I ORDERED- Specjalizacja/Szkolenie
    //OGRANICZENIE WLASNE - KlientPracownik, dodatkowyRabat

}
