package com.thorin.queue;

import java.util.NoSuchElementException;

/**
 * 链表实现队列
 * 注:实际上是一个单链表，只是元素的增加只能发生在队尾，元素的删除只能发生在队头，或者反过来也可以
 */
public class LinkedQueue<E> {

    private int size;

    // Pointer to first node
    private Node<E> front;

    // Pointer to last node
    private Node<E> rear;

    // Define static inner class
    private static class Node<E> {
        private E item;
        private Node<E> next;

        public Node(E item,Node<E> next){
            this.item = item;
            this.next = next;
        }
    }

    // Appends element e as last node
    public void push(E e){
        Node<E> r = rear;
        Node<E> newNode = new Node<>(e,null);
        rear = newNode;
        if (r == null) {
            front = newNode;
        } else {
            r.next = newNode;
        }
        size++;
    }

    // Returns first element from this queue
    public E pop(){
        if (front == null) {
            throw new NoSuchElementException();
        }
        Node<E> f = front;
        E element = f.item;
        Node<E> next = f.next;
        front = next;
        f.next = null;
        f.item = null; // help GC
        return element;
    }

    // Replaces the element at the specified position in this queue with specified element
    public E set(int index, E e) {
        if (index >= size || index < 0) {
            throw new IllegalArgumentException();
        }
        Node<E> f = front;
        for (int i = 0; i < index; i++) {
            f = f.next;
        }
        E oldValue = f.item;
        f.item = e;
        return oldValue;
    }
}
