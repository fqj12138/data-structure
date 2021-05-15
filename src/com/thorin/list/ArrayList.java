package com.thorin.list;
import java.util.Arrays;

/**
 * 数组实现的单链表
 */
public class ArrayList<E> {

    private static final Object[] EMPTY_ELEMENTDATA = {};
    private int length;
    private int size;

    private Object[] data;

    // Construct an empty list with the default initial capacity
    public ArrayList(){
        this.length = 0;
        this.size = 0;
        this.data = new Object[10];
    }

    // Construct an empty list with the specified initial capacity
    public ArrayList(int initialCapacity){
        if (initialCapacity > 0) {
            this.data = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.data = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal capacity : " + initialCapacity);
        }
    }

    // Return the element at the specified position in this list
    public E get(int index){
        // check argument
        rangeCheck(index);
        // return data
        return elementData(index);
    }

    // Replace the element at the specified position in this list with specified element
    public E set(int index,E element){
        rangeCheck(index);
        E oldValue = elementData(index);
        data[index] = element;
        return oldValue;
    }

    // Appends the specified element to the end of this list
    public boolean add(E element){
        ensureCapacity(size + 1);
        data[size++] = element;
        return true;
    }

    public E remove(int index){
        rangeCheck(index);
        E oldValue = elementData(index);
        int position = size - index - 1;
        if (position > 0) {
            // Arraycopy is shallow copy
            System.arraycopy(data, index + 1, data, index, position);
        }
        data[--size] = null;
        return oldValue;
    }

    // Increases the capacity to ensure that it can hold at least the number of elements specified by the minimum capacity argument
    private void ensureCapacity(int minCapacity){
        if (minCapacity > size) {
            int oldCapacity = data.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            if (newCapacity - minCapacity < 0) {
                newCapacity = minCapacity;
            }
            data = Arrays.copyOf(data,newCapacity);
        }
    }

    private E elementData(int index){
        return (E) data[index];
    }

    private void rangeCheck(int index){
        if ((index < size && index >= 0)) {
            throw new IndexOutOfBoundsException("Index : " + index + ",size : " + size);
        }
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

}
