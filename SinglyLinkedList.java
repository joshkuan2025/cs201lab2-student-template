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
        
        // store all the values of the nodes in a map<element, position>
        HashMap<E, Integer> map = new HashMap<>();

        // stores all the nodes where the swap is happening
        ArrayList<Node<E>> nodeList = new ArrayList();
        ArrayList<E> sortedList = new ArrayList<>();

        int indx = 0;

        Node curr = this.head;
        while (curr != null) {
            E element = (E) curr.getElement();
            map.put(element, indx++);
            sortedList.add(element);
            nodeList.add(curr);
            
            curr = curr.getNext();
        }

        // keep track of max and min
        Collections.sort(sortedList);
        int left = 0;
        int right = size() - 1;
        
        while (left < right) {
            E min = sortedList.get(left);
            E max = sortedList.get(right);
           
            int pos1 = map.get(min);
            int pos2 = map.get(max);

            Node<E> tempNode = nodeList.get(pos1);
            nodeList.set(pos1, nodeList.get(pos2));
            nodeList.set(pos2, tempNode);

            left++;
            right--;
        }

        indx = 0;
        head = nodeList.get(0);
        Node<E> ptr = head;
        for (int i = 1; i < size(); i++) {
            ptr.setNext(nodeList.get(i));
            ptr = ptr.getNext();
        }

        tail = ptr;
        tail.setNext(null);
        ptr = head;
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
