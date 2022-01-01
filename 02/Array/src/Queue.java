public class Queue<T> {
    Array<T> array;
    int front, rear, capacity, items;

    Queue(int capacity) {
        front = rear = items = 0;
        this.capacity = capacity;
        array = new Array<>(capacity);
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public T front() {
        if (isEmpty()) {
            return null;
        }
        return array.getDataAtIndex((front+1)%capacity);
    }

    public T rear() {
        if (isEmpty()) {
            return null;
        }
        return array.getDataAtIndex(rear);
    }

    public void enqueue(T data) {
        if ((rear + 1) % capacity == front) {
            System.out.println("Queue is full");
            return;
        }
        rear = (rear + 1) % capacity;
        items++;
        array.setDataAtIndex(rear, data);
    }


    public T dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        }
        T data = array.getDataAtIndex((front+1)%capacity);
        front = (front + 1) % capacity;
        items--;
        return data;
    }

    public int numberOfItemsInside() {
        return items;
    }

    public int search(T key) {
        int index = (front + 1) % capacity;
        while (index != (rear + 1) % capacity) {
            if (array.getDataAtIndex(index) == key) {
                return index;
            }
            index = (index + 1) % capacity;
        }
        return -1;
    }
}
