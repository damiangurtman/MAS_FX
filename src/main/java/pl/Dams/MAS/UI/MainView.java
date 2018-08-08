package pl.Dams.MAS.UI;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainView extends BaseView{

    public MainView() {
        createUtworzZamowienieButton();
        createWyswietlZamowieniaKlientaButton();
    }

    private void createUtworzZamowienieButton() {
        Button utworzZamowienieButton = new Button("Utworz zamowienie");
        utworzZamowienieButton.setOnMouseClicked(event -> onUtworzZamowienieClicked());
        mainPane.getChildren().add(utworzZamowienieButton);
    }

    private void createWyswietlZamowieniaKlientaButton() {
        Button wyswietlZamowieniaKlientaButton = new Button("Wyswietl zamowienia klienta");
        wyswietlZamowieniaKlientaButton.setOnMouseClicked(event -> onWyswietlZamowieniaKlientaClicked());
        mainPane.getChildren().add(wyswietlZamowieniaKlientaButton);
    }

    private void onUtworzZamowienieClicked() {
        UtworzZamowienieView utworzZamowienieView = new UtworzZamowienieView();
        Stage stage = new Stage();
        stage.setTitle("Utworz zamowienie");
        stage.setScene(new Scene(utworzZamowienieView.getMainPane(), 600, 400));
        stage.show();
    }

    private void onWyswietlZamowieniaKlientaClicked() {
        WybierzKlientaView wybierzKlientaView = new WybierzKlientaView();
        Stage stage = new Stage();
        stage.setTitle("Wyswietl zamowienia klienta");
        stage.setScene(new Scene(wybierzKlientaView.getMainPane(),650, 650));
        stage.show();
    }
}
