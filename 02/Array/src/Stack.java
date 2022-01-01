public class Stack<T> {
    Array<T> array;
    int top, capacity;

    Stack(int capacity) {
        top = -1;
        array = new Array<>(capacity);
        this.capacity = capacity;
    }

    public void push(T data) {
        if (top + 1 == capacity) {
            System.out.println("Stack Overflow");
        } else {
            top++;
            array.setDataAtIndex(top, data);
        }
    }


    public T pop() {
        if (top == -1) {
            System.out.println("Stack is empty");
            return null;
        } else {
            T data = array.getDataAtIndex(top);
            top--;
            return data;
        }
    }

    public int numberOfItemsInside() {
        return top + 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int search(T key) {
        for (int i = 0; i < top; i++) {
            if (array.getDataAtIndex(i) == key) {
                return i;
            }
        }
        return -1;
    }
}
