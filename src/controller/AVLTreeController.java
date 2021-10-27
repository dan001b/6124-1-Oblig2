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
    final String BIG_SMALL = " for lite eller for stort";
    

    public AVLTreeController() {
        tree = new AVLTree<>();
        view = new BTView(tree);

    }

    /**
     * metoden setter inn ny verdi i tree
     * ny node merkes med farge
     *
     * @param o bjektet som skal settes inn
     */
    public boolean insert(Object o) {
        boolean sucsess = false;
        if (o != null) {
            if (tree.search((Comparable) o)) {
                updateTreeView(o.toString() + EXISTS, o);
            } else {
                sucsess = tree.insert((Comparable) o);
                updateTreeView(o.toString() + ADDED, o);
            }
        }
        return sucsess;
    }
    
    /**
     * metoden setter fjerner verdi i tree
     *
     * @param o bjektet som skal fjernes
     */
    public boolean delete(Object o) {
        boolean sucsess = false;
        if (o != null) {
            if (!tree.search((Comparable) o)) {
                updateTreeView(o + DOES_NOT_EXISTS, o);
            } else {
                sucsess = tree.delete((Comparable) o);
                updateTreeView(o + DELETED, o);
            }
        }
        return sucsess;
    }
    
    /**
     * metoden setter finner verdi i tree
     * dersom objektet er funnet vil objektet merkes med fargeendring
     *
     * @param o objektet som det søkes etter
     */
    public boolean search(Object o) {

        boolean sucsess = false;
        if (o != null) {
            sucsess = tree.search((Comparable) o);
            if (!sucsess) {
                updateTreeView(o + DOES_NOT_EXISTS, o);
            } else {
                updateTreeView(o + FOUND, o);
            }
        }
        return sucsess;

    }
    
    /**
     * metoden finner verdi i tree basert på
     * forhold fra minste. returnerer 
     * n. minste verdi
     *
     * @param n indeks relativ til minste verdi
     */
    public void findNthSmallest(int n) {
        if (n < 1 || n > tree.size()) {
            alert(BIG_SMALL);
        } else {
            Object result = (Object) tree.find(n);
            updateTreeView("Det " + n + " minste elementet er: " + result, result);
        }
    }

    public BTView getView() {
        return view;
    }

    public void alert(String s) {
        updateTreeView(s, null);
    }

    private void updateTreeView(String s, Object o) {
        if (o != null) {
            view.displayTree((Comparable) o);
        }
        view.setStatus(s);
    }

}
