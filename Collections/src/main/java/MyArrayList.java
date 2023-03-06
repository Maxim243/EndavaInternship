import java.util.*;

public class MyArrayList<E> implements List<E> {
    private int size;
    private Object[] objects;

    private final static int INITIAL_SIZE = 16;

    public MyArrayList(int size) {
        if (size < 0) throw new RuntimeException("Invalid size of ArrayList " + size);
        else if (size == 0)
            objects = new Object[INITIAL_SIZE];
        else this.objects = new Object[size];
    }

    public MyArrayList() {
        this.objects = new Object[INITIAL_SIZE];
    }


    public int size() {
        return this.size;
    }

    public void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + size);

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
        if (size == 15) {
            Object[] objectExample = new Object[size + INITIAL_SIZE];
            for (int i = 0; i < objectExample.length; i++) {
                if(i == size) break;
                objectExample[i] = objects[i];
            }
            objects = objectExample;
        }
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
                    this.objects[j] = this.objects[j + 1];
                }
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {

//        for (int i = 0; i <; i++) {
//
//        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.contains(null)) throw new NullPointerException(" The collection contains null values");
        int j = 0;
        Object[] objectExample = c.toArray();
        int countSize = size;
        for (int i = countSize; i < objectExample.length + countSize; i++) {
            objects[i] = objectExample[j];
            j++;
            size++;
        }
        return countSize > size;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.contains(null)) throw new NullPointerException(" The collection contains null values");
        Object[] objectExample = c.toArray();
        int countSize = size;
        for (int i = 0; i < objectExample.length; i++) {
            for (int j = 0; j < size; j++) {
                if (objects[j] == objectExample[i]) {
                    for (int k = j; k < size; k++) {
                        this.objects[k] = this.objects[k + 1];
                    }
                    size--;
                }
            }
        }
        return size < countSize;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            objects[i] = null;
        }
        size = 0;
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return (E) objects[index];
    }

    @Override
    public E set(int index, E element) {
        rangeCheck(index);
        return (E) (objects[index] = element);
    }

    @Override
    public void add(int index, E element) {
        if (index > size)
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + size);
        Object[] objectExample = objects;
        objects = new Object[size + 1];
        int j = 0;
        for (int i = 0; i < objects.length; i++) {
            if (i != index) {
                objects[i] = objectExample[j];
                j++;
            } else objects[i] = element;
        }
    }


    @Override
    public E remove(int index) {
        rangeCheck(index);
        E e = (E) objects[index];
        for (int i = 0; i < size; i++) {
            if (i == index) {
                for (int j = i; j < size; j++) {
                    this.objects[j] = this.objects[j + 1];
                }
            }
        }
        size--;
        return e;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (objects[i].equals(o)) return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int lastOccurrence = 0;
        for (int i = 0; i < size; i++) {
            if (objects[i].equals(o) && i > lastOccurrence) lastOccurrence = i;
        }
        return lastOccurrence;
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
        Object[] objectExample = new Object[toIndex - fromIndex];
        for (int j = 0; j < objectExample.length; j++) {
            objectExample[j] = objects[fromIndex];
            fromIndex++;
        }

        return (List<E>) Arrays.asList(objectExample);
    }

    @Override
    public String toString() {
        return "MyArrayList{" +
                "size=" + size +
                ", objects=" + Arrays.toString(objects) +
                '}';
    }
}

