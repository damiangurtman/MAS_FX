package pl.Dams.MAS.UI;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public abstract class BaseView {

    final FlowPane mainPane = new FlowPane();

    public BaseView() {
        mainPane.setOrientation(Orientation.VERTICAL);
        mainPane.setPadding(new Insets(30));
        mainPane.setVgap(15);
    }

    public Parent getMainPane() {
        return mainPane;
    }

    public void showMainStage() {
        MainView mainView = new MainView();
        Stage stage = new Stage();
        stage.setTitle("Z-kom");
        stage.setScene(new Scene(mainView.getMainPane(), 400, 200));
        stage.show();

        Stage currentStage = (Stage) mainPane.getScene().getWindow();
        currentStage.close();
    }

    public void showUtworzZamowienieStage() {
        UtworzZamowienieView utworzZamowienieView = new UtworzZamowienieView();
        Stage stage = new Stage();
        stage.setTitle("Utworz zamowienie");
        stage.setScene(new Scene(utworzZamowienieView.getMainPane(), 600, 400));
        stage.show();

        Stage currentStage = (Stage) mainPane.getScene().getWindow();
        currentStage.close();
    }

    public void showError(String komunikat) {
        Alert alert = new Alert(Alert.AlertType.ERROR, komunikat, ButtonType.OK);
        alert.showAndWait();
    }

    public void showSuccess(String komunikat) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, komunikat, ButtonType.OK);
        alert.showAndWait();
    }

    public void numbersOnly(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*"))
                textField.setText(newValue.replaceAll("[^\\d]", ""));
        });
    }
}
