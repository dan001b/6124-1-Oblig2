/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

/**
 *
 * @author Musta
 */
public class Oblig2 extends Application {

    @Override
    public void start(Stage primaryStage) {

        AVLTree<Integer> tree = new AVLTree<>();
        tree.inOrder();

        BorderPane pane = new BorderPane();
        BTView view = new BTView(tree);

        pane.setCenter(view);
        TextField tfKey = new TextField();
        tfKey.setPrefColumnCount(3);
        tfKey.setAlignment(Pos.BASELINE_RIGHT);
        Button btInsert = new Button("Sett inn");
        Button btDelete = new Button("Slett");
        Button btSok = new Button("Søk");
        Button btRndInt = new Button("Sett inn 10 tall");
        HBox hBox = new HBox();
        hBox.getChildren().addAll(
                new Label("Tall: "), tfKey, btInsert, btDelete, btSok, btRndInt);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(5);
        pane.setBottom(hBox);
        btInsert.setOnAction(e -> {
            int key = Integer.parseInt(tfKey.getText());
            if (tree.search(key)) {
                view.displayTree(); // Trengs denne?
                view.setStatus(key + " finnes alt");
            } else {
                tree.insert(key);
                view.displayTree();
                view.setStatus(key + " er satt inn");
            }
        });

        btDelete.setOnAction(e -> {
            int key = Integer.parseInt(tfKey.getText());
            if (!tree.search(key)) {
                view.displayTree(); // Trengs denne?
                view.setStatus(key + " finnes ikke");
            } else {
                tree.delete(key);
                view.displayTree();
                view.setStatus(key + " er slettet");
            }
        });
        
        btSok.setOnAction(e -> {
            int key = Integer.parseInt(tfKey.getText());
            if (!tree.search(key)) {
                view.displayTree(); // Trengs denne?
                view.setStatus(key + " finnes ikke");
            } else {
                tree.search(key);
                view.displayTree();
                view.setStatus(key + " ble funnet!");
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
