/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;
import java.util.Optional;
import controller.AVLTreeController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.AVLTree;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import view.BTView;

/**
 *
 * @author Musta
 * @author sindreolsen
 */
public class Oblig2 extends Application {

    private final int W_WIDTH = 600;
    private final int W_HEIGHT = 400;

    private boolean isString;
    private AVLTreeController avlCont;

    @Override
    public void start(Stage primaryStage) {

        avlCont = new AVLTreeController();
        BorderPane pane = new BorderPane();
        pane.setCenter(avlCont.getView());

        isString = isString();

        TextField tfKey = new TextField();
        tfKey.setPrefColumnCount(3);
        tfKey.setAlignment(Pos.BASELINE_RIGHT);

        Button btInsert = new Button("Sett inn");
        Button btDelete = new Button("Slett");
        Button btSearch = new Button("Søk");
        Button btRnd = new Button("Sett inn 10 verdi");
        Button findNthSmallest = new Button("Find [Nth] smallest value");

        HBox hBox = new HBox();
        hBox.getChildren().addAll(
                new Label("Verdi: "), tfKey, btInsert, btDelete, btSearch, btRnd, findNthSmallest);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(5);
        pane.setBottom(hBox);

        //innsetting av verdier
        btInsert.setOnAction(e -> {
            Object key = parseKey(tfKey.getText());
            avlCont.insert(key);
        });

        //sletting av verdier
        btDelete.setOnAction(e -> {
            Object key = parseKey(tfKey.getText());
            avlCont.delete(key);
        });

        //søk etter verdier
        btSearch.setOnAction(e -> {
            Object key = parseKey(tfKey.getText());
            avlCont.search(key);
        });

        //sett inn 10 verdier
        btRnd.setOnAction(e -> {
            for (int i = 0; i < 10; i++) {
                if (isString) {
                    avlCont.insert(randAlphaNumericString(7));
                } else {
                    avlCont.insert(randNumber((int) parseKey(tfKey.getText())));
                }
            }
        });
        
        findNthSmallest.setOnAction(e -> {
        		int key = Integer.parseInt(tfKey.getText());
        		avlCont.findNthSmallest(key);
        });

        Scene scene = new Scene(pane, W_WIDTH, W_HEIGHT);

        primaryStage.setTitle("AVL Tree");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private Object parseKey(String s) {

        Object key = null;
        if (isString) {
            key = (String) s;
        } else if (!isString) {

            //Dersom parsing til integer feiler vises feilmelding
            try {
                key = (Integer) Integer.parseInt(s);
            } catch (NumberFormatException e) {
                avlCont.alert("Verdi må bestå av kun tall");
            }
        }
        return key;
    }

    private boolean isString() {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Bekreft");
        alert.setHeaderText("Bekreft om du vil lage et string-tre eller integer-tre");
        alert.setContentText("Velg alternativ:");
        ButtonType buttonStringChoice = new ButtonType("String");
        ButtonType buttonIntegerChoice = new ButtonType("Integer");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonStringChoice, buttonIntegerChoice, buttonTypeCancel);
        Optional<ButtonType> resultat = alert.showAndWait();

        return resultat.get() == buttonStringChoice;
    }

    // function to generate a random string of length n
    static String randAlphaNumericString(int n) {

        // Mulige tegn som kan velges
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // oppretter StringBuffer
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // tilfeldig nummer fra 0 til n
            int index = randNumber(n);

            // legg til tegn til sb basert på plassering i AlphaNumericString
            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }

    static int randNumber(int n) {
        return (int) (n * Math.random());
    }

}
