package com.thorin.stack;

import java.util.NoSuchElementException;

public class ArrayStack<E> {
    private static final int INIT_CAPACITY = 10;
    private int size = 0;
    private Object[] elementData;

    public ArrayStack(int capacity){
        if (capacity < 0) {
            throw new IllegalArgumentException("illegal argument : " + capacity);
        }
        this.elementData = new Object[capacity];
    }

    public ArrayStack(){
        this(INIT_CAPACITY);
    }

    public int size() {
        return size;
    }

    public void push(E e){
        ensureCapacity(size + 1);
        elementData[size++] = e;
    }

    private void ensureCapacity(int minCapacity){
        if (minCapacity - elementData.length > 0) {
            int oldCapacity = elementData.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            if (newCapacity - minCapacity < 0) {
                newCapacity = minCapacity;
            }
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(elementData,0,newArray,0,oldCapacity);
            elementData = newArray;
        }
    }

    public E pop(){
        if (size == 0) {
            throw new NoSuchElementException();
        }
        E element = (E) elementData[size];
        size--;
        return element;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index : " + index + ",size : " + size);
        }
        E element = (E) elementData[index];
        return element;
    }

    public E set(int index,E e){
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index : " + index + ",size : " + size);
        }
        E oldValue = (E) elementData[index];
        elementData[index] = e;
        return oldValue;
    }
}
