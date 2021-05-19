package com.thorin.stack;

import java.util.NoSuchElementException;

/**
 * 链表实现栈
 * 注：该类只采用单链表实现栈结构，链表表头为栈顶，采用头插法插入元素
 */
public class LinkedStack<E> {

    private int size;

    // Pointer to first node
    private Node<E> first;

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E item,Node<E> next){
            this.item = item;
            this.next = next;
        }
    }

    // Constructs an empty stack
    public LinkedStack(){

    }

    // Push element e as first node
    public void push(E e){
        Node<E> f = first;
        Node<E> newNode = new Node<>(e,f);
        first = newNode;
        size++;
    }

    // Returns element at first node
    public E pop(){
        Node<E> f = first;
        Node<E> next = f.next;
        f.next = null;
        E element = f.item;
        f.item = null;
        first = next;
        size--;
        return element;
    }

    // Replaces the at the specified position in this list with the specified element.
    public E set(int index,E e){
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index : " + index + ",size : " + size);
        }
        Node<E> f = first;
        for (int i = 0; i < index; i++) {
            f = f.next;
        }
        E oldValue = f.item;
        f.item = e;
        return oldValue;
    }

    public E get(){
        if (first == null) {
            throw new NoSuchElementException();
        }
        return first.item;
    }

}
