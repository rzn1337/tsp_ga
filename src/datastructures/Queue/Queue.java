package datastructures.Queue;


public class Queue<T> {
    Node<T> front;
    Node<T> rear;

    public void enqueue(T obj) {
        Node<T> new_node = new Node<>(obj);
        if (isEmpty()) {
            front = rear = new_node;
        } else {
            rear.next = new_node;
            rear = new_node;
        }
    }

    public T dequeue() {
        T temp = front.data;
        front = front.next;
        return temp;
    }

    public boolean isEmpty() {
        if (front == null && rear == null) {
            return true;
        } else {
            return false;
        }
    }

    public T peek() {
        return front.data;
    }

    public String toString() {
        String str = "{";
        Node<T> temp = front;
        while (temp != null) {
            if (temp.next == null) {
                str += temp.data + "}";
            } else {
                str += temp.data + ", ";
            }
            temp = temp.next;
        }
        return str;
    }



}
