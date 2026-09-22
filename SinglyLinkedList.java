import java.util.*;

public class SinglyLinkedList<E extends Comparable<E>> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> n) {
            next = n;
        }
    }

    public SinglyLinkedList() {

    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
        return head.getElement();
    }

    public E last() {
        if (isEmpty()) {
            return null;
        }
        return tail.getElement();
    }

    public void addFirst(E e) {
        head = new Node<>(e, head);

        if (isEmpty()) {
            tail = head;
        }
        size++;
    }

    public void addLast(E e) {
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()) {
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }

        E answer = head.getElement();
        head = head.getNext();
        size--;

        if (isEmpty()) {
            tail = null;
        }
        return answer;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            sb.append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

    // write your codes here
    public void swap() {
        int size = size();
        
        // store all the nodes in an array
        Node nodeList[] = new Node[size];

        int sortedList[] = new int[size];
        int indx = 0;

        Node curr = this.head;
        while (curr != null) {
            nodeList[indx] = curr;
            sortedList[indx++] = (Integer) curr.getElement();
            curr = curr.getNext();
        }

        // keep track of max and min
        bubbleSort(sortedList);
        int left = 0;
        int right = size() - 1;
        
        while (left < right) {
            int min = sortedList[left];
            int max = sortedList[right];

            int indx1 = 0;
            int indx2 = 0;
            while ((Integer) nodeList[indx1].getElement() != min) {
                indx1++;
            }

            while ((Integer) nodeList[indx2].getElement() != max) {
                indx2++;
            }

            Node temp = nodeList[indx1];
            nodeList[indx1] = nodeList[indx2];
            nodeList[indx2] = temp;

            left++;
            right--;
        }

        indx = 0;
        head = nodeList[indx];
        Node ptr = head;
        for (int i = 1; i < size(); i++) {
            ptr.setNext(nodeList[indx + i]);
            ptr = ptr.getNext();
        }

        tail = ptr;
        tail.setNext(null);
        ptr = head;
        // while (ptr != null) {

        // }
    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap arr[j] and arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
