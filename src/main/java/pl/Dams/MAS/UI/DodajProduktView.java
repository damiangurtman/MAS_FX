package pl.Dams.MAS.UI;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pl.Dams.MAS.DAO.ProduktDao;
import pl.Dams.MAS.Models.Produkt;

import java.util.List;

public class DodajProduktView extends BaseView{

    private TextField nazwaProduktu, wartoscNetto;
    private ProduktDao produktDao = new ProduktDao();

    public DodajProduktView() {
        createNazwaProduktuInput();
        createWartoscNettoInput();
        createDodajButton();
    }


    private void createNazwaProduktuInput() {
        nazwaProduktu = new TextField();
        nazwaProduktu.setPromptText("Nazwa produktu");
        nazwaProduktu.setPrefWidth(200);
        mainPane.getChildren().add(nazwaProduktu);
    }

    private void createWartoscNettoInput() {
        wartoscNetto = new TextField();
        wartoscNetto.setPromptText("Wartosc netto w zl");
        wartoscNetto.setPrefWidth(200);
        numbersOnly(wartoscNetto);
        mainPane.getChildren().add(wartoscNetto);
    }

    private void createDodajButton() {
        Button dodajButton = new Button("Dodaj");
        dodajButton.setOnMouseClicked(event -> onDodajButtonClicked());
        mainPane.getChildren().add(dodajButton);
    }

    private void onDodajButtonClicked() {
        String nazwa = nazwaProduktu.getText();
        boolean isValid = true;

        int wartosc;
        try {
            wartosc = Integer.valueOf(wartoscNetto.getText());
        } catch (NumberFormatException e) {
            wartosc = 0;
            wartoscNetto.setText("" + wartosc);
        }

        if (wartosc == 0 || wartosc < 0) {
            isValid = false;
            showError("Cena przedmiotu musi byc wieksza od 0!");
        }


        if (nazwa == null || nazwa.length() == 0) {
            isValid = false;
            showError("Nazwa przedmiotu nie moze byc pusta!");
        } else {
            List<Produkt> produkty = produktDao.queryForAll(Produkt.class);
            if (produkty != null) {
                for (Produkt produkt : produkty) {
                    if (produkt.getNazwa().equals(nazwa)) {
                        isValid = false;
                        showError("Produkt '" + nazwa + "' juz istnieje!");
                    }
                }
            }
        }

        if (isValid)
            createProdukt(nazwa, wartosc);
    }

    private void createProdukt(String nazwa, int wartosc) {
        Produkt produkt = new Produkt(nazwa, wartosc);
        produktDao.createOrUpdate(produkt);
        showSuccess("Produkt dodany prawidlowo!");

        showUtworzZamowienieStage();
    }
}
