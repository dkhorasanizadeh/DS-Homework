import java.util.Scanner;

class Queue<T> {
    static class Node<T> {
        Node<T> next;
        T data;

        Node(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }

    Node<T> head;
    Node<T> tail;

    Queue() {
        head = tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void enqueue(T data) {
        Node<T> node = new Node<>(data);
        if (tail == null) {
            head = node;
            tail = this.head;
        } else {
            tail.setNext(node);
            tail = node;
        }
    }

    public T dequeue() {
        if (head == tail) {
            T data = tail.getData();
            head = tail = null;
            return data;
        }
        T data = head.getData();
        head = head.getNext();
        return data;
    }
}

class Phonebook {
    boolean[] numberList;
    Queue<Integer> queue;
    int capacity;
    int size;

    Phonebook() {
        capacity = 2;
        size = 0;
        numberList = new boolean[capacity];
        queue = new Queue<>();
    }

    public int getNumber() {
        if (!queue.isEmpty()) {
            int number = queue.dequeue();
            numberList[number] = true;
            return number;
        } else {
            if (size >= capacity) {
                doubleSize();
            }
            numberList[size] = true;
            int number = size;
            size++;
            return number;
        }
    }

    private void doubleSize() {
        boolean[] newList = new boolean[2 * capacity];
        System.arraycopy(numberList, 0, newList, 0, capacity);
        numberList = newList;
        capacity *= 2;
    }

    public boolean isNumberOccupied(int number) {
        if (number >= size) {
            return false;
        }
        return numberList[number];
    }

    public boolean deleteNumber(int number) {
        if (isNumberOccupied(number)) {
            numberList[number] = false;
            queue.enqueue(number);
            return true;
        }
        return false;
    }
}

public class PhonebookManager {
    public static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            System.out.println("Unable to clear the console");
        }
    }

    public static void main(String[] args) {
        Phonebook phonebook = new Phonebook();
        Scanner input = new Scanner(System.in);
        int option;
        do {
            System.out.println("""
                    Please select one of the following items:
                    1. Get a new number
                    2. Check a number
                    3. Delete a number
                    4. Exit
                    Your Choice:\s""");
            option = input.nextInt();
            clearConsole();
            switch (option) {
                case 1 -> System.out.println("The number " + phonebook.getNumber() + " is now yours");
                case 2 -> {
                    System.out.println("Please enter the number: ");
                    int numberToCheck = input.nextInt();
                    if (phonebook.isNumberOccupied(numberToCheck)) {
                        System.out.println("The number is occupied");
                    } else {
                        System.out.println("The number is not occupied");
                    }
                }
                case 3 -> {
                    System.out.println("Please enter the number: ");
                    int numberToDelete = input.nextInt();
                    if (phonebook.deleteNumber(numberToDelete)) {
                        System.out.println("Number deleted successfully");
                    } else {
                        System.out.println("The number was not occupied");
                    }
                }
                case 4 -> System.out.println("Goodbye");
                default -> System.out.println("Invalid option");
            }
        } while (option != 4);
        input.close();
    }
}