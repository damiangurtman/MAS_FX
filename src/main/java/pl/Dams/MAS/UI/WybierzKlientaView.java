package pl.Dams.MAS.UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import pl.Dams.MAS.DAO.KlientNormalnyDao;
import pl.Dams.MAS.DAO.ZamowienieDetalDao;
import pl.Dams.MAS.Models.KlientNormalny;
import pl.Dams.MAS.Models.ZamowienieDetal;

public class WybierzKlientaView extends BaseView{

    private ComboBox<KlientNormalny> klientComboBox;
    private KlientNormalnyDao klientDao = new KlientNormalnyDao();
    private ZamowienieDetalDao zamowienieDetalDao = new ZamowienieDetalDao();

    public WybierzKlientaView() {
        createKlientComboBox();
        createPokazZamowieniaButton();
    }

    @SuppressWarnings("Duplicates")
    private void createKlientComboBox() {
        klientComboBox = new ComboBox<>();
        ObservableList<KlientNormalny> klienci = FXCollections.observableArrayList(klientDao.queryForAll(KlientNormalny.class));
        klientComboBox.setItems(klienci);
        klientComboBox.getSelectionModel().select(0);
        mainPane.getChildren().add(klientComboBox);
    }

    private void createPokazZamowieniaButton() {
        Button pokazZamowieniaButton = new Button("Pokaz zamowienia");
        pokazZamowieniaButton.setOnMouseClicked(event -> onPokazZamowieniaButtonClicked());
        mainPane.getChildren().add(pokazZamowieniaButton);
    }

    private void onPokazZamowieniaButtonClicked() {
        KlientNormalny klient = klientComboBox.getSelectionModel().getSelectedItem();
        ListView<ZamowienieDetal> lista = new ListView<>();
        ObservableList<ZamowienieDetal> zamowienia = FXCollections.observableArrayList(zamowienieDetalDao.znajdzZamowienia(klient));
        lista.setPrefWidth(600);
        lista.setPrefHeight(150);
        lista.setItems(zamowienia);
        mainPane.getChildren().add(lista);
    }

}
