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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * 6124-1 21H Algoritmer og datastrukturer Oblig2-grupper 3
 *
 * @author Mustafa Waleed Alqaisy (studentnummer: 216557)
 * @author Sindre Andreas Olsen Strømnæss (studentnummer: 233595)
 *
 */
public class Oblig2 extends Application {

    private final int W_WIDTH = 600;
    private final int W_HEIGHT = 400;

    private boolean isString;
    private AVLTreeController avlCont;

    @Override
    public void start(Stage primaryStage) {

        isString = isString();

        avlCont = new AVLTreeController();
        BorderPane pane = new BorderPane();
        pane.setCenter(avlCont.getView());

        TextField tfKey = new TextField();
        tfKey.setPrefColumnCount(3);
        tfKey.setAlignment(Pos.BASELINE_RIGHT);

        Button btInsert = new Button("Sett inn");
        Button btDelete = new Button("Slett");
        Button btSearch = new Button("Søk");
        Button btRnd = new Button("Sett inn 10 verdi");
        Button findNthSmallest = new Button("Finn [Nth] minste verdi");

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
            if (key != null) 
                avlCont.delete(key);
        });

        //søk etter verdier
        btSearch.setOnAction(e -> {
            Object key = parseKey(tfKey.getText());
            if (key != null)
                avlCont.search(key);
        });

        //sett inn 10 verdier
        btRnd.setOnAction(e -> {
            int count = 0;
            int seed = 100; //tilfeldig tall velges fra i 

            while (count < 10) {
                boolean check = false;
                if (isString) {
                    check = avlCont.insert(randAlphaNumericString(7));
                } else {
                    check = avlCont.insert(randNumber(seed += 1));
                }

                if (check == true) {
                    count++;
                }
            }
        });

        //finner n. minste tallet 
        findNthSmallest.setOnAction(e -> {
            int key = 0;
            try {
                key = (Integer) Integer.parseInt(tfKey.getText());
                avlCont.findNthSmallest((int)key);
            } catch (NumberFormatException ex) {
                avlCont.alert("Verdi må bestå av kun tall");
            }
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

    /**
     * metoden behandler data som settes inn, basert på modus (Integer/String)
     * så vil den forsøke å levere tilbake object av riktig klasse. Feil type
     * innhold varsles.
     *
     * @param s rå data fra inputfeltet
     */
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

    /**
     * metoden viser vindu og etterlyser type data som skal settes inn standard
     * er tall (Integer) kan byttes til tekst (String).
     */
    private boolean isString() {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Bekreft");
        alert.setHeaderText("Programmet tillater som standard kun tallverdi");
        alert.setContentText("Vil du bytte til tekst?");
        ButtonType buttonStringChoice = new ButtonType("Bytt til tekst");

        ButtonType buttonTypeCancel = new ButtonType("Fortsett med tall");
        alert.getButtonTypes().setAll(buttonStringChoice, buttonTypeCancel);
        Optional<ButtonType> resultat = alert.showAndWait();

        return resultat.get() == buttonStringChoice;
    }

    /**
     * metoden oppretter String med tilfeldig tegn
     *
     * @param n antall tegn
     */
    static String randAlphaNumericString(int n) {

        // Mulige tegn som kan velges
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // oppretter StringBuffer
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // tilfeldig nummer fra 0 til nummeret antall mulige tegn
            int index = randNumber(AlphaNumericString.length());

            // legg til tegn til sb basert på plassering i AlphaNumericString
            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }

    /**
     * metoden oppretter tilfeldig tall
     *
     * @param n tallet oppretes i intervall 0-n
     */
    static int randNumber(int n) {
        return (int) (n * Math.random());
    }

}
