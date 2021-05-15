package com.thorin.list;

import java.util.NoSuchElementException;

/**
 * 单链表实现单列表
 */
public class LinkedList<E> {

    private int size = 0;
    private Node<E> first;

    // static inner class
    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E element,Node<E> next){
            this.item = element;
            this.next = next;
        }
    }

    // Links e as first element
    private void linkedFirst(E e){
        Node<E> f = first;
        Node<E> newNode = new Node<>(e,f);
        first = newNode;
        newNode.next = f;
        size++;
    }

    // Links e as last element
    private void linkedLast(E e){
        Node<E> f = first;
        Node<E> newNode = new Node<>(e, null);
        if (f == null) {
            first = newNode;
        } else {
            Node<E> last = getLast();
            last.next = newNode;
        }

        size++;
    }

    public boolean remove(E element){
        Node<E> f = first;
        if (f == null) {
            throw new NoSuchElementException();
        }
        if (element == null) {
            for (; f != null; f = f.next) {
                if (f.item == null) {
                    unlink(f);
                    return true;
                }
            }
        } else {
            for (; f != null; f = f.next) {
                if (element.equals(f.item)) {
                    unlink(f);
                    return true;
                }
            }
        }

        return false;
    }

    // Returns the number of elements in this list
    public int size(){
        return size;
    }

    public E set(int index,E element){
        if (!(index < size && index >= 0)) {
            throw new IndexOutOfBoundsException("Index : " + index + ",size : " + size);
        }
        Node<E> f = first;
        for (int i = 0; i < index; i++) {
            f = f.next;
        }
        E oldValue = f.item;
        f.item = element;
        return oldValue;
    }

    public Node<E> getLast(){
        Node<E> f = first;
        if (f == null) {
            throw new IndexOutOfBoundsException("Index : 0,size : 0");
        }

        while (f.next != null) {
            f = f.next;
        }

        return f;
    }

    public E getFirst() {
        Node<E> f = first;
        if (f == null) {
            throw new NoSuchElementException();
        }
        return f.item;
    }

    private E unlink(Node<E> e){
        E element = e.item;

        Node<E> f = first;
        while (f.next != null) {
            if (f.next == e) {
                Node<E> node = f.next.next;
                f.next.next = null;
                f.next = node;
                break;
            }
            f = f.next;
        }

        e.item = null;
        size--;
        return element;
    }

}
