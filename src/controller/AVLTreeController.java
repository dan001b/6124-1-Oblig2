/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.AVLTree;
import view.BTView;

/**
 *
 * @author Musta
 */
public class AVLTreeController {

    private AVLTree tree;
    private BTView view;

    final String EXISTS = " finnes alt";
    final String DOES_NOT_EXISTS = " finnes ikke";
    final String ADDED = " er satt inn";
    final String DELETED = " er slettet";
    final String FOUND = " ble funnet";
    final String TOOSMALL = " for lite";
    final String TOOBIG = " for stort";

    public AVLTreeController() {
        tree = new AVLTree<>();
        view = new BTView(tree);
    }

    public boolean insert(Object o) {
        boolean sucsess = false;
        if (o != null) {
            if (tree.search((Comparable) o)) {
                updateStatus(o.toString() + EXISTS);
            } else {
                sucsess = tree.insert((Comparable) o);
                updateStatus(o.toString() + ADDED);
            }
        }
        return sucsess;
    }

    public boolean delete(Object o) {
        boolean sucsess = false;
        if (o != null) {
            if (!tree.search((Comparable) o)) {
                updateStatus(o + DOES_NOT_EXISTS);
            } else {
                sucsess = tree.delete((Comparable) o);
                updateStatus(o + DELETED);
            }
        }
        return sucsess;
    }

    public boolean search(Object o) {
        boolean sucsess = false;
        if (o != null) {
            sucsess = tree.search((Comparable) o);
            if (!sucsess) {
                updateStatus(o + DOES_NOT_EXISTS);
            } else {
                updateStatus(o + FOUND);
            }
        }
        return sucsess;

    }
    
    public void findNthSmallest(int n) {
    	if(n < 1 || n > tree.size()) {
    		updateStatus(TOOSMALL+" eller"+TOOBIG);
    	}
    		else{
    		Object result = (Object) tree.find(n);
    		updateStatus("Det "+n+" minste elementet er: "+result);
    	}
    }

    public BTView getView() {
        return view;
    }

    public void alert(String s) {
        updateStatus(s);
    }

    private void updateStatus(String s) {
        view.displayTree();
        view.setStatus(s);
    }

}
