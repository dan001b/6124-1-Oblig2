/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.AVLTree;
import view.BTView;

/**
 * 6124-1 21H Algoritmer og datastrukturer Oblig2-grupper 3
 *
 * @author Mustafa Waleed Alqaisy (studentnummer: 216557)
 * @author Sindre Andreas Olsen Strømnæss (studentnummer: 233595)
 *
 */
public class AVLTreeController {

    private AVLTree tree;
    private BTView view;

    final String EXISTS = " finnes alt";
    final String DOES_NOT_EXISTS = " finnes ikke";
    final String ADDED = " er satt inn";
    final String DELETED = " er slettet";
    final String FOUND = " ble funnet";

    public AVLTreeController() {
        tree = new AVLTree<>();
        view = new BTView(tree);
        
    }
    
    /**
     * metoden setter inn ny verdi i tree
     *
     * @param o bjektet som skal settes inn
     */
    public boolean insert(Object o) {
        boolean sucsess = false;
        if (o != null) {
            if (tree.search((Comparable) o)) {
                updateStatus(o.toString() + EXISTS,o);
            } else {
                sucsess = tree.insert((Comparable) o);
                updateStatus(o.toString() + ADDED,o);
            }
        }
        return sucsess;
    }

    public boolean delete(Object o) {
        boolean sucsess = false;
        if (o != null) {
            if (!tree.search((Comparable) o)) {
                updateStatus(o + DOES_NOT_EXISTS,o);
            } else {
                sucsess = tree.delete((Comparable) o);
                updateStatus(o + DELETED,o);
            }
        }
        return sucsess;
    }

    public boolean search(Object o) {
        
        boolean sucsess = false;
        if (o != null) {
            sucsess = tree.search((Comparable) o);
            if (!sucsess) {
                updateStatus(o + DOES_NOT_EXISTS,o);
            } else {
                updateStatus(o + FOUND,o);
            }
        }
        return sucsess;

    }

    public BTView getView() {
        return view;
    }

    public void alert(String s,Object o) {
        updateStatus(s,o);
    }

    private void updateStatus(String s,Object o) {
        if (o != null) {
            view.displayTree((Comparable) o);
        }
        view.setStatus(s);
    }

}
