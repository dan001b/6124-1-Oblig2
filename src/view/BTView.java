/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import model.BST;

/**
 * 6124-1 21H Algoritmer og datastrukturer Oblig2-grupper 3
 *
 * @author Mustafa Waleed Alqaisy (studentnummer: 216557)
 * @author Sindre Andreas Olsen Strømnæss (studentnummer: 233595)
 *
 */
public class BTView<E extends Comparable<E>> extends Pane {

    private BST<E> tree = new BST<>();
    private final double RADIUS = 15;
    private final double VGAP = 50;
    private final String EMPTY = "Treet er tomt";
    private final Text msg = new Text(20, 20, EMPTY);

    public BTView(BST<E> tree) {
        this.tree = tree;
        setStatus(EMPTY);
        getChildren().add(msg);
    }

    public void setStatus(String msg) {
        this.msg.setText(msg);
    }

    public void displayTree(E e) {
        this.getChildren().clear();
        if (tree.getRoot() != null) {
            displayTree(tree.getRoot(), getWidth() / 2,
                    VGAP, getWidth() / 4, e);
        }
        this.getChildren().add(msg);
    }

    private void displayTree(BST.TreeNode<E> root, double x, double y, double hGap, E e) {
        if (root.left != null) {
            getChildren().add(new Line(x - hGap, y + VGAP, x, y));
            displayTree(root.left, x - hGap, y + VGAP, hGap / 2, e);
        }
        if (root.right != null) {
            getChildren().add(new Line(x + hGap, y + VGAP, x, y));
            displayTree(root.right, x + hGap, y + VGAP, hGap / 2, e);
        }

        Text text = new Text(x - 4, y + 4, root.element + "");

        double textW = text.getLayoutBounds().getWidth() / 2;
        text.setX(x - textW);

        double radiusX = (textW > RADIUS) ? textW + 4 : RADIUS;

        Ellipse node = new Ellipse(x, y, radiusX, RADIUS);
        if (e.compareTo(root.element) == 0) {
            node.setFill(Color.RED);
            text.setFill(Color.WHITE);
        } else {
            node.setFill(Color.WHITE);
            text.setFill(Color.BLACK);
        }

        node.setStroke(Color.BLACK);
        getChildren().addAll(node, text);
    }
}
