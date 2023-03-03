import java.util.*;

public class MyArrayList<E> implements List<E> {
    private int size;
    private Object[] objects;

    private final static int INITIAL_SIZE = 16;

    public MyArrayList(int size) {
        this.objects = new Object[size];
    }

    public MyArrayList() {
        this.objects = new Object[INITIAL_SIZE];
    }


    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) return true;
        return false;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < objects.length; i++) {
            if (objects[i].equals(o)) return true;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        objects[size] = e;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < objects.length; i++) {
            Object ob = objects[i];
            if (ob.equals(o)) {
                for (int j = i; j < size; j++) {
                    this.objects[i] = this.objects[i + 1];
                }
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {

        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < objects.length; i++) {
            objects[i] = null;
        }
        size = 0;
    }

    @Override
    public E get(int index) {
       return (E) objects[index];
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public String toString() {
        return "MyArrayList{" +
                "size=" + size +
                ", objects=" + Arrays.toString(objects) +
                '}';
    }
}

