package com.thorin.list;

/**
 * 循环链表
 */
public class CycleList<E> {

    private int size;
    private Node<E> last;

    // Define the data structure of the node in this cycle list
    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E item,Node<E> next){
            this.item = item;
            this.next = next;
        }
    }

    // Add specified element to the end of this cycle list
    public boolean add(E e){
        Node<E> node = new Node<>(e,last);
        Node<E> l = last;
        last = node;
        if (l == null) {
            last.next = node;
        } else {
            Node<E> f = l.next;
            while (f.next != l) {
                f = f.next;
            }
            f.next = last;
        }
        size++;
        return true;
    }

    // Remove element from this list with specified index
    public boolean remove(int index){
        checkElementIndex(index);
        Node<E> f = last.next;
        for (int i = 0; i < index - 1; i++) {
            f = f.next;
        }
        Node<E> temp = f.next.next;
        f.next.next = null;
        f.next = temp;
        size--;
        return true;
    }
    // Remove element from this list with specified element
    public boolean remove(E element){
        if (element == null) {
            for (Node<E> f = last.next; f.next != last; f = f.next) {
                if (f.item == null) {
                    unlink(f);
                    return true;
                }
            }
        } else {
            for (Node<E> f = last.next; f.next != last; f = f.next) {
                if (element.equals(f.item)) {
                    unlink(f);
                    return true;
                }
            }
        }
        return false;
    }

    // Replaces the element at the specified position in this list with specified element
    public boolean set(int index,E element){
        checkElementIndex(index);
        Node<E> f = last.next;
        for (int i = 0; i < index; i++) {
            f = f.next;
        }
        f.item = element;
        return true;
    }

    // Returns the element at the specified position in this list
    public E get(int index){
        checkElementIndex(index);
        Node<E> f = last.next;
        for (int i = 0; i < index; i++) {
            f = f.next;
        }
        return f.item;
    }

    // Remove node
    private void unlink(Node<E> e){
        Node<E> l = last;
        while (l.next != last) {
            if (l.next == e) {
                Node<E> temp = l.next.next;
                l.next.next = null;
                l.next = temp;
                break;
            }
            l = l.next;
        }
        e.item = null;
        size--;
    }

    public int size(){
        return size;
    }

    private void checkElementIndex(int index){
        if (!(index >= 0 && index < size)) {
            throw new IndexOutOfBoundsException("Index : " + index + ",size : " + size);
        }
    }

    // Merge two circular linked list
    public CycleList<E> merge(CycleList<E> first,CycleList<E> second){
        Node<E> firstF = first.last.next;
        Node<E> firstL = first.last;
        Node<E> secondL = second.last;
        Node<E> secondF = second.last.next;

        firstL.next = secondF;
        secondL.next = firstF;
        CycleList<E> cycleList = new CycleList<>();
        cycleList.last = first.last;
        cycleList.size = first.size + second.size;
        return cycleList;
    }

}
