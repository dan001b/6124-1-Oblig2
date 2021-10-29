package model;

import java.util.Collection;

/**
 * 6124-1 21H Algoritmer og datastrukturer Oblig2-grupper 3
 *
 * Denne koden er hovedsaklig basert på samme klasse fra boken
 * Y, Daniel Liang. (2019). Introduction to Java Programming and Data Structures (11.utg.). Pearson Education.
 * 
 * @author Mustafa Waleed Alqaisy (studentnummer: 216557)
 * @author Sindre Andreas Olsen Strømnæss (studentnummer: 233595)
 *
 */
public interface Tree<E> extends Collection<E> {

    public boolean search(E e); // ret. true dersom finst

    public boolean insert(E e); // ret. true dersom OK

    public boolean delete(E e); // ret. True dersom OK

    public int getSize();

    public default void inOrder() {
    }

    public default void postOrder() {
    }

    public default void preOrder() {
    }

    @Override
    public default boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public default boolean contains(Object e) {
        return search((E) e);
    }

    @Override
    public default boolean add(E e) {
        return insert((E) e);
    }

    @Override
    public default boolean remove(Object e) {
        return delete((E) e);
    }

    @Override
    public default int size() {
        return getSize();
    }

    @Override
    public default boolean containsAll(Collection<?> c) {
        boolean value = false; //initaliserer en boolsk variabel
        for (Object e : c) { //for hvert object e i collection
            value = search((E) e); //sett resultatet av sok() til value (true/false)
            if (value == false) {
                return value;
            }
        }
        return value; //hvis alle objekter i s�k ble funnet skal metoden returnere true
    }

    @Override
    public default boolean addAll(Collection<? extends E> c) {
        boolean result = false;
        for (Object o : c) {
            result = add((E) o);
        }
        return result;
    }

    @Override
    public default boolean removeAll(Collection<?> c) {
        boolean isDeleted = false;
        for (Object o : c) {
            isDeleted = remove(o);
        }
        return isDeleted;
    }

    @Override
    public default boolean retainAll(Collection<?> c) {
        boolean isRetained = false;
        for (Object o : c) {
            if (c.contains(o)) {
                isRetained = true;
            } else {
                remove(o);
                isRetained = false;
            }
        }
        return isRetained;
    }

    @Override
    public default Object[] toArray() {
        //for hver node i traversering legg node i array som er generisk
        //returner s� array med noder
        return null;
    }

    @Override
    public default <T> T[] toArray(T[] array) {
        return null;
    }
}
