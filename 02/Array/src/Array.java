public class Array<T> {
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.setData(data);
            setNext(null);
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }

    Node<T> head;
    int capacity;

    Array(int capacity) {
        this.capacity = capacity;
        head = new Node<>(null);
        Node<T> node1 = head;
        Node<T> node2;
        for (int i = 0; i < capacity - 1; i++) {
            node2 = new Node<>(null);
            node1.setNext(node2);
            node1 = node2;
        }
        node1.setNext(null);
    }

    public T getDataAtIndex(int index) {
        if (index < capacity) {
            int currentIndex = 0;
            Node<T> currentNode = head;
            while (currentIndex != index && currentNode != null) {
                currentIndex++;
                currentNode = currentNode.getNext();
            }
            if (currentNode != null) {
                return currentNode.getData();
            }
        }
        return null;
    }

    public void setDataAtIndex(int index, T data) {
        if (index < capacity) {
            int currentIndex = 0;
            Node<T> currentNode = head;
            while (currentIndex != index && currentNode != null) {
                currentIndex++;
                currentNode = currentNode.getNext();
            }
            if (currentNode != null) {
                currentNode.setData(data);
            }
        }
    }

    public void empty() {
        Node<T> currentNode = head;
        while (currentNode != null) {
            currentNode.setData(null);
            currentNode = currentNode.getNext();
        }
    }

    public int getSize() {
        return capacity;
    }
}
