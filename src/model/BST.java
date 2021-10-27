package model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 6124-1 21H Algoritmer og datastrukturer Oblig2-grupper 3
 *
 * Denne koden er hovedsaklig basert på samme klasse fra boka
 *
 * @author Mustafa Waleed Alqaisy (studentnummer: 216557)
 * @author Sindre Andreas Olsen Strømnæss (studentnummer: 233595)
 *
 */
public class BST<E extends Comparable<E>> implements Tree<E> {

    protected TreeNode<E> root;
    protected int size = 0;

    //lager et tomt bin�rt tre
    public BST() {

    }

    public BST(E[] objects) {
        for (int i = 0; i < objects.length; i++) {
            insert(objects[i]);
        }
    }

    @Override
    public boolean search(E e) {
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean insert(E e) {
        if (root == null) {
            root = createNewNode(e);
        } else {
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null) {
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else {
                    return false;
                }
            }

            if (e.compareTo(parent.element) < 0) {
                parent.left = createNewNode(e);
            } else {
                parent.right = createNewNode(e);
            }

        }
        size++;
        return true;
    }

    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<>(e);
    }

    @Override
    public void inOrder() {
        inOrder(root);
    }

    protected void inOrder(TreeNode<E> root) { //gj�r inorder traverseringen
        if (root == null) {
            return;
        }

        inOrder(root.left);
        System.out.println(root.element + "  ");
        inOrder(root.right);
    }

    @Override
    public void postOrder() {
        postOrder(root);
    }

    protected void postOrder(TreeNode<E> root) { //gj�r postorder traverseringen
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.element + "  ");
    }

    @Override
    public void preOrder() {
        preOrder(root);
    }

    protected void preOrder(TreeNode<E> root) { //gj�r preorder traverseringen
        if (root == null) {
            return;
        }
        System.out.println(root.element + "  ");
        postOrder(root.left);
        postOrder(root.right);
    }

    @Override
    public boolean delete(E e) {
        TreeNode<E> parent = null;
        TreeNode<E> current = root;

        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            } else {
                break;
            }
        }
        if (current == null) {
            return false;
        }
        if (current.left == null) { //Case 1: Current node har ingen barn til venstre
            if (parent == null) {
                root = current.right;
            } else {
                if (e.compareTo(parent.element) < 0) {
                    parent.left = current.right;
                } else {
                    parent.right = current.right;
                }
            }
        } else { //Case 2: Current node har barn til venstre
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;
            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right;
            }
            current.element = rightMost.element;

            if (parentOfRightMost.right == rightMost) {
                parentOfRightMost.right = rightMost.left;
            } else {
                parentOfRightMost.left = rightMost.left;
            }
        }
        size--;
        return true; //sletting vellykket
    }

    @Override
    public int getSize() {
        return size;
    }

    public TreeNode<E> getRoot() {
        return root;
    }

    public ArrayList<TreeNode<E>> path(E e) {
        ArrayList<TreeNode<E>> list = new ArrayList<>();
        TreeNode<E> current = root;

        while (current != null) {
            list.add(current);
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else {
                break;
            }
        }
        return list;
    }

    @Override
    public Iterator<E> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    public static class TreeNode<E> {

        public E element;
        public TreeNode<E> left;
        public TreeNode<E> right;

        public TreeNode(E e) {
            this.element = e;
        }
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
    }
}
