import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DeckViewController implements Initializable {

    @FXML
    private Button nextCardButton;
    @FXML
    private ImageView deckImageView;
    @FXML
    private ImageView activeCardImageView;
    private DeckOfCards deck;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        deck = new DeckOfCards();
        deck.shuffle();

        deckImageView.setImage(deck.getBackOfCardImage());
    }

    @FXML
    public void nextCardButtonPushed() throws Exception {
        try {
            activeCardImageView.setImage(deck.dealTopCard().getImage());
        } catch (Exception e) {
            System.out.println("Koniec Talii Kart! ");

            HBox verticalBox = new HBox(10);
            verticalBox.setPadding(new Insets(15, 15, 15, 15));
            verticalBox.setSpacing(10);
            ImageView alertimage = new ImageView();
            Image image = new Image(getClass().getResourceAsStream("/images/warning.png"));
            alertimage.setImage(image);
            alertimage.setFitWidth(40);
            alertimage.setFitHeight(40);
            alertimage.setLayoutX(10);
            alertimage.setLayoutY(10);

            Label alert = new Label("Koniec talii kart. Rozpocznij od nowa!");
            alert.setWrapText(true);

            HBox horizontalBox = new HBox();
            horizontalBox.setPadding(new Insets(15, 12, 15, 12));
            horizontalBox.setSpacing(10);
            Button buttonOk = new Button("Powrót");
            buttonOk.setPrefSize(200, 20);
            horizontalBox.getChildren().addAll(buttonOk);
            verticalBox.getChildren().addAll(alertimage, alert, horizontalBox);

            Scene scene = new Scene(verticalBox, 350, 100);
            //               scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            Stage dialogStage = new Stage();
//      																						                        inicjalizacja okna modalnego blokującego okno główne.

            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setScene(scene);
            dialogStage.setTitle("Ostrzeżenie!");
            dialogStage.show();


//																												        metoda zamykająca okieno modalne dopiero po naciśnięciu przycisku
            buttonOk.setOnAction(new EventHandler<ActionEvent>() {

                public void handle(ActionEvent arg0) {
                    dialogStage.hide();
                    dialogStage.close();
                    deck = new DeckOfCards();
                    deck.shuffle();

                    deckImageView.setImage(deck.getBackOfCardImage());
                }
            });
        }
    }
}
