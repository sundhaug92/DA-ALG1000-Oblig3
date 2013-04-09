/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package da.alg1000.oblig3;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.locks.*;

/**
 *
 * @author Martin
 */
class LinkedList<T> extends java.util.LinkedList<T> {

    private Node<T> head;
    private int elements;
    ReentrantLock lock = new ReentrantLock(true); //use a fair, reentrant lock

    public LinkedList() {
        head = null;
        elements = 0;
    }

    public int getElements() {
        lock.lock();
        try {
            return elements;
        } finally {
            lock.unlock();
        }
    }

    public Node<T> getHead() {
        lock.lock();
        try {
            return head;
        } finally {
            lock.unlock();
        }
    }

    public int Count(T element) {
        int count = 0;
        lock.lock();
        try {
            Node<T> n = head;
            for (int i = 0; i < elements; i++) {
                if (n.getElement().equals(element)) {
                    count++;
                }
                n = n.getNext();
            }
        } finally {
            lock.unlock();
        }
        return count;
    }

    @Override
    public String toString() {
        String s = new String();
        lock.lock();
        try {
            Node<T> n = head;
            for (int i = 0; i < elements; i++) {
                s += "{" + n + "}";
                if ((i % 5) == 4) {
                    s += "\n";
                }
                n = n.getNext();
            }
        } finally {
            lock.unlock();
        }
        return s;
    }

    public void reset() {
        lock.lock();
        try {
            elements = 0;
            head = null;
        } finally {
            lock.unlock();
        }
    }

    private Node<T> nodeWithIndex(int index) {
        lock.lock();
        try {
            if (index < 0) {
                return null;
            }
            Node<T> n = head;
            for (int i = 0; i < index; i++) {
                n = n.getNext();
            }
            return n;
        } finally {
            lock.unlock();
        }
    }

    private Node<T> nodeWithElement(T element) {
        lock.lock();
        try {
            if (head != null) {
                if (head.getElement().equals(element)) {
                    return head;
                }
                Node<T> n = head;
                for (int i = 0; i < elements; i++) {
                    if (n.getElement().equals(element)) {
                        return n;
                    }
                    n = n.getNext();
                }
            }
            return null;

        } finally {
            lock.unlock();
        }
    }

    void addItem(T element, int index) {
        lock.lock();
        try {
            Node<T> n = nodeWithIndex(index - 1);
            if (n != null) {
                n.setNext(new Node(element, n.getNext()));
            } else if ((index <= 0) && (head != null)) {
                head = new Node(element, head);
            } else if ((index <= 0) && (head == null)) {
                head = new Node(element, null);
            }
            elements++;
        } finally {
            lock.unlock();
        }
    }

    void addItem(T element) {
        try {
            addItem(element, elements);
        } finally {
            lock.unlock();
        }
    }

    void removeItem(int index) {
        lock.lock();
        try {
            if (head != null) {
                if (index == 0) {
                    head = head.getNext();
                } else {
                    Node<T> n = nodeWithIndex(index - 1);
                    n.setNext(n.getNext().getNext());
                }
                elements--;
            }
        } finally {
            lock.unlock();
        }
    }

    int indexOfFirstMatch(T element) {
        lock.lock();
        try {
            Node<T> target = nodeWithElement(element), n = head;
            for (int i = 0; i < elements; i++) {
                if (n.equals(target)) {
                    return i;
                }
                n = n.getNext();
            }
            return -1;
        } finally {
            lock.unlock();
        }
    }

    void removeItemFirstMatch(T matchElement) {
        lock.lock();
        try {
            removeItem(indexOfFirstMatch(matchElement));
        } finally {
            lock.unlock();
        }
    }

    void addItemAfterFirstMatch(T element, T matchElement) {
        lock.lock();
        try {
            addItem(element, indexOfFirstMatch(matchElement) + 1);
        } finally {
            lock.unlock();
        }
    }

    void addItemBeforeFirstMatch(T element, T matchElement) {
        lock.lock();
        try {
            addItem(element, indexOfFirstMatch(matchElement) - 1);

        } finally {
            lock.unlock();
        }
    }

    void removeLastItem() {
        lock.lock();
        try {
            removeItem(elements - 1);

        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        return elements == 0;
    }

    @Override
    public boolean contains(Object o) {
        return Count((T) o) > 0;
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object[] toArray(Object[] a) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean add(Object e) {
        addItem((T) e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        boolean b = this.Count((T) o) > 0;
        removeItemFirstMatch((T) o);
        return b;
    }

    @Override
    public boolean containsAll(Collection c) {
        boolean b = true;
        for (Object o : c) {
            contains(o);
        }
        return b;
    }

    @Override
    public boolean addAll(Collection c) {
        for (Object o : c) {
            add(o);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean removeAll(Collection c) {
        for (Object o : c) {
            remove(o);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clear() {
        reset();
    }

    @Override
    public T get(int index) {
        return nodeWithIndex(index).getElement();
    }

    @Override
    public T set(int index, T element) {
        nodeWithIndex(index).setElement(element);
        return nodeWithIndex(index).getElement();
    }

    @Override
    public void add(int index, Object element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public T remove(int index) {
        T o = this.get(index);
        this.removeItem(index);
        return o;
    }

    @Override
    public int indexOf(Object o) {
        return this.indexOfFirstMatch((T) o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return -1;
    }

    @Override
    public ListIterator listIterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ListIterator listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
} //class LinkedList

