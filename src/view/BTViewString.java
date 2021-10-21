/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import model.BST;

/**
 *
 * @author Musta
 */
public class BTViewString extends Pane {

    private BST<String> tree = new BST<>();
    private double radius = 15; // final!!!!!!
    private double vGap = 50; // final!!!!!!

    public BTViewString(BST<String> tree) {
        this.tree = tree;
        setStatus("Treet er tomt"); // ??????
    }

    public void setStatus(String msg) {
        getChildren().add(new Text(20, 20, msg));
    }

    public void displayTree() {
        this.getChildren().clear();
        if (tree.getRoot() != null) {
            displayTree(tree.getRoot(), getWidth() / 2,
                    vGap, getWidth() / 4);
        }
    }

private void displayTree(BST.TreeNode<String> root, double x, double y, double hGap) {
    if (root.left != null){
    getChildren().add(new Line(x-hGap, y+vGap, x, y));
    displayTree(root.left, x-hGap, y+vGap, hGap/2);
    }
    if (root.right != null){
    getChildren().add(new Line(x+hGap, y+vGap, x, y));
    displayTree(root.right, x+hGap, y+vGap, hGap/2);
    }
    Circle circle = new Circle(x, y, radius);
    circle.setFill(Color.WHITE);
    circle.setStroke(Color.BLACK);
    getChildren().addAll(circle, new Text(x-4, y+4, root.element + ""));
    }
}
