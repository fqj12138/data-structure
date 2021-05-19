package com.thorin.list;

import java.util.NoSuchElementException;

/**
 * 双向循环链表(该链表实际上只是双向链表，头尾并没有相连形成循环链表，在实际使用中头尾相连并没有太大的意义，只会增加代码的复杂度)
 */
public class DoubleLinkedList<E> {

    private Node<E> first;
    private Node<E> last;
    private int size;

    // Links e as first element
    private void linkFirst(E e){
        Node<E> f = first;
        Node<E> node = new Node<>(e,null,f);
        first = node;
        if (f == null) {
            last = node;
        } else {
            f.pre = node;
        }
        size++;
    }

    // Links e as last element
    private void linkLast(E e){
        Node<E> l = last;
        Node<E> node = new Node<>(e,l,null);
        last = node;
        if (l == null) {
            first = node;
        } else {
            l.next = node;
        }
        size++;
    }

    // Insert element e before non-null Node node
    private void linkBefore(E e,Node<E> node){
        Node<E> pre = node.pre;
        Node<E> newNode = new Node<>(e,pre,node);
        node.pre = newNode;
        if (pre == null) {
            first = newNode;
        } else {
            pre.next = newNode;
        }
        size++;
    }

    private E unlinkFirst(Node<E> f){
        E element = f.item;
        Node<E> next = f.next;
        f.item = null;
        f.next = null;
        first = next;
        if (next == null) {
            last = next;
        } else {
            next.pre = null;
        }
        size--;
        return element;
    }

    private E unlinkLast(Node<E> l){
        E element = l.item;
        Node<E> pre = l.pre;
        l.item = null;
        l.pre = null;
        last = pre;
        if (pre == null) {
            first = null;
        } else {
            pre.next = null;
        }
        size--;
        return element;
    }

    private E unlink(Node<E> e){
        E element = e.item;
        Node<E> pre = e.pre;
        Node<E> next = e.next;

        if (pre == null) {
            first = next;
        } else {
            pre.next = next;
            e.pre = null;
        }

        if (next == null) {
            last = pre;
        } else {
            next.pre = pre;
            e.next = null;
        }
        e.item = null;
        size--;
        return element;
    }

    public E getFirst(){
        E element = first.item;
        if (first == null) {
            throw new NoSuchElementException();
        }
        return element;
    }

    public E getLast(){
        E element = last.item;
        if (last == null) {
            throw new NoSuchElementException();
        }
        return element;
    }

    public E removeFirst() {
        final Node<E> f = first;
        if (f == null)
            throw new NoSuchElementException();
        return unlinkFirst(f);
    }

    public E removeLast() {
        final Node<E> l = last;
        if (l == null)
            throw new NoSuchElementException();
        return unlinkLast(l);
    }

    public void addFirst(E e) {
        linkFirst(e);
    }
    public void addLast(E e) {
        linkLast(e);
    }
    public int size() {
        return size;
    }
    public boolean add(E e) {
        linkLast(e);
        return true;
    }
    public boolean remove(Object o){
        Node<E> f = first;
        if (o == null) {
            for (; f.next != last; f = f.next) {
                if (f.item == null) {
                    unlink(f);
                    return true;
                }
            }
        } else {
            for (; f.next != last; f = f.next) {
                if (o.equals(f.item)) {
                    unlink(f);
                    return true;
                }
            }
        }
        size--;
        return false;
    }

    private static class Node<E> {
        private Node<E> pre;
        private Node<E> next;
        private E item;

        public Node(E item,Node<E> pre,Node<E> next){
            this.item = item;
            this.next = next;
            this.pre = pre;
        }
    }

}
