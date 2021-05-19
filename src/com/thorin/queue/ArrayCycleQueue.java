package com.thorin.queue;

import java.util.NoSuchElementException;

/**
 * 数组实现的循环队列,数组中空余一个元素用来判断循环链表是否已满
 * 循环链表为空条件:front=rear
 * 循环链表满条件:(rear+1)%size=front
 * 循环链表的元素个数计算：
 *  1、若rear > front,则number = rear - front
 *  2、若rear < front,则number = (size - front) + (0 + rear) = rear - front + size
 *  综上，元素个数为 (rear - front + size) % size
 */
public class ArrayCycleQueue<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private int size;
    private int front;
    private int rear;
    private Object[] elementData;

    // Constructs a queue with specified capacity
    public ArrayCycleQueue(int initCapacity){
        this.size = initCapacity;
        this.front = 0;
        this.rear = 0;
        this.elementData = new Object[initCapacity];
    }

    // Constructs a queue with default capacity
    public ArrayCycleQueue(){
        this(DEFAULT_CAPACITY);
    }

    // Appends element e as last node
    public void push(E e){
        if ((rear + 1) % size == front) {
            throw new IndexOutOfBoundsException();
        }
        elementData[rear] = e;
        rear = (rear + 1) % size;
    }

    // Returns first element from array
    public E pop(){
        if (rear == front) {
            throw new NoSuchElementException();
        }
        E oldValue = (E) elementData[front];
        elementData[front] = null;
        front = (front + 1) % size;
        return oldValue;
    }

    // Replaces the element at the specified position with specified element
    public E set(int index,E e){
        if (rear == front) {
            throw new NoSuchElementException();
        }

        if (index >= size || index < 0) {
            throw new IllegalArgumentException("Index : " + index + ",size : " + size);
        }

        int position = front;
        for (int i = 0; i < index; i++) {
            position = position + 1;
        }
        E oldValue = (E) elementData[position % size];
        elementData[position % size] = e;
        return oldValue;
    }

    // Returns the number of element at this queue
    public int length(){
        return (rear - front + size) % size;
    }
}
