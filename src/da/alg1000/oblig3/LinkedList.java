/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package da.alg1000.oblig3;
import java.util.concurrent.locks.*;

/**
 *
 * @author Martin
 */
class LinkedList<T> {

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

    public Node getHead() {
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
            Node n = head;
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
            Node n = head;
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

    private Node nodeWithIndex(int index) {
        lock.lock();
        try {
            if (index < 0) {
                return null;
            }
            Node n = head;
            for (int i = 0; i < index; i++) {
                n = n.getNext();
            }
            return n;
        } finally {
            lock.unlock();
        }
    }

    private Node nodeWithElement(T element) {
        lock.lock();
        try {
            if (head != null) {
                if (head.getElement().equals(element)) {
                    return head;
                }
                Node n = head;
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
            Node n = nodeWithIndex(index - 1);
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
                    Node n = nodeWithIndex(index - 1);
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
            Node target = nodeWithElement(element), n = head;
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
} //class LinkedList

