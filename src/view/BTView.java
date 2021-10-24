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
public class BTView<E extends Comparable<E>> extends Pane {

    private BST<E> tree = new BST<>();
    private final double RADIUS = 15;
    private final double VGAP = 50;
    private final String EMPTY = "Treet er tomt";

    public BTView(BST<E> tree) {
        this.tree = tree;
        setStatus(EMPTY);
    }

    public void setStatus(String msg) {
        getChildren().add(new Text(20, 20, msg));
    }

    public void displayTree() {
        this.getChildren().clear();
        if (tree.getRoot() != null) {
            displayTree(tree.getRoot(), getWidth() / 2,
                    VGAP, getWidth() / 4);
        }
    }

    private void displayTree(BST.TreeNode<E> root, double x, double y, double hGap) {
        if (root.left != null) {
            getChildren().add(new Line(x - hGap, y + VGAP, x, y));
            displayTree(root.left, x - hGap, y + VGAP, hGap / 2);
        }
        if (root.right != null) {
            getChildren().add(new Line(x + hGap, y + VGAP, x, y));
            displayTree(root.right, x + hGap, y + VGAP, hGap / 2);
        }
        Circle circle = new Circle(x, y, RADIUS);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        getChildren().addAll(circle, new Text(x - 4, y + 4, root.element + ""));
    }
}
