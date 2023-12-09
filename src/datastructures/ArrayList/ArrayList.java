package datastructures.ArrayList;

public class ArrayList<T extends Comparable<T>> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        elements = (T[]) new Comparable[DEFAULT_CAPACITY];
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(T element) {
        ensureCapacity();
        elements[size++] = element;
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            resizeArray();
        }
    }

    private void resizeArray() {
        int newCapacity = elements.length * 2;
        T[] newArray = (T[]) new Comparable[newCapacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = elements[i];
        }
        elements = newArray;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for ArrayList");
        }
        return elements[index];
    }

    // Other methods like remove, contains, indexOf, clear, etc. can be added as needed.
}
