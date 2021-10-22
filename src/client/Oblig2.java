/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.Optional;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.AVLTree;
import model.BST;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import view.BTView;
import view.BTViewString;

/**
 *
 * @author Musta
 * @author sindreolsen
 */
public class Oblig2 extends Application {

    @Override
    public void start(Stage primaryStage) {

        AVLTree<Integer> tree = new AVLTree<>();
        AVLTree<String> stringTree = new AVLTree<>();
        tree.inOrder();

        BorderPane pane = new BorderPane();
        BTView view = new BTView(tree);
        BTViewString stringView = new BTViewString(stringTree);
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Bekreft");
        alert.setHeaderText("Bekreft om du vil lage et string-tre eller integer-tre");
        alert.setContentText("Velg alternativ:");
        ButtonType buttonStringChoice = new ButtonType("String");
        ButtonType buttonIntegerChoice = new ButtonType("Integer");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonStringChoice, buttonIntegerChoice, buttonTypeCancel);
        Optional<ButtonType> resultat = alert.showAndWait();
        if (resultat.get() == buttonStringChoice){
            pane.setCenter(stringView);
        }
        else{
            pane.setCenter(view);
        }

        TextField tfKey = new TextField();
        tfKey.setPrefColumnCount(3);
        tfKey.setAlignment(Pos.BASELINE_RIGHT);
        Button btInsert = new Button("Sett inn");
        Button btDelete = new Button("Slett");
        Button btSok = new Button("Søk");
        Button btRndInt = new Button("Sett inn 10 tall");
        HBox hBox = new HBox();
        hBox.getChildren().addAll(
                new Label("Verdi: "), tfKey, btInsert, btDelete, btSok, btRndInt);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(5);
        pane.setBottom(hBox);
        
        //innsetting av verdier
        btInsert.setOnAction(e -> {
        	if(tfKey.getText().matches("[a-zA-Z]+")) {
                String stringKey = tfKey.getText();
                if (stringTree.search(stringKey)) {
                	stringView.displayTree(); // Trengs denne?
                	stringView.setStatus(stringKey + " finnes alt");
                } else {
                	stringTree.insert(stringKey);
                	stringView.displayTree();
                	stringView.setStatus(stringKey + " er satt inn");
                }
        	}
        	else if(tfKey.getText().matches("[0-9]+")) {
        		if(pane.getCenter()==stringView) {
        			stringView.displayTree();
                	stringView.setStatus("Ugyldig verdi for string-tre.");
        		}
        		else {
            int key = Integer.parseInt(tfKey.getText());
            if (tree.search(key)) {
                view.displayTree(); // Trengs denne?
                view.setStatus(key + " finnes alt");
            } else {
                tree.insert(key);
                view.displayTree();
                view.setStatus(key + " er satt inn");
            }
           }
          }
        });

        //sletting av verdier
        btDelete.setOnAction(e -> {
        	if(tfKey.getText().matches("[a-zA-Z]+")) {
                String stringKey = tfKey.getText();
                if (!stringTree.search(stringKey)) {
                	stringView.displayTree(); // Trengs denne?
                	stringView.setStatus(stringKey + " finnes ikke");
                } else {
                	stringTree.delete(stringKey);
                	stringView.displayTree();
                	stringView.setStatus(stringKey + " er slettet");
                }
        	}
        	else if(tfKey.getText().matches("[0-9]+")){
        		if(pane.getCenter()==stringView) {
                	stringView.displayTree();
                	stringView.setStatus("Ugyldig verdi for string-tre.");
        		}
        		else {
            int key = Integer.parseInt(tfKey.getText());
            if (!tree.search(key)) {
                view.displayTree(); // Trengs denne?
                view.setStatus(key + " finnes ikke");
            } else {
                tree.delete(key);
                view.displayTree();
                view.setStatus(key + " er slettet");
            }
           }
          }
        });
      
        //søk etter verdier
        btSok.setOnAction(e -> {
        	if(tfKey.getText().matches("[a-zA-Z]+")) {
                String stringKey = tfKey.getText();
                if (!stringTree.search(stringKey)) {
                	stringView.displayTree(); // Trengs denne?
                	stringView.setStatus(stringKey + " finnes ikke");
                } else {
                	stringTree.search(stringKey);
                	stringView.displayTree();
                	stringView.setStatus(stringKey + " ble funnet!");
                }
        	}
        	else if(tfKey.getText().matches("[0-9]+")) {
        		if(pane.getCenter()==stringView) {
                	stringView.displayTree();
                	stringView.setStatus("Ugyldig verdi for string-tre.");
        		}
        		else {
            int key = Integer.parseInt(tfKey.getText());
            if (!tree.search(key)) {
                view.displayTree(); // Trengs denne?
                view.setStatus(key + " finnes ikke");
            } else {
                tree.search(key);
                view.displayTree();
                view.setStatus(key + " ble funnet!");
            }
           }
          }
        });

        Scene scene = new Scene(pane, 600, 400);

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

}
