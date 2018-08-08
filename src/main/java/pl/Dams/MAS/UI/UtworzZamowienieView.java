package pl.Dams.MAS.UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.Dams.MAS.DAO.KlientNormalnyDao;
import pl.Dams.MAS.DAO.ProduktDao;
import pl.Dams.MAS.DAO.ZamowienieDetalDao;
import pl.Dams.MAS.Models.KlientNormalny;
import pl.Dams.MAS.Models.Produkt;
import pl.Dams.MAS.Models.ZamowienieDetal;

public class UtworzZamowienieView extends BaseView{

    private KlientNormalnyDao klientDao = new KlientNormalnyDao();
    private ProduktDao produktDao = new ProduktDao();
    private ZamowienieDetalDao zamowienieDetalDao = new ZamowienieDetalDao();

    private ComboBox<KlientNormalny> klientComboBox;
    private ComboBox<Produkt> produktComboBox;

    private TextField maxWaga, kosztPrzesylki;

    public UtworzZamowienieView() {
        createKlientComboBox();
        createProduktComboBox();
        createDodajProduktButton();
        createMaxWagaInput();
        createKosztPrzesylkiInput();
        createUtworzZamowienieButton();
    }

    @SuppressWarnings("Duplicates")
    private void createKlientComboBox() {
        klientComboBox = new ComboBox<>();
        ObservableList<KlientNormalny> klienci = FXCollections.observableArrayList(klientDao.queryForAll(KlientNormalny.class));
        klientComboBox.setItems(klienci);
        klientComboBox.getSelectionModel().select(0);
        mainPane.getChildren().add(klientComboBox);
    }

    private void createProduktComboBox() {
        produktComboBox = new ComboBox<>();
        ObservableList<Produkt> produkty = FXCollections.observableArrayList(produktDao.queryForAll(Produkt.class));
        produktComboBox.setItems(produkty);
        produktComboBox.getSelectionModel().select(0);
        mainPane.getChildren().add(produktComboBox);
    }

    private void createDodajProduktButton() {
        Button dodajProduktButton = new Button("Dodaj produkt");
        dodajProduktButton.setOnMouseClicked(event -> onDodajProduktButtonClicked());
        mainPane.getChildren().add(dodajProduktButton);
    }

    private void onDodajProduktButtonClicked() {
        DodajProduktView dodajProduktView = new DodajProduktView();
        Stage stage = new Stage();
        stage.setTitle("Dodaj produkt");
        stage.setScene(new Scene(dodajProduktView.getMainPane(), 400, 200));
        stage.show();
    }

    private void createMaxWagaInput() {
        maxWaga = new TextField();
        maxWaga.setPromptText("Maksymalna waga");
        maxWaga.setPrefWidth(200);
        numbersOnly(maxWaga);
        mainPane.getChildren().add(maxWaga);
    }

    private void createKosztPrzesylkiInput() {
        kosztPrzesylki = new TextField();
        kosztPrzesylki.setPromptText("Koszt przesylki");
        kosztPrzesylki.setPrefWidth(200);
        numbersOnly(kosztPrzesylki);
        mainPane.getChildren().add(kosztPrzesylki);
    }

    private void createUtworzZamowienieButton() {
        Button utworzZamowienieButton = new Button("Utworz zamowienie");
        utworzZamowienieButton.setOnMouseClicked(event -> onUtworzZamowienieButtonClicked());
        mainPane.getChildren().add(utworzZamowienieButton);
    }

    private void onUtworzZamowienieButtonClicked() {
        KlientNormalny klient = klientComboBox.getSelectionModel().getSelectedItem();
        Produkt produkt = produktComboBox.getSelectionModel().getSelectedItem();
        boolean isValid = true;

        int waga;
        try {
            waga = Integer.valueOf(maxWaga.getText());
        } catch(NumberFormatException e) {
            waga = 0;
            maxWaga.setText("" + waga);
        }

        double koszt;
        try {
            koszt = Double.valueOf(kosztPrzesylki.getText());
        } catch (NumberFormatException e) {
            koszt = 0;
            kosztPrzesylki.setText("" + koszt);
        }


        if (waga <= 0 || waga > 100) {
            isValid = false;
            showError("Waga musi byc wieksza od 0, ale niewieksza od 100kg!");
        }

        if ( koszt <= 0 || koszt > 100) {
            isValid = false;
            showError("Koszt przesylki musi byc wiekszy od 0, ale niewiekszy niz 100zl!");
        }


        if (isValid)
            createZamowienie(produkt, waga, koszt, klient);
    }

    private void createZamowienie(Produkt produkt, int waga, double koszt, KlientNormalny klient) {
        ZamowienieDetal zamowienie = new ZamowienieDetal(produkt, waga, koszt, klient);
        zamowienieDetalDao.createOrUpdate(zamowienie);
        showSuccess("Zamowienie utworzone!");

        showMainStage();
    }

}
